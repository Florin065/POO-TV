package pooTV.command.authenticated.logout;

import fileio.Credentials;
import fileio.UserInput;
import pooTV.Menu;

public class Logout {
    public static void logout() {
        Menu.setCurrPage("homepage unauth");
        Menu.setCurrML(null);
        Credentials nullCred = new Credentials(null, null, null, null, 0);
        Menu.setCurrUser(new UserInput(nullCred, 0, 15, null, null, null, null));
    }
}
