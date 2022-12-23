package pootv.command.authenticated.seeDetails;

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

        for (MovieInput iterator : Menu.getCurrUser().getWatchedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Error.doError(output);
                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        UserInput user = new UserInput(Menu.getCurrUser());

        if (user.getPurchasedMovies().equals(new ArrayList<>())) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : user.getPurchasedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                user.getWatchedMovies().add(iterator);
                movieOutput.add(iterator);

                output.addObject().put("error", (String) null)
                        .putPOJO("currentMoviesList", movieOutput)
                        .putPOJO("currentUser", user);

                Menu.setCurrUser(new UserInput(user));
                return;
            }
        }

        Error.doError(output);
    }
}
