package api;

import java.util.HashMap;

public class Tenant extends User {
    HashMap<Rental, Review> reviews;

    public Tenant(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        reviews = new HashMap<>();
    }

    public void addReview(Rental rental, Review review) {
        reviews.put(rental, review);
    }

    public void removeReview(Rental rental) {
        reviews.remove(rental);
    }

    public HashMap<Rental, Review> getReviews() {
        return reviews;
    }

    public void updateRating() {
        rating = 0;
        for (Review review : reviews.values()) {
            rating += review.getRating();
        }
        if (reviews.size() > 0)
            rating = rating / reviews.size();
        else
            rating = 0;
    }

}