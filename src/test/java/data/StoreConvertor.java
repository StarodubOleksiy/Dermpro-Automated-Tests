package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.PropertyLoader;
import data.bean.Account;
import data.bean.Store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class StoreConvertor {

    private static String user1Email;
    private static String user1Password;

    public static Store readStoreDataFromFile(String fileNameSuffix) {
        if(fileNameSuffix==null){
            fileNameSuffix= PropertyLoader.jsonFileDefaultSuffix;
        }

        Store store = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            store = objectMapper.readValue(new File("store_" + fileNameSuffix + ".json"), Store.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return store;
    }

    private static void readLoggedUserDataFromFile(String fileNameSuffix) {
        if(fileNameSuffix==null){
            fileNameSuffix= PropertyLoader.jsonFileDefaultSuffix;
        }

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("./src/main/resources/users_"+fileNameSuffix+".properties"));
            user1Email = prop.getProperty("user1Email");
            user1Password = prop.getProperty("user1Password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
