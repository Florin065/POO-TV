package pootv.command.authenticated.seeDetails;

import fileio.MovieInput;
import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class CommandOutput {
    @Getter @Setter
    private String error;
    @Getter @Setter
    private ArrayList<MovieInput> currentMoviesList;
    @Getter @Setter
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
