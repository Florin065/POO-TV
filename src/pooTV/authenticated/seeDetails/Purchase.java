package pooTV.authenticated.seeDetails;

import pooTV.Actions;
import pooTV.Command;

public class Purchase implements Command {
    private Actions actions;

    public Purchase(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
