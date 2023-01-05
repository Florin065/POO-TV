package pootv.recommendation;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import fileio.Notifications;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.NotBannedMovies;
import pootv.command.authenticated.seeDetails.CommandOutput;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public final class Recommendation {
    private Recommendation() {
    }

    /**
     *
     */
    public static void recommendation() {
        Map<String, Integer> genreTop = new HashMap<>();
        UserInput user = Menu.getCurrUser();
        if (user.getCredentials().getName() != null
                && user.getCredentials().getAccountType().equals("premium")) {
            for (MovieInput movie : user.getLikedMovies()) {
                for (String genre : movie.getGenres()) {
                    genreTop.put(genre, 0);
                }
            }
            for (MovieInput movie : user.getLikedMovies()) {
                for (String genre : movie.getGenres()) {
                    genreTop.put(genre, genreTop.get(genre) + 1);
                }
            }
           Map<String, Integer> sortedGenreTop = genreTop
                   .entrySet()
                   .stream()
                   .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                   .collect(
                           toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                   LinkedHashMap::new));

            ArrayList<MovieInput> notBanned = new ArrayList<>();
            NotBannedMovies.get(notBanned);
            ArrayList<MovieInput> notWatched = new ArrayList<>();
            for (MovieInput movieInput : notBanned) {
                int ok = 0;
                for (MovieInput movieInput1 : user.getWatchedMovies()) {
                    if (movieInput1.getName().equals(movieInput.getName())) {
                        ok = 1;
                        break;
                    }
                }
                if (ok == 0) {
                    notWatched.add(movieInput);
                }
            }
            notWatched.sort((t1, t2) -> Integer.compare(t2.getNumLikes(), t1.getNumLikes()));

            UserInput userCopy = new UserInput(user);
            for (String genre : sortedGenreTop.keySet()) {
                if (notWatched.get(0) != null && notWatched.get(0).getGenres().contains(genre)) {
                    Notifications notifications = new Notifications();
                    notifications.setMovieName(notWatched.get(0).getName());
                    notifications.setMessage("Recommendation");
                    userCopy.getNotifications().add(notifications);
                    user = new UserInput(userCopy);

                    ObjectMapper mapper = new ObjectMapper();
                    Menu.getOutput()
                            .add(mapper.valueToTree(new CommandOutput(null, null, user)));
                    return;
                }
            }

            Notifications notifications = new Notifications();
            notifications.setMovieName("No recommendation");
            notifications.setMessage("Recommendation");
            userCopy.getNotifications().add(notifications);
            user = new UserInput(userCopy);
            ObjectMapper mapper = new ObjectMapper();
            Menu.getOutput()
                    .add(mapper.valueToTree(new CommandOutput(null, null, user)));
        }
    }
}
