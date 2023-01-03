package fileio;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Getter @Setter
    private ArrayList<Notifications> notifications;
    @Getter @Setter @JsonIgnore
    private ArrayList<String> subscribedGenres;

    public static class Builder {
        private Credentials credentials;
        private int tokensCount;
        private int numFreePremiumMovies;
        private ArrayList<MovieInput> purchasedMovies;
        private ArrayList<MovieInput> watchedMovies;
        private ArrayList<MovieInput> likedMovies;
        private ArrayList<MovieInput> ratedMovies;
        private ArrayList<Notifications> notifications;
        private ArrayList<String> subscribedGenres;

        public Builder(Credentials credentials) {
            this.credentials = credentials;
        }

        public Builder tokensCount(int tokensCount) {
            this.tokensCount = tokensCount;
            return this;
        }
        public Builder numFreePremiumMovies(int numFreePremiumMovies) {
            this.numFreePremiumMovies = numFreePremiumMovies;
            return this;
        }
        public Builder purchasedMovies(ArrayList<MovieInput> purchasedMovies) {
            this.purchasedMovies = purchasedMovies;
            return this;
        }
        public Builder watchedMovies(ArrayList<MovieInput> watchedMovies) {
            this.watchedMovies = watchedMovies;
            return this;
        }
        public Builder likedMovies(ArrayList<MovieInput> likedMovies) {
            this.likedMovies = likedMovies;
            return this;
        }
        public Builder ratedMovies(ArrayList<MovieInput> ratedMovies) {
            this.ratedMovies = ratedMovies;
            return this;
        }
        public Builder notifications(ArrayList<Notifications> notifications) {
            this.notifications = notifications;
            return this;
        }
        public Builder subscribedGenres(ArrayList<String> subscribedGenres) {
            this.subscribedGenres = subscribedGenres;
            return this;
        }

        public UserInput build() {
            return new UserInput(this);
        }
    }

    public UserInput(Builder builder) {
        this.credentials = builder.credentials;
        this.tokensCount = builder.tokensCount;
        this.numFreePremiumMovies = builder.numFreePremiumMovies;
        this.purchasedMovies = builder.purchasedMovies;
        this.watchedMovies = builder.watchedMovies;
        this.likedMovies = builder.likedMovies;
        this.ratedMovies = builder.ratedMovies;
        this.notifications = builder.notifications;
        this.subscribedGenres = builder.subscribedGenres;
    }

    public UserInput(final UserInput usersInput) {
        credentials = new Credentials(usersInput.getCredentials());
        tokensCount = usersInput.tokensCount;
        numFreePremiumMovies = usersInput.numFreePremiumMovies;
        purchasedMovies = new ArrayList<>(usersInput.purchasedMovies);
        watchedMovies = new ArrayList<>(usersInput.watchedMovies);
        likedMovies = new ArrayList<>(usersInput.likedMovies);
        ratedMovies = new ArrayList<>(usersInput.ratedMovies);
        notifications = new ArrayList<>(usersInput.notifications);
        subscribedGenres = new ArrayList<>(usersInput.subscribedGenres);
    }

    public UserInput(final Credentials credentials,
                     final int tokensCount,
                     final int numFreePremiumMovies,
                     final ArrayList<MovieInput> purchasedMovies,
                     final ArrayList<MovieInput> watchedMovies,
                     final ArrayList<MovieInput> likedMovies,
                     final ArrayList<MovieInput> ratedMovies,
                     final ArrayList<Notifications> notifications,
                     final ArrayList<String> subscribedGenres) {
        this.credentials = credentials;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
        this.notifications = notifications;
        this.subscribedGenres = subscribedGenres;
    }

    public UserInput() {
    }
}
