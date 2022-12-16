package pootv.command.changepage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;

import java.util.ArrayList;

public final class SeeDetailsCP {
    private SeeDetailsCP() {
    }

    /**
     *
     * @param movie
     * @param currUser
     * @param output
     */
    public static void movieDetails(final MovieInput movie, final UserInput currUser,
                                    final ArrayNode output) {
        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        movieOutput.add(movie);
        Menu.setMovieDetailsName(movie.getName());

        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", movieOutput)
                .putPOJO("currentUser", currUser);
    }
}
