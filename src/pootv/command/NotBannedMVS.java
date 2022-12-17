package pootv.command;

import fileio.MovieInput;
import pootv.Menu;

import java.util.ArrayList;

public final class NotBannedMVS {
    private NotBannedMVS() {
    }

    /**
     *
     * @param currML
     */
    public static void get(final ArrayList<MovieInput> currML) {
        for (MovieInput iterator : Menu.getInput().getMovies()) {
            if (!iterator.getCountriesBanned().contains(
                    Menu.getCurrUser().getCredentials().getCountry())) {
                currML.add(iterator);
            }
        }
    }
}
