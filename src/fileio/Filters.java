package fileio;

import lombok.Getter;
import lombok.Setter;

public class Filters {
    @Getter @Setter
    private Sort sort;
    @Getter @Setter
    private Contains contains;

    public Filters() {

    }

    @Override
    public String toString() {
        return "Filters{" +
                "sort=" + sort +
                ", contains=" + contains +
                '}';
    }
}
