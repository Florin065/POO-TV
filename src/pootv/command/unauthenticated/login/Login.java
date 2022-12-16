package pootv.command.unauthenticated.login;

import fileio.Credentials;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.Actions;
import pootv.command.unauthenticated.UnauthOutput;
import pootv.command.Command;
import pootv.DataBase;
import pootv.Error;

public class Login implements Command {
    public Login() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("login")) {
            Error.doError(Menu.getOutput());
            return;
        }

        Actions actions = Menu.getActions();
        Credentials credentials = actions.getActionInput().getCredentials();

        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())
                    && iterator.getCredentials().getPassword().equals(credentials.getPassword())) {
                Menu.getCurrUser().setCredentials(iterator.getCredentials());
                break;
            }
        }

        if (Menu.getCurrUser().getCredentials().getName() == null) {
            Error.doError(Menu.getOutput());
            Menu.setCurrPage("homepage unauth");
        } else {
            Menu.setCurrPage("homepage auth");
            UnauthOutput.doOutput(Menu.getOutput());
        }
    }
}
