package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.CommandOutput;
import pootv.Menu;
import pootv.command.Command;

import java.util.ArrayList;

import static pootv.Error.doError;

public class Like implements Command {
    public Like() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("see details")) {
            doError();
            return;
        }

        for (MovieInput iterator : Menu.getCurrUser().getLikedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                doError();
                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();

        if (Menu.getCurrUser().getWatchedMovies().isEmpty()) {
            doError();
            return;
        }

        for (MovieInput iterator : Menu.getCurrUser().getWatchedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                iterator.setNumLikes(iterator.getNumLikes() + 1);
                Menu.getCurrUser().getLikedMovies().add(iterator);
                movieOutput.add(iterator);
                ObjectMapper mapper = new ObjectMapper();
                Menu.getOutput().add(mapper.valueToTree(
                        new CommandOutput(null, movieOutput, Menu.getCurrUser())));

                return;
            }
        }
        doError();
    }
}
