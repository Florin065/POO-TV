package fileio;

import lombok.Getter;
import lombok.Setter;

public class SortFilter {
    @Getter @Setter
    private String rating;
    @Getter @Setter
    private String duration;

    public SortFilter() {

    }

    @Override
    public String toString() {
        return "Sort{" +
                "rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
