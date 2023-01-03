package pootv.command.unauthenticated.register;

import fileio.Credentials;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.unauthenticated.UnauthOutput;
import pootv.command.Command;
import pootv.Error;

import java.util.ArrayList;
import java.util.List;

public class Register implements Command {
    public Register() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("register")) {
            Error.doError(Menu.getOutput());
            return;
        }

        Credentials credentials = Menu.getActions().getActionInput().getCredentials();

        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())) {
                Error.doError(Menu.getOutput());
                return;
            }
        }

        Menu.getCurrUser().setCredentials(credentials);
        List<UserInput> list = new ArrayList<>(DataBase.getDataBase().getUsers());
        list.add(Menu.getCurrUser());

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            Menu.setCurrPage("homepage auth");
            DataBase.getDataBase().setUsers(new ArrayList<>(list));
            Menu.setUserIndex(DataBase.getDataBase().getUsers().size() - 1);
            UnauthOutput.doOutput(Menu.getOutput());
        } else {
            Menu.setCurrPage("homepage unauth");
        }
    }
}
