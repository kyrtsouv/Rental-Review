package api;

import java.util.HashMap;

public class Rental {
    private HashMap<String, String> details;
    private HashMap<String, Review> reviews;

    public Rental(String name, String address, String city, String postcode, String description, String type,
            String owner) {
        details = new HashMap<>();
        details.put("name", name);
        details.put("address", address);
        details.put("city", city);
        details.put("postcode", postcode);
        details.put("description", description);
        details.put("type", type);
        details.put("owner", owner);
        details.put("rating", String.valueOf(null));

        this.reviews = new HashMap<String, Review>();
    }

    public HashMap<String, String> getRental() {
        return details;
    }

    public HashMap<String, Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.put(review.getUser(), review);
        updateRating();
    }

    public void editReview(String user, String comment, int rating) {
        reviews.get(user).editReview(comment, rating);
        updateRating();
    }

    public void updateRating() {
        int sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        details.replace("rating", String.valueOf(sum / reviews.size()));
    }

    public void deleteReview(String user) {
        reviews.remove(user);
        updateRating();
    }

    public void editRental() {
        // TODO
    }

    // θα πρεπει να μπορει να γινει ελεχγος χρηστη για ειτε την επεξεργασια σχολιου
    // ειτε του rental
    // plus getter για το κομματι του gui

}