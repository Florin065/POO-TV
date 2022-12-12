package pooTV.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;

import java.util.ArrayList;

public class Actions {
    public static void error(ArrayNode output) {
        output.addObject().put("error", "Error")
                .putPOJO("currentMovieList", new ArrayList<>())
                .putPOJO("currentUser", null);
    }

    public static void changePageToMovies(ArrayNode output,
                                          ArrayList<MovieInput> currentMovieList,
                                          UserInput currentUser) {
        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", currentMovieList)
                .putPOJO("currentUser", currentUser);
    }

    public static void movieDetails(ArrayNode output,
                                    MovieInput movie,
                                    UserInput currentUser) {
        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", movie)
                .putPOJO("currentUser", currentUser);
    }

    public void register(/*ArrayNode output, UsersInput usersInput*/) {
//        output.addObject().put("type", "on page")
//                .put("page", "register")
//                .put("feature", "register")
//                .putPOJO("credentials", usersInput.toString());
    }

    public void login(/*ArrayNode output, User user*/) {
// TODO
    }

    public void search() {
// TODO
    }

    public void filter() {
//        TODO
    }

    public void buyTokens() {
//        TODO
    }

    public void buyPA() {
//        TODO
    }

    public void purchase() {
//        TODO
    }

    public void watch() {
//        TODO
    }

    public void like() {
//        TODO
    }

    public void rateMovie() {
//        TODO
    }
}
