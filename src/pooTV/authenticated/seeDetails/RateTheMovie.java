package pooTV.authenticated.seeDetails;

import pooTV.Actions;
import pooTV.Command;

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
