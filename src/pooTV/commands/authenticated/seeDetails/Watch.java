package pooTV.commands.authenticated.seeDetails;

import pooTV.commands.Actions;
import pooTV.commands.Command;

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
