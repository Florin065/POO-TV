package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
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
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }

    public UserInput(Credentials credentials, int tokensCount, int numFreePremiumMovies,
                     ArrayList<String> purchasedMovies, ArrayList<String> watchedMovies,
                     ArrayList<String> likedMovies, ArrayList<String> ratedMovies) {
        this.credentials = credentials;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
    }

    public UserInput() {
    }
}
