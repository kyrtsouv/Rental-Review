package api;

import java.util.HashMap;

public class Rental {
    private HashMap<String, Review> reviews;
    private int rating;
    private String name;
    private Location location;
    private String description;
    private String type;
    private String owner;

    public Rental(String name, String address, String city, String postcode, String description, String type,
            String owner) {
        this.name = name;
        this.location = new Location(address, city, postcode);
        this.description = description;
        this.type = type;
        this.owner = owner;
        this.reviews = new HashMap<String, Review>();
    }

    public void print() {
        System.out.println("Name: " + name);
        location.print();
        System.out.println("Description: " + description);
        System.out.println("Type: " + type);
        System.out.println("Owner: " + owner);
        System.out.println("Rating: " + rating);
    }

    public void addReview(Review review) {
        reviews.put(review.getUser(), review);
        updateRating();
    }

    public void updateRating() {
        int sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        rating = sum / reviews.size();
    }

}
