package api;

import java.util.HashSet;

//Αυτή είναι μία κλάση επεξεργασίας και αποθήκευσης πληροφοριών μόνο για χρήστης με ειδικό χαρακτηριστικό ότι είναι ιδιώτες
public class Renter extends User {

    private HashSet<Rental> rentals;
    //Εδώ γίνεται η αρχικοποίηση ενός καταλύματος για το χρήστη
    public Renter(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        rentals = new HashSet<Rental>();
    }
    //Παρόμοια με τη Rental εδώ δίνονται οι επιλογές στο χρήστη για δημιουργία & διαγραφή καταλύματος/ιδιοκτησίας(rental)
    public void addRental(Rental rental) {
        rentals.add(rental);
    }
    public void deleteRental(Rental rental) {
        rentals.remove(rental);
    }

    //Φυσικά, εδώ πρέπει να υπάρχει η επιλογή για επιστροφή πληροφοριών ενός καταλύματος
    public HashSet<Rental> getRentals() {
        return new HashSet<>(rentals);
    }

    //Καθώς και για επιστροφή συνολικής αξιολόγησης του καταλύματος
    public int getRatingAmount() {
        int sum = 0;
        for (Rental rental : rentals) {
            sum += rental.getReviews().size();
        }
        return sum;
    }

    //Εδώ γίνεται υπολογισμός συνολικής αξιολόγησης του καταλύματος
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

}
