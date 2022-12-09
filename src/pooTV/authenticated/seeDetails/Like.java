package pooTV.authenticated.seeDetails;

import pooTV.Actions;
import pooTV.Command;

public class Like implements Command {
    private Actions actions;

    public Like(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
