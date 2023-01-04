package pootv.command.authenticated.seeDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pootv.Menu;

import java.util.ArrayList;

public final class TokensPurchase {
    private TokensPurchase() {
    }

    /**
     *
     * @param output
     * @param user
     * @param movie
     * @param movieOutput
     */
    public static void tokens(final ArrayNode output, final UserInput user,
                              final MovieInput movie, final ArrayList<MovieInput> movieOutput) {
        user.setTokensCount(user.getTokensCount() - 2);
        user.getPurchasedMovies().add(movie);
        movieOutput.add(movie);

        ObjectMapper mapper = new ObjectMapper();
        output.add(mapper.valueToTree(new CommandOutput(null, movieOutput, user)));

        Menu.setCurrUser(new UserInput(user));
    }
}
