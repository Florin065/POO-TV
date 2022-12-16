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
    private int tokensCount = 0;
    @Getter @Setter
    private int numFreePremiumMovies;
    @Getter @Setter
    private ArrayList<MovieInput> purchasedMovies;
    @Getter @Setter
    private ArrayList<MovieInput> watchedMovies;
    @Getter @Setter
    private ArrayList<MovieInput> likedMovies;
    @Getter @Setter
    private ArrayList<MovieInput> ratedMovies;

    public UserInput(final UserInput usersInput) {
        credentials = new Credentials(usersInput.getCredentials());
        tokensCount = usersInput.getTokensCount();
        numFreePremiumMovies = usersInput.getNumFreePremiumMovies();
        purchasedMovies = new ArrayList<>(usersInput.getPurchasedMovies());
        watchedMovies = new ArrayList<>(usersInput.getWatchedMovies());
        likedMovies = new ArrayList<>(usersInput.getLikedMovies());
        ratedMovies = new ArrayList<>(usersInput.getRatedMovies());
    }

    public UserInput(final ArrayList<MovieInput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public UserInput(final Credentials credentials,
                     final int tokensCount,
                     final int numFreePremiumMovies,
                     final ArrayList<MovieInput> purchasedMovies,
                     final ArrayList<MovieInput> watchedMovies,
                     final ArrayList<MovieInput> likedMovies,
                     final ArrayList<MovieInput> ratedMovies) {
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
