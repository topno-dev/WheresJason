package io.github.clupthegreat.wheresjason;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class WheresJasonController {
    @FXML
    private Menu Json_controls_menu;

    @FXML
    private Menu about;

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

    @FXML
    private MenuItem open_json;

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

    }

    @FXML
    void open_json_file(ActionEvent event) {

    }

    @FXML
    void prev_search_click(ActionEvent event) {

    }

    @FXML
    void save_json_file(ActionEvent event) {

    }

    @FXML
    void show_about(ActionEvent event) {

    }

    @FXML
    void start_tutorial_help(ActionEvent event) {

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

}
