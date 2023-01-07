package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.CommandOutput;
import pootv.Menu;

import java.util.ArrayList;

public final class TokensPurchase {
    private TokensPurchase() {
    }

    /**
     * Utility method used when a standard/premium (if numFreePremiumMovies = 0)
     * user wants to purchase a movie.
     * @param currML
     */
    public static void tokens(final ArrayList<MovieInput> currML) {
        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Menu.getCurrUser().setTokensCount(Menu.getCurrUser().getTokensCount() - 2);
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
