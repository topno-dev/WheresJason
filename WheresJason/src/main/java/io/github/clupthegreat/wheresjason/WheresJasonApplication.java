package io.github.clupthegreat.wheresjason;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WheresJasonApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(WheresJasonApplication.class.getResource("wheresjason-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1101, 663 );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
