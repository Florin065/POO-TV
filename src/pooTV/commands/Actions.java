package pooTV.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;
import pooTV.DataBase;

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

    public void register(UserInput usersInput) {
//        output.addObject().put("type", "on page")
//                .put("page", "register")
//                .put("feature", "register")
//                .putPOJO("credentials", usersInput.toString());
    }

    public void login(ArrayNode output, DataBase dataBase, Input input,
                      UserInput currentUser, ArrayList<MovieInput> currentMovieList,
                      Credentials credentials) {

        for (UserInput iterator : dataBase.getUsers()) {
            if (iterator.getCredentials().getName().equals(credentials.getName())
                    && iterator.getCredentials().getPassword().equals(credentials.getPassword())) {
                currentUser = iterator;
                break;
            }
        }

        if (currentUser == null) {
            error(output);
            return;
        }

        for (MovieInput iterator : input.getMovies()) {
            for (String country : iterator.getCountriesBanned()) {
                if (!country.equals(currentUser.getCredentials().getCountry())) {
                    currentMovieList.add(iterator);
                }
            }
        }
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
