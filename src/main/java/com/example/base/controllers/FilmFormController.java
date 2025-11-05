package com.example.base.controllers;

import com.example.base.DataRepository;
import com.example.base.models.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FilmFormController {
    private BaseController baseController;
    private Film film; // Usuario a editar o null para nuevo

    @FXML private TextField txtId;
    @FXML private TextField txtTitle;
    @FXML private TextField txtDirector;
    @FXML private TextField txtYear;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
        initEvents();
    }

    public void setData(Film film) {
        this.film = film;
        loadFilmData();
    }

    private void loadFilmData() {
        if (film != null) {
            txtTitle.setText(film.getTitle());
            txtDirector.setText(film.getDirector());
            txtYear.setText(String.valueOf(film.getYear()));
        } else {
            txtTitle.clear();
            txtDirector.clear();
            txtYear.clear();
        }
    }

    private void initEvents() {
        btnSave.setOnAction(e -> saveFilm());
        btnCancel.setOnAction(e -> cancel());
    }

    private void saveFilm() {
        DataRepository repo = DataRepository.getInstance();

        int year = 0;
        try {
            year = Integer.parseInt(txtYear.getText());
        } catch (NumberFormatException ex) {
            // Opcional: mostrar alerta al usuario
            year = 0;
        }

        if (film == null) {
            // Crear nuevo usuario
            film = new Film(
                    repo.getNextFilmId(),
                    txtTitle.getText(),
                    txtDirector.getText(),
                    year
            );
            repo.getFilms().add(film);
        } else {
            // Editar usuario existente
            film.setTitle(txtTitle.getText());
            film.setDirector(txtDirector.getText());
            film.setYear(year);
        }

        // Volver al listado
        baseController.loadView("films/film-list-view.fxml");
    }

    private void cancel() {
        baseController.loadView("films/film-list-view.fxml");
    }
}
