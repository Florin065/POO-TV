package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;

import java.util.ArrayList;

public class MoviesCP {
    public static void changePageToMovies(ArrayList<MovieInput> currML,
                                          UserInput currUser, ArrayNode output) {
        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", currML)
                .putPOJO("currentUser", currUser);
    }
}
