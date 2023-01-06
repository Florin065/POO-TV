package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
     *
     */
    @Override
    public void execute() {
        ArrayNode output = Menu.getOutput();

        if (!Menu.getCurrPage().equals("see details")) {
            doError();
            return;
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();

        if (Menu.getCurrUser().getPurchasedMovies().isEmpty()) {
            doError();
            return;
        }

        for (MovieInput iterator : Menu.getCurrUser().getPurchasedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                movieOutput.add(iterator);
                for (MovieInput movie : Menu.getCurrUser().getWatchedMovies()) {
                    if (movie.getName().equals(Menu.getMovieDetailsName())) {
                        ObjectMapper mapper = new ObjectMapper();
                        output.add(mapper.valueToTree(
                                new CommandOutput(null, movieOutput, Menu.getCurrUser())));
                        return;
                    }
                }
                Menu.getCurrUser().getWatchedMovies().add(iterator);
                ObjectMapper mapper = new ObjectMapper();
                output.add(mapper.valueToTree(
                        new CommandOutput(null, movieOutput, Menu.getCurrUser())));

                return;
            }
        }
        doError();
    }
}
