package fileio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class ContainsFilter {
    @Getter @Setter
    private ArrayList<String> actors;
    @Getter @Setter
    private ArrayList<String> genre;

    public ContainsFilter() {

    }

    @Override
    public String toString() {
        return "Contains{" +
                "actors=" + actors +
                ", genre=" + genre +
                '}';
    }
}
