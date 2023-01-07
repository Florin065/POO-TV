package pootv.command.authenticated.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Command;
import pootv.CommandOutput;

import java.util.ArrayList;

import static pootv.Error.doError;

public class Search implements Command {
    public Search() {
    }

    /**
     * Finds all movies available in the user's country that start with a given text.
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("movies")) {
            doError();
            return;
        }

        ArrayList<MovieInput> searchML = new ArrayList<>();
        for (MovieInput iterator : DataBase.getDataBase().getMovies()) {
            if ((!iterator.getCountriesBanned().contains(
                    Menu.getCurrUser().getCredentials().getCountry()))
                    && iterator.getName().startsWith(
                            Menu.getActions().getActionInput().getStartsWith())) {
                searchML.add(iterator);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        Menu.getOutput().add(mapper.valueToTree(
                new CommandOutput(null, searchML, Menu.getCurrUser())));
    }
}
