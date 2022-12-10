package pooTV.commands.authenticated.seeDetails;

import pooTV.commands.Actions;
import pooTV.commands.Command;

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
