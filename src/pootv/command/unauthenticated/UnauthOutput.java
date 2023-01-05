package pootv.command.unauthenticated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import pootv.Menu;
import pootv.command.authenticated.seeDetails.CommandOutput;

import java.util.ArrayList;

public final class UnauthOutput {
    private UnauthOutput() {
    }

    /**
     *
     * @param output
     */
    public static void doOutput(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        output.add(mapper.valueToTree(
                new CommandOutput(null, new ArrayList<>(), Menu.getCurrUser())));
    }
}
