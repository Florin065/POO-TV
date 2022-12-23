package pootv.command;

import fileio.ActionsInput;
import fileio.MovieInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Actions {
    @Getter @Setter
    private Invoker invoker;
    @Getter @Setter
    private ActionsInput actionInput;
    @Getter @Setter
    private ArrayList<MovieInput> filter;

    public Actions(final ActionsInput actionInput) {
        this.invoker = new Invoker();
        this.actionInput = actionInput;
        this.filter = new ArrayList<>();
    }

    /**
     *
     * @param command
     */
    public void doAction(final Command command) {
        invoker.execute(command);
    }
}
