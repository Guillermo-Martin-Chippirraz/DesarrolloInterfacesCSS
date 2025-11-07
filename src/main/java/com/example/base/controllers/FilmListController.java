package com.example.base.controllers;

import com.example.base.DataRepository;
import com.example.base.models.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class FilmListController {
    // Controllers
    private BaseController baseController;

    // Node View Elements
    @FXML private TableView<Film> tableFilms;
    @FXML private TableColumn<Film, String> colTitle;
    @FXML private TableColumn<Film, String> colDirector;
    @FXML private TableColumn<Film, Integer> colYear;
    @FXML private TableColumn<Film, Void> colActions;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    @FXML
    public void initialize() {
        // Configurar columnas
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        // Bind con observable list
        tableFilms.setItems(DataRepository.getInstance().getFilms());

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
                    Film film = getTableView().getItems().get(getIndex());
                    baseController.loadView("films/film-form-view.fxml", film);
                });

                // Acción Eliminar
                btnDelete.setOnAction(event -> {
                    Film film = getTableView().getItems().get(getIndex());
                    DataRepository.getInstance().getFilms().remove(film);
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
