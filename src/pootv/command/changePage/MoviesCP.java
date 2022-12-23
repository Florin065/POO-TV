package pootv.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;

import java.util.ArrayList;

public final class MoviesCP {
    private MoviesCP() {
    }

    /**
     *
     * @param currML
     * @param currUser
     * @param output
     */
    public static void changePageToMovies(final ArrayList<MovieInput> currML,
                                          final UserInput currUser, final ArrayNode output) {
        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", currML)
                .putPOJO("currentUser", currUser);
    }
}
