package pootv.command.authenticated.upgrades;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Actions;
import pootv.command.Command;
import pootv.command.Error;

public class BuyPA implements Command {
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput user;

    public BuyPA(final Actions actions, final ArrayNode output, final UserInput newCurrUser) {
        this.actions = actions;
        this.output = output;
        user = newCurrUser;
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (user.getTokensCount() < 2 + 2 + 2 + 2 + 2) {
            Error.doError(output);
            return;
        }

        user.setTokensCount(user.getTokensCount() - (2 + 2 + 2 + 2 + 2));
        user.getCredentials().setAccountType("premium");
    }
}
