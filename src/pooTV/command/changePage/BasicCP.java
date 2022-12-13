package pooTV.command.changePage;

import fileio.ActionsInput;
import pooTV.Menu;
import pooTV.command.Error;
import pooTV.command.authenticated.logout.Logout;

import java.util.ArrayList;
import java.util.Map;

public class BasicCP {
    public static void doChangePage(ActionsInput actionsInput) {
        for (Map.Entry<String, ArrayList<String>> page : Menu.getPageList().entrySet()) {
            if (page.getKey().equals(Menu.getCurrPage())) {
                for (String pageToGo : page.getValue()) {
                    if (pageToGo.equals(actionsInput.getPage())) {
                        Menu.setCurrPage(pageToGo);
                        break;
                    }
                }
            }

            if (!Menu.getCurrPage().equals("homepage unauth")) {
                break;
            }
        }

        if (Menu.getCurrPage() == null) {
            Error.doError();
            return;
        }

        if (Menu.getCurrUser().getCredentials().getName() != null) {
            FindCurrUserML.find(Menu.getCurrUser(), Menu.getCurrML(), Menu.getInput().getMovies());

            switch (Menu.getCurrPage()) {
                case "logout" -> Logout.logout();
                case "movies" -> MoviesCP.changePageToMovies(
                        Menu.getCurrML(), Menu.getCurrUser());
                case "see details" ->
                        FindMovieByName.findMovie(Menu.getCurrUser(),
                                Menu.getCurrML(), actionsInput.getMovie());
            }
        }
    }
}
