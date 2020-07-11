package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    public static String browser;

    public static String defaultBaseUrl;

    public static String chromeBrowserVersion;

    public static String jsonFileDefaultSuffix;

    public static String restClientConnectionUrl;

    public static int maxPageLoadTime;

    public static int implicitlyWait;

    public static int retryTestMethodCount;

    public static int retryConfigMethodCount;

    public static String AdminPageUrl;

    public static boolean caseInsensitiveTitle;


    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("./src/main/resources/ApplicationConfig.properties"));

            Properties adminProp = new Properties();
            adminProp.load(new FileInputStream("./src/main/resources/AdminConfig.properties"));

            browser = prop.getProperty("Browser");
            defaultBaseUrl = prop.getProperty("DefaultBaseUrl");
            chromeBrowserVersion = prop.getProperty("ChromeBrowserVersion");
            jsonFileDefaultSuffix = prop.getProperty("DefaultFileSuffix");
            restClientConnectionUrl = prop.getProperty("RestClientConnectionUrl");
            maxPageLoadTime = Integer.parseInt(prop.getProperty("MaxPageLoadTime"));
            implicitlyWait = Integer.parseInt(prop.getProperty("ImplicitlyWait"));
            retryTestMethodCount = Integer.parseInt(prop.getProperty("RetryTestMethodCount"));
            retryConfigMethodCount = Integer.parseInt(prop.getProperty("RetryConfigMethodCount"));
            caseInsensitiveTitle = Boolean.parseBoolean(prop.getProperty("CaseInsensitiveTitle"));

            AdminPageUrl = adminProp.getProperty("AdminPageUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
