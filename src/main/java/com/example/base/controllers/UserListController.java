package com.example.base.controllers;

import com.example.base.models.User;
import com.example.base.DataRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class UserListController {
    // Controllers
    private BaseController baseController;

    // Node View Elements
    @FXML private TableView<User> tableUsers;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, Integer> colAge;
    @FXML private TableColumn<User, Void> colActions;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    @FXML
    public void initialize() {
        // Configurar columnas
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        // Bind con observable list
        tableUsers.setItems(DataRepository.getInstance().getUsers());

        // Crear columnas acciones
        colActions.setCellFactory(column -> new TableCell<>() {
            private final Button btnEdit = new Button("Editar");
            private final Button btnDelete = new Button("Eliminar");
            private final HBox container = new HBox(10, btnEdit, btnDelete);

            {
                estilizarBotonEditar(btnEdit);
                estilizarBotonEliminar(btnDelete);
                // Acción Editar (por ahora sólo imprime)
                btnEdit.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    baseController.loadView("users/user-form-view.fxml", user);
                });

                // Acción Eliminar
                btnDelete.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    DataRepository.getInstance().getUsers().remove(user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(container);
                }
            }
        });
    }

    private void estilizarBotonEditar(Button btn) {
        btn.setStyle("-fx-background-color: #7a3fc8; -fx-text-fill: white; -fx-font-size: 13px; -fx-padding: 6 14; -fx-background-radius: 4;");
    }

    private void estilizarBotonEliminar(Button btn) {
        btn.setStyle("-fx-background-color: #cc3333; -fx-text-fill: white; -fx-font-size: 13px; -fx-padding: 6 14; -fx-background-radius: 4;");
    }
}
