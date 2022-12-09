package pooTV.authenticated.seeDetails;

import pooTV.Actions;
import pooTV.Command;

public class Watch implements Command {
    private Actions actions;

    public Watch(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
