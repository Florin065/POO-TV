package fileio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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

    public MovieInput(MovieInput moviesInput) {
        this.name = moviesInput.getName();
        this.year = moviesInput.getYear();
        this.duration = moviesInput.getDuration();
        this.genres = moviesInput.getGenres();
        this.actors = moviesInput.getActors();
        this.countriesBanned = moviesInput.getCountriesBanned();
    }

    public MovieInput() {
    }

    @Override
    public String toString() {
        return "MoviesInput{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                '}';
    }
}
