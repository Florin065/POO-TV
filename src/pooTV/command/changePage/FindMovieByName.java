package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pooTV.command.Error;

import java.util.ArrayList;

public class FindMovieByName {
    public static void findMovie(UserInput currUser,
                                 ArrayList<MovieInput> currML, String movieName) {
        for (MovieInput iterator : currML) {
            if (iterator.getName().equals(movieName)) {
                SeeDetailsCP.movieDetails(iterator, currUser);
            } else {
                Error.doError();
            }
        }
    }
}