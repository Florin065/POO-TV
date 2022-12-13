package pooTV.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import pooTV.Menu;

import java.util.ArrayList;

public class Error {
    public static void doError() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode output = mapper.createArrayNode();

        output.addObject().put("error", "Error")
                .putPOJO("currentMovieList", new ArrayList<>())
                .putPOJO("currentUser", null);

        Menu.setOutput(output);
    }
}
