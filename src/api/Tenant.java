package api;

import java.util.HashSet;

public class Tenant extends User {
    private HashSet<Rental> reviewdRentals;

    public Tenant(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        reviewdRentals = new HashSet<>();
    }

    public void addReview(Rental rental) {
        reviewdRentals.add(rental);
    }

    public HashSet<Rental> getReviewdRentals() {
        return reviewdRentals;
    }

}