package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;

public class Like implements Command {
    public Like() {
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

        for (MovieInput iterator : Menu.getCurrUser().getLikedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Error.doError(output);

                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        UserInput user = new UserInput(Menu.getCurrUser());
        ArrayList<MovieInput> watchedMovies = user.getWatchedMovies();

        if (watchedMovies.equals(new ArrayList<>())) {
            Error.doError(output);

            return;
        }

        for (MovieInput iterator : watchedMovies) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                MovieInput deepCopy = new MovieInput(iterator);
                deepCopy.setNumLikes(deepCopy.getNumLikes() + 1);

                user.getPurchasedMovies().set(user.getPurchasedMovies()
                        .indexOf(iterator), deepCopy);
                user.getWatchedMovies().set(user.getWatchedMovies().indexOf(iterator), deepCopy);

                if (!user.getRatedMovies().isEmpty()) {
                    user.getRatedMovies().set(user.getRatedMovies().indexOf(iterator), deepCopy);
                }

                Menu.getInput().getMovies().set(
                        Menu.getInput().getMovies().indexOf(iterator), deepCopy);

                iterator = new MovieInput(deepCopy);
                user.getLikedMovies().add(iterator);
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
