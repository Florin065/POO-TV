package pooTV;

import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.ActionsInput;
import fileio.Input;
import lombok.Getter;
import lombok.Setter;


public class Menu {
    @Getter @Setter
    private DataBase dataBase;
    @Getter @Setter
    private Input input;
    @Getter @Setter
    private ArrayNode output;

    public Menu(Input input, ArrayNode output) {
        this.input = input;
        this.output = output;
        this.dataBase.addUsers(input.getUsers());
        this.dataBase.addMovies(input.getMovies());
    }

    public void actionsPOOTV() {
        for (ActionsInput actionsInput : this.input.getActions()) {
            switch (actionsInput.getFeature()) {
                case "login" -> {
//                    TODO 1
                }
                case "register" -> {
//                    TODO 2
                }
                case "search" -> {
//                    TODO 3
                }
                case "filter" -> {
//                    TODO 4
                }
                case "purchase" -> {
//                    TODO 5
                }
                case "watch" -> {
//                    TODO 6
                }
                case "like" -> {
//                    TODO 7
                }
                case "rate the movie" -> {
//                    TODO 8
                }
                case "buy premium account" -> {
//                    TODO 9
                }
                case "buy tokens" -> {
//                    TODO 10
                }
            }
        }
    }
}
