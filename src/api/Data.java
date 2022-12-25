package api;

import java.io.Serializable;
import java.util.HashMap;

public class Data implements Serializable {
    HashMap<String, User> users;
    HashMap<String, Rental> rentals;

    public Data() {
        users = new HashMap<>();
        rentals = new HashMap<>();
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public HashMap<String, Rental> getRentals() {
        return rentals;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void addRental(Rental rental) {
        rentals.put(rental.getName(), rental);
    }

    public void removeRental(Rental rental) {
        rentals.remove(rental.getName());
    }
}
