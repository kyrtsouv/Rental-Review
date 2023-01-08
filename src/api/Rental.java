package api;

import java.util.HashMap;
import java.util.HashSet;
import java.io.Serializable;

public class Rental implements Serializable {
    private HashMap<String, String> details;
    private HashMap<Tenant, Review> reviews;
    private HashSet<String> amenities;
    private Renter owner;
    private HashSet<String> searchFilters;

    public Rental(String name, String address, String city, String postcode, String description, String type,
            Renter owner, HashSet<String> amenities) {
        details = new HashMap<>() {
            {
                put("name", name);
                put("address", address);
                put("city", city);
                put("postcode", postcode);
                put("description", description);
                put("type", type);
                put("location", address + ", " + city + ", " + postcode);
            }
        };
        this.owner = owner;
        this.reviews = new HashMap<Tenant, Review>();
        this.amenities = amenities;
        this.searchFilters = new HashSet<>() {
            {
                add(name);
                add(address);
                add(city);
                add(postcode);
                add(type);
            }
        };
    }

    public HashSet<String> getSearchFilters() {
        return new HashSet<>(searchFilters);
    }

    public String get(String key) {
        return details.get(key);
    }

    public Renter getOwner() {
        return owner;
    }

    public float getRating() {
        int sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        if (reviews.size() > 0)
            return (float) sum / reviews.size();
        return 0;
    }

    public HashSet<String> getAmenities() {
        return new HashSet<>(amenities);
    }

    public HashMap<Tenant, Review> getReviews() {
        return new HashMap<>(reviews);
    }

    public void addReview(Review review) {
        reviews.put(review.getUser(), review);
    }

    public void deleteReview(Tenant user) {
        reviews.remove(user);
    }

    public void editRental(String name, String address, String city, String postcode, String description, String type,
            HashSet<String> amenities) {
        details.replace("name", name);
        details.replace("address", address);
        details.replace("city", city);
        details.replace("postcode", postcode);
        details.replace("description", description);
        details.replace("type", type);
        details.replace("location", address + ", " + city + ", " + postcode);
        this.amenities = amenities;
    }

}