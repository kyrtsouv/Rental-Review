package api;

import java.util.HashMap;

public class Rental {
    private HashMap<String, String> details;
    private HashMap<String, Review> reviews;

    public Rental(String name, String address, String city, String postcode, String description, String type,
            String owner) {
        details = new HashMap<>() {
            {
                put("name", name);
                put("address", address);
                put("city", city);
                put("postcode", postcode);
                put("description", description);
                put("type", type);
                put("owner", owner);
                put("rating", "0");
            }
        };
        this.reviews = new HashMap<String, Review>();
    }

    public HashMap<String, String> getDetails() {
        return new HashMap<>(details);
    }

    public String getName() {
        return details.get("name");
    }

    public HashMap<String, String> getPreview() {
        return new HashMap<>() {
            {
                put("name", details.get("name"));
                put("type", details.get("type"));
                put("address", details.get("address"));
                put("city", details.get("city"));
                put("postcode", details.get("postcode"));
                put("rating", details.get("rating"));
            }
        };
    }

    public HashMap<String, Review> getReviews() {
        return new HashMap<>(reviews);
    }

    public void addReview(Review review) {
        reviews.put(review.getUser(), review);
        updateRating();
    }

    public void editReview(Review review) {
        reviews.replace(review.getUser(), review);
        updateRating();
    }

    public void updateRating() {
        int sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        if (reviews.size() > 0)
            details.replace("rating", String.valueOf(sum / reviews.size()));
        else
            details.replace("rating", "0");
    }

    public void deleteReview(String user) {
        reviews.remove(user);
        updateRating();
    }

    public void editRental(String name, String address, String city, String postcode, String description, String type,
            String owner) {
        details.replace("name", name);
        details.replace("address", address);
        details.replace("city", city);
        details.replace("postcode", postcode);
        details.replace("description", description);
        details.replace("type", type);
    }

    // θα πρεπει να μπορει να γινει ελεχγος χρηστη για ειτε την επεξεργασια σχολιου
    // ειτε του rental
    // plus getter για το κομματι του gui

}