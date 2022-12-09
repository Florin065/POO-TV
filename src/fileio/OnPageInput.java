package fileio;

import lombok.Getter;
import lombok.Setter;

public class OnPageInput {
    @Getter @Setter
    private String feature;
    @Getter @Setter
    private UserInput credentials;
    @Getter @Setter
    private String startsWith;
    @Getter @Setter
    private String filters;
    @Getter @Setter
    private int count;
    @Getter @Setter
    private int rate;

    public OnPageInput() {
    }

    @Override
    public String toString() {
        return "OnPageInput{" +
                "feature='" + feature + '\'' +
                ", credentials=" + credentials +
                ", startsWith='" + startsWith + '\'' +
                ", filters='" + filters + '\'' +
                ", count=" + count +
                ", rate=" + rate +
                '}';
    }
}
