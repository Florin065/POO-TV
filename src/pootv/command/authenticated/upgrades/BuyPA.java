package pootv.command.authenticated.upgrades;

import pootv.Menu;
import pootv.command.Command;

import static pootv.Error.doError;

public class BuyPA implements Command {
    public BuyPA() {
    }

    /**
     * If the user wants to upgrade his account to premium, he will pay 10 tokens for the upgrade.
     */
    @Override
    public void execute() {
        if (Menu.getCurrUser().getTokensCount() < 2 + 2 + 2 + 2 + 2
                || (!Menu.getCurrPage().equals("upgrades"))
                || Menu.getCurrUser().getCredentials().getAccountType().equals("premium")) {
            doError();
            return;
        }
        Menu.getCurrUser().setTokensCount(
                Menu.getCurrUser().getTokensCount() - (2 + 2 + 2 + 2 + 2));
        Menu.getCurrUser().getCredentials().setAccountType("premium");
    }
}
