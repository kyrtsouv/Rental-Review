package api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class that represents the data of the application
 * It has a map of (String)usernames to users and a set of (String)names
 * to rentals
 */
public class Data implements Serializable {
    HashMap<String, User> users;
    HashSet<Rental> rentals;

    /**
     * Constructor:
     * Initializes the field variables with empty map and set
     */
    public Data() {
        users = new HashMap<>();
        rentals = new HashSet<>();
    }

    /**
     * Returns the map of users
     * 
     * @return users
     */
    public HashMap<String, User> getUsers() {
        return users;
    }

    /**
     * Returns the set of rentals
     * 
     * @return rentals
     */
    public HashSet<Rental> getRentals() {
        return rentals;
    }

    /**
     * Adds the user that is passed as a parameter to the map of usernames to
     * users
     * 
     * @param user
     */
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Adds the rental that is passed as a parameter to the set of rentals
     * 
     * @param rental
     */
    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    /**
     * Removes the rental that is passed as a parameter from the set of rentals
     * 
     * @param rental
     */
    public void removeRental(Rental rental) {
        rentals.remove(rental);
    }
}
