package pooTV.command;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class Error {
    public static void doError(ArrayNode output) {
        output.addObject().put("error", "Error")
                .putPOJO("currentMoviesList", new ArrayList<>())
                .putPOJO("currentUser", null);
    }
}
