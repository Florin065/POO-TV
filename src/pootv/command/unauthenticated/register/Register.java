package pootv.command.unauthenticated.register;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pootv.DataBase;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.command.Error;

public class Register implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private ArrayNode output;

    public Register(final Actions actions, final ArrayNode output) {
        this.actions = actions;
        credentials = actions.getActionInput().getCredentials();
        this.output = output;
    }

    /**
     *
     */
    @Override
    public void execute() {
        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())) {
                Error.doError(output);
                return;
            }
        }

        actions.getCurrUser().setCredentials(credentials);
    }
}
