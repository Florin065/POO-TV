package pootv.command.database;

import fileio.MovieInput;
import fileio.Notifications;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Command;

import static pootv.Error.doError;

public class Add implements Command {
    public Add() {
    }

    /**
     * The action of adding a movie to the database by an administrator, if it does not
     * already exist.
     * All users who are subscribed to a genre of the added movie and are not in any country where
     * the movie is banned will receive a notification that a new movie has been added and its name
     */
    @Override
    public void execute() {
        MovieInput addedMovie = Menu.getActions().getActionInput().getAddedMovie();
        for (MovieInput movie : DataBase.getDataBase().getMovies()) {
            if (movie.getName().equals(addedMovie.getName())) {
                doError();
                return;
            }
        }

        DataBase.getDataBase().getMovies().add(addedMovie);
        if ((!addedMovie.getCountriesBanned().contains(
                Menu.getCurrUser().getCredentials().getCountry()))
                && (Menu.getCurrUser().getSubscribedGenres() != null)) {
            for (String genre : Menu.getCurrUser().getSubscribedGenres()) {
                if (addedMovie.getGenres().contains(genre)) {
                    Menu.getCurrUser().getNotifications().add(
                            new Notifications(addedMovie.getName(), "ADD"));
                    break;
                }
            }
        }
    }
}
