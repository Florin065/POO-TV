package pooTV.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.Menu;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.command.Error;

import java.util.ArrayList;

public class Watch implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput user;

    public Watch(Actions actions, ArrayNode output, UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        this.user = newCurrUser;
    }

    @Override
    public void execute() {
        for (MovieInput iterator : Menu.getCurrUser().getWatchedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Error.doError(output);
                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();

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

                return;
            }
        }

        Error.doError(output);
    }
}
