package api;

import java.util.HashSet;

public class Tenant extends User {
    HashSet<Review> reviews;

    public Tenant(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        reviews = new HashSet<Review>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void deleteReview(Review review) {
        reviews.remove(review);
    }

    public HashSet<Review> getReviews() {
        return new HashSet<>(reviews);
    }

    public String getRating() {
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        if (reviews.size() > 0)
            return Rounder.round(sum / reviews.size());
        else
            return "0";
    }
}