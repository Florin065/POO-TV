package pooTV;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import pooTV.command.Actions;
import pooTV.command.BasicOutput;
import pooTV.command.Error;
import pooTV.command.authenticated.movies.Filter;
import pooTV.command.authenticated.movies.Search;
import pooTV.command.authenticated.seeDetails.Like;
import pooTV.command.authenticated.seeDetails.Purchase;
import pooTV.command.authenticated.seeDetails.RateTheMovie;
import pooTV.command.authenticated.seeDetails.Watch;
import pooTV.command.authenticated.upgrades.BuyPA;
import pooTV.command.authenticated.upgrades.BuyTokens;
import pooTV.command.changePage.BasicCP;
import pooTV.command.unauthenticated.login.Login;
import pooTV.command.unauthenticated.register.Register;

import java.util.ArrayList;
import java.util.Map;

import static pooTV.Page.createPageHierarchy;

public class Menu {
    @Getter @Setter
    private static ArrayNode output;
    @Getter @Setter
    private static Input input;
    @Getter @Setter
    private static Actions actions;
    @Getter @Setter
    private static Map<String, ArrayList<String>> pageList;
    @Getter @Setter
    private static String currPage;
    @Getter @Setter
    private static UserInput currUser;
    @Getter @Setter
    private static ArrayList<MovieInput> currML;
    @Getter @Setter
    private Credentials nullCred;

    public Menu(Input input, ArrayNode output) {
        Menu.input = input;
        Menu.output = output;
        nullCred = new Credentials(null, null, null, null, 0);
        currUser = new UserInput(nullCred, 0, 15, null, null, null, null);
        currPage = "homepage unauth";
        currML = new ArrayList<>();
        pageList = createPageHierarchy();
        ArrayList<UserInput> usersList = new ArrayList<>(input.getUsers());
        ArrayList<MovieInput> moviesList = new ArrayList<>(input.getMovies());
        DataBase.getDataBase().setUsers(usersList);
        DataBase.getDataBase().setMovies(moviesList);
    }

    public void actionsPOOTV() {

        DataBase dataBase = DataBase.getDataBase();

        for (ActionsInput actionInput : input.getActions()) {
            System.out.println(output);
            System.out.println(actionInput);
            switch (actionInput.getType()) {
                case "change page" -> BasicCP.doChangePage(actionInput);
                case "on page" -> {
                    actions = new Actions(currPage, currUser, currML, actionInput);

                    switch (actionInput.getFeature()) {
                        case "login" -> {
                            if (!currPage.equals("login")) {
                                Error.doError();
                                break;
                            }

                            Login login = new Login(actions);
                            actions.doAction(login);

                            if (currUser.getCredentials().getName() != null) {
                                currPage = "homepage auth";
                                BasicOutput.doOutput();
                                return;
                            } else {
                                currPage = "homepage unauth";
                            }
                        }
                        case "register" -> {
                            if (!currPage.equals("register")) {
                                Error.doError();
                                break;
                            }

                            Register register = new Register(actions);
                            actions.doAction(register);

                            currUser.setCredentials(actions.getCurrUser().getCredentials());

                            if (currUser.getCredentials().getName() != null) {
                                currPage = "homepage auth";
                                BasicOutput.doOutput();
                                break;
                            } else {
                                currPage = "homepage unauth";
                            }
                        }
                        case "search" -> {
                            Search search = new Search();
                            actions.doAction(search);
                        }
                        case "filter" -> {
                            Filter filter = new Filter();
                            actions.doAction(filter);
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
                        case "rate the movie" -> {
                            RateTheMovie rateTheMovie = new RateTheMovie();
                            actions.doAction(rateTheMovie);
                        }
                        case "buy premium account" -> {
                            BuyPA buyPA = new BuyPA();
                            actions.doAction(buyPA);
                        }
                        case "buy tokens" -> {
                            BuyTokens buyTokens = new BuyTokens();
                            actions.doAction(buyTokens);
                        }
                    }
                }
            }
        }
    }
}
