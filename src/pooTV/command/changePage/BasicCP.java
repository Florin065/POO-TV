package pooTV.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MovieInput;
import pooTV.Menu;
import pooTV.command.Error;
import pooTV.command.authenticated.logout.Logout;

import java.util.ArrayList;
import java.util.Map;

public class BasicCP {
    public static void doChangePage(ActionsInput actionsInput, ArrayNode output) {
        ArrayList<MovieInput> cornerCase = new ArrayList<>();

        if (actionsInput.getPage().equals("see details")) {
            for (MovieInput iterator : Menu.getInput().getMovies()) {
                if (iterator.getName().equals(actionsInput.getMovie())) {
                    cornerCase.add(iterator);
                }
            }

            if (cornerCase.equals(new ArrayList<>())) {
                Error.doError(output);
                return;
            }
        }

        String copy = Menu.getCurrPage();

        for (Map.Entry<String, ArrayList<String>> page : Menu.getPageList().entrySet()) {
            if (page.getKey().equals(Menu.getCurrPage())) {
                for (String pageToGo : page.getValue()) {
                    if (pageToGo.equals(actionsInput.getPage())) {
                        Menu.setCurrPage(pageToGo);
                        break;
                    }
                }

//                de pus conditie de iesire
            }
        }

        if (copy.equals(Menu.getCurrPage()) ) {
            Error.doError(output);
            return;
        }

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            ArrayList<MovieInput> currML = new ArrayList<>();

            for (MovieInput iterator : Menu.getInput().getMovies()) {
                if (!iterator.getCountriesBanned().contains(
                        Menu.getActions().getCurrUser().getCredentials().getCountry())) {
                    currML.add(iterator);
                }
            }

            switch (Menu.getCurrPage()) {
                case "logout" -> {
                    Logout.logout();
                }
                case "movies" -> {
                    MoviesCP.changePageToMovies(currML, Menu.getCurrUser(), output);
                }
                case "see details" -> {
                    FindMovieByName.findMovie(Menu.getCurrUser(), currML,
                            actionsInput.getMovie(), output);
                }
            }
        }
    }
}
