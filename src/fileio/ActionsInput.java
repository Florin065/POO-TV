package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
public class ActionsInput {
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String page;
    @Getter @Setter
    private String movie;
    @Getter @Setter
    private String feature;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private String startsWith;
    @Getter @Setter
    private Filters filters;
    @Getter @Setter
    private String count;
    @Getter @Setter
    private double rate;
    @Getter @Setter
    private String deletedMovie;
    @Getter @Setter
    private String subscribedGenre;
    @Getter @Setter
    private MovieInput addedMovie;

    public ActionsInput(final ActionsInput actionsInput) {
        this.startsWith = actionsInput.startsWith;
        this.rate = actionsInput.rate;
        this.page = actionsInput.page;
        this.type = actionsInput.type;
        this.movie = actionsInput.movie;
        this.filters = actionsInput.filters;
        this.feature = actionsInput.feature;
        this.count = actionsInput.count;
        this.credentials = actionsInput.credentials;
        this.deletedMovie = actionsInput.deletedMovie;
        this.subscribedGenre = actionsInput.subscribedGenre;
        this.addedMovie = actionsInput.addedMovie;
    }

    public ActionsInput() {
    }
}
