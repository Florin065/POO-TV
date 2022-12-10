package pooTV;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.ActionsInput;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    @Getter @Setter
    private Input input;
    @Getter @Setter
    private ArrayNode output;
    @Getter @Setter
    private UserInput currentUser;
    @Getter @Setter
    private String currentPage;

    public Menu(Input input, ArrayNode output) {
        this.input = input;
        this.output = output;
        this.currentUser = null;
        this.currentPage = "Homepage neautentificat";
        ArrayList<UserInput> usersList = new ArrayList<>(input.getUsers());
        ArrayList<MovieInput> moviesList = new ArrayList<>(input.getMovies());
        DataBase dataBase = DataBase.getDataBase();
        dataBase.setUsers(usersList);
        dataBase.setMovies(moviesList);
    }

    public void actionsPOOTV() {
        Map<String, ArrayList<String>> pages = new HashMap<>();
        pages.put("Homepage neautentificat", new ArrayList<>());
        pages.put("Login", new ArrayList<>());
        pages.put("Register", new ArrayList<>());
        pages.put("Homepage autentificat", new ArrayList<>());
        pages.put("Movies", new ArrayList<>());
        pages.put("See details", new ArrayList<>());
        pages.put("Upgrades", new ArrayList<>());
        pages.put("Logout", new ArrayList<>());

        pages.get("Homepage neautentificat").add("Login");
        pages.get("Homepage neautentificat").add("Register");

        pages.get("Homepage autentificat").add("Movies");
        pages.get("Homepage autentificat").add("Upgrades");
        pages.get("Homepage autentificat").add("Logout");

        pages.get("Movies").add("");
        pages.get("Movies").add("");
        pages.get("Movies").add("");

        pages.get("See details").add("");
        pages.get("See details").add("");
        pages.get("See details").add("");
        pages.get("See details").add("");

        pages.get("Upgrades").add("");
        pages.get("Upgrades").add("");
        pages.get("Upgrades").add("");

        for (ActionsInput actionsInput : this.input.getActions()) {
            switch (actionsInput.getType()) {
                case "change page" -> {
//                    cu hashmap sa verific daca e pe pagina -> error handler in caz contrar
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
