package pootv.command.database;

import fileio.MovieInput;
import fileio.Notifications;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Command;

import java.util.ArrayList;
import java.util.List;

import static pootv.Error.doError;

public class Delete implements Command {
    public Delete() {
    }

    /**
     * The action of deleting a movie from the database by an administrator.
     * Users who purchased that movie will be notified and their resources will be refunded.
     * Premium users will receive a free movie, and standard users 2 tokens.
     */
    @Override
    public void execute() {
        String deletedMovie = Menu.getActions().getActionInput().getDeletedMovie();
        MovieInput deleted = null;
        List<String> movieNames = new ArrayList<>();
        for (MovieInput movie : DataBase.getDataBase().getMovies()) {
            movieNames.add(movie.getName());
            if (movie.getName().equals(deletedMovie)) {
                deleted = movie;
            }
        }

        if (!movieNames.contains(deletedMovie)) {
            doError();
            return;
        }

        DataBase.getDataBase().getMovies().remove(deleted);

        for (UserInput user : DataBase.getDataBase().getUsers()) {
            UserInput newUser = new UserInput(user);
            if (newUser.getCredentials().getAccountType().equals("premium")) {
                newUser.setNumFreePremiumMovies(newUser.getNumFreePremiumMovies() + 1);
            } else {
                newUser.setTokensCount(newUser.getTokensCount() + 2);
            }
            newUser.getNotifications().add(new Notifications(deletedMovie, "DELETE"));
        }
    }
}
