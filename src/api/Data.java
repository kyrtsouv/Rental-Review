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
    final HashMap<String, HashSet<String>> amenities;
    final HashSet<String> type;

    /**
     * Constructor:
     * Initializes the field variables with empty map and set
     */
    public Data() {
        users = new HashMap<>();
        rentals = new HashSet<>();
        amenities = new HashMap<>();
        type = new HashSet<>();

        type.add("Ξενοδοχείο");
        type.add("Διαμέρισμα");
        type.add("Μεζονέτα");

        HashSet<String> set = new HashSet<>();
        set.add("Θέα σε πισίνα");
        set.add("Θέα σε παραλία");
        set.add("Θέα στη θάλασσα");
        set.add("Θέα στο λιμάνι");
        set.add("Θέα στο βουνό");
        set.add("Θέα στο δρόμο");
        amenities.put("Θέα", new HashSet<>(set));
        set.clear();

        set.add("Πιστολάκι μαλλιών");
        amenities.put("Μπάνιο", new HashSet<>(set));
        set.clear();

        set.add("Πλυντήριο ρούχων");
        set.add("Στεγνωτήριο");
        amenities.put("Πλύσιμο ρούχων", new HashSet<>(set));
        set.clear();

        set.add("Τηλεόραση");
        amenities.put("Ψυχαγωγία", new HashSet<>(set));
        set.clear();

        set.add("Εσωτερικό τζάκι");
        set.add("Κλιματισμός");
        set.add("Κεντρική θέρμανση");
        amenities.put("Θέρμανση και κλιματισμός", new HashSet<>(set));
        set.clear();

        set.add("wifi");
        set.add("ethernet");
        amenities.put("Διαδίκτυο", new HashSet<>(set));
        set.clear();

        set.add("Κουζίνα");
        set.add("Ψυγείο");
        set.add("Φούρνος μικροκυμάτων");
        set.add("Μαγειρικά είδη");
        set.add("Πιάτα και μαχαιροπίρουνα");
        set.add("Πλυντήριο πιάτων");
        set.add("Καφετιέρα");
        amenities.put("Κουζίνα και τραπεζαρία", new HashSet<>(set));
        set.clear();

        set.add("Μπαλκόνι");
        set.add("αυλή");
        amenities.put("Εξωτερικός χώρος", new HashSet<>(set));
        set.clear();

        set.add("Δωρεάν χώρος στάθμευσης στην ιδιοκτησία");
        set.add("Δωρεάν παρκινγκ στο δρόμο");
        amenities.put("Χώρος στάθμευσης", new HashSet<>(set));
        set.clear();

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
