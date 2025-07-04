package io.github.clupthegreat.wheresjason;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeViewCreator {
    private final String filepath;
    private final JsonObject rootObject;

    public TreeViewCreator(String filepath) throws IOException {
        this.filepath = filepath;
        JSONManager manager = new JSONManager();
        this.rootObject = manager.loadJsonFile(filepath);
    }

    public TreeItem<String> createTreeItemRoot(){

        Set<String> rootItemKeySet = rootObject.keySet();
        List<String> rootItemList = new ArrayList<>(rootItemKeySet);
        return new TreeItem<String>(rootItemList.getFirst());

    }

//    public TreeItem<String> fillChildren(ArrayList<String> current_level, TreeItem<String> rootItem){
//        int total_keys = 0;
//
//        JsonObject jsonObject = rootObject;
//
//        // Traverse the object
//        for (int i = 0; i < current_level.size(); i++){
//            try {
//                jsonObject = jsonObject.getAsJsonObject(current_level.get(i));
//                continue;
//            } catch (Exception _){  }
//
////            try {
////                jsonObject
////            }
//        }
//
////        try {
////            List<String> listOfKeys = new ArrayList<>(jsonObject.keySet());
////            total_keys = listOfKeys.size();
////            for (int i=0; i < total_keys; i++){
////                ArrayList<String> item_current_level =
////                TreeItem<String> iterItems = fillChildren(current_level.add())
////            }
////        } catch (Exception _) { }
//
//        System.out.println(jsonObject.keySet());
//
//        return rootItem;
//    }

    public TreeItem<String> fillChildren(ArrayList<String> currentLevel, TreeItem<String> rootItem) {
        JsonObject jsonObject = rootObject;

        // Traverse to the correct nested JsonObject
        for (String key : currentLevel) {
            try {
                jsonObject = jsonObject.getAsJsonObject(key);
            } catch (Exception e) {
                return rootItem; // exit early if path breaks
            }
        }

        // Add each key from the current JsonObject
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            TreeItem<String> childItem = new TreeItem<>(key);
            rootItem.getChildren().add(childItem);

            if (value == null || value.isJsonNull()) {
                // Handle null values explicitly
                TreeItem<String> nullItem = new TreeItem<>("Value: null");
                childItem.getChildren().add(nullItem);
            } else if (value.isJsonObject()) {
                // Recurse into nested JsonObject
                ArrayList<String> nextLevel = new ArrayList<>(currentLevel);
                nextLevel.add(key);
                fillChildren(nextLevel, childItem);
            } else {
                // Primitive value (String, Number, Boolean)
                TreeItem<String> valueItem = new TreeItem<>("Value: " + value.getAsString());
                childItem.getChildren().add(valueItem);
            }
        }

        return rootItem;
    }


    public static void main(String[] args) throws IOException {
//        String filePath = "D:\\Code\\WheresJason\\WheresJason\\src\\main\\java\\io\\github\\clupthegreat\\wheresjason\\sampledata_simple.json";
//        TreeViewCreator treeViewCreator = new TreeViewCreator(filePath);
//        TreeItem<String> rootItem = treeViewCreator.createTreeItemRoot();
//        ArrayList<String> current_level = new ArrayList<String>();
//        current_level.add(rootItem.getValue());
//        TreeItem<String> exampleItem = treeViewCreator.fillChildren(current_level,rootItem);
//        System.out.println(rootItem.getValue());
        String filePath = "D:\\Code\\WheresJason\\WheresJason\\src\\main\\java\\io\\github\\clupthegreat\\wheresjason\\sampledata_simple.json";
        TreeViewCreator treeViewCreator = new TreeViewCreator(filePath);

        TreeItem<String> rootItem = treeViewCreator.createTreeItemRoot();

        ArrayList<String> currentLevel = new ArrayList<>();
        currentLevel.add(rootItem.getValue());

        treeViewCreator.fillChildren(currentLevel, rootItem);

        // Optional: print the structure
        printTree(rootItem, "");

    }

    private static void printTree(TreeItem<String> node, String indent) {
        System.out.println(indent + "- " + node.getValue());
        for (TreeItem<String> child : node.getChildren()) {
            printTree(child, indent + "  ");
        }
    }

}
