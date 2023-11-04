package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.Menu;
import pootv.CommandOutput;

import java.util.ArrayList;

import static pootv.Error.doError;

public final class ChangePageToSeeDetails {
    private ChangePageToSeeDetails() {
    }

    /**
     * We display the movie on the page we are on.
     */
    public static void findMovie(final ArrayList<MovieInput> currML,
                                 final String movieName, final String copy) {
        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(movieName)) {
                Menu.setMovieDetailsName(movieName);

                ArrayList<MovieInput> movieOutput = new ArrayList<>();
                movieOutput.add(iterator);
                ObjectMapper mapper = new ObjectMapper();
                Menu.getOutput().add(mapper.valueToTree(
                        new CommandOutput(null, movieOutput, Menu.getCurrUser())));

                return;
            }
        }

        Menu.getLastPages().remove(Menu.getLastPages().size() - 1);
        Menu.setCurrPage(copy);
        doError();
    }
}
