import api.Database;
import api.Rental;
import api.Renter;
import gui.LoginForm;
//ΓΕΝΙΚΑ μας εχει μεινει στο κομματι API: database,search
//Μετά θα δοκιμάσουμε με αυτό το πρόγραμμα που μας έχει δώσει ο Τσουμάκας για να διορθώσουμε το κώδικα αν χρειάζεται και μετά έχουμε tests & GUI
//ΕΠΙΣΗΣ πρεπει να δούμε το controller για να μην υπάρχει κάποιο θέμα

public class Main {

    public static void main(String[] args) {
        Renter renter = new Renter("testName", "testSurname", "testUsername",
                "testPassword");

        Rental r = new Rental("Flat in Kifisia", "Kanellopoulou 21", "Thessaloniki", "54248", "Big house", "Flat",
                "Viktor Kyrtsoudis");

        renter.addRental(r);

        r.deleteReview("John Wick");

        Database b = new Database();
        LoginForm login = new LoginForm(b);

    }

}