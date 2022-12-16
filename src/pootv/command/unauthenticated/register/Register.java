package pootv.command.unauthenticated.register;

import fileio.Credentials;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.unauthenticated.UnauthOutput;
import pootv.command.Command;
import pootv.Error;

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

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            Menu.setCurrPage("homepage auth");
            UnauthOutput.doOutput(Menu.getOutput());
        } else {
            Menu.setCurrPage("homepage unauth");
        }
    }
}
