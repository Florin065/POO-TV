package fileio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Input {
    @Getter @Setter
    private List<UserInput> users;
    @Getter @Setter
    private List<MovieInput> movies;
    @Getter @Setter
    private List<ActionsInput> actions;

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
