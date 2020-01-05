package exchange;

import com.google.gson.*;

public class Serializer {
    private static Gson gson = new Gson();

    public String serialize(ComData data) {
        return gson.toJson(data);
    }

    public ComData deserialize(String jsonString) {
        return gson.fromJson(jsonString, ComData.class);
    }
    
}
