package pooTV.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.Menu;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.command.Error;

import java.util.ArrayList;

public class Purchase implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput user;
    @Getter @Setter
    private ArrayList<MovieInput> movieList;
    @Getter @Setter
    private ArrayList<MovieInput> currML;

    public Purchase(Actions actions, ArrayNode output, UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        this.user = newCurrUser;
        this.currML = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (Menu.getCurrUser().getCredentials().getAccountType().equals("premium")) {
            if (user.getNumFreePremiumMovies() < 1) {
                Error.doError(output);
                return;
            }

            if (actions.getFilterML() != null) {
                for (MovieInput iterator : actions.getFilterML()) {
                    if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                        user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
                        user.getPurchasedMovies().add(iterator);

                        output.addObject().put("error", (String) null)
                                .putPOJO("currentMoviesList", iterator)
                                .putPOJO("currentUser", user);

                        return;
                    }
                }
            }

            for (MovieInput iterator : Menu.getInput().getMovies()) {
                for (String bannedCountry : iterator.getCountriesBanned()) {
                    if (!Menu.getActions().getCurrUser()
                            .getCredentials().getCountry().equals(bannedCountry)) {
                        currML.add(iterator);
                    }
                }
            }

            for (MovieInput iterator : currML) {
                if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                    user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
                    user.getPurchasedMovies().add(iterator);

                    output.addObject().put("error", (String) null)
                            .putPOJO("currentMoviesList", iterator)
                            .putPOJO("currentUser", user);

                    return;
                }
            }
        }

        if (getUser().getTokensCount() < 2) {
            Error.doError(output);
            return;
        }

        if (actions.getFilterML() != null) {
            for (MovieInput iterator : actions.getFilterML()) {
                if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                    user.setTokensCount(getUser().getTokensCount() - 2);
                    user.getPurchasedMovies().add(iterator);

                    output.addObject().put("error", (String) null)
                            .putPOJO("currentMoviesList", iterator)
                            .putPOJO("currentUser", user);

                    return;
                }
            }
        }

        for (MovieInput iterator : Menu.getInput().getMovies()) {
            for (String bannedCountry : iterator.getCountriesBanned()) {
                if (!Menu.getActions().getCurrUser()
                        .getCredentials().getCountry().equals(bannedCountry)) {
                    currML.add(iterator);
                }
            }
        }

        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                user.setTokensCount(getUser().getTokensCount() - 2);
                user.getPurchasedMovies().add(iterator);

                output.addObject().put("error", (String) null)
                        .putPOJO("currentMoviesList", iterator)
                        .putPOJO("currentUser", user);
            }
        }

        Error.doError(output);
    }
}
