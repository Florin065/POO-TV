package pooTV.command.authenticated.upgrades;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.Menu;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.command.Error;

public class BuyTokens implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private UserInput user;
    @Getter @Setter
    private ArrayNode output;

    public BuyTokens(Actions actions, ArrayNode output, UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        user = newCurrUser;
    }

    @Override
    public void execute() {
        if (Integer.parseInt(user.getCredentials().getBalance())
                < Integer.parseInt(actions.getActionInput().getCount())) {
            Error.doError(output);
            return;
        }

        user.getCredentials().setBalance(String.valueOf(
                Integer.parseInt(user.getCredentials().getBalance())
                        - Integer.parseInt(actions.getActionInput().getCount())));
        user.setTokensCount(Integer.parseInt(
                user.getTokensCount() + actions.getActionInput().getCount()));
    }
}
