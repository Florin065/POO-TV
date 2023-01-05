package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import pootv.Error;
import pootv.Menu;
import pootv.command.NotBannedMovies;
import pootv.command.authenticated.seeDetails.CommandOutput;

import java.util.ArrayList;

public final class Back {
    private Back() {
    }

    /**
     *
     * @param output
     */
    public static void goBack(final ArrayNode output) {
        String page = Menu.getLastPages().get(Menu.getLastPages().size() - 1);
        if (page.equals("homepage unauth")
                || Menu.getCurrUser().getCredentials().getName() == null) {
            Error.doError(output);
            return;
        }

        Menu.setCurrPage(Menu.getLastPages().get(Menu.getLastPages().size() - 1));
        Menu.getLastPages().remove(Menu.getLastPages().size() - 1);
        ArrayList<MovieInput> currML = new ArrayList<>();
        NotBannedMovies.get(currML);

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        for (MovieInput movie : currML) {
            if (movie.getName().equals(Menu.getMovieDetailsName())) {
                movieOutput.add(movie);
            }
        }

        if (Menu.getCurrPage().equals("movies")) {
            ObjectMapper mapper = new ObjectMapper();
            output.add(mapper.valueToTree(
                    new CommandOutput(null, currML, Menu.getCurrUser())));
        } else if (Menu.getCurrPage().equals("see details")) {
            ObjectMapper mapper = new ObjectMapper();
            output.add(mapper.valueToTree(
                    new CommandOutput(null, movieOutput, Menu.getCurrUser())));
        }
    }
}
