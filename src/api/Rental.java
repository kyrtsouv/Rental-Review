package api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class that represents a rental property
 * It has a name, a type, an address, a city, a zipcode, a description, a set of
 * amenities, a rating, a number of reviews, a map of reviewers to reviews
 * and the Renter that owns it
 * It also has a searchID that is used to search for it in the database
 */
public class Rental implements Serializable {
    int rating;
    int totalReviews;
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

    /**
     * Constructor:
     * Initializes the field variables with the corresponding parameters
     * Rating and number of ratings get initialized with 0
     * SearchID gets initialized with the name, type, address, city and zipcode of
     * the rental in lowercase letters
     * Reviews gets initialized with an empty HashMap of reviewers to reviews
     * 
     * @param name
     * @param type
     * @param address
     * @param city
     * @param zipcode
     * @param description
     * @param amenities
     * @param owner
     */
    public Rental(String name, String type, String address, String city, String zipcode, String description,
            HashSet<String> amenities, Renter owner) {
        this.rating = 0;
        this.totalReviews = 0;
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

    // ------------------ GETTERS ------------------ //
    /**
     * Returns the rating of the rental
     * 
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns the number of reviews of the rental
     * 
     * @return totalRatings
     */
    public int gettotalReviews() {
        return totalReviews;
    }

    /**
     * Returns the map of reviewers to reviews of the rental
     * 
     * @return reviews
     */
    public HashMap<Tenant, Review> getReviews() {
        return reviews;
    }

    /**
     * Returns the searchID of the rental
     * 
     * @return searchID
     */
    public String getSearchID() {
        return searchID;
    }

    /**
     * Returns the name of the rental
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the rental
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the location of the rental as a String
     * 
     * @return location
     */
    public String getLocation() {
        return address + ", " + city + ", " + zipcode;
    }

    /**
     * Returns the address of the rental
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the city of the rental
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the zipcode of the rental
     * 
     * @return zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Returns the description of the rental
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the set of amenities of the rental
     * 
     * @return amenities
     */
    public HashSet<String> getAmenities() {
        return amenities;
    }

    /**
     * Returns the owner of the rental
     * 
     * @return owner
     */
    public Renter getOwner() {
        return owner;
    }

    // ------------------ SETTERS ------------------ //

    /**
     * Updates the name field according to the parameter and updates the
     * searchID
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        updateSearchID();
    }

    /**
     * Updates the type field according to the parameter and updates the
     * searchID
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
        updateSearchID();
    }

    /**
     * Updates the address field according to the parameter and updates the
     * searchID
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
        updateSearchID();
    }

    /**
     * Updates the city field according to the parameter and updates the
     * searchID
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
        updateSearchID();
    }

    /**
     * Updates the zipcode field according to the parameter and updates the
     * searchID
     * 
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
        updateSearchID();
    }

    /**
     * Updates the description field according to the parameter
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Updates the amenities field according to the parameter
     * 
     * @param amenities
     */
    public void setAmenities(HashSet<String> amenities) {
        this.amenities = amenities;
    }

    // ------------------ OTHER METHODS ------------------ //

    /**
     * Updates the searchID field according to the name, type, address, city
     * and zipcode
     */
    public void updateSearchID() {
        this.searchID = (name + " " + type + " " + address + " " + city + " " + zipcode).toLowerCase();
    }

    /**
     * Updates the rating according to the ratings of the
     * reviews
     */
    public void updateRating() {
        this.rating = 0;
        for (Review review : reviews.values()) {
            this.rating += review.getRating();
        }
        if (reviews.size() > 0)
            this.rating /= reviews.size();
        else
            this.rating = 0;
    }

    /**
     * Adds to the rental the review that is passed as a parameter and increases the
     * number of reviews
     * 
     * @param review
     */
    public void addReview(Review review) {
        reviews.put(review.getTenant(), review);
        totalReviews++;
    }

    /**
     * Removes from the rental the review that is passed as a parameter and
     * decreases the number of reviews
     * 
     * @param review
     */
    public void removeReview(Review review) {
        reviews.remove(review.getTenant());
        totalReviews--;
    }
}
