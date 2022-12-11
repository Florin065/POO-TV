package pooTV;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.ActionsInput;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.commands.Actions;

import java.util.ArrayList;
import java.util.Map;

import static pooTV.Page.createPage;

public class Menu {
    @Getter @Setter
    private Input input;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput currentUser;
    @Getter @Setter
    private String currentPage;
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Map<String, ArrayList<String>> pageList;

    public Menu(Input input, ArrayNode output) {
        this.input = input;
        this.output = output;
        this.currentUser = null;
        this.currentPage = "Homepage neautentificat";
        pageList = createPage();
        ArrayList<UserInput> usersList = new ArrayList<>(input.getUsers());
        ArrayList<MovieInput> moviesList = new ArrayList<>(input.getMovies());
        DataBase dataBase = DataBase.getDataBase();
        dataBase.setUsers(usersList);
        dataBase.setMovies(moviesList);
    }

    public void changePage(ArrayNode output, String page) {
        output.addObject().put("type", "change page")
                .put("page", page);
    }

    public void changePage(ArrayNode output, MovieInput moviesInput, String page) {
        output.addObject().put("type", "change page")
                .put("page", page)
                .put("movie", moviesInput.getName());
    }

    public void actionsPOOTV() {

        for (ActionsInput actionsInput : this.input.getActions()) {
            System.out.println(actionsInput);
            switch (actionsInput.getType()) {
                case "change page" -> {
                    for (Map.Entry<String, ArrayList<String>> page : pageList.entrySet()) {
                        if (page.getKey().equals(currentPage)) {
                            for (String pageToGo : page.getValue()) {
                                if (pageToGo.equals(actionsInput.getPage())) {
                                    currentPage = pageToGo;
                                    return;
                                }
                            }
                        }
                    }
                    Actions.error(output);
                }
                case "on page" -> {
                    switch (actionsInput.getFeature()) {
                        case "login" -> {
//                    Login login = new Login(output, dataBase);
//                    login.execute();
                        }
                        case "register" -> {
//                    Register register = new Register();
//                    register.execute();
                        }
                        case "search" -> {
//                    Search search = new Search();
//                    search.execute();
                        }
                        case "filter" -> {
//                    Filter filter = new Filter();
//                    filter.execute();
                        }
                        case "purchase" -> {
//                    Purchase purchase = new Purchase();
//                    purchase.execute();
                        }
                        case "watch" -> {
//                    Watch watch = new Watch();
//                    watch.execute();
                        }
                        case "like" -> {
//                    Like like = new Like();
//                    like.execute();
                        }
                        case "rate the movie" -> {
//                    RateTheMovie rateTheMovie = new RateTheMovie();
//                    rateTheMovie.execute();
                        }
                        case "buy premium account" -> {
//                    BuyPA buyPA = new BuyPA();
//                    buyPA.execute();
                        }
                        case "buy tokens" -> {
//                    BuyTokens buyTokens = new BuyTokens();
//                    buyTokens.execute();
                        }
                    }
                }
            }
        }
    }
}
