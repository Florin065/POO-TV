package fileio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class UserInput {
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private int tokensCount;
    @Getter @Setter
    private int numFreePremiumMovies;
    @Getter @Setter
    private ArrayList<String> purchasedMovies;
    @Getter @Setter
    private ArrayList<String> watchedMovies;
    @Getter @Setter
    private ArrayList<String> likedMovies;
    @Getter @Setter
    private ArrayList<String> ratedMovies;

    public UserInput(UserInput usersInput) {
        credentials = new Credentials(usersInput.getCredentials());
        tokensCount = 0;
        numFreePremiumMovies = 15;
        purchasedMovies = null;
        watchedMovies = null;
        likedMovies = null;
        ratedMovies = null;
    }

    public UserInput() {
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "credentials=" + credentials +
                '}';
    }
}
