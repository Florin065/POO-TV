package pootv.command.unauthenticated.login;

import fileio.Credentials;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.unauthenticated.UnauthOutput;
import pootv.command.Command;
import pootv.DataBase;
import pootv.Error;

public class Login implements Command {
    public Login() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("login")) {
            Error.doError(Menu.getOutput());
            return;
        }

        Actions actions = Menu.getActions();
        Credentials credentials = actions.getActionInput().getCredentials();

        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())
                    && iterator.getCredentials().getPassword().equals(credentials.getPassword())) {
                UserInput user = new UserInput.Builder(iterator.getCredentials())
                        .tokensCount(iterator.getTokensCount())
                        .numFreePremiumMovies(iterator.getNumFreePremiumMovies())
                        .build();

                if (iterator.getPurchasedMovies() != null) {
                    user.setPurchasedMovies(iterator.getPurchasedMovies());
                }
                if (iterator.getWatchedMovies() != null) {
                    user.setWatchedMovies(iterator.getWatchedMovies());
                }
                if (iterator.getLikedMovies() != null) {
                    user.setLikedMovies(iterator.getLikedMovies());
                }
                if (iterator.getRatedMovies() != null) {
                    user.setRatedMovies(iterator.getRatedMovies());
                }
                if (iterator.getSubscribedGenres() != null) {
                    user.setSubscribedGenres(iterator.getSubscribedGenres());
                }
                if (iterator.getPurchasedMovies() != null) {
                    user.setNotifications(iterator.getNotifications());
                }

                Menu.setUserIndex(DataBase.getDataBase().getUsers().indexOf(iterator));
                break;
            }
        }

        if (Menu.getCurrUser().getCredentials().getName() == null) {
            Error.doError(Menu.getOutput());
            Menu.setCurrPage("homepage unauth");
        } else {
            Menu.setCurrPage("homepage auth");
            UnauthOutput.doOutput(Menu.getOutput());
        }
    }
}
