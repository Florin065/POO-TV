package pootv;

import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class CommandOutput {
    private String error;
    private ArrayList<MovieInput> currentMoviesList;
    private UserInput currentUser;

    public CommandOutput() {
    }

    public CommandOutput(final String error, final ArrayList<MovieInput> currentMoviesList,
                         final UserInput currentUser) {
        this.error = error;
        this.currentMoviesList = currentMoviesList;
        this.currentUser = currentUser;
    }
}
