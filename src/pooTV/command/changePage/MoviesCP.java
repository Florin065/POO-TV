package pooTV.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pooTV.Menu;

import java.util.ArrayList;

public class MoviesCP {
    public static void changePageToMovies(ArrayList<MovieInput> currentML,
                                          UserInput currentUser) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode output = mapper.createArrayNode();

        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", currentML)
                .putPOJO("currentUser", currentUser);

        Menu.setOutput(output);
    }
}
