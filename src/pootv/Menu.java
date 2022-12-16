package pootv;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.Credentials;
import fileio.UserInput;
import fileio.MovieInput;
import fileio.Input;
import fileio.ActionsInput;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Actions;
import pootv.command.BasicOutput;
import pootv.command.Error;
import pootv.command.FilterOutput;
import pootv.command.authenticated.movies.Filter;
import pootv.command.authenticated.movies.Search;
import pootv.command.authenticated.movies.filterstrategy.ContainsFilter;
import pootv.command.authenticated.movies.filterstrategy.SortFilter;
import pootv.command.authenticated.seedetails.Like;
import pootv.command.authenticated.seedetails.Purchase;
import pootv.command.authenticated.seedetails.RateTheMovie;
import pootv.command.authenticated.seedetails.Watch;
import pootv.command.authenticated.upgrades.BuyPA;
import pootv.command.authenticated.upgrades.BuyTokens;
import pootv.command.changepage.BasicCP;
import pootv.command.unauthenticated.login.Login;
import pootv.command.unauthenticated.register.Register;

import java.util.ArrayList;
import java.util.Map;

import static pootv.Page.createPageHierarchy;

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
    @Getter @Setter
    private static String movieDetailsName = null;
    @Getter @Setter
    private static String lastAction = null;

    public Menu(final Input input, final ArrayNode output) {
        Menu.input = input;
        this.output = output;
        nullCred = new Credentials(null, null, null, null, "0");
        currUser = new UserInput(nullCred, 0, 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        currPage = "homepage unauth";
        currML = new ArrayList<>();
        pageList = createPageHierarchy();

        ArrayList<UserInput> usersList = new ArrayList<>(input.getUsers());
        ArrayList<MovieInput> moviesList = new ArrayList<>(input.getMovies());

        DataBase.getDataBase().setUsers(usersList);
        DataBase.getDataBase().setMovies(moviesList);
    }

    /**
     *
     */
    public void actionsPOOTV() {
        for (ActionsInput actionInput : input.getActions()) {
            switch (actionInput.getType()) {
                case "change page" -> BasicCP.doChangePage(actionInput, output);
                case "on page" -> {
                    actions = new Actions(currPage, currUser, currML, actionInput);
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
                            lastAction = actionInput.getFeature();
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
                            lastAction = actionInput.getFeature();
                        }
                        case "search" -> {
                            if (!currPage.equals("movies")) {
                                Error.doError(output);
                                break;
                            }

                            Search search = new Search(input, actions, output);
                            actions.doAction(search);
                            lastAction = actionInput.getFeature();
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
                            FilterOutput.doOutput(output, actions);
                            lastAction = actionInput.getFeature();
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
                            lastAction = actionInput.getFeature();
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
                            lastAction = actionInput.getFeature();
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
                            lastAction = actionInput.getFeature();
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
                            lastAction = actionInput.getFeature();
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
                            lastAction = actionInput.getFeature();
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
                            lastAction = actionInput.getFeature();
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
