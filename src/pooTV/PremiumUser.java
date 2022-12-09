package pooTV;

import fileio.UserInput;
import lombok.Getter;
import lombok.Setter;

public class PremiumUser extends User {
    @Getter @Setter
    private int numFreePremiumMovies;

    public PremiumUser(UserInput userInput) {
        super(userInput);
        numFreePremiumMovies = 15;
    }

    public PremiumUser() {

    }
}
