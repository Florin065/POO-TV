package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter @Setter @ToString
public class MovieInput {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes = 0;
    private double rating = 0;
    private int numRatings = 0;

    public MovieInput(final MovieInput moviesInput) {
        this.name = moviesInput.getName();
        this.year = moviesInput.getYear();
        this.duration = moviesInput.getDuration();
        this.genres = moviesInput.getGenres();
        this.actors = moviesInput.getActors();
        this.countriesBanned = moviesInput.getCountriesBanned();
        rating = moviesInput.getRating();
        numLikes = moviesInput.numLikes;
        numRatings = moviesInput.getNumRatings();
    }

    public MovieInput() {
    }
}
