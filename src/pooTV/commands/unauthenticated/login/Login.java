package pooTV.commands.unauthenticated.login;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Credentials;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;
import pooTV.commands.Actions;
import pooTV.commands.Command;
import pooTV.DataBase;

import java.util.ArrayList;

public class Login implements Command {
    @Getter @Setter
    private DataBase dataBase;
    @Getter @Setter
    private Input input;
    @Getter @Setter
    private UserInput currentUser;
    @Getter @Setter
    private ArrayList<MovieInput> currentMovieList;
    @Getter @Setter
    private String currentPage;
    @Getter @Setter
    private Actions actions;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private ArrayNode output;

    public Login(Actions actions) {
        this.actions = actions;
    }

    public Login(ArrayNode output, DataBase dataBase, Input input,
                 UserInput currentUser, ArrayList<MovieInput> currentMovieList,
                 Credentials credentials) {
        this.input = input;
        this.dataBase = dataBase;
        this.currentUser = currentUser;
        this.currentMovieList = currentMovieList;
        this.credentials = credentials;
        this.output = output;
    }

    @Override
    public void execute() {
        actions.login(output, dataBase, input, currentUser,
                currentMovieList, credentials);
    }
}
