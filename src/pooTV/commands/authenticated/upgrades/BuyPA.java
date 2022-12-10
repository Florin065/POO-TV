package pooTV.commands.authenticated.upgrades;

import pooTV.commands.Actions;
import pooTV.commands.Command;

public class BuyPA implements Command {
    private Actions actions;

    public BuyPA(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
