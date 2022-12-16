package pootv.command.authenticated.movies.filterstrategy;

import fileio.Input;
import fileio.MovieInput;
import pootv.command.Actions;

import java.util.ArrayList;

public class ContainsFilter implements FilterStrategy {
    /**
     *
     * @param actions
     * @param input
     * @param currML
     */
    @Override
    public void doFiltering(final Actions actions, final Input input,
                            final ArrayList<MovieInput> currML) {
        ArrayList<MovieInput> newML = new ArrayList<>();
        actions.setFilterML(new ArrayList<>());

        if ((!currML.equals(new ArrayList<>()))
                && actions.getActionInput().getFilters().getContains() != null) {
            if (actions.getActionInput().getFilters().getContains().getActors() != null
                    && actions.getActionInput().getFilters().getContains().getGenre() != null) {
                for (MovieInput movie : currML) {
                    if (movie.getActors().containsAll(actions.getActionInput()
                            .getFilters().getContains().getActors())
                            && movie.getGenres().containsAll(actions.getActionInput()
                            .getFilters().getContains().getGenre())) {
                        newML.add(movie);
                    }
                }
            } else if (actions.getActionInput().getFilters().getContains().getActors() != null) {
                for (MovieInput movie : currML) {
                    if (movie.getActors().containsAll(actions.getActionInput()
                            .getFilters().getContains().getActors())) {
                        newML.add(movie);
                    }
                }
            } else if (actions.getActionInput().getFilters().getContains().getGenre() != null) {
                for (MovieInput movie : currML) {
                    if (movie.getGenres().containsAll(actions.getActionInput()
                            .getFilters().getContains().getGenre())) {
                        newML.add(movie);
                    }
                }
            }
        }

        actions.setFilterML(newML);
    }
}
