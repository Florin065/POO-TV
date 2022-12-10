package fileio;

import lombok.Getter;
import lombok.Setter;

public class Sort {
    @Getter @Setter
    private String rating;
    @Getter @Setter
    private String duration;

    public Sort() {

    }

    @Override
    public String toString() {
        return "Sort{" +
                "rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
