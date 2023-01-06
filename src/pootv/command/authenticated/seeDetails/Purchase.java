package pootv.command.authenticated.seeDetails;

import fileio.MovieInput;
import pootv.Menu;
import pootv.command.Command;

import java.util.ArrayList;

import static pootv.Error.doError;
import static pootv.command.NotBannedMovies.notBannedMovies;

public class Purchase implements Command {
    public Purchase() {
    }

    /**
     *
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
        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        notBannedMovies(currML);

        if (Menu.getCurrUser().getCredentials().getAccountType().equals("premium")) {
            if (Menu.getCurrUser().getNumFreePremiumMovies() <= 0) {
                if (Menu.getCurrUser().getTokensCount() < 2) {
                    doError();
                    return;
                }

                if (Menu.getActions().getFilter() != null) {
                    for (MovieInput iterator : Menu.getActions().getFilter()) {
                        if (iterator.getName().equals(
                                Menu.getActions().getActionInput().getMovie())) {
                            TokensPurchase.tokens(iterator, movieOutput);

                            return;
                        }
                    }
                }
                for (MovieInput iterator : currML) {
                    if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                        TokensPurchase.tokens(iterator, movieOutput);

                        return;
                    }
                }
            }

            if (Menu.getActions().getFilter() != null) {
                for (MovieInput iterator : Menu.getActions().getFilter()) {
                    if (iterator.getName().equals(Menu.getActions().getActionInput().getMovie())) {
                        FreePurchase.free(iterator, movieOutput);

                        return;
                    }
                }
            }
            for (MovieInput iterator : currML) {
                if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                    FreePurchase.free(iterator, movieOutput);

                    return;
                }
            }
        }

        if (Menu.getCurrUser().getTokensCount() < 2) {
            doError();
            return;
        }

        if (Menu.getActions().getFilter() != null) {
            for (MovieInput iterator : Menu.getActions().getFilter()) {
                if (iterator.getName().equals(Menu.getActions().getActionInput().getMovie())) {
                    TokensPurchase.tokens(iterator, movieOutput);

                    return;
                }
            }
        }

        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                TokensPurchase.tokens(iterator, movieOutput);

                return;
            }
        }
        doError();
    }
}
