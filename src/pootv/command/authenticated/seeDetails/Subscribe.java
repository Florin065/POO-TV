package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Error;
import pootv.Menu;
import pootv.command.Command;
import pootv.command.NotBannedMovies;

import java.util.ArrayList;

public class Subscribe implements Command {
    public Subscribe() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        ArrayNode output = Menu.getOutput();

        if (!Menu.getCurrPage().equals("see details")) {
            Error.doError(output);
            return;
        }

        ArrayList<MovieInput> currML = new ArrayList<>();
        NotBannedMovies.get(currML);

        UserInput user = new UserInput(Menu.getCurrUser());

        for (MovieInput movie : currML) {
            if (movie.getName().equals(Menu.getCurrMovie())) {
                for (String genre : movie.getGenres()) {
                    if (genre.equals(Menu.getActions().getActionInput().getSubscribedGenre())
                            && (!user.getSubscribedGenres().contains(genre))) {
                        user.getSubscribedGenres().add(genre);
                        Menu.setCurrUser(new UserInput(user));
                        return;
                    }
                }
            }
        }

        Error.doError(output);
    }
}
