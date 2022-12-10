package pooTV.commands.authenticated.seeDetails;

import pooTV.commands.Actions;
import pooTV.commands.Command;

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
