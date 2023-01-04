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
        String deletedMovie = Menu.getActions().getActionInput().getDeletedMovie();
        MovieInput deleted = null;

        for (MovieInput movie : DataBase.getDataBase().getMovies()) {
            if (movie.getName().equals(deletedMovie)) {
                deleted = new MovieInput(movie);
            }
        }

        List<String> movieNames = new ArrayList<>();
        for (MovieInput movieInput : DataBase.getDataBase().getMovies()) {
            movieNames.add(movieInput.getName());
        }

        if (!movieNames.contains(deletedMovie)) {
            Error.doError(Menu.getOutput());
            return;
        }

        List<MovieInput> newMovieList = new ArrayList<>(DataBase.getDataBase().getMovies());
        newMovieList.remove(deleted);
        DataBase.getDataBase().setMovies(newMovieList);

        List<UserInput> newUserList = new ArrayList<>(DataBase.getDataBase().getUsers());

        for (UserInput user : newUserList) {
            ArrayList<Notifications> notification = new ArrayList<>(user.getNotifications());
            notification.add(new Notifications(deletedMovie, "DELETE"));
            UserInput newUser = new UserInput(user);

            if (newUser.getCredentials().getAccountType().equals("premium")) {
                newUser.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
            } else {
                newUser.setTokensCount(newUser.getTokensCount() + 2);
            }

            newUser.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
            newUser.getPurchasedMovies().remove(deleted);
            newUser.getWatchedMovies().remove(deleted);
            newUser.getLikedMovies().remove(deleted);
            newUser.getRatedMovies().remove(deleted);
            newUser.setNotifications(notification);
        }

        DataBase.getDataBase().setUsers(newUserList);
    }
}
