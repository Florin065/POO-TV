package pootv.command.authenticated.movies;
import fileio.MovieInput;
import lombok.Getter;
import lombok.Setter;
import pootv.command.Command;
import pootv.command.NonBannedMVS;
import pootv.command.authenticated.movies.filterstrategy.FilterStrategy;

import java.util.ArrayList;

public class Filter implements Command {
    @Getter @Setter
    private FilterStrategy strategy;

    public Filter(final FilterStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     *
     */
    @Override
    public void execute() {
        ArrayList<MovieInput> currML = new ArrayList<>();
        NonBannedMVS.get(currML);

        strategy.doFiltering(currML);
    }
}
