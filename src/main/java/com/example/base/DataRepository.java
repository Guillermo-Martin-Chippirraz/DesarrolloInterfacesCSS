package com.example.base;

import com.example.base.models.Film;
import com.example.base.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataRepository {
    private static DataRepository instance;

    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<Film> films = FXCollections.observableArrayList();

    private DataRepository() {
        seedUsers();
        seedFilms();
    }

    public static DataRepository getInstance() {
        if (instance == null) instance = new DataRepository();
        return instance;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public ObservableList<Film> getFilms() {
        return films;
    }

    // Simula datos iniciales
    private void seedUsers() {
        users.add(new User(0,"Juan", "juan@mail.com", 30));
        users.add(new User(1, "Ana", "ana@mail.com", 25));
        users.add(new User(2, "Carlos", "carlos@mail.com", 40));
    }

    private void seedFilms() {
        films.add(new Film(0, "Matrix", "Sci-Fi", 1999));
        films.add(new Film(1, "Avatar", "Fantasía", 2009));
        films.add(new Film(2, "Inception", "Thriller", 2010));
    }

    public int getNextUserId() {
        return users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1; // Si no hay usuarios, empieza desde 1
    }

    public int getNextFilmId() {
        return films.stream()
                .mapToInt(Film::getId)
                .max()
                .orElse(0) + 1;
    }
}
