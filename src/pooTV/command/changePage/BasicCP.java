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
        if ((actionsInput.getPage().equals("login") || actionsInput.getPage().equals("register"))
                && (!Menu.getCurrPage().equals("homepage unauth"))) {
            Error.doError(output);
            return;
        }

        ArrayList<MovieInput> cornerCase = new ArrayList<>();

        if (actionsInput.getPage().equals("see details")) {
            for (MovieInput iterator : Menu.getInput().getMovies()) {
                if (iterator.getName().equals(actionsInput.getMovie())) {
                    cornerCase.add(iterator);
                    break;
                }
            }

            if (cornerCase.equals(new ArrayList<>())) {
                Error.doError(output);
                return;
            }
        }

        String copy = Menu.getCurrPage();

        for (Map.Entry<String, ArrayList<String>> page : Menu.getPageList().entrySet()) {
            String cond = null;

            if (page.getKey().equals(Menu.getCurrPage())) {
                for (String pageToGo : page.getValue()) {
                    if (pageToGo.equals(actionsInput.getPage())) {
                        Menu.setCurrPage(pageToGo);
                        cond = pageToGo;
                        break;
                    }
                }

                if (cond != null && cond.equals(Menu.getCurrPage())) {
                    break;
                }
            }
        }

        if (copy.equals(Menu.getCurrPage())) {
            if (!copy.equals("movies")) {
                Error.doError(output);
                return;
            }
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
                case "logout" -> Logout.logout();
                case "movies" -> {
                    if (!Menu.getActions().getFilterML().equals(new ArrayList<>())) {
                        MoviesCP.changePageToMovies(Menu.getActions().getFilterML(),
                                Menu.getCurrUser(), output);
                        return;
                    }

                    MoviesCP.changePageToMovies(currML, Menu.getCurrUser(), output);
                }
                case "see details" -> {
                    if (Menu.getLastAction().equals("filter")) {
                        FindMovieByName.findMovie(Menu.getCurrUser(),
                                Menu.getActions().getFilterML(), actionsInput.getMovie(), output, copy);
                        return;
                    }

                    FindMovieByName.findMovie(Menu.getCurrUser(), currML,
                            actionsInput.getMovie(), output, copy);
                }
            }
        }
    }
}
