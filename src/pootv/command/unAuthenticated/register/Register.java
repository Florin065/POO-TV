package pootv.command.unAuthenticated.register;

import fileio.Credentials;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.Command;

import static pootv.Error.doError;
import static pootv.command.unAuthenticated.UnauthOutput.doOutput;

public class Register implements Command {
    public Register() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("register")) {
            doError();
            return;
        }

        Credentials credentials = Menu.getActions().getActionInput().getCredentials();
        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())) {
                doError();
                return;
            }
        }

        Menu.getCurrUser().setCredentials(credentials);
        DataBase.getDataBase().getUsers().add(Menu.getCurrUser());

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            Menu.setCurrPage("homepage auth");
            Menu.setUserIndex(DataBase.getDataBase().getUsers().size() - 1);
            doOutput();
        } else {
            Menu.setCurrPage("homepage unauth");
        }
    }
}
