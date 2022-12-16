package pootv.command;

import fileio.ActionsInput;
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
    @Getter @Setter
    private ArrayList<MovieInput> filterML;

    public Actions(final String currPage, final UserInput currUser,
                   final ArrayList<MovieInput> currML, final ActionsInput actionInput) {
        this.invoker = new Invoker();
        this.currPage = currPage;
        this.currUser = currUser;
        this.currML = currML;
        this.actionInput = actionInput;
        this.filterML = new ArrayList<>();
    }

    /**
     *
     * @param command
     */
    public void doAction(final Command command) {
        invoker.execute(command);
    }
}
