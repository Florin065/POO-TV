package pooTV.unauthenticated.login;

import pooTV.Actions;
import pooTV.Command;

public class Login implements Command {
    private Actions actions;

    public Login(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
