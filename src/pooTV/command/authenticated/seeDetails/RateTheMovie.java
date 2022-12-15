package pooTV.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.command.Error;

import java.util.ArrayList;

public class RateTheMovie implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput user;

    public RateTheMovie(Actions actions, ArrayNode output, UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        this.user = newCurrUser;
    }

    @Override
    public void execute() {
        ArrayList<MovieInput> movieOutput = new ArrayList<>();

        if (user.getWatchedMovies().equals(new ArrayList<>())) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : user.getWatchedMovies()) {
            if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                double sum = iterator.getRating() * iterator.getNumRatings()
                        + actions.getActionInput().getRate();
                iterator.setNumRatings(iterator.getNumRatings() + 1);
                sum = sum / iterator.getNumRatings();
                iterator.setRating(sum);

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
