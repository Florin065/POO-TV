package pooTV.authenticated.upgrades;

import pooTV.Actions;
import pooTV.Command;

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
