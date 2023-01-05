package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
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
                double sum;
                if (user.getRating().containsKey(Menu.getMovieDetailsName())) {
                    sum = deepCopy.getRating() * deepCopy.getNumRatings()
                            - user.getRating().get(Menu.getMovieDetailsName())
                            + actions.getActionInput().getRate();
                    sum = sum / deepCopy.getNumRatings();
                    deepCopy.setRating(sum);
                } else {
                    sum = deepCopy.getRating() * deepCopy.getNumRatings()
                            + actions.getActionInput().getRate();
                    deepCopy.setNumRatings(deepCopy.getNumRatings() + 1);
                    sum = sum / deepCopy.getNumRatings();
                    deepCopy.setRating(sum);
                    user.getRatedMovies().add(deepCopy);
                }
                int index;
                List<MovieInput> movies = DataBase.getDataBase().getMovies();
                for (MovieInput iterator2 : user.getRatedMovies()) {
                    if (iterator.getName().equals(iterator2.getName())) {
                        index = user.getRatedMovies().indexOf(iterator2);
                        user.getRatedMovies().set(index, deepCopy);
                        break;
                    }
                }
                for (MovieInput iterator2 : user.getPurchasedMovies()) {
                    if (iterator.getName().equals(iterator2.getName())) {
                        index = user.getPurchasedMovies().indexOf(iterator2);
                        user.getPurchasedMovies().set(index, deepCopy);
                        break;
                    }
                }
                for (MovieInput iterator2 : user.getWatchedMovies()) {
                    if (iterator.getName().equals(iterator2.getName())) {
                        index = user.getWatchedMovies().indexOf(iterator2);
                        user.getWatchedMovies().set(index, deepCopy);
                        break;
                    }
                }
                for (MovieInput iterator2 : movies) {
                    if (iterator.getName().equals(iterator2.getName())) {
                        index = movies.indexOf(iterator2);
                        DataBase.getDataBase().getMovies().set(index, deepCopy);
                        break;
                    }
                }
                if (!user.getLikedMovies().isEmpty()) {
                    for (MovieInput iterator2 : user.getLikedMovies()) {
                        if (iterator.getName().equals(iterator2.getName())) {
                            index = user.getLikedMovies().indexOf(iterator2);
                            user.getLikedMovies().set(index, deepCopy);
                            break;
                        }
                    }
                }
                ArrayList<UserInput> users = new ArrayList<>(DataBase.getDataBase().getUsers());
                for (UserInput userData : users) {
                    int index1 = DataBase.getDataBase().getUsers().indexOf(userData);
                    if (!userData.getRatedMovies().isEmpty()) {
                        for (MovieInput rated : userData.getRatedMovies()) {
                            if (rated.getName().equals(deepCopy.getName())) {
                                int index2 = userData.getRatedMovies().indexOf(rated);
                                userData.getRatedMovies().set(index2, deepCopy);
                                break;
                            }
                        }
                    }
                    for (MovieInput watched : userData.getWatchedMovies()) {
                        if (iterator.getName().equals(watched.getName())) {
                            int index4 = userData.getWatchedMovies()
                                    .indexOf(watched);
                            userData.getWatchedMovies().set(index4, deepCopy);
                            break;
                        }
                    }
                    for (MovieInput purchased : userData.getPurchasedMovies()) {
                        if (iterator.getName().equals(purchased.getName())) {
                            int index5 = userData.getPurchasedMovies()
                                    .indexOf(purchased);
                            userData.getPurchasedMovies().set(index5, deepCopy);
                            break;
                        }
                    }
                    if (!userData.getLikedMovies().isEmpty()) {
                        for (MovieInput liked : userData.getLikedMovies()) {
                            if (iterator.getName().equals(liked.getName())) {
                                int index3 = userData.getLikedMovies()
                                        .indexOf(liked);
                                userData.getLikedMovies().set(index3, deepCopy);
                                break;
                            }
                        }
                    }
                    DataBase.getDataBase().getUsers().set(index1, userData);
                }
                movieOutput.add(deepCopy);
                ObjectMapper mapper = new ObjectMapper();
                output.add(mapper.valueToTree(new CommandOutput(null, movieOutput, user)));
                user.getRating().put(Menu.getMovieDetailsName(),
                        actions.getActionInput().getRate());
                Menu.setCurrUser(new UserInput(user));
                return;
            }
        }
        Error.doError(output);
    }
}
