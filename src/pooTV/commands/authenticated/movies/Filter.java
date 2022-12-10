package pooTV.commands.authenticated.movies;

import fileio.Filters;
import pooTV.commands.Actions;
import pooTV.commands.Command;

public class Filter implements Command {
    private Actions actions;

    public Filter(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }

}
