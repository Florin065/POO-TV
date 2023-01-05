package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;
import pootv.Error;
import pootv.command.authenticated.seeDetails.CommandOutput;

import java.util.ArrayList;

public final class ChangePageToSeeDetails {
    private ChangePageToSeeDetails() {
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

                ObjectMapper mapper = new ObjectMapper();
                output.add(mapper.valueToTree(new CommandOutput(null, movieOutput, currUser)));

                return;
            }
        }

        Menu.getLastPages().remove(Menu.getLastPages().size() - 1);
        Menu.setCurrPage(copy);
        Error.doError(output);
    }
}
