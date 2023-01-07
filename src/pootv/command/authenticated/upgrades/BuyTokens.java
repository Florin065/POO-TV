package pootv.command.authenticated.upgrades;

import pootv.Menu;
import pootv.command.Command;

import static pootv.Error.doError;

public class BuyTokens implements Command {
    public BuyTokens() {
    }

    /**
     * Any operation on the platform is done in tokens. The equivalence relationship between
     * balance and tokens is 1 to 1. Basically, when tokens are bought, 1 balance = 1 token.
     * Any operation on the platform is done in tokens.
     */
    @Override
    public void execute() {
        if (Integer.parseInt(Menu.getCurrUser().getCredentials().getBalance())
                < Integer.parseInt(Menu.getActions().getActionInput().getCount())
                || (!Menu.getCurrPage().equals("upgrades"))) {
            doError();
            return;
        }
        Menu.getCurrUser().getCredentials().setBalance(String.valueOf(
                Integer.parseInt(Menu.getCurrUser().getCredentials().getBalance())
                        - Integer.parseInt(Menu.getActions().getActionInput().getCount())));
        Menu.getCurrUser().setTokensCount(Menu.getCurrUser().getTokensCount()
                + Integer.parseInt(Menu.getActions().getActionInput().getCount()));
    }
}
