package pootv.command.database;

import fileio.MovieInput;
import fileio.Notifications;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Error;
import pootv.Menu;
import pootv.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Delete implements Command {
    public Delete() {
    }

    @Override
    public void execute() {
        MovieInput deletedMovie = Menu.getActions().getActionInput().getAddedMovie();

        if (!DataBase.getDataBase().getMovies().contains(deletedMovie)) {
            Error.doError(Menu.getOutput());
            return;
        }

        List<MovieInput> newMovieList = new ArrayList<>(DataBase.getDataBase().getMovies());
        newMovieList.remove(deletedMovie);
        DataBase.getDataBase().setMovies(newMovieList);

        List<UserInput> newUserList = new ArrayList<>(DataBase.getDataBase().getUsers());

        for (UserInput user : newUserList) {
            ArrayList<Notifications> notification = new ArrayList<>(user.getNotifications());
            notification.add(new Notifications(deletedMovie.getName(), "DELETE"));
            UserInput newUser = new UserInput(user);

            if (newUser.getCredentials().getAccountType().equals("premium")) {
                newUser.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
            } else {
                newUser.setTokensCount(newUser.getTokensCount() + 2);
            }

            newUser.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
            newUser.getPurchasedMovies().remove(deletedMovie);
            newUser.getWatchedMovies().remove(deletedMovie);
            newUser.getLikedMovies().remove(deletedMovie);
            newUser.getRatedMovies().remove(deletedMovie);
            newUser.setNotifications(notification);
        }

        DataBase.getDataBase().setUsers(newUserList);
    }
}
