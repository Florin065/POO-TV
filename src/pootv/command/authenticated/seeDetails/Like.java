package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;
import java.util.List;

public class Like implements Command {
    public Like() {
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

        for (MovieInput iterator : Menu.getCurrUser().getLikedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                Error.doError(output);

                return;
            }
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        UserInput user = new UserInput(Menu.getCurrUser());
        ArrayList<MovieInput> watchedMovies = user.getWatchedMovies();

        if (watchedMovies.isEmpty()) {
            Error.doError(output);
            return;
        }

        for (MovieInput iterator : watchedMovies) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                MovieInput deepCopy = new MovieInput(iterator);
                deepCopy.setNumLikes(deepCopy.getNumLikes() + 1);

                user.getPurchasedMovies().set(user.getPurchasedMovies()
                        .indexOf(iterator), deepCopy);
                user.getWatchedMovies().set(user.getWatchedMovies().indexOf(iterator), deepCopy);

                if (!user.getRatedMovies().isEmpty()) {
                    user.getRatedMovies().set(user.getRatedMovies().indexOf(iterator), deepCopy);
                }

                List<MovieInput> movies = DataBase.getDataBase().getMovies();
                int index = movies.indexOf(iterator);
                DataBase.getDataBase().getMovies().set(index, deepCopy);

                for (UserInput userData : DataBase.getDataBase().getUsers()) {
                    int index1 = DataBase.getDataBase().getUsers().indexOf(userData);
                    if (!userData.getLikedMovies().isEmpty()) {
                        for (MovieInput liked : userData.getLikedMovies()) {
                            if (liked.getName().equals(deepCopy.getName())) {
                                int index2 = userData.getLikedMovies().indexOf(liked);
                                userData.getLikedMovies().set(index2, deepCopy);

                                for (MovieInput watched : userData.getWatchedMovies()) {
                                    if (liked.getName().equals(watched.getName())) {
                                        int index3 = userData.getWatchedMovies().indexOf(watched);
                                        userData.getWatchedMovies().set(index3, deepCopy);
                                    }
                                }

                                for (MovieInput purchased : userData.getPurchasedMovies()) {
                                    if (liked.getName().equals(purchased.getName())) {
                                        int index4 =
                                                userData.getPurchasedMovies().indexOf(purchased);
                                        userData.getPurchasedMovies().set(index4, deepCopy);
                                    }
                                }

                                for (MovieInput rated : userData.getRatedMovies()) {
                                    if (liked.getName().equals(rated.getName())) {
                                        int index5 = userData.getRatedMovies().indexOf(rated);
                                        userData.getRatedMovies().set(index5, deepCopy);
                                    }
                                }

                                DataBase.getDataBase().getUsers().set(index1, userData);
                            }
                        }
                    }
                }

                iterator = new MovieInput(deepCopy);
                user.getLikedMovies().add(iterator);
                movieOutput.add(iterator);

                ObjectMapper mapper = new ObjectMapper();
                output.add(mapper.valueToTree(new CommandOutput(null, movieOutput, user)));

                Menu.setCurrUser(new UserInput(user));

                return;
            }
        }

        Error.doError(output);
    }
}
