package api;

import java.util.HashMap;

//Αυτή η κλάση είναι παρόμοια με την κλάση api.Renter, μόνο όπου σε αυτή ο χρήστης δεν έχει τον ειδικό ρόλο ενός πάροχου και είναι ένας απλός χρήστης
public class Tenant extends User {
    HashMap<Rental, Review> reviews;

    //Αρχικοποίηση στοιχείων χρήστη
    public Tenant(String name, String surname, String username, String password) {
        super(name, surname, username, password);
        reviews = new HashMap<Rental, Review>();
    }

    //Έπειτα ο χρήστης έχει τις επιλογές για προσθήκη ή διαγραφή rental
    public void addReview(Rental rental, Review review) {
        reviews.put(rental, review);
    }
    public void deleteReview(Rental rental) {
        reviews.remove(rental);
    }

    //Έπειτα υπάρχει η επιλογή για επιστροφή συνολικών αξιολογήσεων που έχει κάνει ο συγκεκριμένος χρήστης
    public HashMap<Rental, Review> getReviews() {
        return new HashMap<>(reviews);
    }

    //Επιστροφή συνολικής αξιολόγησης
    public float getRating() {
        float sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        if (reviews.size() > 0)
            return sum / reviews.size();
        else
            return 0;
    }
}