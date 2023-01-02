package api;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class that represents the data of the application
 * It has a map of (String)usernames to users and a set of (String)names
 * to rentals
 * 
 * It also has a map of (String)amenityTitles to a set of (String)amenities
 * and a set of (String)rentalTypes
 */
public class Data implements Serializable {
    HashMap<String, User> users;
    HashSet<Rental> rentals;
    final HashMap<String, HashSet<String>> amenities;
    final HashSet<String> rentalTypes;

    private static final long serialVersionUID = 1;

    /**
     * Constructor:
     * Initializes the users map with an empty HashMap, the rentals set with an
     * empty HashSet the amenities map with the required amenities and the
     * rentalTypes set with the required rental types
     */
    public Data() {
        users = new HashMap<>();
        rentals = new HashSet<>();
        amenities = new HashMap<>();
        rentalTypes = new HashSet<>();

        rentalTypes.add("Ξενοδοχείο");
        rentalTypes.add("Διαμέρισμα");
        rentalTypes.add("Μεζονέτα");

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
     * Returns the set of rentalTypes
     * 
     * @return rentalTypes
     */
    public HashSet<String> getRentalTypes() {
        return rentalTypes;
    }

    /**
     * Returns the map of amenityTitles to set of amenities
     * 
     * @return amenities
     */
    public HashMap<String, HashSet<String>> getAmenities() {
        return amenities;
    }

    /**
     * Returns the user with the username and password that are passed as
     * parameters if they exist, null otherwise
     * 
     * @param username
     * @param password
     * @return user
     */
    public User getUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).getPassword().equals(password))
            return users.get(username);
        return null;
    }

    /**
     * Returns false if the username that is passed as a parameter exists in the
     * map of users, true otherwise
     * 
     * @param username
     * @return true if the username exists, false otherwise
     */
    public boolean available(String username) {
        return !users.containsKey(username);
    }

    /**
     * Returns the map of username to users
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

    /**
     * Saves the data object to a file
     */
    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and returns the data object from a file if the file exists. If the file
     * does not exist a new data object is created and returned
     * 
     * @return data
     */
    public static Data load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (Exception e) {
            return new Data();
        }
    }
}
