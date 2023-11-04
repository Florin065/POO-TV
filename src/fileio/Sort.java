package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Sort {
    private String rating;
    private String duration;

    public Sort() {
    }
}
