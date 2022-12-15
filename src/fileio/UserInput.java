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
    private int numFreePremiumMovies = 15;
    @Getter @Setter
    private ArrayList<MovieInput> purchasedMovies = new ArrayList<>();
    @Getter @Setter
    private ArrayList<MovieInput> watchedMovies = new ArrayList<>();
    @Getter @Setter
    private ArrayList<MovieInput> likedMovies = new ArrayList<>();
    @Getter @Setter
    private ArrayList<MovieInput> ratedMovies = new ArrayList<>();

    public UserInput(UserInput usersInput) {
        credentials = new Credentials(usersInput.getCredentials());
        tokensCount = usersInput.getTokensCount();
        numFreePremiumMovies = usersInput.getNumFreePremiumMovies();
        purchasedMovies = usersInput.getPurchasedMovies();
        watchedMovies = usersInput.getWatchedMovies();
        likedMovies = usersInput.getLikedMovies();
        ratedMovies = usersInput.getRatedMovies();
    }

    public UserInput(ArrayList<MovieInput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public UserInput(Credentials credentials, int tokensCount, int numFreePremiumMovies,
                     ArrayList<MovieInput> purchasedMovies, ArrayList<MovieInput> watchedMovies,
                     ArrayList<MovieInput> likedMovies, ArrayList<MovieInput> ratedMovies) {
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
