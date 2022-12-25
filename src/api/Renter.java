package api;

import java.util.HashSet;

public class Renter extends User {
    public HashSet<Rental> rentals;

    public Renter(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        rentals = new HashSet<>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public HashSet<Rental> getRentals() {
        return rentals;
    }
}
