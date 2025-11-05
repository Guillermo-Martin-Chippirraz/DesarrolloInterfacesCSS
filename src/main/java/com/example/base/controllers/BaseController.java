package com.example.base.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Arrays;

public class BaseController {
    // Controllers
    @FXML private TopMenuController topMenuController;
    @FXML private LeftMenuController leftMenuController;

    // Node View Elements
    @FXML private StackPane centralPane;

    @FXML
    public void initialize() throws IOException {
        // Añadimos el controlador del padre
        topMenuController.setBaseController(this);
        leftMenuController.setBaseController(this);

        // Cargamos la vista home "por defecto"
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/com/example/base/home-view.fxml"));
        centralPane.getChildren().setAll((Node) loaderHome.load());
    }

    public void loadView(String fxmlPath) {
        loadView(fxmlPath, null);
    }

    public <T> void loadView(String fxmlPath, T data) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/base/" + fxmlPath));
            Node view = loader.load();

            Object controller = loader.getController();

            // Carga del controlador padre
            Arrays.stream(controller.getClass().getMethods()).filter(
                    m -> m.getName().equals("setBaseController") && m.getParameterCount() == 1
            ).findFirst().ifPresent(m -> {
                try {
                    m.invoke(controller, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Carga de los datos si estos se han enviado
            if (data != null) {
                Arrays.stream(controller.getClass().getMethods()).filter(
                        m -> m.getName().equals("setData") && m.getParameterCount() == 1
                ).findFirst().ifPresent(m -> {
                    try {
                        m.invoke(controller, data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            centralPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
