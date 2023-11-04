package pootv.command.authenticated.movies.filterStrategy;

import fileio.MovieInput;

import java.util.ArrayList;

public interface FilterStrategy {
    /**
     * Interface used when implementing filters following the Strategy Pattern
     */
    void doFiltering(ArrayList<MovieInput> currML);
}
