package pootv.command.authenticated.movies.filterStrategy;

import fileio.MovieInput;

import java.util.ArrayList;

public interface FilterStrategy {
    /**
     * interface used when implementing filters following the Strategy Pattern
     * @param currML
     */
    void doFiltering(ArrayList<MovieInput> currML);
}
