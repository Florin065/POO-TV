package pootv.command.authenticated.movies;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;

public class Search implements Command {
    public Search() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("movies")) {
            Error.doError(Menu.getOutput());
            return;
        }

        ArrayList<MovieInput> searchML = new ArrayList<>();

        for (MovieInput iterator : Menu.getInput().getMovies()) {
            if ((!iterator.getCountriesBanned().contains(
                    Menu.getCurrUser().getCredentials().getCountry()))
                    && iterator.getName().startsWith(
                            Menu.getActions().getActionInput().getStartsWith())) {
                searchML.add(iterator);
            }
        }

        ArrayNode output = Menu.getOutput();
        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", searchML)
                .putPOJO("currentUser", Menu.getCurrUser());
    }
}
