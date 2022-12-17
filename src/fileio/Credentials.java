package fileio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
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
