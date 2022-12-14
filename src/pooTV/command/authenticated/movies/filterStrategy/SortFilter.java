package pooTV.command.authenticated.movies.filterStrategy;

import fileio.Input;
import fileio.MovieInput;
import pooTV.command.Actions;

import java.util.ArrayList;

public class SortFilter implements FilterStrategy {
    @Override
    public void doFiltering(Actions actions, Input input, ArrayList<MovieInput> currML) {
        if (actions.getFilterML() != null) {
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

        if (currML != null) {
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
            } else if (actions.getActionInput().getFilters().getSort().getDuration() != null) {
                String duration = actions.getActionInput().getFilters().getSort().getDuration();

                currML.sort((t2, t1) -> {
                    if (duration.equals("decreasing")) {
                        return t1.getDuration() - t2.getDuration();
                    }
                    return t2.getDuration() - t1.getDuration();
                });
            }
            String rating = actions.getActionInput().getFilters().getSort().getRating();

            currML.sort((t2, t1) -> {
                if (rating.equals("decreasing")) {
                    return (int) (t1.getRating() - t2.getRating());
                }
                return (int) (t2.getRating() - t1.getRating());
            });
        }
    }
}
