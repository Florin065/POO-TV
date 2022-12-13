package pooTV.command.authenticated.logout;

import fileio.Credentials;
import fileio.UserInput;
import pooTV.Menu;

import java.util.ArrayList;

public class Logout {
    public static void logout() {
        Menu.setCurrPage("homepage unauth");
        Menu.setCurrML(new ArrayList<>());
        Credentials nullCred = new Credentials(null, null, null, null, "0");
        Menu.setCurrUser(new UserInput(nullCred, 0, 15, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
}
