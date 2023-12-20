package api;

import java.util.HashMap;
import java.util.HashSet;
import java.io.Serializable;
/*Η παρακάτω κλάση αποτελεί το κομμάτι api του Rental
Με άλλα λόγια εδώ υπάρχουν όλες οι γενικές πληροφορίες μίας ιδιοκτησίας/καταλύματος καθώς και για κάθε σχετικό σχόλιο που υπάρχει για την ίδια
Επιπλέον οι παρακάτω πληροφορίες συνδέονται με αυτές της Database και βοηθάνε στο κομμάτι του GUI (AddRental, AddReview, EditRental, EditReview & PreviewGUI)*/
public class Rental implements Serializable {
    HashMap<String, String> details;
    HashMap<Tenant, Review> reviews;
    HashSet<String> amenities;
    Renter owner;
    HashSet<String> searchFilters;

    public Rental(String name, String address, String city, String postcode, String description, String type,
            Renter owner, HashSet<String> amenities) {
        /*Αρχική δημιουργία hashmap για την αποθήκευση γενικών πληροφοριών για το rental*/
        details = new HashMap<>() {
            {
                put("name", name);
                put("address", address);
                put("city", city);
                put("postcode", postcode);
                put("description", description);
                put("type", type);
                put("location", address + ", " + city + ", " + postcode);
            }
        };
        /*Εδω γίνεται αρχικοποίηση ορισμένων μεταβλητών*/
        this.owner = owner;
        this.reviews = new HashMap<Tenant, Review>();
        this.amenities = amenities;
        this.searchFilters = new HashSet<>() {
            {
                add(name);
                add(address);
                add(city);
                add(postcode);
                add(type);
            }
        };
    }
    /*Η συγκεκριμένη διαδικασία υπάρχει για την αναζήτηση στοιχείων που αποθηκεύονται εδώ για τη χρήση τους σε άλλες κλάσεις*/
    public HashSet<String> getSearchFilters() {
        return new HashSet<>(searchFilters);
    }
    /*Μια απλή getter*/
    public String get(String key) {
        return details.get(key);
    }
    /*Παρόμοια με την προηγούμενη για τον ιδιοκτήτη*/
    public Renter getOwner() {
        return owner;
    }
    /*Επίσης, παρόμοια άλλα πιο αναλυτική καθώς αφορά την αξιολόγηση ενός καταλύματος σαν σύνολο επεξεργάζοντας κάθε αξιολόγηση και υπολογίζοντας το μέσω όρo*/
    public float getRating() {
        int sum = 0;
        for (Review review : reviews.values()) {
            sum += review.getRating();
        }
        if (reviews.size() > 0)
            return (float) sum / reviews.size();
        return 0;
    }
    /*Απλή getter για επιστροφή επιπλέον αλλά μη γενικών πληροφοριών*/
    public HashSet<String> getAmenities() {
        return new HashSet<>(amenities);
    }
    /*Επιστροφή αξιολογήσεων*/
    public HashMap<Tenant, Review> getReviews() {
        return new HashMap<>(reviews);
    }
    /*Εδώ γίνεται αποθήκευση καινούργιας αξιολόγησης*/
    public void addReview(Review review) {
        reviews.put(review.getUser(), review);
    }
    /*Κατόπιν εδώ γίνεται η διαγραφή μίας αξιολόγησης*/
    public void deleteReview(Tenant user) {
        reviews.remove(user);
    }
    /*Και εν τέλη εδώ γίνεται η επεξεργασία ήδη υπάρχων καταλύματος/ιδιοκτησίας*/
    public void editRental(String name, String address, String city, String postcode, String description, String type,
            HashSet<String> amenities) {
        details.replace("name", name);
        details.replace("address", address);
        details.replace("city", city);
        details.replace("postcode", postcode);
        details.replace("description", description);
        details.replace("type", type);
        details.replace("location", address + ", " + city + ", " + postcode);
        this.amenities = amenities;
    }

}