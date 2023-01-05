package pootv;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Actions;
import pootv.command.authenticated.movies.FilterOutput;
import pootv.command.authenticated.movies.Filter;
import pootv.command.authenticated.movies.Search;
import pootv.command.authenticated.movies.filterStrategy.ContainsFilter;
import pootv.command.authenticated.movies.filterStrategy.SortFilter;
import pootv.command.authenticated.seeDetails.*;
import pootv.command.authenticated.upgrades.BuyPA;
import pootv.command.authenticated.upgrades.BuyTokens;
import pootv.command.changePage.Back;
import pootv.command.changePage.ChangePage;
import pootv.command.database.Add;
import pootv.command.database.Delete;
import pootv.command.unauthenticated.login.Login;
import pootv.command.unauthenticated.register.Register;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    @Getter @Setter
    private static ArrayNode output;
    @Getter @Setter
    private static Input input;
    @Getter @Setter
    private static Actions actions;
    @Getter @Setter
    private static String currPage;
    @Getter @Setter
    private static UserInput currUser;
    @Getter @Setter
    private static String movieDetailsName;
    @Getter @Setter
    private static String lastAction;
    @Getter @Setter
    private static ArrayList<String> lastPages;
    @Getter @Setter
    private static String currMovie;
    @Getter @Setter
    private static int userIndex;

    public Menu(final Input input, final ArrayNode output) {
        Menu.input = input;
        Menu.output = output;
        currUser = new UserInput(new Credentials(), 0, 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        movieDetailsName = null;
        lastAction = null;
        currMovie = null;
        lastPages = new ArrayList<>();
        currPage = "homepage unauth";
        DataBase.getDataBase().setUsers(new ArrayList<>(input.getUsers()));
        DataBase.getDataBase().setMovies(new ArrayList<>(input.getMovies()));
    }

    /**
     *
     */
    public void actionsPOOTV() {
        for (ActionsInput actionInput : input.getActions()) {
            switch (actionInput.getType()) {
                case "change page" -> ChangePage.doChangePage(actionInput, output);
                case "on page" -> {
                    actions = new Actions(actionInput);
                    setLastAction(actions.getActionInput().getFeature());
                    switch (actionInput.getFeature()) {
                        case "login" -> actions.doAction(new Login());
                        case "register" -> actions.doAction(new Register());
                        case "search" -> actions.doAction(new Search());
                        case "filter" -> {
                            if (!Menu.getCurrPage().equals("movies")) {
                                Error.doError(Menu.getOutput());
                                break;
                            }
                            actions.doAction(new Filter(new ContainsFilter()));
                            actions.doAction(new Filter(new SortFilter()));
                            FilterOutput.doOutput(Menu.getOutput(), actions);
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
                case "back" -> Back.goBack(output);
                default -> {
                    return;
                }
            }
        }
    }
}
