package api;

import java.util.HashSet;

//Για τους ιδιώτες
public class Renter extends User {

    HashSet<Rental> rentals;

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

    public int getRatingAmount() {
        int sum = 0;
        for (Rental rental : rentals) {
            sum += rental.getReviews().size();
        }
        return sum;
    }

    public float getRating() {
        float sum = 0;
        for (Rental rental : rentals) {
            for (Review review : rental.getReviews().values()) {
                sum += review.getRating();
            }
        }
        if (getRatingAmount() > 0)
            return sum / getRatingAmount();
        return 0;
    }
    // θα πρεπει να συνδεσουμε τις πληροφοριες απο το rental στο renter για να γινει
    // επισκοπηση ,δημιουργια,επεξεργασια και διαγραφη του rental
}
