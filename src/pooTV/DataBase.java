package pooTV;

import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    @Getter @Setter
    private List<UserInput> users = new ArrayList<>();
    @Getter @Setter
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
}
