package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.CommandOutput;
import pootv.Menu;

import java.util.ArrayList;

public final class FreePurchase {
    private FreePurchase() {
    }

    /**
     *
     * @param movie
     * @param movieOutput
     */
    public static void free(final MovieInput movie, final ArrayList<MovieInput> movieOutput) {
        Menu.getCurrUser().setNumFreePremiumMovies(
                Menu.getCurrUser().getNumFreePremiumMovies() - 1);
        Menu.getCurrUser().getPurchasedMovies().add(movie);

        movieOutput.add(movie);

        ObjectMapper mapper = new ObjectMapper();
        Menu.getOutput().add(mapper.valueToTree(
                new CommandOutput(null, movieOutput, Menu.getCurrUser())));
    }
}
