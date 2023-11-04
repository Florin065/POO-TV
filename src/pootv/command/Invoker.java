package pootv.command;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Invoker {
    private List<Command> commandList = new ArrayList<>();

    /**
     * Invoker object looks for the appropriate object which can handle this command and passes
     * the command to the corresponding object which executes the command.
     */
    public void execute(final Command command) {
        commandList.add(command);
        command.execute();
    }
}
