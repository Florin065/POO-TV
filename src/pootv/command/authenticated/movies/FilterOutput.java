package pootv.command.authenticated.movies;

import com.fasterxml.jackson.databind.node.ArrayNode;
import pootv.Menu;
import pootv.command.Actions;

public final class FilterOutput {
    private FilterOutput() {
    }

    /**
     *
     * @param output
     * @param actions
     */
    public static void doOutput(final ArrayNode output, final Actions actions) {
        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", actions.getFilterML())
                .putPOJO("currentUser", Menu.getCurrUser());
    }
}
