package pootv.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.MovieInput;
import pootv.Error;
import pootv.Menu;
import pootv.command.NotBannedMovies;

import java.util.ArrayList;

public class Back {
    public Back() {
    }

    public static void goBack(final ArrayNode output) {
        if (Menu.getCurrPage().equals("homepage unauth")
                || Menu.getCurrPage().equals("homepage auth")
                || Menu.getCurrUser().getCredentials().getName() == null) {
            Error.doError(output);
            return;
        }

        Menu.setCurrPage(Menu.getLastPage());
        ArrayList<MovieInput> currML = new ArrayList<>();
        NotBannedMovies.get(currML);

        if (Menu.getCurrPage().equals("movies")) {
            output.addObject().put("error", (String) null)
                    .putPOJO("currentMoviesList", currML)
                    .putPOJO("currentUser", Menu.getCurrUser());
        } else if (Menu.getCurrPage().equals("see details")) {
            output.addObject().put("error", (String) null)
                    .putPOJO("currentMoviesList", currML)
                    .putPOJO("currentUser", Menu.getCurrUser());
        }
    }
}
