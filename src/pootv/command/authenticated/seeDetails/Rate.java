package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.CommandOutput;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;

import java.util.ArrayList;

import static pootv.Error.doError;

public class Rate implements Command {
    public Rate() {
    }

    /**
     * If the user has seen the movie, he can give a grade between 1 and 5 to the movie.
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("see details")) {
            doError();
            return;
        }

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        Actions actions = Menu.getActions();

        if (Menu.getCurrUser().getWatchedMovies().isEmpty()
                || actions.getActionInput().getRate() > 5
                || actions.getActionInput().getRate() < 0) {
            doError();
            return;
        }

        for (MovieInput iterator : Menu.getCurrUser().getWatchedMovies()) {
            if (iterator.getName().equals(Menu.getMovieDetailsName())) {
                double sum;
                if (Menu.getCurrUser().getRating().containsKey(Menu.getMovieDetailsName())) {
                    sum = iterator.getRating() * iterator.getNumRatings()
                            - Menu.getCurrUser().getRating().get(Menu.getMovieDetailsName())
                            + actions.getActionInput().getRate();
                    sum = sum / iterator.getNumRatings();
                    iterator.setRating(sum);
                } else {
                    sum = iterator.getRating() * iterator.getNumRatings()
                            + actions.getActionInput().getRate();
                    iterator.setNumRatings(iterator.getNumRatings() + 1);
                    sum = sum / iterator.getNumRatings();
                    iterator.setRating(sum);
                    Menu.getCurrUser().getRatedMovies().add(iterator);
                }
                movieOutput.add(iterator);
                ObjectMapper mapper = new ObjectMapper();
                Menu.getCurrUser().getRating().put(Menu.getMovieDetailsName(),
                        actions.getActionInput().getRate());
                Menu.getOutput().add(mapper.valueToTree(
                        new CommandOutput(null, movieOutput, Menu.getCurrUser())));

                return;
            }
        }
        doError();
    }
}
