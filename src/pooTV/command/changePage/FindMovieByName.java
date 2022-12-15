package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pooTV.Menu;
import pooTV.command.Error;

import java.util.ArrayList;

public class FindMovieByName {
    public static void findMovie(UserInput currUser, ArrayList<MovieInput> currML,
                                 String movieName, ArrayNode output) {
        for (MovieInput iterator : Menu.getInput().getMovies()) {
            for (String ban : iterator.getCountriesBanned()) {
                if (currUser.getCredentials().getCountry().equals(ban)
                        && movieName.equals(iterator.getName())) {
                    Error.doError(output);
                    return;
                }
            }
        }

        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(movieName)) {
                SeeDetailsCP.movieDetails(iterator, currUser, output);
                return;
            }
        }

        Error.doError(output);
    }
}
