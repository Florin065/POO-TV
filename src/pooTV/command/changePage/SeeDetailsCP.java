package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;

public class SeeDetailsCP {
    public static void movieDetails(MovieInput movie, UserInput currentUser, ArrayNode output) {
        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", movie)
                .putPOJO("currentUser", currentUser);
    }
}
