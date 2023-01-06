package pootv.command;

import fileio.MovieInput;
import pootv.DataBase;
import pootv.Menu;

import java.util.ArrayList;

public final class NotBannedMovies {
    private NotBannedMovies() {
    }

    /**
     *
     * @param currML
     */
    public static void notBannedMovies(final ArrayList<MovieInput> currML) {
        for (MovieInput iterator : DataBase.getDataBase().getMovies()) {
            if (!iterator.getCountriesBanned().contains(
                    Menu.getCurrUser().getCredentials().getCountry())) {
                currML.add(iterator);
            }
        }
    }
}
