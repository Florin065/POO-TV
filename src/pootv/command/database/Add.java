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

public class Add implements Command {
    public Add() {
    }

    @Override
    public void execute() {
        MovieInput addedMovie = Menu.getActions().getActionInput().getAddedMovie();

        for (MovieInput movie : DataBase.getDataBase().getMovies()) {
            if (movie.getName().equals(addedMovie.getName())) {
                Error.doError(Menu.getOutput());
                return;
            }
        }

        List<MovieInput> newMovieList = new ArrayList<>(DataBase.getDataBase().getMovies());
        newMovieList.add(addedMovie);
        DataBase.getDataBase().setMovies(newMovieList);

        List<UserInput> newUserList = new ArrayList<>(DataBase.getDataBase().getUsers());
        UserInput currUser = Menu.getCurrUser();

        if ((!addedMovie.getCountriesBanned().contains(currUser.getCredentials().getCountry()))
                && (currUser.getSubscribedGenres() != null)) {
            for (String genre : currUser.getSubscribedGenres()) {
                if (addedMovie.getGenres().contains(genre)) {
                    ArrayList<Notifications> notification = new ArrayList<>(currUser.getNotifications());
                    notification.add(new Notifications(addedMovie.getName(), "ADD"));
                    Menu.getCurrUser().setNotifications(notification);
                    break;
                }
            }
        }

        for (UserInput user : newUserList) {
            if ((!addedMovie.getCountriesBanned().contains(user.getCredentials().getCountry()))
                    && (user.getSubscribedGenres() != null)) {
                for (String genre : user.getSubscribedGenres()) {
                    if (addedMovie.getGenres().contains(genre)) {
                        ArrayList<Notifications> notification = new ArrayList<>(user.getNotifications());
                        notification.add(new Notifications(addedMovie.getName(), "ADD"));
                        user.setNotifications(notification);
                        break;
                    }
                }
            }
        }
    }
}
