package pooTV.commands.unauthenticated.register;

import pooTV.commands.Actions;
import pooTV.commands.Command;

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
