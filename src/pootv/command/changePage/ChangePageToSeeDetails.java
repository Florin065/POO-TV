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
     *
     * @param currML
     * @param movieName
     * @param copy
     */
    public static void findMovie(final ArrayList<MovieInput> currML,
                                 final String movieName, final String copy) {
        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(movieName)) {
                ArrayList<MovieInput> movieOutput = new ArrayList<>();
                movieOutput.add(iterator);
                Menu.setMovieDetailsName(movieName);
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
