package pootv.command.authenticated.upgrades;

import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;
import java.util.List;

public class BuyTokens implements Command {
    public BuyTokens() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        UserInput user = new UserInput(Menu.getCurrUser());
        List<UserInput> userList =  new ArrayList<>(DataBase.getDataBase().getUsers());
        int indexUser = 0;
        for (UserInput userInput : userList) {
            if (user.getCredentials().getName().equals(userInput.getCredentials().getName())) {
                indexUser = userList.indexOf(userInput);
            }
        }

        if (Integer.parseInt(user.getCredentials().getBalance())
                < Integer.parseInt(Menu.getActions().getActionInput().getCount())
                || (!Menu.getCurrPage().equals("upgrades"))) {
            Error.doError(Menu.getOutput());
            return;
        }

        user.getCredentials().setBalance(String.valueOf(
                Integer.parseInt(user.getCredentials().getBalance())
                        - Integer.parseInt(Menu.getActions().getActionInput().getCount())));
        user.setTokensCount(user.getTokensCount()
                + Integer.parseInt(Menu.getActions().getActionInput().getCount()));

        Menu.setCurrUser(new UserInput(user));
        userList.set(indexUser, user);
        DataBase.getDataBase().setUsers(new ArrayList<>(userList));
    }
}
