package pooTV.commands.unauthenticated.login;

import com.fasterxml.jackson.databind.node.ArrayNode;
import pooTV.commands.Actions;
import pooTV.commands.Command;
import pooTV.DataBase;

public class Login implements Command {
    private Actions actions;

    public Login(Actions actions) {
        this.actions = actions;
    }

    public Login(ArrayNode output, DataBase dataBase) {
    }

    @Override
    public void execute() {
        actions.login();
    }
}
