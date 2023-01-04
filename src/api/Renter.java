package api;

import java.util.HashMap;
import java.util.HashSet;

//Για τους ιδιώτες
public class Renter {

    private HashSet<Rental> rentals;
    private HashMap<String, String> details;

    public Renter(String name, String surname, String username, String password) {
        rentals = new HashSet<Rental>();
        details = new HashMap<String, String>() {
            {
                put("name", name);
                put("surname", surname);
                put("username", username);
                put("password", password);
                put("ratingAmount", "0");
                put("rating", "0");
            }
        };
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void editRental(Rental rental, String name, String address, String city, String postcode, String description,
                           String type, String owner) {
        if (rentals.contains(rental))
            rental.editRental(name, address, city, postcode, description, type, owner);
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
