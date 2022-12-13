package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;

import java.util.ArrayList;

public class MoviesCP {
    public static void changePageToMovies(ArrayList<MovieInput> currentML,
                                          UserInput currentUser, ArrayNode output) {
        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", currentML)
                .putPOJO("currentUser", currentUser);
    }
}
