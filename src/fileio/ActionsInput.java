package fileio;

import lombok.Getter;
import lombok.Setter;

public class ActionsInput {
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String page;
    @Getter @Setter
    private String movie;
    @Getter @Setter
    private String feature;
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private String startsWith;
    @Getter @Setter
    private Filters filters;
    @Getter @Setter
    private int count;
    @Getter @Setter
    private int rate;

    public ActionsInput() {
    }

    @Override
    public String toString() {
        return "ActionsInput{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", movie='" + movie + '\'' +
                ", feature='" + feature + '\'' +
                ", credentials=" + credentials +
                ", startsWith='" + startsWith + '\'' +
                ", filters='" + filters + '\'' +
                ", count=" + count +
                ", rate=" + rate +
                '}';
    }
}
