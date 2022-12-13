package pooTV.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import pooTV.Menu;

import java.util.ArrayList;

public class BasicOutput {
    public static void doOutput() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode output = mapper.createArrayNode();

        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", Menu.getCurrML())
                .putPOJO("currentUser", Menu.getCurrUser());

        Menu.setOutput(output);
    }
}
