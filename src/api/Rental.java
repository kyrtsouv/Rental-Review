package api;

import java.util.HashMap;
import java.util.HashSet;

public class Rental {
    private int rating;
    private int numOfRatings;
    private String name;
    private String type;
    private String address;
    private String city;
    private String zipcode;
    private String description;
    private HashSet<String> amenities;
    private HashMap<User, Review> reviews;
    private User owner;

    public Rental(String name, String type, String address, String city, String zipcode, String description,
            HashSet<String> amenities, User owner) {
        this.rating = 0;
        this.numOfRatings = 0;
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.description = description;
        this.amenities = amenities;
        this.owner = owner;
        this.reviews = new HashMap<User, Review>();
    }

    public int getRating() {
        return rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getDescription() {
        return description;
    }

    public HashSet<String> getAmenities() {
        return amenities;
    }

    public HashMap<User, Review> getReviews() {
        return reviews;
    }

    public User getOwner() {
        return owner;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmenities(HashSet<String> amenities) {
        this.amenities = amenities;
    }

    public void updateRating() {
        this.rating = 0;
        for (Review review : reviews.values()) {
            this.rating += review.getRating();
        }
        this.rating /= reviews.size();
        this.numOfRatings++;
    }

    public void addReview(Review review) {
        reviews.put(review.getUser(), review);
        updateRating();
        numOfRatings += 1;
    }

    public void removeReview(Review review) {
        reviews.remove(review.getUser());
        updateRating();
        numOfRatings -= 1;
    }
}
