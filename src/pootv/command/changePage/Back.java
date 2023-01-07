package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.Menu;
import pootv.CommandOutput;

import java.util.ArrayList;

import static pootv.Error.doError;
import static pootv.command.NotBannedMovies.notBannedMovies;

public final class Back {
    private Back() {
    }

    /**
     * This action can be seen as the reverse of the change page action, but instead of navigating
     * to the next page received in the input, you return to the page you were on before the last
     * change page command.
     * At each changepage I stored the last page in a list. On the back action I set the current
     * page as the last item in the list, then delete it from the list.
     * Returning to a specific page will generate the same output as a change page action
     * to that page.
     */
    public static void goBack() {
        String page = Menu.getLastPages().get(Menu.getLastPages().size() - 1);
        if (page.equals("homepage unauth")
                || Menu.getCurrUser().getCredentials().getName() == null) {
            doError();
            return;
        }

        Menu.setCurrPage(Menu.getLastPages().get(Menu.getLastPages().size() - 1));
        Menu.getLastPages().remove(Menu.getLastPages().size() - 1);
        ArrayList<MovieInput> currML = new ArrayList<>();
        notBannedMovies(currML);

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        for (MovieInput movie : currML) {
            if (movie.getName().equals(Menu.getMovieDetailsName())) {
                movieOutput.add(movie);
            }
        }
        if (Menu.getCurrPage().equals("movies")) {
            ObjectMapper mapper = new ObjectMapper();
            Menu.getOutput().add(mapper.valueToTree(
                    new CommandOutput(null, currML, Menu.getCurrUser())));
        } else if (Menu.getCurrPage().equals("see details")) {
            ObjectMapper mapper = new ObjectMapper();
            Menu.getOutput().add(mapper.valueToTree(
                    new CommandOutput(null, movieOutput, Menu.getCurrUser())));
        }
    }
}
