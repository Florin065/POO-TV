package pooTV.command.unauthenticated.login;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.DataBase;
import pooTV.command.Error;

public class Login implements Command {
    @Getter @Setter
    private Input input;
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;

    public Login(Actions actions) {
        this.actions = actions;
        this.credentials = actions.getActionInput().getCredentials();
    }

    @Override
    public void execute() {
        for (UserInput iterator : DataBase.getDataBase().getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())
                    && iterator.getCredentials().getPassword().equals(credentials.getPassword())) {
                actions.setCurrUser(iterator);
                break;
            }
        }

        if (actions.getCurrUser().getCredentials().getName() == null) {
            Error.doError();
            return;
        }

        for (MovieInput iterator : input.getMovies()) {
            for (String country : iterator.getCountriesBanned()) {
                if (!country.equals(actions.getCurrUser().getCredentials().getCountry())) {
                    actions.getCurrML().add(iterator);
                }
            }
        }
    }
}
