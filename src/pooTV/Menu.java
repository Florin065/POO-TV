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
import pooTV.command.authenticated.movies.filterStrategy.ContainsFilter;
import pooTV.command.authenticated.movies.filterStrategy.SortFilter;
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
    private ArrayNode output;
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
        this.output = output;
        nullCred = new Credentials(null, null, null, null, "0");
        currUser = new UserInput(nullCred, 0, 15,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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
//            System.out.println(actionInput);
            switch (actionInput.getType()) {
                case "change page" -> BasicCP.doChangePage(actionInput, output);
                case "on page" -> {
                    ActionsInput actionInputCopy = new ActionsInput(actionInput);

                    actions = new Actions(currPage, currUser, currML, actionInputCopy);

                    switch (actionInput.getFeature()) {
                        case "login" -> {
                            if (!currPage.equals("login")) {
                                Error.doError(output);
                                break;
                            }

                            Login login = new Login(actions, output);
                            actions.doAction(login);

                            if (currUser.getCredentials().getName() != null) {
                                currPage = "homepage auth";
                                BasicOutput.doOutput(output);
                            } else {
                                currPage = "homepage unauth";
                            }
                        }
                        case "register" -> {
                            if (!currPage.equals("register")) {
                                Error.doError(output);
                                break;
                            }

                            Register register = new Register(actions, output);
                            actions.doAction(register);

                            currUser.setCredentials(actions.getCurrUser().getCredentials());

                            if (currUser.getCredentials().getName() != null) {
                                currPage = "homepage auth";
                                BasicOutput.doOutput(output);
                            } else {
                                currPage = "homepage unauth";
                            }
                        }
                        case "search" -> {
                            if (!currPage.equals("movies")) {
                                Error.doError(output);
                                break;
                            }

                            Search search = new Search(input, actions, output);
                            actions.doAction(search);
                        }
                        case "filter" -> {
                            if (!currPage.equals("movies")) {
                                Error.doError(output);
                                break;
                            }

                            Filter filter = new Filter(input, actions, new ContainsFilter());
                            actions.doAction(filter);
                            filter = new Filter(input, actions, new SortFilter());
                            actions.doAction(filter);

                            output.addObject().put("error", (String) null)
                                    .putPOJO("currentMoviesList", actions.getFilterML())
                                    .putPOJO("currentUser", Menu.getCurrUser());
                        }
                        case "purchase" -> {
                            if (!currPage.equals("see details")) {
                                Error.doError(output);
                                break;
                            }

                            UserInput newCurrUser = new UserInput(currUser);

                            Purchase purchase = new Purchase(actions, output, newCurrUser);
                            actions.doAction(purchase);

                            currUser = new UserInput(newCurrUser);
                        }
                        case "watch" -> {
                            if (!currPage.equals("see details")) {
                                Error.doError(output);
                                break;
                            }

                            UserInput newCurrUser = new UserInput(currUser);

                            Watch watch = new Watch(actions, output, newCurrUser);
                            actions.doAction(watch);

                            currUser = new UserInput(newCurrUser);
                        }
                        case "like" -> {
                            if (!currPage.equals("see details")) {
                                Error.doError(output);
                                break;
                            }

                            UserInput newCurrUser = new UserInput(currUser);

                            Like like = new Like(actions, output, newCurrUser);
                            actions.doAction(like);

                            currUser = new UserInput(newCurrUser);
                        }
                        case "rate" -> {
                            if (!currPage.equals("see details")) {
                                Error.doError(output);
                                break;
                            }

                            UserInput newCurrUser = new UserInput(currUser);

                            RateTheMovie rateTheMovie = new RateTheMovie(
                                    actions, output, newCurrUser);
                            actions.doAction(rateTheMovie);

                            currUser = new UserInput(newCurrUser);
                        }
                        case "buy premium account" -> {
                            if (!currPage.equals("upgrades")) {
                                Error.doError(output);
                                break;
                            }

                            UserInput newCurrUser = new UserInput(currUser);

                            BuyPA buyPA = new BuyPA(actions, output, newCurrUser);
                            actions.doAction(buyPA);

                            currUser = new UserInput(newCurrUser);
                        }
                        case "buy tokens" -> {
                            if (!currPage.equals("upgrades")) {
                                Error.doError(output);
                                break;
                            }

                            UserInput newCurrUser = new UserInput(currUser);

                            BuyTokens buyTokens = new BuyTokens(actions, output, newCurrUser);
                            actions.doAction(buyTokens);

                            currUser = new UserInput(newCurrUser);
                        }
                    }
                }
            }
        }
    }
}
