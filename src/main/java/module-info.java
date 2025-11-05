module com.example.base {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.base to javafx.fxml;
    exports com.example.base;
    exports com.example.base.models;
    opens com.example.base.models to javafx.fxml;
    exports com.example.base.controllers;
    opens com.example.base.controllers to javafx.fxml;
}