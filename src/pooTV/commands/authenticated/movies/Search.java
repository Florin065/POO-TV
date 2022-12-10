package pooTV.commands.authenticated.movies;

import pooTV.commands.Actions;
import pooTV.commands.Command;

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
