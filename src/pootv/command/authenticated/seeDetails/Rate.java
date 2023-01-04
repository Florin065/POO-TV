package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.Rating;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;
import java.util.List;

public class Rate implements Command {
    public Rate() {
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

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        UserInput user = new UserInput(Menu.getCurrUser());
        Actions actions = Menu.getActions();

        if (user.getWatchedMovies().isEmpty()
                || actions.getActionInput().getRate() > 2 + 2 + 1
                || actions.getActionInput().getRate() < 0) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : user.getWatchedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                MovieInput deepCopy = new MovieInput(iterator);
                double sum = deepCopy.getRating() * deepCopy.getNumRatings()
                        + actions.getActionInput().getRate();
                deepCopy.setNumRatings(deepCopy.getNumRatings() + 1);
                sum = sum / deepCopy.getNumRatings();
                deepCopy.setRating(sum);

                user.getPurchasedMovies().set(user.getPurchasedMovies()
                        .indexOf(iterator), deepCopy);
                user.getWatchedMovies().set(user.getWatchedMovies().indexOf(iterator), deepCopy);

                List<MovieInput> movies = DataBase.getDataBase().getMovies();
                int index = movies.indexOf(iterator);
                DataBase.getDataBase().getMovies().set(index, deepCopy);

                if (!user.getLikedMovies().isEmpty()) {
                    user.getLikedMovies().set(user.getLikedMovies().indexOf(iterator), deepCopy);
                }

                for (UserInput userData : DataBase.getDataBase().getUsers()) {
                    int index1 = DataBase.getDataBase().getUsers().indexOf(userData);
                    if (!userData.getRatedMovies().isEmpty()) {
                        for (MovieInput rated : userData.getRatedMovies()) {
                            if (rated.getName().equals(deepCopy.getName())) {
                                int index2 = userData.getRatedMovies().indexOf(rated);
                                userData.getRatedMovies().set(index2, deepCopy);

                                for (MovieInput watched : userData.getWatchedMovies()) {
                                    if (rated.getName().equals(watched.getName())) {
                                        int index4 = userData.getWatchedMovies().indexOf(watched);
                                        userData.getWatchedMovies().set(index4, deepCopy);
                                    }
                                }

                                for (MovieInput purchased : userData.getPurchasedMovies()) {
                                    if (rated.getName().equals(purchased.getName())) {
                                        int index5 = userData.getPurchasedMovies().indexOf(purchased);
                                        userData.getPurchasedMovies().set(index5, deepCopy);
                                    }
                                }

                                for (MovieInput liked : userData.getLikedMovies()) {
                                    if (rated.getName().equals(liked.getName())) {
                                        int index3 = userData.getLikedMovies().indexOf(liked);
                                        userData.getLikedMovies().set(index3, deepCopy);
                                    }
                                }

                                DataBase.getDataBase().getUsers().set(index1, userData);
                            }
                        }
                    }
                }

                iterator = new MovieInput(deepCopy);
                user.getRatedMovies().add(iterator);
                movieOutput.add(iterator);

                output.addObject().put("error", (String) null)
                        .putPOJO("currentMoviesList", movieOutput)
                        .putPOJO("currentUser", user);

                user.getRating().add(new Rating(Menu.getMovieDetailsName(), actions.getActionInput().getRate()));
                Menu.setCurrUser(new UserInput(user));
                return;
            }
        }

        Error.doError(output);
    }
}
