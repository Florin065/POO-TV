package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
public class Input {
    @Getter @Setter
    private ArrayList<UserInput> users;
    @Getter @Setter
    private ArrayList<MovieInput> movies;
    @Getter @Setter
    private ArrayList<ActionsInput> actions;

    public Input() {
    }
}
