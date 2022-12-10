package pooTV.commands.authenticated.seeDetails;

import pooTV.commands.Actions;
import pooTV.commands.Command;

public class RateTheMovie implements Command {
    private Actions actions;

    public RateTheMovie(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
