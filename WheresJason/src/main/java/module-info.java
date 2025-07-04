module io.github.clupthegreat.wheresjason {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;


    opens io.github.clupthegreat.wheresjason to javafx.fxml;
    exports io.github.clupthegreat.wheresjason;
}