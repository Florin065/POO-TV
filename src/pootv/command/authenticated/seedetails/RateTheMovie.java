package pootv.command.authenticated.seedetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.command.Error;

import java.util.ArrayList;

public class RateTheMovie implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput user;

    public RateTheMovie(final Actions actions, final ArrayNode output,
                        final UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        this.user = newCurrUser;
    }

    /**
     *
     */
    @Override
    public void execute() {
        for (MovieInput iterator : Menu.getCurrUser().getRatedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Error.doError(output);
                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();

        if (user.getWatchedMovies().equals(new ArrayList<>())
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

                return;
            }
        }

        Error.doError(output);
    }
}
