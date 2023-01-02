package api;

import java.util.HashMap;

/**
 * Class that represents a tenant that can review rentals
 * Apart from the fields of a User it also has a map of rentals to reviews
 */
public class Tenant extends User {
    HashMap<Rental, Review> reviews;

    /**
     * Constructor:
     * Initializes the field variables with the corresponding parameters using the
     * super constructor of the User class
     * Reviews gets initialized with an empty HashMap
     * 
     * @param name
     * @param surname
     * @param username
     * @param password
     */
    public Tenant(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        reviews = new HashMap<>();
    }

    /**
     * Adds to the map of rentals to reviews the rental and review that are passed
     * as parameters
     * 
     * @param rental
     * @param review
     */
    public void addReview(Rental rental, Review review) {
        reviews.put(rental, review);
    }

    /**
     * Removes from the map of rentals to reviews the rental that is passed as a
     * parameter and the corresponding review
     * 
     * @param rental
     */
    public void removeReview(Rental rental) {
        reviews.remove(rental);
    }

    /**
     * Returns the map of rentals to reviews of the tenant
     * 
     * @return reviews
     */
    public HashMap<Rental, Review> getReviews() {
        return reviews;
    }

    /**
     * Updates the rating of the tenant according to the reviews it has given
     */
    public void updateRating() {
        rating = 0;
        for (Review review : reviews.values()) {
            rating += review.getRating();
        }
        if (reviews.size() > 0)
            rating /= reviews.size();
    }

}