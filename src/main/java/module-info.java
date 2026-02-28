module com.example.tallerbicicletas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tallerbicicletas to javafx.fxml;
    exports com.example.tallerbicicletas;
}