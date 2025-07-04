package io.github.clupthegreat.wheresjason;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WheresJasonController {

    private final List<TreeItem<String>> searchResults = new ArrayList<>();
    private int currentSearchIndex = -1;
    private final String tempDir = System.getProperty("user.dir") + File.separator + "tempFiles";
    private final String tempFilePath = tempDir + File.separator + "live_edit.json";


    @FXML
    private Menu Json_controls_menu;

    @FXML
    private MenuItem close_app;

    @FXML
    private MenuItem collapse_all;

    @FXML
    private MenuItem expand_all;

    @FXML
    private TextArea json_area;

    @FXML
    private Button next_search_btn;

//    @FXML
//    private MenuItem open_json;

    @FXML
    private Button prev_search_btn;

    @FXML
    private MenuItem save_json;

    @FXML
    private TextField search_tree_text_field;

    @FXML
    private SplitPane split_pane_panel;

    @FXML
    private TreeView<String> tree_view_area;

    @FXML
    private MenuItem tutorial_help_button;

    @FXML
    private Button load_text;



    @FXML
    void close_program(ActionEvent event) {

    }

    @FXML
    void collapse_all_tree(ActionEvent event) {
        TreeItem<String> rootItem = tree_view_area.getRoot();
        collapseAll(rootItem);
    }

    @FXML
    void expand_all_tree(ActionEvent event){
        TreeItem<String> rootItem = tree_view_area.getRoot();
        expandAll(rootItem);
    }

    @FXML
    void next_search_click(ActionEvent event) {
        String searchItem = search_tree_text_field.getText().trim();
        if (searchItem.isEmpty()) return;

        // If new search or query changed, reset results
        if (searchResults.isEmpty() ||
                currentSearchIndex == -1 ||
                !searchResults.get(currentSearchIndex).getValue().equalsIgnoreCase(searchItem)) {

            searchResults.clear();
            TreeItem<String> root = tree_view_area.getRoot();
            collectMatchingItems(root, searchItem, searchResults);
            currentSearchIndex = -1;
        }

        if (!searchResults.isEmpty()) {
            currentSearchIndex = (currentSearchIndex + 1) % searchResults.size();
            TreeItem<String> match = searchResults.get(currentSearchIndex);
            expandPath(match);
            tree_view_area.getSelectionModel().select(match);
            tree_view_area.scrollTo(tree_view_area.getRow(match));
        } else {
            System.out.println("No matches found for: " + searchItem);
        }
    }

//    @FXML
//    void open_json_file(ActionEvent event) {
//        load_json_text();
//    }

    @FXML
    void prev_search_click(ActionEvent event) {
        String searchItem = search_tree_text_field.getText().trim();
        if (searchItem.isEmpty()) return;

        if (searchResults.isEmpty()) return;

        currentSearchIndex = (currentSearchIndex - 1 + searchResults.size()) % searchResults.size();
        TreeItem<String> match = searchResults.get(currentSearchIndex);
        expandPath(match);
        tree_view_area.getSelectionModel().select(match);
        tree_view_area.scrollTo(tree_view_area.getRow(match));
    }

    @FXML
    void save_json_file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            writeJsonFile(file.getAbsolutePath(), json_area.getText());
        }
    }


    @FXML
    void start_tutorial_help(ActionEvent event) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/topno-dev/WheresJason"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void load_json_text(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                String content = Files.readString(file.toPath());
                json_area.setText(content);

                // Save to temp file
                writeJsonFile("tempFiles/temp.json", content);

                // Create tree from temp file
                TreeViewCreator creator = new TreeViewCreator("tempFiles/temp.json");
                TreeItem<String> rootItem = creator.createTreeItemRoot();
                ArrayList<String> currentLevel = new ArrayList<>();
                currentLevel.add(rootItem.getValue());
                creator.fillChildren(currentLevel, rootItem);

                tree_view_area.setRoot(rootItem);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        json_area.setOnKeyReleased(event -> {
            String jsonContent = json_area.getText();

            Set<String> expandedPaths = new HashSet<>();
            if (tree_view_area.getRoot() != null) {
                expandedPaths = getExpandedPaths(tree_view_area.getRoot());
            }

            writeJsonFile("tempFiles/temp.json", jsonContent);

            try {
                TreeViewCreator creator = new TreeViewCreator("tempFiles/temp.json");
                TreeItem<String> rootItem = creator.createTreeItemRoot();
                ArrayList<String> currentLevel = new ArrayList<>();
                currentLevel.add(rootItem.getValue());
                creator.fillChildren(currentLevel, rootItem);

                applyExpandedPaths(rootItem, "", expandedPaths);

                tree_view_area.setRoot(rootItem);

                searchResults.clear();
                currentSearchIndex = -1;
//                search_tree_text_field.clear();
            } catch (Exception e) {
                // Likely invalid JSON while typing, ignore silently
            }
        });
    }

    public void writeJsonFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setTree_view_area(TreeItem<String> rootItem){
        tree_view_area.setRoot(rootItem);
        rootItem.setExpanded(true);

    }

    private void expandAll(TreeItem<String> item){
        if (item != null && !item.isLeaf()){
            item.setExpanded(true);
            for (TreeItem<String> child: item.getChildren()){
                expandAll(child);
            }
        }
    }

    private void collapseAll(TreeItem<String> item){
        if (item!=null  && !item.isLeaf()){
            item.setExpanded(false);
            for (TreeItem<String> child: item.getChildren()){
                collapseAll(child);
            }
        }
    }

    private TreeItem<String> searchTree(String treeItemValue){
        TreeItem<String> rootItem = tree_view_area.getRoot();
        if (rootItem == null) return null;
        return searchRecursive(rootItem, treeItemValue);
    }

    private TreeItem<String> searchRecursive(TreeItem<String> rootItem, String target){
        if (rootItem.getValue().equals(target)){
            return rootItem;
        }

        for (TreeItem<String> child: rootItem.getChildren()){
            TreeItem<String> res = searchRecursive(child,target);
            if (res != null) return res;
        }
        return null;
    }

    public void selectAndScrollTo(String treeItemValue){
        TreeItem<String> found = searchTree(treeItemValue);
        if (found != null){
            expandPath(found);
            tree_view_area.getSelectionModel().select(found);
            tree_view_area.scrollTo(tree_view_area.getRow(found));
        } else {
            System.out.println("Not found");
        }
    }

    private void expandPath(TreeItem<String> item){
        TreeItem<String> parent = item.getParent();
        while (parent != null){
            parent.setExpanded(true);
            parent = parent.getParent();
        }
    }

    private void collectMatchingItems(TreeItem<String> item, String query, List<TreeItem<String>> results) {
        if (item.getValue().equalsIgnoreCase(query)) {
            results.add(item);
        }
        for (TreeItem<String> child : item.getChildren()) {
            collectMatchingItems(child, query, results);
        }
    }

    private void createOrUpdateTempJson(String content) {
        try {
            File dir = new File(tempDir);
            if (!dir.exists()) dir.mkdirs();
            writeJsonFile(tempFilePath, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<String> getExpandedPaths(TreeItem<String> root) {
        Set<String> expandedPaths = new HashSet<>();
        collectExpandedPaths(root, "", expandedPaths);
        return expandedPaths;
    }

    private void collectExpandedPaths(TreeItem<String> item, String path, Set<String> expandedPaths) {
        String currentPath = path.isEmpty() ? item.getValue() : path + "." + item.getValue();
        if (item.isExpanded()) {
            expandedPaths.add(currentPath);
        }
        for (TreeItem<String> child : item.getChildren()) {
            collectExpandedPaths(child, currentPath, expandedPaths);
        }
    }

    private void applyExpandedPaths(TreeItem<String> item, String path, Set<String> expandedPaths) {
        String currentPath = path.isEmpty() ? item.getValue() : path + "." + item.getValue();
        if (expandedPaths.contains(currentPath)) {
            item.setExpanded(true);
        }

        for (TreeItem<String> child : item.getChildren()) {
            applyExpandedPaths(child, currentPath, expandedPaths);
        }
    }

}
