package fileio;

import lombok.Getter;
import lombok.Setter;

public class ChangePageInput {
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String page;
    @Getter @Setter
    private String movie;

    public ChangePageInput() {
    }

    @Override
    public String toString() {
        return "ChangePageInput{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", movie='" + movie + '\'' +
                '}';
    }
}
