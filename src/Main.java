import api.*;
import gui.*;

//Αυτή είναι η κεντρική κλάση main στην οποία υλοποιούνται όλες οι άλλες
public class Main {

    public static void main(String[] args) {
        Renter renter = new Renter("testName", "testSurname", "testUsername",
                "testPassword");

        Rental r = new Rental("Flat in Kifisia", "Kanellopoulou 21", "Thessaloniki", "54248", "Big house", "Flat",
                "Viktor Kyrtsoudis");

        renter.addRental(r);

        r.deleteReview("John Wick");

        GUImain guImain=new GUImain();
//        Database b = new Database();
//        LoginForm login = new LoginForm();
//        UserGUI userGUI = new UserGUI();
//        Search search = new Search();
        Preview preview = new Preview();

    }

}