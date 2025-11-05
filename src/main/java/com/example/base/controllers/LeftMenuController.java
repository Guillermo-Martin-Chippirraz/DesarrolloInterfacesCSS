package com.example.base.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LeftMenuController {
    // Controllers
    private BaseController baseController;

    // Node View Elements
    @FXML private Button btnHome;
    @FXML private Button btnUsuariosListado;
    @FXML private Button btnUsuariosAgregar;
    @FXML private Button btnPeliculasListado;
    @FXML private Button btnPeliculasAgregar;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
        initEvents();
    }

    private void initEvents() {
        btnHome.setOnAction(e -> baseController.loadView("home-view.fxml"));

        btnUsuariosListado.setOnAction(e -> baseController.loadView("users/user-list-view.fxml"));
        btnUsuariosAgregar.setOnAction(e -> baseController.loadView("users/user-form-view.fxml"));

        btnPeliculasListado.setOnAction(e -> baseController.loadView("films/film-list-view.fxml"));
        btnPeliculasAgregar.setOnAction(e -> baseController.loadView("films/film-form-view.fxml"));
    }
}
