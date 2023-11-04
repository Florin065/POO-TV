package pootv.command;

import fileio.ActionsInput;
import fileio.MovieInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class Actions {
    private Invoker invoker;
    private ActionsInput actionInput;
    private ArrayList<MovieInput> filter;

    public Actions(final ActionsInput actionInput) {
        this.invoker = new Invoker();
        this.actionInput = actionInput;
        this.filter = new ArrayList<>();
    }

    /**
     * Uses invoker to do an action.
     */
    public void doAction(final Command command) {
        invoker.execute(command);
    }
}
