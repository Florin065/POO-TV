package pootv.command.authenticated.movies.filterStrategy;

import fileio.MovieInput;

import java.util.ArrayList;

public interface FilterStrategy {
    /**
     *
     * @param currML
     */
    void doFiltering(ArrayList<MovieInput> currML);
}
