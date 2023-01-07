package pootv.command.unAuthenticated.login;

import fileio.Credentials;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.DataBase;

import static pootv.Error.doError;
import static pootv.command.unAuthenticated.UnauthOutput.doOutput;

public class Login implements Command {
    public Login() {
    }

    /**
     * Login action.
     * At login, user data is updated using the Builder class from UserInput, which is implemented
     * using the Builder Pattern.
     * If he has successfully logged in, he will be sent to the homepage auth.
     * Otherwise, on homepage unauth.
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("login")) {
            doError();
            return;
        }

        Actions actions = Menu.getActions();
        Credentials credentials = actions.getActionInput().getCredentials();

        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())
                    && iterator.getCredentials().getPassword().equals(credentials.getPassword())) {
                Menu.setCurrUser(
                        new UserInput.Builder(
                        iterator.getCredentials(),
                        iterator.getTokensCount(),
                        iterator.getNumFreePremiumMovies())
                        .build());

                if (!iterator.getPurchasedMovies().isEmpty()) {
                    Menu.getCurrUser().setPurchasedMovies(iterator.getPurchasedMovies());
                }
                if (!iterator.getWatchedMovies().isEmpty()) {
                    Menu.getCurrUser().setWatchedMovies(iterator.getWatchedMovies());
                }
                if (!iterator.getLikedMovies().isEmpty()) {
                    Menu.getCurrUser().setLikedMovies(iterator.getLikedMovies());
                }
                if (!iterator.getRatedMovies().isEmpty()) {
                    Menu.getCurrUser().setRatedMovies(iterator.getRatedMovies());
                }
                if (!iterator.getSubscribedGenres().isEmpty()) {
                    Menu.getCurrUser().setSubscribedGenres(iterator.getSubscribedGenres());
                }
                if (!iterator.getNotifications().isEmpty()) {
                    Menu.getCurrUser().setNotifications(iterator.getNotifications());
                }
                if (!iterator.getRating().isEmpty()) {
                    Menu.getCurrUser().setRating(iterator.getRating());
                }

                Menu.setUserIndex(DataBase.getDataBase().getUsers().indexOf(iterator));
                break;
            }
        }

        if (Menu.getCurrUser().getCredentials().getName() == null) {
            doError();
            Menu.setCurrPage("homepage unauth");
        } else {
            Menu.setCurrPage("homepage auth");
            doOutput();
        }
    }
}
