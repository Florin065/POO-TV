package pootv.command.changePage;

import fileio.ActionsInput;
import fileio.MovieInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.command.authenticated.logout.Logout;

import java.util.ArrayList;
import java.util.Map;

import static pootv.Error.doError;
import static pootv.Page.createPageHierarchy;
import static pootv.command.NotBannedMovies.notBannedMovies;
import static pootv.command.changePage.ChangePageToMovies.changePageToMovies;
import static pootv.command.changePage.ChangePageToSeeDetails.findMovie;

public final class ChangePage {
    private ChangePage() {
    }

    /**
     *
     * @param actionsInput
     */
    public static void doChangePage(final ActionsInput actionsInput) {
        if ((actionsInput.getPage().equals("login") || actionsInput.getPage().equals("register"))
                && (!Menu.getCurrPage().equals("homepage unauth"))) {
            doError();
            return;
        }

        if (actionsInput.getPage().equals("see details")) {
            ArrayList<MovieInput> cornerCase = new ArrayList<>();
            for (MovieInput iterator : DataBase.getDataBase().getMovies()) {
                if (iterator.getName().equals(actionsInput.getMovie())) {
                    cornerCase.add(iterator);
                    break;
                }
            }
            if (cornerCase.isEmpty()) {
                doError();
                return;
            }
        }

        String copyCurrPage = Menu.getCurrPage();
        for (Map.Entry<String, ArrayList<String>> page : createPageHierarchy().entrySet()) {
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
        if (copyCurrPage.equals(Menu.getCurrPage())) {
            if (!copyCurrPage.equals("movies")) {
                doError();
                return;
            }
        }

        Menu.getLastPages().add(copyCurrPage);

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            ArrayList<MovieInput> currML = new ArrayList<>();
            notBannedMovies(currML);
            switch (Menu.getCurrPage()) {
                case "logout" -> Logout.logout();
                case "movies" -> {
                    if (!Menu.getActions().getFilter().isEmpty()) {
                        changePageToMovies(Menu.getActions().getFilter());
                        return;
                    }
                    changePageToMovies(currML);
                }
                case "see details" -> {
                    if (Menu.getLastAction().equals("filter")) {
                        findMovie(Menu.getActions().getFilter(),
                                actionsInput.getMovie(), copyCurrPage);
                        return;
                    }
                    findMovie(currML, actionsInput.getMovie(), copyCurrPage);
                }
                default -> {
                }
            }
        }
    }
}
