package fileio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ToString
public class UserInput {
    @Getter @Setter
    private Credentials credentials;
    @Getter @Setter
    private int tokensCount = 0;
    @Getter @Setter
    private int numFreePremiumMovies = 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1;
    @Getter @Setter
    private ArrayList<MovieInput> purchasedMovies = new ArrayList<>();
    @Getter @Setter
    private ArrayList<MovieInput> watchedMovies = new ArrayList<>();
    @Getter @Setter
    private ArrayList<MovieInput> likedMovies = new ArrayList<>();
    @Getter @Setter
    private ArrayList<MovieInput> ratedMovies = new ArrayList<>();
    @Getter @Setter
    private ArrayList<Notifications> notifications = new ArrayList<>();
    @Getter @Setter @JsonIgnore
    private ArrayList<String> subscribedGenres = new ArrayList<>();
    @Getter @Setter @JsonIgnore
    private Map<String, Double> rating = new HashMap<>();

    public static class Builder {
        private final Credentials credentials;
        private int tokensCount = 0;
        private int numFreePremiumMovies = 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1;
        private ArrayList<MovieInput> purchasedMovies = new ArrayList<>();
        private ArrayList<MovieInput> watchedMovies = new ArrayList<>();
        private ArrayList<MovieInput> likedMovies = new ArrayList<>();
        private ArrayList<MovieInput> ratedMovies = new ArrayList<>();
        private ArrayList<Notifications> notifications = new ArrayList<>();
        private ArrayList<String> subscribedGenres = new ArrayList<>();
        private Map<String, Double> rating = new HashMap<>();

        public Builder(final Credentials credentials) {
            this.credentials = credentials;
        }
        /**
         *
         * @param tokensCount
         * @return
         */
        public Builder tokensCount(final int tokensCount) {
            this.tokensCount = tokensCount;
            return this;
        }
        /**
         *
         * @param numFreePremiumMovies
         * @return
         */
        public Builder numFreePremiumMovies(final int numFreePremiumMovies) {
            this.numFreePremiumMovies = numFreePremiumMovies;
            return this;
        }
        /**
         *
         * @param purchasedMovies
         * @return
         */
        public Builder purchasedMovies(final ArrayList<MovieInput> purchasedMovies) {
            this.purchasedMovies = purchasedMovies;
            return this;
        }
        /**
         *
         * @param watchedMovies
         * @return
         */
        public Builder watchedMovies(final ArrayList<MovieInput> watchedMovies) {
            this.watchedMovies = watchedMovies;
            return this;
        }
        /**
         *
         * @param likedMovies
         * @return
         */
        public Builder likedMovies(final ArrayList<MovieInput> likedMovies) {
            this.likedMovies = likedMovies;
            return this;
        }
        /**
         *
         * @param ratedMovies
         * @return
         */
        public Builder ratedMovies(final ArrayList<MovieInput> ratedMovies) {
            this.ratedMovies = ratedMovies;
            return this;
        }
        /**
         *
         * @param notifications
         * @return
         */
        public Builder notifications(final ArrayList<Notifications> notifications) {
            this.notifications = notifications;
            return this;
        }
        /**
         *
         * @param subscribedGenres
         * @return
         */
        public Builder subscribedGenres(final ArrayList<String> subscribedGenres) {
            this.subscribedGenres = subscribedGenres;
            return this;
        }
        /**
         *
         * @param rating
         * @return
         */
        public Builder rating(final Map<String, Double> rating) {
            this.rating = rating;
            return this;
        }
        /**
         *
         * @return
         */
        public UserInput build() {
            return new UserInput(this);
        }
    }

    public UserInput(final Builder builder) {
        this.credentials = builder.credentials;
        this.tokensCount = builder.tokensCount;
        this.numFreePremiumMovies = builder.numFreePremiumMovies;
        this.purchasedMovies = builder.purchasedMovies;
        this.watchedMovies = builder.watchedMovies;
        this.likedMovies = builder.likedMovies;
        this.ratedMovies = builder.ratedMovies;
        this.notifications = builder.notifications;
        this.subscribedGenres = builder.subscribedGenres;
        this.rating = builder.rating;
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
        rating = new HashMap<>(usersInput.rating);
    }

    public UserInput(final Credentials credentials,
                     final int tokensCount,
                     final int numFreePremiumMovies,
                     final ArrayList<MovieInput> purchasedMovies,
                     final ArrayList<MovieInput> watchedMovies,
                     final ArrayList<MovieInput> likedMovies,
                     final ArrayList<MovieInput> ratedMovies,
                     final ArrayList<Notifications> notifications,
                     final ArrayList<String> subscribedGenres,
                     final Map<String, Double> rating) {
        this.credentials = credentials;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
        this.notifications = notifications;
        this.subscribedGenres = subscribedGenres;
        this.rating = rating;
    }

    public UserInput() {
    }
}
