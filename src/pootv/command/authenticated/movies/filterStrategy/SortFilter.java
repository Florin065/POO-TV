package pootv.command.authenticated.movies.filterStrategy;

import fileio.MovieInput;
import pootv.Menu;
import pootv.command.Actions;

import java.util.ArrayList;
import java.util.Comparator;

public class SortFilter implements FilterStrategy {
    /**
     * Sort the movies by duration, rating, or both.
     * If the user has used the contains filter before, then we will sort that list of movies.
     * Otherwise, it sorts all movies available in the user's country.
     */
    @Override
    public void doFiltering(final ArrayList<MovieInput> currML) {
        Actions actions = Menu.getActions();

        Comparator<MovieInput> durationComparator = (t2, t1) -> {
                                if (t1.getDuration() != t2.getDuration()
                                        && actions.getActionInput().getFilters().getSort().getDuration().equals("decreasing")) {
                                    return t1.getDuration() - t2.getDuration();
                                } else if (t1.getDuration() != t2.getDuration()
                                        && actions.getActionInput().getFilters().getSort().getDuration().equals("increasing")) {
                                    return t2.getDuration() - t1.getDuration();
                                } else if (t1.getRating() != t2.getRating()
                                        && actions.getActionInput().getFilters().getSort().getRating().equals("decreasing")) {
                                    return (int) (t1.getRating() - t2.getRating());
                                }
                                return (int) (t2.getRating() - t1.getRating());
                            };

        if ((!actions.getFilter().isEmpty())
                && actions.getActionInput().getFilters().getSort() != null) {
            if (actions.getActionInput().getFilters().getSort().getDuration() != null
                    && actions.getActionInput().getFilters().getSort().getRating() != null) {
                actions.getFilter().sort(durationComparator);
            } else if (actions.getActionInput().getFilters().getSort().getDuration() != null) {
                String duration = actions.getActionInput().getFilters().getSort().getDuration();

                actions.getFilter().sort((t2, t1) -> {
                    if (duration.equals("decreasing")) {
                        return t1.getDuration() - t2.getDuration();
                    }
                    return t2.getDuration() - t1.getDuration();
                });
            }
            String rating = actions.getActionInput().getFilters().getSort().getRating();

            actions.getFilter().sort((t2, t1) -> {
                if (rating.equals("decreasing")) {
                    return (int) (t1.getRating() - t2.getRating());
                }
                return (int) (t2.getRating() - t1.getRating());
            });

            return;
        }

        if ((!currML.isEmpty())
                && actions.getActionInput().getFilters().getSort() != null) {
            if (actions.getActionInput().getFilters().getSort().getDuration() != null
                    && actions.getActionInput().getFilters().getSort().getRating() != null) {
                currML.sort(durationComparator);
                actions.setFilter(currML);
                return;
            } else if (actions.getActionInput().getFilters().getSort().getDuration() != null) {
                String duration = actions.getActionInput().getFilters().getSort().getDuration();

                currML.sort((t2, t1) -> {
                    if (duration.equals("decreasing")) {
                        return t1.getDuration() - t2.getDuration();
                    }
                    return t2.getDuration() - t1.getDuration();
                });

                actions.setFilter(currML);
                return;
            }
            String rating = actions.getActionInput().getFilters().getSort().getRating();

            if (rating.equals("decreasing")) {
                currML.sort((t1, t2) -> Double.compare(t2.getRating(), t1.getRating()));
            } else {
                currML.sort(Comparator.comparingDouble(MovieInput::getRating));
            }

            actions.setFilter(currML);
        }
    }
}
