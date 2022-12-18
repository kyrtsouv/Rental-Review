package api.User;

import java.util.HashMap;
import java.util.HashSet;

import api.*;

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
            String type,
            String owner) {
        if (rentals.contains(rental))
            rental.editRental(name, address, city, postcode, description, type, owner);
    }

    public void deleteRental(Rental rental) {
        rentals.remove(rental);
        updateRating();
    }

    public HashSet<Rental> getRentals() {
        return new HashSet<>(rentals);
    }

    public HashMap<String, String> getInfo() {
        return new HashMap<>(details);
    }

    public void editName(String name) {
        details.replace("name", name);
    }

    public void editSurname(String surname) {
        details.replace("surname", surname);
    }

    public void editUsername(String username) {
        details.replace("username", username);
    }

    public void editPassword(String password) {
        details.replace("password", password);
    }

    public void editReview(Rental rental, Review review) {
        rental.editReview(review);
        updateRating();
    }

    public void addReview(Rental rental, Review review) {
        rental.addReview(review);
        updateRating();
    }

    public void deleteReview(Rental rental, String user) {
        rental.deleteReview(user);
        updateRating();
    }

    private void updateRating() {
        int sum = 0;
        for (Rental rental : rentals) {
            sum += Integer.parseInt(rental.getRental().get("rating"));
        }
        if (rentals.size() > 0)
            details.replace("rating", String.valueOf(sum / rentals.size()));
        else
            details.replace("rating", "0");
    }

    public void deleteReview(String user) {
        for (Rental rental : rentals)
            if (rental.getReviews().containsKey(user))
                rental.deleteReview(user);
        updateRating();
    }
    // θα πρεπει να συνδεσουμε τις πληροφοριες απο το rental στο renter για να γινει
    // επισκοπηση ,δημιουργια,επεξεργασια και διαγραφη του rental
}
