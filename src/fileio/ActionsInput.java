package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ActionsInput {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private Filters filters;
    private String count;
    private double rate;
    private String deletedMovie;
    private String subscribedGenre;
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
