package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.Menu;
import pootv.CommandOutput;

import java.util.ArrayList;

public final class ChangePageToMovies {
    private ChangePageToMovies() {
    }

    /**
     * We display the current list of movies.
     */
    public static void changePageToMovies(final ArrayList<MovieInput> currML) {
        ObjectMapper mapper = new ObjectMapper();
        Menu.getOutput().add(mapper.valueToTree(
                new CommandOutput(null, currML, Menu.getCurrUser())));
    }
}
