package pootv.command;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public final class Error {
    private Error() {
    }

    /**
     *
     * @param output
     */
    public static void doError(final ArrayNode output) {
        output.addObject().put("error", "Error")
                .putPOJO("currentMoviesList", new ArrayList<>())
                .putPOJO("currentUser", null);
    }
}
