package io.github.clupthegreat.wheresjason;
import com.google.gson.Gson;

import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class JSONManager {

    public String sample_data_path = "D:\\Code\\WheresJason\\WheresJason\\src\\main\\java\\io\\github\\clupthegreat\\wheresjason\\sampledata.json";

    public void loadJson(String _pathJson) throws FileNotFoundException {

    Gson gson = new Gson();

        JsonElement root = gson.fromJson(new FileReader(_pathJson), JsonElement.class);
        String[] names = {"Rohan", "Value"};
        MyJsonObject myJsonObject = new MyJsonObject("name", names);

    }

    public void JsonReader(String pathToJsonFile) throws FileNotFoundException {
        // TODO: It has to return a MyJsonObject
        File jsonFile = new File(pathToJsonFile);
        Scanner jsonReader = new Scanner(jsonFile);
        while (jsonReader.hasNextLine()){
            String data = jsonReader.nextLine();
            System.out.println(data);
        }
        jsonReader.close();
    }

//    public static class MyJsonObject {
//
//
//    }
}

