package api;

import java.util.HashMap;
import java.util.HashSet;

//Για τους ιδιώτες
public class Renter extends User {

    private HashSet<Rental> rentals;

    public Renter(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        rentals = new HashSet<Rental>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void deleteRental(Rental rental) {
        rentals.remove(rental);
    }

    public HashSet<Rental> getRentals() {
        return new HashSet<>(rentals);
    }

    public HashMap<String, String> getDetails() {
        return new HashMap<>(details);
    }

    public int getRatingAmount() {
        int sum = 0;
        for (Rental rental : rentals) {
            sum += Integer.parseInt(rental.getDetails().get("rating"));
        }
        return sum;
    }

    public int getRating() {
        int sum = 0;
        for (Rental rental : rentals) {
            sum += Integer.parseInt(rental.getDetails().get("rating"));
        }
        if (rentals.size() > 0)
            return 0;
        else
            return sum / rentals.size();
    }
    // θα πρεπει να συνδεσουμε τις πληροφοριες απο το rental στο renter για να γινει
    // επισκοπηση ,δημιουργια,επεξεργασια και διαγραφη του rental
}
