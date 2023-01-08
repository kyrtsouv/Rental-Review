package api;

import java.util.HashMap;

public class Tenant extends User {
    HashMap<Rental, Review> reviews;

    public Tenant(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        reviews = new HashMap<Rental, Review>();
    }

    public void addReview(Rental rental, Review review) {
        reviews.put(rental, review);
    }

    public void deleteReview(Rental rental) {
        reviews.remove(rental);
    }

    public HashMap<Rental, Review> getReviews() {
        return new HashMap<>(reviews);
    }

    public float getRating() {
        float sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        if (reviews.size() > 0)
            return sum / reviews.size();
        else
            return 0;
    }
}