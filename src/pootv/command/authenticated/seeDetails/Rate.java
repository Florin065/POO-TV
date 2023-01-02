package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;

public class Rate implements Command {
    public Rate() {
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

        for (MovieInput iterator : Menu.getCurrUser().getRatedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Error.doError(output);
                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        UserInput user = new UserInput(Menu.getCurrUser());
        Actions actions = Menu.getActions();

        if (user.getWatchedMovies().isEmpty()
                || actions.getActionInput().getRate() > 2 + 2 + 1
                || actions.getActionInput().getRate() < 0) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : user.getWatchedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                MovieInput deepCopy = new MovieInput(iterator);
                double sum = deepCopy.getRating() * deepCopy.getNumRatings()
                        + actions.getActionInput().getRate();
                deepCopy.setNumRatings(deepCopy.getNumRatings() + 1);
                sum = sum / deepCopy.getNumRatings();
                deepCopy.setRating(sum);

                user.getPurchasedMovies().set(user.getPurchasedMovies()
                        .indexOf(iterator), deepCopy);
                user.getWatchedMovies().set(user.getWatchedMovies().indexOf(iterator), deepCopy);
                Menu.getInput().getMovies().set(
                        Menu.getInput().getMovies().indexOf(iterator), deepCopy);

                if (!user.getLikedMovies().equals(new ArrayList<>())) {
                    user.getLikedMovies().set(user.getRatedMovies().indexOf(iterator), deepCopy);
                }

                iterator = new MovieInput(deepCopy);
                user.getRatedMovies().add(iterator);
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
