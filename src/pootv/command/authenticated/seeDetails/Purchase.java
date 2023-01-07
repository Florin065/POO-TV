package pootv.command.authenticated.seeDetails;

import fileio.MovieInput;
import pootv.Menu;
import pootv.command.Command;

import java.util.ArrayList;

import static pootv.Error.doError;
import static pootv.command.NotBannedMovies.notBannedMovies;
import static pootv.command.authenticated.seeDetails.FreePurchase.free;
import static pootv.command.authenticated.seeDetails.TokensPurchase.tokens;

public class Purchase implements Command {
    public Purchase() {
    }

    /**
     * We search through the list of movies available in the user's country and if the movie
     * is found, then it can be purchased.
     * If the user is premium and still has free movies available, then numFreePremiumMovies - 1;
     * otherwise, it will pay 2 tokens.
     * If the user is standard it will pay 2 tokens.
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("see details")) {
            doError();
            return;
        }
        if (!Menu.getCurrUser().getPurchasedMovies().isEmpty()) {
            for (MovieInput iterator : Menu.getCurrUser().getPurchasedMovies()) {
                if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                    doError();
                    return;
                }
            }
        }

        ArrayList<MovieInput> currML = new ArrayList<>();
        notBannedMovies(currML);
        if (Menu.getCurrUser().getCredentials().getAccountType().equals("premium")) {
            if (Menu.getCurrUser().getNumFreePremiumMovies() <= 0) {
                tokens(currML);
                return;
            }
            free(currML);
            return;
        }
        if (Menu.getCurrUser().getTokensCount() < 2) {
            doError();
            return;
        }
        tokens(currML);
    }
}
