package pootv.command.authenticated.logout;

import fileio.Credentials;
import fileio.UserInput;
import pootv.DataBase;
import pootv.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Logout {
    private Logout() {
    }

    /**
     * Logout action. Updates the current user's data in the database, then disconnects him from
     * the account.
     */
    public static void logout() {
        List<UserInput> userList = new ArrayList<>(DataBase.getDataBase().getUsers());
        userList.set(Menu.getUserIndex(), Menu.getCurrUser());
        DataBase.getDataBase().setUsers(new ArrayList<>(userList));
        Menu.setCurrPage("homepage unauth");
        Menu.setCurrUser(new UserInput(new Credentials(), 0, 15,
                                       new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                                       new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>()));
        Menu.setLastPages(new ArrayList<>());
    }
}
