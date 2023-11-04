package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import pootv.CommandOutput;
import pootv.Menu;

import java.util.ArrayList;

public final class PurchasedOutput {
    private PurchasedOutput() {
    }

    public static void doOutput(MovieInput iterator) {
        Menu.getCurrUser().getPurchasedMovies().add(iterator);

        ArrayList<MovieInput> movieOutput = new ArrayList<>();
        movieOutput.add(iterator);
        ObjectMapper mapper = new ObjectMapper();
        Menu.getOutput().add(mapper.valueToTree(
                new CommandOutput(null, movieOutput, Menu.getCurrUser())));
    }
}
