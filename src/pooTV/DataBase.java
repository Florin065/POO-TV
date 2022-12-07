package pooTV;

public class DataBase {
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
