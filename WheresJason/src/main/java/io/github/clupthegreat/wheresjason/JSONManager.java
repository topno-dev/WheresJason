package io.github.clupthegreat.wheresjason;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONManager {
    private final Gson gson;

    public JSONManager(){
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public JsonObject loadJsonFile(String filepath) throws IOException, JsonParseException{
        String jsonContent = Files.readString(Paths.get(filepath));

        JsonElement jsonElement = JsonParser.parseString(jsonContent);

        JsonObject rootObject = new JsonObject();
        rootObject.add("JSON_&id", jsonElement);

        return rootObject;
    }

    public String loadJsonFileAsString(String filepath) throws IOException, JsonParseException {
        JsonObject rootObject = loadJsonFile(filepath);
        return gson.toJson(rootObject);
    }

    public <T> T loadJsonFileAs(String filepath, Class<T> targetClass) throws IOException, JsonParseException{
        JsonObject rootObject = loadJsonFile(filepath);
        return gson.fromJson(rootObject, targetClass);
    }

    public JsonElement loadOriginalJson(String filepath) throws IOException, JsonParseException {
        String jsonContent = Files.readString(Paths.get(filepath));
        return JsonParser.parseString(jsonContent);
    }


    public static void main(String[] args){
        JSONManager manager = new JSONManager();

        try {
            String filePath = "D:\\Code\\WheresJason\\WheresJason\\src\\main\\java\\io\\github\\clupthegreat\\wheresjason\\sampledata.json";
            JsonObject rootObject = manager.loadJsonFile(filePath);
//            System.out.println(manager.gson.toJson(rootObject));
//            System.out.println(rootObject.getAsJsonObject("JSON").getAsJsonObject("widget"));
            System.out.println(rootObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JsonParseException e){
            System.err.println("Invalid JSON Format");
        }
    }
}

