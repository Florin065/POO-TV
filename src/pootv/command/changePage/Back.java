package pootv.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.MovieInput;
import pootv.Error;
import pootv.Menu;
import pootv.command.NotBannedMovies;
import pootv.command.authenticated.seeDetails.CommandOutput;

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

        String page1 = Menu.getCurrPage();
        String page2 = Menu.getLastPage();

        if (page1.equals(page2)) {
            Menu.setLastPage("homepage auth");
        }

        Menu.setCurrPage(Menu.getLastPage());
        ArrayList<MovieInput> currML = new ArrayList<>();
        NotBannedMovies.get(currML);

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        for (MovieInput movie : currML) {
            if (movie.getName().equals(Menu.getMovieDetailsName())) {
                movieOutput.add(movie);
            }
        }

        if (Menu.getCurrPage().equals("movies")) {
            ObjectMapper mapper = new ObjectMapper();
            output.add(mapper.valueToTree(new CommandOutput(null, currML, Menu.getCurrUser())));
        } else if (Menu.getCurrPage().equals("see details")) {
            ObjectMapper mapper = new ObjectMapper();
            output.add(mapper.valueToTree(new CommandOutput(null, movieOutput, Menu.getCurrUser())));
        }
    }
}