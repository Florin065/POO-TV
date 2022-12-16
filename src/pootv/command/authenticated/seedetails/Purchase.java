package pootv.command.authenticated.seedetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.command.Error;

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

    public Purchase(final Actions actions, final ArrayNode output, final UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        this.user = newCurrUser;
        this.currML = new ArrayList<>();
    }

    /**
     *
     */
    @Override
    public void execute() {
        for (MovieInput iterator : Menu.getCurrUser().getPurchasedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Error.doError(output);
                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();

        if (Menu.getCurrUser().getCredentials().getAccountType().equals("premium")) {
            if (user.getNumFreePremiumMovies() <= 0) {
                if (user.getTokensCount() < 2) {
                    Error.doError(output);
                    return;
                }

                if (actions.getFilterML() != null) {
                    for (MovieInput iterator : actions.getFilterML()) {
                        if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                            user.setTokensCount(user.getTokensCount() - 2);
                            user.getPurchasedMovies().add(iterator);

                            movieOutput.add(iterator);

                            output.addObject().put("error", (String) null)
                                    .putPOJO("currentMoviesList", movieOutput)
                                    .putPOJO("currentUser", user);

                            return;
                        }
                    }
                }

                for (MovieInput iterator : Menu.getInput().getMovies()) {
                    if (!iterator.getCountriesBanned().contains(
                            Menu.getActions().getCurrUser().getCredentials().getCountry())) {
                        currML.add(iterator);
                    }
                }

                for (MovieInput iterator : currML) {
                    if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                        user.setTokensCount(user.getTokensCount() - 2);
                        user.getPurchasedMovies().add(iterator);

                        movieOutput.add(iterator);

                        output.addObject().put("error", (String) null)
                                .putPOJO("currentMoviesList", movieOutput)
                                .putPOJO("currentUser", user);

                        return;
                    }
                }
            }

            if (actions.getFilterML() != null) {
                for (MovieInput iterator : actions.getFilterML()) {
                    if (iterator.getName().equals(actions.getActionInput().getMovie())) {
                        user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
                        user.getPurchasedMovies().add(iterator);

                        movieOutput.add(iterator);

                        output.addObject().put("error", (String) null)
                                .putPOJO("currentMoviesList", movieOutput)
                                .putPOJO("currentUser", user);

                        return;
                    }
                }
            }

            for (MovieInput iterator : Menu.getInput().getMovies()) {
                if (!iterator.getCountriesBanned().contains(
                        Menu.getActions().getCurrUser().getCredentials().getCountry())) {
                    currML.add(iterator);
                }
            }

            for (MovieInput iterator : currML) {
                if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                    user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
                    user.getPurchasedMovies().add(iterator);

                    movieOutput.add(iterator);

                    output.addObject().put("error", (String) null)
                            .putPOJO("currentMoviesList", movieOutput)
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

                    movieOutput.add(iterator);

                    output.addObject().put("error", (String) null)
                            .putPOJO("currentMoviesList", movieOutput)
                            .putPOJO("currentUser", user);

                    return;
                }
            }
        }

        for (MovieInput iterator : Menu.getInput().getMovies()) {
            if (!iterator.getCountriesBanned().contains(
                    Menu.getActions().getCurrUser().getCredentials().getCountry())) {
                currML.add(iterator);
            }
        }

        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                user.setTokensCount(getUser().getTokensCount() - 2);
                user.getPurchasedMovies().add(iterator);

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
