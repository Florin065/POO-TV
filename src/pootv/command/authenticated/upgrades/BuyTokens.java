package pootv.command.authenticated.upgrades;

import fileio.UserInput;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

public class BuyTokens implements Command {
    public BuyTokens() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        UserInput user = new UserInput(Menu.getCurrUser());

        if (Integer.parseInt(user.getCredentials().getBalance())
                < Integer.parseInt(Menu.getActions().getActionInput().getCount())
                || (!Menu.getCurrPage().equals("upgrades"))) {
            Error.doError(Menu.getOutput());
            return;
        }

        user.getCredentials().setBalance(String.valueOf(
                Integer.parseInt(user.getCredentials().getBalance())
                        - Integer.parseInt(Menu.getActions().getActionInput().getCount())));
        user.setTokensCount(Integer.parseInt(
                user.getTokensCount() + Menu.getActions().getActionInput().getCount()));

        Menu.setCurrUser(new UserInput(user));
    }
}
