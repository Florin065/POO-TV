package pootv.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import pootv.Menu;

public final class BasicOutput {
    private BasicOutput() {
    }

    /**
     *
     * @param output
     */
    public static void doOutput(final ArrayNode output) {
        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", Menu.getCurrML())
                .putPOJO("currentUser", Menu.getCurrUser());
    }
}
