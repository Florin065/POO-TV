package pooTV.command.unauthenticated.register;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.DataBase;
import pooTV.Menu;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.command.Error;

public class Register implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private ArrayNode output;

    public Register(Actions actions, ArrayNode output) {
        this.actions = actions;
        credentials = actions.getActionInput().getCredentials();
        this.output = output;
    }

    @Override
    public void execute() {
        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())) {
                Error.doError(output);
                return;
            }
        }

        actions.getCurrUser().setCredentials(credentials);

//        for (MovieInput iterator : Menu.getInput().getMovies()) {
//            for (String country : iterator.getCountriesBanned()) {
//                if (!country.equals(actions.getCurrUser().getCredentials().getCountry())) {
//                    actions.getCurrML().add(iterator);
//                }
//            }
//        }
    }
}
