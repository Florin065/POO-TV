package pootv.command.unauthenticated.login;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.DataBase;
import pootv.command.Error;

public class Login implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private ArrayNode output;

    public Login(final Actions actions, final ArrayNode output) {
        this.actions = actions;
        this.credentials = actions.getActionInput().getCredentials();
        this.output = output;
    }

    /**
     *
     */
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
