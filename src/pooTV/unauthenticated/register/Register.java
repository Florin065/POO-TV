package pooTV.unauthenticated.register;

import pooTV.Actions;
import pooTV.Command;

public class Register implements Command {
    private Actions actions;

    public Register(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
