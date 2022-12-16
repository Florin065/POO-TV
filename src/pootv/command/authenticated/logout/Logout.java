package pootv.command.authenticated.logout;

import fileio.Credentials;
import fileio.UserInput;
import pootv.Menu;

import java.util.ArrayList;

public final class Logout {
    private Logout() {
    }

    /**
     *
     */
    public static void logout() {
        Menu.setCurrPage("homepage unauth");
        Credentials nullCred = new Credentials(null, null, null, null, "0");
        Menu.setCurrUser(new UserInput(nullCred, 0, 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
}
