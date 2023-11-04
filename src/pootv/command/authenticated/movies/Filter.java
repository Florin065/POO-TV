package pootv.command.authenticated.movies;
import fileio.MovieInput;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Command;
import pootv.command.NotBannedMovies;
import pootv.command.authenticated.movies.filterStrategy.FilterStrategy;

import java.util.ArrayList;

@Getter @Setter
public class Filter implements Command {
    private FilterStrategy strategy;

    public Filter(final FilterStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calls the doFiltering method of the strategy received as a parameter, after which it will
     * filter the list of movies.
     */
    @Override
    public void execute() {
        ArrayList<MovieInput> currML = new ArrayList<>();
        NotBannedMovies.notBannedMovies(currML);
        strategy.doFiltering(currML);
    }
}
