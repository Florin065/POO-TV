package pootv.command.changePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MovieInput;
import pootv.DataBase;
import pootv.Menu;
import pootv.Error;
import pootv.command.NotBannedMovies;
import pootv.command.authenticated.logout.Logout;

import java.util.ArrayList;
import java.util.Map;

import static pootv.Page.createPageHierarchy;

public final class ChangePage {
    private ChangePage() {
    }

    /**
     *
     * @param actionsInput
     * @param output
     */
    public static void doChangePage(final ActionsInput actionsInput, final ArrayNode output) {
        if ((actionsInput.getPage().equals("login") || actionsInput.getPage().equals("register"))
                && (!Menu.getCurrPage().equals("homepage unauth"))) {
            Error.doError(output);
            return;
        }

        ArrayList<MovieInput> cornerCase = new ArrayList<>();

        if (actionsInput.getPage().equals("see details")) {
            for (MovieInput iterator : DataBase.getDataBase().getMovies()) {
                if (iterator.getName().equals(actionsInput.getMovie())) {
                    cornerCase.add(iterator);
                    break;
                }
            }

            if (cornerCase.isEmpty()) {
                Error.doError(output);
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
                Error.doError(output);
                return;
            }
        }

        Menu.getLastPages().add(copyCurrPage);

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            ArrayList<MovieInput> currML = new ArrayList<>();
            NotBannedMovies.get(currML);

            switch (Menu.getCurrPage()) {
                case "logout" -> Logout.logout();
                case "movies" -> {
                    if (!Menu.getActions().getFilter().isEmpty()) {
                        ChangePageToMovies.changePageToMovies(Menu.getActions().getFilter(),
                                Menu.getCurrUser(), output);
                        return;
                    }

                    ChangePageToMovies.changePageToMovies(currML, Menu.getCurrUser(), output);
                }
                case "see details" -> {
                    Menu.setCurrMovie(actionsInput.getMovie());

                    if (Menu.getLastAction().equals("filter")) {
                        ChangePageToSeeDetails.findMovie(Menu.getCurrUser(),
                                Menu.getActions().getFilter(),
                                actionsInput.getMovie(), output, copyCurrPage);
                        return;
                    }

                    ChangePageToSeeDetails.findMovie(Menu.getCurrUser(), currML,
                            actionsInput.getMovie(), output, copyCurrPage);
                }
                default -> {
                }
            }
        }
    }
}
