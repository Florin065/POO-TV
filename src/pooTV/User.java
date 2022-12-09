package pooTV;

import fileio.UsersInput;
import lombok.Getter;
import lombok.Setter;

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

    public User(UsersInput usersInput) {
        name = usersInput.getName();
        password = usersInput.getPassword();
        accountType = usersInput.getAccountType();
        country = usersInput.getCountry();
        balance = usersInput.getBalance();
    }

    public User() {
    }
}
