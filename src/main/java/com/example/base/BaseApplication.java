package com.example.base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BaseApplication.class.getResource("base.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FILMING");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
