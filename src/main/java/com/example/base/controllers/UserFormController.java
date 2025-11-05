package com.example.base.controllers;

import com.example.base.models.User;
import com.example.base.DataRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserFormController {

    private BaseController baseController;
    private User user; // Usuario a editar o null para nuevo

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAge;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
        initEvents();
    }

    public void setData(User user) {
        this.user = user;
        loadUserData();
    }

    private void loadUserData() {
        if (user != null) {
            txtName.setText(user.getName());
            txtEmail.setText(user.getEmail());
            txtAge.setText(String.valueOf(user.getAge()));
        } else {
            txtName.clear();
            txtEmail.clear();
            txtAge.clear();
        }
    }

    private void initEvents() {
        btnSave.setOnAction(e -> saveUser());
        btnCancel.setOnAction(e -> cancel());
    }

    private void saveUser() {
        DataRepository repo = DataRepository.getInstance();

        int age = 0;
        try {
            age = Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException ex) {
            // Opcional: mostrar alerta al usuario
            age = 0;
        }

        if (user == null) {
            // Crear nuevo usuario
            user = new User(
                    repo.getNextUserId(),
                    txtName.getText(),
                    txtEmail.getText(),
                    age
            );
            repo.getUsers().add(user);
        } else {
            // Editar usuario existente
            user.setName(txtName.getText());
            user.setEmail(txtEmail.getText());
            user.setAge(age);
        }

        // Volver al listado
        baseController.loadView("users/user-list-view.fxml");
    }

    private void cancel() {
        baseController.loadView("users/user-list-view.fxml");
    }
}
