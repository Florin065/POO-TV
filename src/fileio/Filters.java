package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Filters {
    @Getter @Setter
    private Sort sort;
    @Getter @Setter
    private Contains contains;

    public Filters() {
    }
}
