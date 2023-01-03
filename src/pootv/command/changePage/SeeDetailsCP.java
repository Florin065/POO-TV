package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;
import pootv.Error;

import java.util.ArrayList;

public final class SeeDetailsCP {
    private SeeDetailsCP() {
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
                ArrayList<MovieInput> movieOutput = new ArrayList<>();
                movieOutput.add(iterator);
                Menu.setMovieDetailsName(iterator.getName());

                output.addObject().put("error", (String) null)
                        .putPOJO("currentMoviesList", movieOutput)
                        .putPOJO("currentUser", currUser);

                return;
            }
        }

        Menu.setCurrPage(copy);
        Error.doError(output);
    }
}
