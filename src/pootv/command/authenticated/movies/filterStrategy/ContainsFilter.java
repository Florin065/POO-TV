package pootv.command.authenticated.movies.filterStrategy;

import fileio.MovieInput;
import pootv.Menu;
import pootv.command.Actions;

import java.util.ArrayList;

public class ContainsFilter implements FilterStrategy {
    /**
     * Finds all movies available in the user's country by actors, genre or both.
     */
    @Override
    public void doFiltering(final ArrayList<MovieInput> currML) {
        Actions actions = Menu.getActions();
        ArrayList<MovieInput> newML = new ArrayList<>();
        actions.setFilter(new ArrayList<>());

        if ((!currML.isEmpty())
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
        actions.setFilter(newML);
    }
}
