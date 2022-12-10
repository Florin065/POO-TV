package pooTV.commands.authenticated.upgrades;

import pooTV.commands.Actions;
import pooTV.commands.Command;

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
