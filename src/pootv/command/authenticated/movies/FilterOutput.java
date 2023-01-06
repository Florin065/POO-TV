package pootv.command.authenticated.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import pootv.Menu;
import pootv.CommandOutput;

public final class FilterOutput {
    private FilterOutput() {
    }

    /**
     * Generate output for Filter action.
     */
    public static void doOutput() {
        ObjectMapper mapper = new ObjectMapper();
        Menu.getOutput().add(mapper.valueToTree(
                new CommandOutput(null, Menu.getActions().getFilter(), Menu.getCurrUser())));
    }
}
