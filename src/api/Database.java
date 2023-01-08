package api;

import java.io.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
//Η εξής κλάση αποτελεί το κομμάτι του Database.
// Με άλλα λόγια εδώ υπάρχουν όλες οι πληροφορίες για το κομμάτι της αναζήτησης όσο αφορά την ιδιοκτησία
public class Database implements Serializable {
    HashMap<String, User> users;
    HashSet<Rental> rentals;
    HashMap<String, HashSet<String>> amenities;
    HashSet<String> rentalTypes;

    private static final long serialVersionUID = 1;
    //Καταρχάς όλες οι πληροφορίες θα είναι σε hashmap καθώς θεωρήσαμε ότι έτσι είναι πιο εύκολο για αποθήκευση και επιστροφή στοιχείων
    //Επίσης κάθε πληροφορία απο αυτές χρησιμοποιήται στο κομμάτι του EditRental, RentalGUI & SearchGUI
    public Database() {
        users = new HashMap<>();
        rentals = new HashSet<>();
        //HashSet για τους τύπους rental
        rentalTypes = new HashSet<>() {
            {
                add("Ξενοδοχείο");
                add("Διαμέρισμα");
                add("Μεζονέτα");
            }
        };
        //HashSet για το τι προσφέρει κάθε rental με υποκατηγορίες για επιλογή σε άλλες κλάσεις
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

    //Εδώ γίνεται απλή επιστροφή πληροφορίας του @user
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
    //Με αυτό το τρόπο δημιουργείται ένα rental
    public void addRental(Rental rental) {
        rentals.add(rental);
    }
    //Με παρόμοιο τρόπο γίνεται η αφαίρεση του @rentals
    public void removeRental(Rental rental) {
        rentals.remove(rental);
    }
    //Επίσης με παρόμοιο τρόπο επιστρέφουμε τις πληροφορίες των @rentals
    public HashSet<Rental> getRentals() {
        return rentals;
    }
    //Παρόμοιο τρόπο για επιστροφή @users
    public HashMap<String, User> getUsers() {
        return users;
    }
    //Επιστροφή τύπου ιδιοκτησίας/καταλύματος
    public HashSet<String> getRentalTypes() {
        return rentalTypes;
    }
    //Επιστροφή πιο συγκεκριμένων πληροφοριών για το rental(@amenities)
    public HashMap<String, HashSet<String>> getAmenities() {
        return amenities;
    }
    //Με την παρακάτω διαδικασία γίνεται αποθήκευση νέων πληροφοριών
    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.dat"));
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Με παρόμοιο τρόπο κατόπιν γίνεται η φόρτωση και εμφάνιση πληροφοριών
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
}
