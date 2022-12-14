package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pooTV.Menu;
import pooTV.command.Error;

public class SeeDetailsCP {
    public static void movieDetails(MovieInput movie, UserInput currUser, ArrayNode output) {
        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", movie)
                .putPOJO("currentUser", currUser);
    }
}
