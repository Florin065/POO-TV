package pooTV.command.authenticated.upgrades;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.Menu;
import pooTV.command.Actions;
import pooTV.command.Command;
import pooTV.command.Error;

public class BuyPA implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput user;

    public BuyPA(Actions actions, ArrayNode output, UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        user = newCurrUser;
    }

    @Override
    public void execute() {
        if (user.getTokensCount() < 10) {
            Error.doError(output);
            return;
        }

        user.setTokensCount(user.getTokensCount() - 10);
        user.getCredentials().setAccountType("premium");
    }
}
