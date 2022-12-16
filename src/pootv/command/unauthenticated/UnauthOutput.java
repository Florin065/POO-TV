package pootv.command.unauthenticated;

import com.fasterxml.jackson.databind.node.ArrayNode;
import pootv.Menu;

import java.util.ArrayList;

public final class UnauthOutput {
    private UnauthOutput() {
    }

    /**
     *
     * @param output
     */
    public static void doOutput(final ArrayNode output) {
        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", new ArrayList<>())
                .putPOJO("currentUser", Menu.getCurrUser());
    }
}
