package pootv.command;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    @Getter @Setter
    private List<Command> commandList = new ArrayList<>();

    /**
     *
     * @param command
     */
    public void execute(final Command command) {
        commandList.add(command);
        command.execute();
    }
}
