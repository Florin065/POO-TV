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

public class Like implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput user;
    @Getter @Setter
    private ArrayList<MovieInput> watchedMovies;
    @Getter @Setter
    private ArrayList<MovieInput> like;

    public Like(Actions actions, ArrayNode output,
                UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        this.user = newCurrUser;
        watchedMovies = user.getWatchedMovies();
    }

    @Override
    public void execute() {
        if (watchedMovies.equals(new ArrayList<>())) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : watchedMovies) {
            if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                iterator.setNumLikes(iterator.getNumLikes() + 1);
                user.getLikedMovies().add(iterator);

                output.addObject().put("error", (String) null)
                        .putPOJO("currentMoviesList", iterator)
                        .putPOJO("currentUser", user);

                return;
            }
        }

        Error.doError(output);
    }
}
