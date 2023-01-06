package pootv.command.authenticated.seeDetails;

import fileio.MovieInput;
import pootv.Menu;
import pootv.command.Command;

import java.util.ArrayList;

import static pootv.Error.doError;
import static pootv.command.NotBannedMovies.notBannedMovies;

public class Subscribe implements Command {
    public Subscribe() {
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (!Menu.getCurrPage().equals("see details")) {
            doError();
            return;
        }

        ArrayList<MovieInput> currML = new ArrayList<>();
        notBannedMovies(currML);

        for (MovieInput movie : currML) {
            if (movie.getName().equals(Menu.getMovieDetailsName())) {
                for (String genre : movie.getGenres()) {
                    if (genre.equals(Menu.getActions().getActionInput().getSubscribedGenre())
                            && (!Menu.getCurrUser().getSubscribedGenres().contains(genre))) {
                        Menu.getCurrUser().getSubscribedGenres().add(genre);
                        return;
                    }
                }
            }
        }
        doError();
    }
}
