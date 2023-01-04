package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.Error;
import pootv.command.NotBannedMovies;

import java.util.ArrayList;

public class Purchase implements Command {
    public Purchase() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        ArrayNode output = Menu.getOutput();

        if (!Menu.getCurrPage().equals("see details")) {
            Error.doError(output);
            return;
        }

        if (!Menu.getCurrUser().getPurchasedMovies().isEmpty()) {
            for (MovieInput iterator : Menu.getCurrUser().getPurchasedMovies()) {
                if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                    Error.doError(output);
                    return;
                }
            }
        }

        Actions actions = Menu.getActions();
        ArrayList<MovieInput> currML = new ArrayList<>();
        UserInput user = new UserInput(Menu.getCurrUser());
        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        NotBannedMovies.get(currML);

        if (Menu.getCurrUser().getCredentials().getAccountType().equals("premium")) {
            if (user.getNumFreePremiumMovies() <= 0) {
                if (user.getTokensCount() < 2) {
                    Error.doError(output);
                    return;
                }

                if (actions.getFilter() != null) {
                    for (MovieInput iterator : actions.getFilter()) {
                        if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                            TokensPurchase.tokens(output, user, iterator, movieOutput);

                            return;
                        }
                    }
                }

                for (MovieInput iterator : currML) {
                    if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                        TokensPurchase.tokens(output, user, iterator, movieOutput);

                        return;
                    }
                }
            }

            if (actions.getFilter() != null) {
                for (MovieInput iterator : actions.getFilter()) {
                    if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                        FreePurchase.free(output, user, iterator, movieOutput);

                        return;
                    }
                }
            }

            for (MovieInput iterator : currML) {
                if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                    FreePurchase.free(output, user, iterator, movieOutput);

                    return;
                }
            }
        }

        if (user.getTokensCount() < 2) {
            Error.doError(output);
            return;
        }

        if (actions.getFilter() != null) {
            for (MovieInput iterator : actions.getFilter()) {
                if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                    TokensPurchase.tokens(output, user, iterator, movieOutput);

                    return;
                }
            }
        }

        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                TokensPurchase.tokens(output, user, iterator, movieOutput);

                return;
            }
        }

        Error.doError(output);
    }
}
