import api.*;
// import gui.*;

/**
 * This class can be deleted
 */
public class Main {

    public static void main(String[] args) {
        Rental r = new Rental("Flat in Kifisia", "Kanellopoulou 21", "Thessaloniki", "54248", "Big house", "Flat",
                "Viktor Kyrtsoudis");

        r.addReview(new Review("John Wick", "Amazing house with all amenities ", 5));
        r.print();

        r.editReview("John Wick", "Amazing house with all amenities and a great view", 4);

    }

}
