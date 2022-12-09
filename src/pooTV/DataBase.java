package pooTV;

import fileio.MovieInput;
import fileio.UserInput;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<UserInput> users = new ArrayList<>();
    private List<MovieInput> movies = new ArrayList<>();
    private static DataBase dataBase = null;

    private DataBase() {
    }

    public static DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }

        return dataBase;
    }

    public void addUsers(List<UserInput> userInput) {
        this.users.addAll(userInput);
    }

    public void addMovies(List<MovieInput> movieInput) {
        this.movies.addAll(movieInput);
    }
}
