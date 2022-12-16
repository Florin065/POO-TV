package pootv.command.authenticated.seedetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;

import java.util.ArrayList;

public final class FreePurchase {
    private FreePurchase() {
    }

    /**
     *
     * @param output
     * @param user
     * @param movie
     * @param movieOutput
     */
    public static void free(final ArrayNode output, final UserInput user,
                            final MovieInput movie, final ArrayList<MovieInput> movieOutput) {
        user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
        user.getPurchasedMovies().add(movie);

        movieOutput.add(movie);

        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", movieOutput)
                .putPOJO("currentUser", user);

        Menu.setCurrUser(new UserInput(user));
    }
}
