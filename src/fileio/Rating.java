package fileio;

import lombok.Getter;
import lombok.Setter;

public class Rating {
    @Getter @Setter
    private String movieName = null;
    @Getter @Setter
    private double rate = 0;

    public Rating(String movieName, double rate) {
        this.movieName = movieName;
        this.rate = rate;
    }
    public Rating() {
    }
}
