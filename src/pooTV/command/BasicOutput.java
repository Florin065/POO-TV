package pooTV.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import pooTV.Menu;

public class BasicOutput {
    public static void doOutput(ArrayNode output) {
        output.addObject().put("error", (String) null)
                .putPOJO("currentMoviesList", Menu.getCurrML())
                .putPOJO("currentUser", Menu.getCurrUser());
    }
}
