package pooTV.authenticated.upgrades;

import pooTV.Actions;
import pooTV.Command;

public class BuyTokens implements Command {
    private Actions actions;

    public BuyTokens(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.login();
    }
}
