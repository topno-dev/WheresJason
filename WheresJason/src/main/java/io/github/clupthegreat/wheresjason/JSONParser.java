package io.github.clupthegreat.wheresjason;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class JSONParser {

    public static String sample_data_path = "D:\\Code\\WheresJason\\WheresJason\\src\\main\\java\\io\\github\\clupthegreat\\wheresjason\\sampledata.json";
    public static String sample_data_simple_path = "D:\\Code\\WheresJason\\WheresJason\\src\\main\\java\\io\\github\\clupthegreat\\wheresjason\\sampledata_simple.json";

//    public void loadJson(String _pathJson) throws FileNotFoundException {
//
//    Gson gson = new Gson();
//
//        JsonElement root = gson.fromJson(new FileReader(_pathJson), JsonElement.class);
//        String[] names = {"Rohan", "Value"};
//        MyJsonObject myJsonObject = new MyJsonObject("name", names);
//
//    }

    public static void JsonReaderManager(String pathToJsonFile){
        // TODO: It has to return a MyJsonObject
        try {
            String jsonFileString = readFileFromPath(pathToJsonFile);
            System.out.println(jsonFileString);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static String readFileFromPath(String pathToJsonFile) throws FileNotFoundException {
        File jsonFile = new File(pathToJsonFile);
        Scanner jsonReader = new Scanner(jsonFile);
        String jsonFileToString = "";
        while (jsonReader.hasNextLine()){
            String data = jsonReader.nextLine();
            jsonFileToString = jsonFileToString.concat(data);
        }
        jsonReader.close();
        return jsonFileToString;
    }


    public static void main(String[] args){
        JsonReaderManager(sample_data_simple_path);
    }


}


