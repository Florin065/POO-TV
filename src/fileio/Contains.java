package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter @Setter @ToString
public class Contains {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public Contains() {
    }
}
