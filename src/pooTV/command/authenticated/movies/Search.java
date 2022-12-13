package pooTV.command.authenticated.movies;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Credentials;
import fileio.Input;
import fileio.MovieInput;
import lombok.Getter;
import lombok.Setter;
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
    @Getter @Setter
    private ArrayList<MovieInput> currML;

    public Search(Input input, Actions actions, ArrayNode output) {
        this.input = input;
        this.actions = actions;
        this.output = output;
        this.credentials = actions.getActionInput().getCredentials();
        this.actionsInput = actions.getActionInput();
        this.searchML = new ArrayList<>();
        this.currML = new ArrayList<>();
    }

    @Override
    public void execute() {
        for (MovieInput iterator : input.getMovies()) {
            for (String bannedCountry : iterator.getCountriesBanned()) {
                if (!actions.getCurrUser().getCredentials().getCountry().equals(bannedCountry)) {
                    currML.add(iterator);
                }
            }
        }

        for (MovieInput iterator : currML) {
            if (iterator.getName().startsWith(actionsInput.getStartsWith())) {
                searchML.add(iterator);
            }
        }

        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", searchML)
                .putPOJO("currentUser", actions.getCurrUser());
    }
}
