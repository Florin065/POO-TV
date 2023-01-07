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
     * Utility method used when a premium user wants to purchase a movie.
     * @param currML
     */
    public static void free(final ArrayList<MovieInput> currML) {
        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Menu.getCurrUser().setNumFreePremiumMovies(
                        Menu.getCurrUser().getNumFreePremiumMovies() - 1);
                Menu.getCurrUser().getPurchasedMovies().add(iterator);

                ArrayList<MovieInput> movieOutput = new ArrayList<>();
                movieOutput.add(iterator);
                ObjectMapper mapper = new ObjectMapper();
                Menu.getOutput().add(mapper.valueToTree(
                        new CommandOutput(null, movieOutput, Menu.getCurrUser())));
            }
        }
    }
}
