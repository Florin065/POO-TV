package fileio;

import lombok.Getter;
import lombok.Setter;

public class Input {
    @Getter @Setter
    private UsersInput usersInput;
    @Getter @Setter
    private MoviesInput moviesInput;
    @Getter @Setter
    private ActionsInput actionsInput;

    public Input() {

    }

    @Override
    public String toString() {
        return "Input{" +
                "usersInput=" + usersInput +
                ", moviesInput=" + moviesInput +
                ", actionsInput=" + actionsInput +
                '}';
    }
}
