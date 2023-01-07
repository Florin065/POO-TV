package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.CommandOutput;
import pootv.Menu;
import pootv.command.Command;

import java.util.ArrayList;

import static pootv.Error.doError;

public class Watch implements Command {
    public Watch() {
    }

    /**
     * If the user is on the page of a purchased movie, then he can watch it.
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("see details")) {
            doError();
            return;
        }
        if (Menu.getCurrUser().getPurchasedMovies().isEmpty()) {
            doError();
            return;
        }

        for (MovieInput iterator : Menu.getCurrUser().getPurchasedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                ArrayList<MovieInput> movieOutput = new ArrayList<>();
                movieOutput.add(iterator);
                for (MovieInput movie : Menu.getCurrUser().getWatchedMovies()) {
                    if (movie.getName().equals(Menu.getMovieDetailsName())) {
                        ObjectMapper mapper = new ObjectMapper();
                        Menu.getOutput().add(mapper.valueToTree(
                                new CommandOutput(null, movieOutput, Menu.getCurrUser())));
                        return;
                    }
                }
                Menu.getCurrUser().getWatchedMovies().add(iterator);
                ObjectMapper mapper = new ObjectMapper();
                Menu.getOutput().add(mapper.valueToTree(
                        new CommandOutput(null, movieOutput, Menu.getCurrUser())));
                return;
            }
        }
        doError();
    }
}
