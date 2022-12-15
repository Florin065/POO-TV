package pooTV.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.DataBase;
import pooTV.Menu;
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
        ArrayList<MovieInput> movieOutput = new ArrayList<>();

        if (watchedMovies.equals(new ArrayList<>())) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : watchedMovies) {
            if (iterator.getName().equals(actions.getActionInput().getMovie())) {

                MovieInput deepCopy = new MovieInput(iterator);
                deepCopy.setNumLikes(deepCopy.getNumLikes() + 1);
                user.getPurchasedMovies().set(user.getPurchasedMovies().indexOf(iterator), deepCopy);
                user.getWatchedMovies().set(user.getWatchedMovies().indexOf(iterator), deepCopy);

                if (!user.getRatedMovies().equals(new ArrayList<>())) {
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

                return;
            }
        }

        Error.doError(output);
    }
}
