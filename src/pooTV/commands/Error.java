package pooTV.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class Error implements Command {
    private Actions actions;
    private ArrayNode output;

    public Error(Actions actions, ArrayNode output) {
        this.actions = actions;
        this.output = output;
    }

    @Override
    public void execute() {
        actions.error(output);
    }
}
