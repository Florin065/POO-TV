package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;

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
            Error.doError(output);
            return;
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        UserInput user = new UserInput(Menu.getCurrUser());

        if (user.getPurchasedMovies().isEmpty()) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : user.getPurchasedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                user.getWatchedMovies().add(iterator);
                movieOutput.add(iterator);

                for (MovieInput movie : Menu.getCurrUser().getWatchedMovies()) {
                    if (movie.getName().equals(Menu.getMovieDetailsName())) {
                        ObjectMapper mapper = new ObjectMapper();
                        output.add(mapper.valueToTree(
                                new CommandOutput(null, movieOutput, Menu.getCurrUser())));
                        return;
                    }
                }

                ObjectMapper mapper = new ObjectMapper();
                output.add(mapper.valueToTree(new CommandOutput(null, movieOutput, user)));

                Menu.setCurrUser(new UserInput(user));
                return;
            }
        }

        Error.doError(output);
    }
}
