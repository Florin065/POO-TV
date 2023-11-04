package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter @Setter @ToString
public class Input {
    private ArrayList<UserInput> users;
    private ArrayList<MovieInput> movies;
    private ArrayList<ActionsInput> actions;

    public Input() {
    }
}
