package api;

import java.util.HashSet;

public class Tenant extends User{
    private HashSet<Review> reviews;

    public Tenant(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        reviews = new HashSet<>();
    }
}
