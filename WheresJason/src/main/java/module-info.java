module io.github.clupthegreat.wheresjason {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.google.gson;


    opens io.github.clupthegreat.wheresjason to javafx.fxml;
    exports io.github.clupthegreat.wheresjason;
}