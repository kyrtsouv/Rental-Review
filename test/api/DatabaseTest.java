package api;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class DatabaseTest {
    Renter renter;
    Rental rental;
    Database db;
    @Before
    public void setUp() throws Exception {
        db = new Database();
        renter = new Renter("name2","surname2","username2","password2");
        rental = new Rental("name","address","city","postcode","description","type",renter,new HashSet<>());
    }

    @Test
    public void addUser() {
        db.addUser(renter);
        assertTrue(db.users.containsValue(renter));
        assertEquals(1,db.users.size());
    }

    @Test
    public void addRental() {
        db.addRental(rental);
        assertTrue(db.rentals.contains(rental));
        assertEquals(1,db.rentals.size());
    }

    @Test
    public void removeRental() {
        db.addRental(rental);
        db.removeRental(rental);
        assertFalse(db.rentals.contains(rental));
        assertEquals(0,db.rentals.size());
    }

    @Test
    public void getRentals() {
        assertEquals(db.rentals,db.getRentals());
        db.addRental(rental);
        assertEquals(db.rentals,db.getRentals());
    }

    @Test
    public void getUsers() {
        assertEquals(db.users,db.getUsers());
        db.addUser(renter);
        assertEquals(db.users,db.getUsers());
    }

    @Test
    public void getRentalTypes() {
        assertEquals(3,db.getRentalTypes().size());
        assertTrue(db.getRentalTypes().contains("Ξενοδοχείο"));
        assertTrue(db.getRentalTypes().contains("Διαμέρισμα"));
        assertTrue(db.getRentalTypes().contains("Μεζονέτα"));
    }

    @Test
    public void getAmenities() {
        assertEquals(9,db.getAmenities().size());
    }
}