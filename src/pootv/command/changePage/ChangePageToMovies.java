package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.command.authenticated.seeDetails.CommandOutput;

import java.util.ArrayList;

public final class ChangePageToMovies {
    private ChangePageToMovies() {
    }

    /**
     *
     * @param currML
     * @param currUser
     * @param output
     */
    public static void changePageToMovies(final ArrayList<MovieInput> currML,
                                          final UserInput currUser, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        output.add(mapper.valueToTree(new CommandOutput(null, currML, currUser)));
    }
}
