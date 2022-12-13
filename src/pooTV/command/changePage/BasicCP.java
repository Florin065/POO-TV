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
        for (Map.Entry<String, ArrayList<String>> page : Menu.getPageList().entrySet()) {
            if (page.getKey().equals(Menu.getCurrPage())) {
                for (String pageToGo : page.getValue()) {
                    if (pageToGo.equals(actionsInput.getPage())) {
                        Menu.setCurrPage(pageToGo);
                        break;
                    }
                }
            }
        }

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            ArrayList<MovieInput> currML = new ArrayList<>();

            for (MovieInput iterator : Menu.getInput().getMovies()) {
                for (String bannedCountry : iterator.getCountriesBanned()) {
                    if (!Menu.getActions().getCurrUser()
                            .getCredentials().getCountry().equals(bannedCountry)) {
                        currML.add(iterator);
                    }
                }
            }

            switch (Menu.getCurrPage()) {
                case "logout" -> {
                    Logout.logout();
                    return;
                }
                case "movies" -> {
                    MoviesCP.changePageToMovies(currML, Menu.getCurrUser(), output);
                    return;
                }
                case "see details" -> {
                    FindMovieByName.findMovie(Menu.getCurrUser(), currML,
                            actionsInput.getMovie(), output);
                    return;
                }
            }

            Error.doError(output);
        }
    }
}
