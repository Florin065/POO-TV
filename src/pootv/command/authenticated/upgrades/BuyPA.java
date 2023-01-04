package pootv.command.authenticated.upgrades;

import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;
import java.util.List;

public class BuyPA implements Command {
    public BuyPA() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        UserInput user = new UserInput(Menu.getCurrUser());
        List<UserInput> userList = new ArrayList<>(DataBase.getDataBase().getUsers());
        int indexUser = 0;
        for (UserInput userInput : userList) {
            if (user.getCredentials().getName().equals(userInput.getCredentials().getName())) {
                indexUser = userList.indexOf(userInput);
            }
        }

        if (user.getTokensCount() < 2 + 2 + 2 + 2 + 2
                || (!Menu.getCurrPage().equals("upgrades"))) {
            Error.doError(Menu.getOutput());
            return;
        }

        user.setTokensCount(user.getTokensCount() - (2 + 2 + 2 + 2 + 2));
        user.getCredentials().setAccountType("premium");

        Menu.setCurrUser(new UserInput(user));
        userList.set(indexUser, user);
        DataBase.getDataBase().setUsers(new ArrayList<>(userList));
    }
}
