package pootv.command.authenticated.upgrades;

import pootv.Menu;
import pootv.command.Command;

import static pootv.Error.doError;

public class BuyPA implements Command {
    public BuyPA() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (Menu.getCurrUser().getTokensCount() < 2 + 2 + 2 + 2 + 2
                || (!Menu.getCurrPage().equals("upgrades"))) {
            doError();
            return;
        }
        Menu.getCurrUser().setTokensCount(
                Menu.getCurrUser().getTokensCount() - (2 + 2 + 2 + 2 + 2));
        Menu.getCurrUser().getCredentials().setAccountType("premium");
    }
}
