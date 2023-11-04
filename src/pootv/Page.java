package pootv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Page {
    private Page() {
    }

    /**
     * Creating the page hierarchy.
     */
    public static Map<String, ArrayList<String>> createPageHierarchy() {
        Map<String, ArrayList<String>> pages = new HashMap<>();

        pages.put("homepage unauth", new ArrayList<>());
        pages.put("login", new ArrayList<>());
        pages.put("register", new ArrayList<>());
        pages.put("homepage auth", new ArrayList<>());
        pages.put("movies", new ArrayList<>());
        pages.put("see details", new ArrayList<>());
        pages.put("upgrades", new ArrayList<>());
        pages.put("logout", new ArrayList<>());

        pages.get("homepage unauth").add("login");
        pages.get("homepage unauth").add("register");

        pages.get("homepage auth").add("movies");
        pages.get("homepage auth").add("upgrades");
        pages.get("homepage auth").add("logout");

        pages.get("movies").add("homepage auth");
        pages.get("movies").add("see details");
        pages.get("movies").add("logout");

        pages.get("see details").add("homepage auth");
        pages.get("see details").add("movies");
        pages.get("see details").add("upgrades");
        pages.get("see details").add("logout");

        pages.get("upgrades").add("homepage auth");
        pages.get("upgrades").add("movies");
        pages.get("upgrades").add("logout");

        return pages;
    }
}
