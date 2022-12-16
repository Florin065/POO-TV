package pootv.command.changepage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;
import pootv.Error;

import java.util.ArrayList;

public final class FindMovieByName {
    private FindMovieByName() {
    }

    /**
     *
     * @param currUser
     * @param currML
     * @param movieName
     * @param output
     * @param copy
     */
    public static void findMovie(final UserInput currUser, final ArrayList<MovieInput> currML,
                                 final String movieName, final ArrayNode output,
                                 final String copy) {
        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(movieName)) {
                SeeDetailsCP.movieDetails(iterator, currUser, output);
                return;
            }
        }

        Menu.setCurrPage(copy);
        Error.doError(output);
    }
}
