package pooTV.command;

import fileio.ActionsInput;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Actions {
    @Getter @Setter
    private Invoker invoker;
    @Getter @Setter
    private String currPage;
    @Getter @Setter
    private UserInput currUser;
    @Getter @Setter
    private ArrayList<MovieInput> currML;
    @Getter @Setter
    private ActionsInput actionInput;

    public Actions(String currPage, UserInput currUser,
                   ArrayList<MovieInput> currML, ActionsInput actionInput) {
        this.invoker = new Invoker();
        this.currPage = currPage;
        this.currUser = currUser;
        this.currML = currML;
        this.actionInput = actionInput;
    }

    public void doAction(Command command) {
        invoker.execute(command);
    }
}
