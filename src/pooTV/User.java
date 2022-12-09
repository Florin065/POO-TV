package pooTV;

import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class User {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String accountType;
    @Getter @Setter
    private String country;
    @Getter @Setter
    private int balance;
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

    public User(UserInput usersInput) {
        name = usersInput.getName();
        password = usersInput.getPassword();
        accountType = usersInput.getAccountType();
        country = usersInput.getCountry();
        balance = usersInput.getBalance();
        tokensCount = 0;
        purchasedMovies = null;
        watchedMovies = null;
        likedMovies = null;
        ratedMovies = null;
    }

    public User() {
    }
}
