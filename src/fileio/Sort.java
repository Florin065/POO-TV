package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Sort {
    @Getter @Setter
    private String rating;
    @Getter @Setter
    private String duration;

    public Sort() {
    }
}
