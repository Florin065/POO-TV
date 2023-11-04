package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public Credentials() {
        this.name = null;
        this.password = null;
        this.accountType = null;
        this.country = null;
        this.balance = "0";
    }

    public Credentials(final Credentials credentials) {
        name = credentials.name;
        password = credentials.password;
        accountType = credentials.accountType;
        country = credentials.country;
        balance = credentials.balance;
    }
}
