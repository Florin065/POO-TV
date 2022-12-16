package pootv.command.authenticated.movies.filterstrategy;

import fileio.MovieInput;
import pootv.Menu;
import pootv.command.Actions;

import java.util.ArrayList;

public class SortFilter implements FilterStrategy {
    /**
     *
     * @param currML
     */
    @Override
    public void doFiltering(final ArrayList<MovieInput> currML) {
        Actions actions = Menu.getActions();

        if ((!actions.getFilterML().equals(new ArrayList<>()))
                && actions.getActionInput().getFilters().getSort() != null) {
            if (actions.getActionInput().getFilters().getSort().getDuration() != null
                    && actions.getActionInput().getFilters().getSort().getRating() != null) {
                String duration = actions.getActionInput().getFilters().getSort().getDuration();
                String rating = actions.getActionInput().getFilters().getSort().getRating();

                actions.getFilterML().sort((t2, t1) -> {
                    if (t1.getDuration() != t2.getDuration()
                            && duration.equals("decreasing")) {
                        return t1.getDuration() - t2.getDuration();
                    } else if (t1.getDuration() != t2.getDuration()
                            && duration.equals("increasing")) {
                        return t2.getDuration() - t1.getDuration();
                    } else if (t1.getRating() != t2.getRating()
                            && rating.equals("decreasing")) {
                        return (int) (t1.getRating() - t2.getRating());
                    }
                    return (int) (t2.getRating() - t1.getRating());
                });
            } else if (actions.getActionInput().getFilters().getSort().getDuration() != null) {
                String duration = actions.getActionInput().getFilters().getSort().getDuration();

                actions.getFilterML().sort((t2, t1) -> {
                    if (duration.equals("decreasing")) {
                        return t1.getDuration() - t2.getDuration();
                    }
                    return t2.getDuration() - t1.getDuration();
                });
            }
            String rating = actions.getActionInput().getFilters().getSort().getRating();

            actions.getFilterML().sort((t2, t1) -> {
                if (rating.equals("decreasing")) {
                    return (int) (t1.getRating() - t2.getRating());
                }
                return (int) (t2.getRating() - t1.getRating());
            });

            return;
        }

        if ((!currML.equals(new ArrayList<>()))
                && actions.getActionInput().getFilters().getSort() != null) {
            if (actions.getActionInput().getFilters().getSort().getDuration() != null
                    && actions.getActionInput().getFilters().getSort().getRating() != null) {
                String duration = actions.getActionInput().getFilters().getSort().getDuration();
                String rating = actions.getActionInput().getFilters().getSort().getRating();

                currML.sort((t2, t1) -> {
                    if (t1.getDuration() != t2.getDuration()
                            && duration.equals("decreasing")) {
                        return t1.getDuration() - t2.getDuration();
                    } else if (t1.getDuration() != t2.getDuration()
                            && duration.equals("increasing")) {
                        return t2.getDuration() - t1.getDuration();
                    } else if (t1.getRating() != t2.getRating()
                            && rating.equals("decreasing")) {
                        return (int) (t1.getRating() - t2.getRating());
                    }
                    return (int) (t2.getRating() - t1.getRating());
                });

                actions.setFilterML(currML);
                return;
            } else if (actions.getActionInput().getFilters().getSort().getDuration() != null) {
                String duration = actions.getActionInput().getFilters().getSort().getDuration();

                currML.sort((t2, t1) -> {
                    if (duration.equals("decreasing")) {
                        return t1.getDuration() - t2.getDuration();
                    }
                    return t2.getDuration() - t1.getDuration();
                });

                actions.setFilterML(currML);
                return;
            }
            String rating = actions.getActionInput().getFilters().getSort().getRating();

            currML.sort((t2, t1) -> {
                if (rating.equals("decreasing")) {
                    return (int) (t1.getRating() - t2.getRating());
                }
                return (int) (t2.getRating() - t1.getRating());
            });

            actions.setFilterML(currML);
        }
    }
}
