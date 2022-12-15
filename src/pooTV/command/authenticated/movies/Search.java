package pooTV.command.authenticated.movies;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Credentials;
import fileio.Input;
import fileio.MovieInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.Menu;
import pooTV.command.Actions;
import pooTV.command.Command;

import java.util.ArrayList;

public class Search implements Command {
    @Getter @Setter
    private Input input;
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private ActionsInput actionsInput;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private ArrayList<MovieInput> searchML;

    public Search(Input input, Actions actions, ArrayNode output) {
        this.input = input;
        this.actions = actions;
        this.output = output;
        this.credentials = actions.getActionInput().getCredentials();
        this.actionsInput = actions.getActionInput();
        this.searchML = new ArrayList<>();
    }

    @Override
    public void execute() {
        for (MovieInput iterator : input.getMovies()) {
            if ((!iterator.getCountriesBanned().contains(
                    actions.getCurrUser().getCredentials().getCountry()))
                    && iterator.getName().startsWith(actions.getActionInput().getStartsWith())) {
                searchML.add(iterator);
            }
        }

        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", searchML)
                .putPOJO("currentUser", actions.getCurrUser());
    }
}
