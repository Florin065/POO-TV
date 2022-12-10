package fileio;

import lombok.Getter;
import lombok.Setter;

public class Credentials {
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

    public Credentials() {
    }

    public Credentials(Credentials credentials) {
        name = credentials.name;
        password = credentials.name;
        accountType = credentials.accountType;
        country = credentials.country;
        balance = credentials.balance;
    }

    @Override
    public String toString() {
        return "CredentialsInput{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", country='" + country + '\'' +
                ", balance=" + balance +
                '}';
    }
}
