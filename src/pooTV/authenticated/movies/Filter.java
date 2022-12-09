package pooTV.authenticated.movies;

import fileio.FiltersInput;
import pooTV.Actions;
import pooTV.Command;

public class Filter implements Command, pooTV.Filter {
    private Actions actions;

    public Filter(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }

    @Override
    public void filter(FiltersInput filtersInput) {

    }
}
