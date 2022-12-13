package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
public class MovieInput {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int year;
    @Getter @Setter
    private int duration;
    @Getter @Setter
    private ArrayList<String> genres;
    @Getter @Setter
    private ArrayList<String> actors;
    @Getter @Setter
    private ArrayList<String> countriesBanned;
    @Getter @Setter
    private int numLikes;
    @Getter @Setter
    private int numRatings;
    @Getter @Setter
    private double rating;

    public MovieInput(MovieInput moviesInput) {
        this.name = moviesInput.getName();
        this.year = moviesInput.getYear();
        this.duration = moviesInput.getDuration();
        this.genres = moviesInput.getGenres();
        this.actors = moviesInput.getActors();
        this.countriesBanned = moviesInput.getCountriesBanned();
        rating = 0;
        numLikes = 0;
        numRatings = 0;
    }

    public MovieInput() {
    }
}
