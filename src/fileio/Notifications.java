package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Notifications {
    @Getter @Setter
    private String movieName;
    @Getter @Setter
    private String message;

    public Notifications(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notifications() {
    }
}
