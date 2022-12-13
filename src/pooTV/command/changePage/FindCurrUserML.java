package pooTV.command.changePage;

import fileio.MovieInput;
import fileio.UserInput;

import java.util.ArrayList;

public class FindCurrUserML {
    public static void find(UserInput currUser, ArrayList<MovieInput> currML,
                            ArrayList<MovieInput> moviesInputList) {
        for (MovieInput iterator : moviesInputList) {
            for (String bannedCountry : iterator.getCountriesBanned()) {
                if (!currUser.getCredentials().getCountry().equals(bannedCountry)) {
                    currML.add(iterator);
                }
            }
        }
    }
}
