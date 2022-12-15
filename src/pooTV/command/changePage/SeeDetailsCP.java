package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pooTV.Menu;
import pooTV.command.Error;

import java.util.ArrayList;

public class SeeDetailsCP {
    public static void movieDetails(MovieInput movie, UserInput currUser, ArrayNode output) {
        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        movieOutput.add(movie);

        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", movieOutput)
                .putPOJO("currentUser", currUser);
    }
}
