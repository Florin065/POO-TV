package pooTV.command.authenticated.movies.filterStrategy;

import fileio.Input;
import fileio.MovieInput;
import pooTV.command.Actions;

import java.util.ArrayList;

public interface FilterStrategy {
    void doFiltering(Actions actions, Input input, ArrayList<MovieInput> currML);
}
