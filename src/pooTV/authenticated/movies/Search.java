package pooTV.authenticated.movies;

import pooTV.Actions;
import pooTV.Command;

public class Search implements Command {
    private Actions actions;

    public Search(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
