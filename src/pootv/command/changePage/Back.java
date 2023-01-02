package pootv.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pootv.Error;
import pootv.Menu;

public class Back {
    public Back() {
    }

    public static void goBack(final ArrayNode output) {
        if (Menu.getCurrPage().equals("homepage unauth")
                || Menu.getCurrPage().equals("homepage auth")) {
            Error.doError(output);
            return;
        }

        Menu.setCurrPage(Menu.getLastPage());

        if (Menu.getCurrPage().equals("homepage auth")
                || Menu.getCurrPage().equals("movies")
                || Menu.getCurrPage().equals("see details")) {
            ObjectNode outputCopy = (ObjectNode) output.get(output.size() - 1);
            output.add(outputCopy);
        }
    }
}
