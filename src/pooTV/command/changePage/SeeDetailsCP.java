package pooTV.command.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.MovieInput;
import fileio.UserInput;
import pooTV.Menu;

public class SeeDetailsCP {
    public static void movieDetails(MovieInput movie, UserInput currentUser) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode output = mapper.createArrayNode();

        output.addObject().put("error", "null")
                .putPOJO("currentMovieList", movie)
                .putPOJO("currentUser", currentUser);

        Menu.setOutput(output);
    }
}
