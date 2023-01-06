package pootv;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public final class Error {
    private Error() {
    }

    /**
     * Error output.
     */
    public static void doError() {
        ObjectMapper mapper = new ObjectMapper();
        Menu.getOutput().add(mapper.valueToTree(
                new CommandOutput("Error", new ArrayList<>(), null)));
    }
}
