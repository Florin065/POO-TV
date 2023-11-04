package pootv;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Actions;
import pootv.command.authenticated.movies.Filter;
import pootv.command.authenticated.movies.Search;
import pootv.command.authenticated.movies.filterStrategy.ContainsFilter;
import pootv.command.authenticated.movies.filterStrategy.SortFilter;
import pootv.command.authenticated.seeDetails.*;
import pootv.command.authenticated.upgrades.BuyPA;
import pootv.command.authenticated.upgrades.BuyTokens;
import pootv.command.database.Add;
import pootv.command.database.Delete;
import pootv.command.unAuthenticated.login.Login;
import pootv.command.unAuthenticated.register.Register;

import java.util.ArrayList;
import java.util.HashMap;

import static pootv.Error.doError;
import static pootv.command.authenticated.movies.FilterOutput.doOutput;
import static pootv.command.changePage.Back.goBack;
import static pootv.command.changePage.ChangePage.doChangePage;

public class Menu {
                    private  final Input input;
    @Getter         private static ArrayNode output;
    @Getter         private static Actions actions;
    @Getter @Setter private static String currPage;
    @Getter @Setter private static UserInput currUser;
    @Getter @Setter private static String movieDetailsName;
    @Getter         private static String lastAction;
    @Getter @Setter private static ArrayList<String> lastPages;
    @Getter @Setter private static Integer userIndex;

    public Menu(final Input input, final ArrayNode output) {
        this.input = input;
        Menu.output = output;
        currUser = new UserInput(new Credentials(), 0, 15,
                                 new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                                 new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        movieDetailsName = null;
        lastAction = null;
        lastPages = new ArrayList<>();
        currPage = "homepage unauth";
        DataBase.getDataBase().setUsers(new ArrayList<>(this.input.getUsers()));
        DataBase.getDataBase().setMovies(new ArrayList<>(this.input.getMovies()));
    }

    /**
     * Action caller. Firstly it finds action type, and if it has a feature, then it calls
     * that command
     */
    public void actionsPooTv() {
        for (ActionsInput actionInput : this.input.getActions()) {
            switch (actionInput.getType()) {
                case "change page" -> doChangePage(actionInput);
                case "on page" -> {
                    actions = new Actions(actionInput);
                    lastAction = actions.getActionInput().getFeature();

                    switch (actionInput.getFeature()) {
                        case "login" -> actions.doAction(new Login());
                        case "register" -> actions.doAction(new Register());
                        case "search" -> actions.doAction(new Search());
                        case "filter" -> {
                            if (!currPage.equals("movies")) {
                                doError();
                                break;
                            }
                            actions.doAction(new Filter(new ContainsFilter()));
                            actions.doAction(new Filter(new SortFilter()));
                            doOutput();
                        }
                        case "purchase" -> actions.doAction(new Purchase());
                        case "watch" -> actions.doAction(new Watch());
                        case "like" -> actions.doAction(new Like());
                        case "rate" -> actions.doAction(new Rate());
                        case "buy premium account" -> actions.doAction(new BuyPA());
                        case "buy tokens" -> actions.doAction(new BuyTokens());
                        case "subscribe" -> actions.doAction(new Subscribe());
                        default -> {
                            return;
                        }
                    }
                }
                case "database" -> {
                    actions = new Actions(actionInput);
                    switch (actionInput.getFeature()) {
                        case "add" -> actions.doAction(new Add());
                        case "delete" -> actions.doAction(new Delete());
                        default -> {
                            return;
                        }
                    }
                }
                case "back" -> goBack();
                default -> {
                    return;
                }
            }
        }
    }
}
