package pootv.command.unAuthenticated;

import com.fasterxml.jackson.databind.ObjectMapper;
import pootv.Menu;
import pootv.CommandOutput;

import java.util.ArrayList;

public final class UnauthOutput {
    private UnauthOutput() {
    }

    /**
     * Login / Register output.
     */
    public static void doOutput() {
        ObjectMapper mapper = new ObjectMapper();
        Menu.getOutput().add(mapper.valueToTree(
                new CommandOutput(null, new ArrayList<>(), Menu.getCurrUser())));
    }
}
