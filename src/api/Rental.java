package api;

import java.util.HashMap;
import java.util.HashSet;

public class Rental {
    private HashMap<String, String> details;
    private HashMap<String, Review> reviews;
    private HashMap<String, HashSet<String>> amenities;

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
            }
        };
        this.reviews = new HashMap<String, Review>();
        this.amenities = new HashMap<String, HashSet<String>>();
    }

    public HashMap<String, String> getPreview() {
        return new HashMap<>() {
            {
                put("name", details.get("name"));
                put("type", details.get("type"));
                put("address", details.get("address"));
                put("city", details.get("city"));
                put("postcode", details.get("postcode"));
            }
        };
    }

    public HashMap<String, HashSet<String>> getAmenities() {
        return new HashMap<>(amenities);
    }

    public HashMap<String, String> getDetails() {
        return new HashMap<>(details);
    }

    public HashMap<String, Review> getReviews() {
        return new HashMap<>(reviews);
    }

    public void addReview(Review review) {
        reviews.put(review.getUser(), review);
    }

    public void deleteReview(String user) {
        reviews.remove(user);
    }

    public String getRating() {
        int sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        if (reviews.size() > 0)
            return Rounder.round(sum / reviews.size());
        return "0";
    }

    public void editRental(String name, String address, String city, String postcode, String description, String type,
            String owner) {
        details.replace("name", name);
        details.replace("address", address);
        details.replace("city", city);
        details.replace("postcode", postcode);
        details.replace("description", description);
        details.replace("type", type);
        // this.amenities = amenities;
    }



}