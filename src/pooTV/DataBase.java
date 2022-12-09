package pooTV;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<User> users = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private static DataBase dataBase = null;

    private DataBase() {
    }

    public static DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }

        return dataBase;
    }

    public void addUsers(List<User> users) {
        this.users.addAll(users);
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
    }
}
