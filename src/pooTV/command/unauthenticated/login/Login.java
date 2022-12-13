package pooTV.command.unauthenticated.login;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.DataBase;
import pooTV.command.Error;

public class Login implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private ArrayNode output;

    public Login(Actions actions, ArrayNode output) {
        this.actions = actions;
        this.credentials = actions.getActionInput().getCredentials();
        this.output = output;
    }

    @Override
    public void execute() {
        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())
                    && iterator.getCredentials().getPassword().equals(credentials.getPassword())) {
                actions.getCurrUser().setCredentials(iterator.getCredentials());
                break;
            }
        }

        if (actions.getCurrUser().getCredentials().getName() == null) {
            Error.doError(output);
        }
    }
}
