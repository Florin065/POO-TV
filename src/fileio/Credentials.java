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
    }

    public Credentials(String name, String password,
                       String accountType, String country, String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public Credentials(Credentials credentials) {
        name = credentials.name;
        password = credentials.password;
        accountType = credentials.accountType;
        country = credentials.country;
        balance = credentials.balance;
    }
}
