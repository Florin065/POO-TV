package fileio;

import lombok.Getter;
import lombok.Setter;

public class UsersInput {
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

    public UsersInput() {
    }

    @Override
    public String toString() {
        return "UsersInput{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", country='" + country + '\'' +
                ", balance=" + balance +
                '}';
    }
}
