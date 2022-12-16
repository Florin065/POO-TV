package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public ActionsInput(final ActionsInput actionsInput) {
        this.startsWith = actionsInput.getStartsWith();
        this.rate = actionsInput.getRate();
        this.page = actionsInput.getPage();
        this.type = actionsInput.getType();
        this.movie = actionsInput.getMovie();
        this.filters = actionsInput.getFilters();
        this.feature = actionsInput.getFeature();
        this.count = actionsInput.getCount();
        this.credentials = actionsInput.getCredentials();
    }

    public ActionsInput() {
    }
}
