package pootv.recommendation;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.MovieInput;
import fileio.Notifications;
import fileio.UserInput;
import pootv.Menu;
import pootv.command.NotBannedMovies;
import pootv.CommandOutput;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public final class Recommendation {
    private Recommendation() {
    }

    /**
     * At the end of the action string, the connected premium user will receive a movie
     * recommendation based on the movie genres they like the most.
     * Initially, we find all the genres of user's liked movies, which we increase with
     * each appearance. They are stored in a HashMap, the key representing the genre and the value
     * the number of likes.
     * After that, we sort the HashMap descending (according to the number of likes each genre has)
     * using a stream method.
     * Then, we search in the list of movies available in the user's country, the movies that he
     * has not watched, and sort the new list in descending order according to the number of likes.
     * If the first movie in the database with the highest number of likes is found that has not
     * been watched before by the user and is part of the movie genre most appreciated by him,
     * he will receive a recommendation with the name of the movie, in the notification list.
     * if there is no unwatched movie in the most popular genre category, it will move on to the
     * user's next most popular genre, and so on until either we have found a movie that meets the
     * problem data, or we have exhausted the user's genres.
     * If no recommendation was found, the string "No recommendation" will be written instead of
     * the movie name.
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
            NotBannedMovies.notBannedMovies(notBanned);
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

            for (String genre : sortedGenreTop.keySet()) {
                if (notWatched.get(0) != null && notWatched.get(0).getGenres().contains(genre)) {
                    Notifications notifications = new Notifications();
                    notifications.setMovieName(notWatched.get(0).getName());
                    notifications.setMessage("Recommendation");
                    user.getNotifications().add(notifications);

                    ObjectMapper mapper = new ObjectMapper();
                    Menu.getOutput()
                            .add(mapper.valueToTree(new CommandOutput(null, null, user)));
                    return;
                }
            }

            Notifications notifications = new Notifications();
            notifications.setMovieName("No recommendation");
            notifications.setMessage("Recommendation");
            user.getNotifications().add(notifications);
            ObjectMapper mapper = new ObjectMapper();
            Menu.getOutput()
                    .add(mapper.valueToTree(new CommandOutput(null, null, user)));
        }
    }
}
