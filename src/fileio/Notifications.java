package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Notifications {
    private String movieName;
    private String message;

    public Notifications(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notifications() {
    }
}
