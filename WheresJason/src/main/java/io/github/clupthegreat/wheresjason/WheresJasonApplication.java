package io.github.clupthegreat.wheresjason;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WheresJasonApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        File tempDir = new File("tempFiles");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(WheresJasonApplication.class.getResource("wheresjason-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1101, 663 );

        WheresJasonController controller = fxmlLoader.getController();

        String filePath = "D:\\Code\\WheresJason\\WheresJason\\src\\main\\java\\io\\github\\clupthegreat\\wheresjason\\sampledata_simple.json";
        TreeViewCreator treeViewCreator = new TreeViewCreator(filePath);
        TreeItem<String> rootItem = treeViewCreator.createTreeItemRoot();
        ArrayList<String> currentLevel = new ArrayList<>();
        currentLevel.add(rootItem.getValue());
        treeViewCreator.fillChildren(currentLevel, rootItem);
        controller.setTree_view_area(rootItem);
        stage.setScene(scene);

        // Disable resizing
        stage.setResizable(false);
        stage.setTitle("WheresJason");
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        launch();
    }

}
