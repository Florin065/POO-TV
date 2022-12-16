package pootv.command.authenticated.upgrades;

import fileio.UserInput;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

public class BuyPA implements Command {
    public BuyPA() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        UserInput user = new UserInput(Menu.getCurrUser());

        if (user.getTokensCount() < 2 + 2 + 2 + 2 + 2
                || (!Menu.getCurrPage().equals("upgrades"))) {
            Error.doError(Menu.getOutput());
            return;
        }

        user.setTokensCount(user.getTokensCount() - (2 + 2 + 2 + 2 + 2));
        user.getCredentials().setAccountType("premium");

        Menu.setCurrUser(new UserInput(user));
    }
}
