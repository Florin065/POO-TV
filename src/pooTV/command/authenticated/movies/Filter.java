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
import pooTV.command.authenticated.movies.filterStrategy.FilterStrategy;

import java.util.ArrayList;

public class Filter implements Command {
    @Getter @Setter
    private FilterStrategy strategy;
    @Getter @Setter
    private Input input;
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private ActionsInput actionsInput;
    @Getter @Setter
    private ArrayList<MovieInput> currML;

    public Filter(Input input, Actions actions, FilterStrategy strategy) {
        this.input = input;
        this.actions = actions;
        this.strategy = strategy;
        currML = new ArrayList<>();
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

        strategy.doFiltering(actions, input, currML);
    }
}
