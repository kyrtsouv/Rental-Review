package api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Rental implements Serializable {
    int rating;
    int numOfRatings;
    String searchID;
    String name;
    String type;
    String address;
    String city;
    String zipcode;
    String description;
    HashSet<String> amenities;
    HashMap<Tenant, Review> reviews;
    Renter owner;

    public Rental(String name, String type, String address, String city, String zipcode, String description,
            HashSet<String> amenities, Renter owner) {
        this.rating = 0;
        this.numOfRatings = 0;
        this.searchID = (name + " " + type + " " + address + " " + city + " " + zipcode).toLowerCase();
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.description = description;
        this.amenities = amenities;
        this.reviews = new HashMap<Tenant, Review>();
        this.owner = owner;
    }

    public int getRating() {
        return rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public HashMap<Tenant, Review> getReviews() {
        return reviews;
    }

    public String getSearchID() {
        return searchID;
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

    public Renter getOwner() {
        return owner;
    }

    public void setName(String name) {
        this.name = name;
        updateSearchID();
    }

    public void setType(String type) {
        this.type = type;
        updateSearchID();
    }

    public void setAddress(String address) {
        this.address = address;
        updateSearchID();
    }

    public void setCity(String city) {
        this.city = city;
        updateSearchID();
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
        updateSearchID();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmenities(HashSet<String> amenities) {
        this.amenities = amenities;
    }

    public void updateSearchID() {
        this.searchID = (name + " " + type + " " + address + " " + city + " " + zipcode).toLowerCase();
    }

    public void updateRating() {
        this.rating = 0;
        for (Review review : reviews.values()) {
            this.rating += review.getRating();
        }
        if (reviews.size() > 0)
            this.rating /= reviews.size();
        else
            this.rating = 0;
        this.numOfRatings = reviews.size();
    }

    public void addReview(Review review) {
        reviews.put(review.getTenant(), review);
    }

    public void removeReview(Review review) {
        reviews.remove(review.getTenant());
    }
}
