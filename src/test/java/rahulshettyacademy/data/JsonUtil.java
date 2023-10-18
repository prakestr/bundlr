package rahulshettyacademy.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<HashMap<String, String>> readJsonFileToMap(String filePath) throws IOException {
        File file = new File(filePath);
        return objectMapper.readValue(file, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public static HashMap<String, String> jsonStringToMap(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<HashMap<String, String>>() {});
    }
}
