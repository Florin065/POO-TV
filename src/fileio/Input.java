package fileio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Input {
    @Getter @Setter
    private ArrayList<UserInput> users;
    @Getter @Setter
    private ArrayList<MovieInput> movies;
    @Getter @Setter
    private ArrayList<ActionsInput> actions;

    public Input() {
    }

    @Override
    public String toString() {
        return "Input{" +
                "usersInput=" + users +
                ", moviesInput=" + movies +
                ", actionsInput=" + actions +
                '}';
    }
}
