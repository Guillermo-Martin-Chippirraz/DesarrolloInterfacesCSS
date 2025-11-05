package com.example.base.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class TopMenuController {
    // Controllers
    private BaseController baseController;

    // Node View Elements
    @FXML private MenuItem menuHome;
    @FXML private MenuItem menuUsuariosListado;
    @FXML private MenuItem menuUsuariosAgregar;
    @FXML private MenuItem menuPeliculasListado;
    @FXML private MenuItem menuPeliculasAgregar;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
        initEvents();
    }

    private void initEvents() {
        menuHome.setOnAction(e -> baseController.loadView("home-view.fxml"));

        menuUsuariosListado.setOnAction(e -> baseController.loadView("users/user-list-view.fxml"));
        menuUsuariosAgregar.setOnAction(e -> baseController.loadView("users/user-form-view.fxml"));

        menuPeliculasListado.setOnAction(e -> baseController.loadView("films/film-list-view.fxml"));
        menuPeliculasAgregar.setOnAction(e -> baseController.loadView("films/film-form-view.fxml"));
    }
}
