package creation;

import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.PropertyLoader;
import creation.bean.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class DermPRORestClient {

    public static StoreBeanCollection getStoreList() {
        return getResponse("GET", "/auto/stores", StoreBeanCollection.class);
    }

    public static StoreDetailedBean getStoreById(Integer storeId) {
        String url = String.format("/auto/store/%d", storeId);
        return getResponse("GET", url, StoreDetailedBean.class);
    }

    public static StoreDetailedBean getStoreByName(String storeName) {
        Map<String, String> map = new HashMap<>();
        map.put("name", storeName);
        return getResponse("GET", "/auto/store", map, StoreDetailedBean.class);
    }

    public static PrivateLabelBean getPrivateLabel(Integer storeId) {
        String url = String.format("/v1/stores/private-label/%d", storeId);
        return getResponse("GET", url, PrivateLabelBean.class);
    }

    public static List<PrivateLabelBean> getPrivateLabelsList(Integer storeId) {
        String url = String.format("/v1/private-labels/%d", storeId);
        return Arrays.asList(getResponse("GET", url, PrivateLabelBean.class));
    }

    public static BrandedProductBean getBrandedProduct(Integer storeId) {
        String url = String.format("/v1/branded-products/%d", storeId);
        return getResponse("GET", url, BrandedProductBean.class);
    }

    public static List<OptionBean> getOptionList() {
        return Arrays.asList(getResponse("GET", "/v1/options", OptionBean[].class));
    }

    public static List<BrandedProductBean> getBrandedProductsList(Integer storeId) {
        return Arrays.asList(getResponse("GET", String.format("/v1/stores/%d/branded-products", storeId), BrandedProductBean[].class));
    }

    private static <T> T getResponse(String method, String endpointUrl, Map<String, String> parametersMap, Class<T> contentClass) {
        return getResponseBasic(method, endpointUrl, parametersMap, contentClass);
    }

    private static <T> T getResponse(String method, String endpointUrl, Class<T> contentClass) {
        return getResponseBasic(method, endpointUrl, null, contentClass);
    }


    private static String getToken() throws IOException {
        String result = null;

        URL obj = new URL(PropertyLoader.restClientConnectionUrl + "/v1/users/login");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        ObjectMapper mapper = new ObjectMapper();
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/main/resources/storage_config_user.properties"));

        ConfigUser configUser = new ConfigUser();
        configUser.setEmail(prop.getProperty("email"));
        configUser.setPassword(prop.getProperty("password"));
        String text = mapper.writeValueAsString(configUser);

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(text);
        wr.flush();
        wr.close();

        if (!(conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED)) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        result = response.toString();
        TokenResponse tokenResponse = mapper.readValue(result, TokenResponse.class);

        return tokenResponse.getToken();
    }


    private static <T> T getResponseBasic(String method, String endpointUrl, Map<String, String> parametersMap, Class<T> contentClass) {
        try {
            StringBuilder str
                    = new StringBuilder();

            if (parametersMap != null) {
                str.append("?");
                parametersMap.forEach((k, v) -> {
                    try {
                        String query = String.format("%s=%s", k,
                                URLEncoder.encode(v, "UTF-8"));
                        str.append(query);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
            }
            URL url = new URL(PropertyLoader.restClientConnectionUrl + endpointUrl + str.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json, text/plain, */*");
            conn.setRequestProperty("Authorization", getToken());


            if (!(conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED)) {
                throw new RuntimeException("Failed: HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            T responseObject = null;
            while ((output = br.readLine()) != null) {
                responseObject = new ObjectMapper().readValue(output, contentClass);
            }

            return responseObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

}
