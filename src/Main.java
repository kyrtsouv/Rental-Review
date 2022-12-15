import api.*;
import api.User.*;

public class Main {

    public static void main(String[] args) {
        Renter renter = new Renter("testName", "testSurname", "testUsername",
                "testPassword");

        Rental r = new Rental("Flat in Kifisia", "Kanellopoulou 21", "Thessaloniki", "54248", "Big house", "Flat",
                "Viktor Kyrtsoudis");

        renter.addRental(r);

        renter.addReview(r, new Review("John Wick", "Amazing house with all amenities ", 5));

        renter.editReview(r, new Review("John Wick", "Amazing house with all amenities and a great view", 4));

        r.deleteReview("John Wick");
    }

}
