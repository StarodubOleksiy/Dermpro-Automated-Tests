package creation;

import com.fasterxml.jackson.databind.ObjectMapper;
import creation.bean.StoreBean;
import data.bean.Store;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonManager {

    public static void writeJson(Store store) {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("store_" + store.getCode() + ".json");
        try {
            mapper.writeValue(file, store);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
