package fileio;

import lombok.Getter;
import lombok.Setter;

public class FiltersInput {
    @Getter @Setter
    private SortFilter sort;
    @Getter @Setter
    private ContainsFilter contains;

    public FiltersInput() {

    }

    @Override
    public String toString() {
        return "Filters{" +
                "sort=" + sort +
                ", contains=" + contains +
                '}';
    }
}
