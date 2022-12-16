package pootv.command.authenticated.movies;

import fileio.ActionsInput;
import fileio.Credentials;
import fileio.Input;
import fileio.MovieInput;
import lombok.Getter;
import lombok.Setter;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.command.authenticated.movies.filterstrategy.FilterStrategy;

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

    public Filter(final Input input, final Actions actions, final FilterStrategy strategy) {
        this.input = input;
        this.actions = actions;
        this.strategy = strategy;
        currML = new ArrayList<>();
    }

    /**
     *
     */
    @Override
    public void execute() {
        for (MovieInput iterator : Menu.getInput().getMovies()) {
            if (!iterator.getCountriesBanned().contains(
                    Menu.getActions().getCurrUser().getCredentials().getCountry())) {
                currML.add(iterator);
            }
        }

        strategy.doFiltering(actions, input, currML);
    }
}
