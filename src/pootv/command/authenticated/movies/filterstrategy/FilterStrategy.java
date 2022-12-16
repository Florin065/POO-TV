package pootv.command.authenticated.movies.filterstrategy;

import fileio.Input;
import fileio.MovieInput;
import pootv.command.Actions;

import java.util.ArrayList;

public interface FilterStrategy {
    /**
     *
     * @param actions
     * @param input
     * @param currML
     */
    void doFiltering(Actions actions, Input input, ArrayList<MovieInput> currML);
}
