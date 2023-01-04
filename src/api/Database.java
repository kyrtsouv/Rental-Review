package api;

import java.io.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Database implements Serializable, CharSequence {
    private HashMap<String, User> users;
    private HashSet<Rental> rentals;
    private HashMap<String, HashSet<String>> amenities;
    private HashSet<String> rentalTypes;

    private static final long serialVersionUID = 1;

    public Database() {
        users = new HashMap<>();
        rentals = new HashSet<>();

        rentalTypes = new HashSet<>() {
            {
                add("Ξενοδοχείο");
                add("Διαμέρισμα");
                add("Μεζονέτα");
            }
        };

        amenities = new HashMap<>() {
            {
                put("Θέα", new HashSet<>() {
                    {
                        add("Θέα σε πισίνα");
                        add("Θέα σε παραλία");
                        add("Θέα στη θάλασσα");
                        add("Θέα στο λιμάνι");
                        add("Θέα στο βουνό");
                        add("Θέα στο δρόμο");
                    }
                });
                put("Μπάνιο", new HashSet<>() {
                    {
                        add("Πιστολάκι μαλλιών");
                    }
                });
                put("Πλύσιμο ρούχων", new HashSet<>() {
                    {
                        add("Πλυντήριο ρούχων");
                        add("Στεγνωτήριο");
                    }
                });
                put("Ψυχαγωγία", new HashSet<>() {
                    {
                        add("Τηλεόραση");
                    }
                });
                put("Θέρμανση και κλιματισμός", new HashSet<>() {
                    {
                        add("Εσωτερικό τζάκι");
                        add("Κλιματισμός");
                        add("Κεντρική θέρμανση");
                    }
                });
                put("Διαδίκτυο", new HashSet<>() {
                    {
                        add("wifi");
                        add("ethernet");
                    }
                });
                put("Κουζίνα και τραπεζαρία", new HashSet<>() {
                    {
                        add("Κουζίνα");
                        add("Ψυγείο");
                        add("Φούρνος μικροκυμάτων");
                        add("Μαγειρικά είδη");
                        add("Πιάτα και μαχαιροπίρουνα");
                        add("Πλυντήριο πιάτων");
                        add("Καφετιέρα");
                    }
                });
                put("Εξωτερικός χώρος", new HashSet<>() {
                    {
                        add("Μπαλκόνι");
                        add("Αυλή");
                    }
                });
                put("Χώρος στάθμευσης", new HashSet<>() {
                    {
                        add("Δωρεάν χώρος στάθμευσης στην ιδιοκτησία");
                        add("Δωρεάν παρκινγκ στο δρόμο");
                    }
                });
            }
        };
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void removeRental(Rental rental) {
        rentals.remove(rental);
    }

    public HashSet<Rental> getRentals() {
        return rentals;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public HashSet<String> getRentalTypes() {
        return rentalTypes;
    }

    public HashMap<String, HashSet<String>> getAmenities() {
        return amenities;
    }

    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.dat"));
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.dat"));
            Database db = (Database) in.readObject();
            in.close();
            return db;
        } catch (Exception e) {
            return new Database();
        }
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
