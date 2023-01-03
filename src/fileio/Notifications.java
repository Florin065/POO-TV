package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
public class Notifications {
    @Getter @Setter
    private String movieName;
    @Getter @Setter
    private String message;

    public Notifications(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notifications() {
    }
}
