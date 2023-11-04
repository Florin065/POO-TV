package fileio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter @ToString
public class UserInput {
    private Credentials credentials;
    private int tokensCount = 0;
    private int numFreePremiumMovies = 15;
    private ArrayList<MovieInput> purchasedMovies = new ArrayList<>();
    private ArrayList<MovieInput> watchedMovies = new ArrayList<>();
    private ArrayList<MovieInput> likedMovies = new ArrayList<>();
    private ArrayList<MovieInput> ratedMovies = new ArrayList<>();
    private ArrayList<Notifications> notifications = new ArrayList<>();
    @JsonIgnore private ArrayList<String> subscribedGenres = new ArrayList<>();
    @JsonIgnore private Map<String, Double> rating = new HashMap<>();

    public static class Builder {
        private final Credentials credentials;
        private final int tokensCount;
        private final int numFreePremiumMovies;
        private ArrayList<MovieInput> purchasedMovies = new ArrayList<>();
        private ArrayList<MovieInput> watchedMovies = new ArrayList<>();
        private ArrayList<MovieInput> likedMovies = new ArrayList<>();
        private ArrayList<MovieInput> ratedMovies = new ArrayList<>();
        private ArrayList<Notifications> notifications = new ArrayList<>();
        private ArrayList<String> subscribedGenres = new ArrayList<>();
        private Map<String, Double> rating = new HashMap<>();

        public Builder(final Credentials credentials,
                       final int tokensCount,
                       final int numFreePremiumMovies) {
            this.credentials = credentials;
            this.tokensCount = tokensCount;
            this.numFreePremiumMovies = numFreePremiumMovies;
        }
        /**
         * used for adding purchasedMovies data in Menu.currUser at login
         */
        public Builder purchasedMovies(final ArrayList<MovieInput> purchasedMovies) {
            this.purchasedMovies = purchasedMovies;
            return this;
        }
        /**
         * used for adding watchedMovies data in Menu.currUser at login
         */
        public Builder watchedMovies(final ArrayList<MovieInput> watchedMovies) {
            this.watchedMovies = watchedMovies;
            return this;
        }
        /**
         * used for adding likedMovies data in Menu.currUser at login
         */
        public Builder likedMovies(final ArrayList<MovieInput> likedMovies) {
            this.likedMovies = likedMovies;
            return this;
        }
        /**
         * used for adding ratedMovies data in Menu.currUser at login
         */
        public Builder ratedMovies(final ArrayList<MovieInput> ratedMovies) {
            this.ratedMovies = ratedMovies;
            return this;
        }
        /**
         * used for adding notifications data in Menu.currUser at login
         */
        public Builder notifications(final ArrayList<Notifications> notifications) {
            this.notifications = notifications;
            return this;
        }
        /**
         * used for adding subscribedGenres data in Menu.currUser at login
         */
        public Builder subscribedGenres(final ArrayList<String> subscribedGenres) {
            this.subscribedGenres = subscribedGenres;
            return this;
        }
        /**
         * used for adding rating data in Menu.currUser at login
         */
        public Builder rating(final Map<String, Double> rating) {
            this.rating = rating;
            return this;
        }
        /**
         * build user with mandatory criteria (credentials, tokensCount and numFreePremiumMovies)
         * at login
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
