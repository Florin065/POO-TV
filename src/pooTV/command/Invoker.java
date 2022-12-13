package pooTV.command;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    @Getter @Setter
    List<Command> commandList = new ArrayList<>();

    public void execute(Command command) {
        commandList.add(command);
        command.execute();
    }
}
