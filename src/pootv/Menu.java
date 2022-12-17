package pootv;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Actions;
import pootv.command.authenticated.movies.FilterOutput;
import pootv.command.authenticated.movies.Filter;
import pootv.command.authenticated.movies.Search;
import pootv.command.authenticated.movies.filterstrategy.ContainsFilter;
import pootv.command.authenticated.movies.filterstrategy.SortFilter;
import pootv.command.authenticated.seedetails.Like;
import pootv.command.authenticated.seedetails.Purchase;
import pootv.command.authenticated.seedetails.Rate;
import pootv.command.authenticated.seedetails.Watch;
import pootv.command.authenticated.upgrades.BuyPA;
import pootv.command.authenticated.upgrades.BuyTokens;
import pootv.command.changepage.ChangePage;
import pootv.command.unauthenticated.login.Login;
import pootv.command.unauthenticated.register.Register;

import java.util.ArrayList;

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

    public Menu(final Input input, final ArrayNode output) {
        Menu.input = input;
        Menu.output = output;
        currUser = new UserInput(new Credentials(), 0, 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        movieDetailsName = null;
        lastAction = null;
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
                    Menu.setLastAction(actions.getActionInput().getFeature());

                    switch (actionInput.getFeature()) {
                        case "login" -> {
                            Login login = new Login();
                            actions.doAction(login);
                        }
                        case "register" -> {
                            Register register = new Register();
                            actions.doAction(register);
                        }
                        case "search" -> {
                            Search search = new Search();
                            actions.doAction(search);
                        }
                        case "filter" -> {
                            if (!Menu.getCurrPage().equals("movies")) {
                                Error.doError(Menu.getOutput());
                                break;
                            }

                            Filter filter = new Filter(new ContainsFilter());
                            actions.doAction(filter);
                            filter = new Filter(new SortFilter());
                            actions.doAction(filter);

                            FilterOutput.doOutput(Menu.getOutput(), actions);
                        }
                        case "purchase" -> {
                            Purchase purchase = new Purchase();
                            actions.doAction(purchase);
                        }
                        case "watch" -> {
                            Watch watch = new Watch();
                            actions.doAction(watch);
                        }
                        case "like" -> {
                            Like like = new Like();
                            actions.doAction(like);
                        }
                        case "rate" -> {
                            Rate rate = new Rate();
                            actions.doAction(rate);
                        }
                        case "buy premium account" -> {
                            BuyPA buyPA = new BuyPA();
                            Menu.getActions().doAction(buyPA);
                        }
                        case "buy tokens" -> {
                            BuyTokens buyTokens = new BuyTokens();
                            actions.doAction(buyTokens);
                        }
                        default -> {
                            return;
                        }
                    }
                }
                default -> {
                    return;
                }
            }
        }
    }
}
