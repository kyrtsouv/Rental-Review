package api;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class RenterTest {
    Renter renter;
    Rental rental;
    Review review;
    Tenant tenant;

    @Before
    public void setUp() throws Exception {
        renter = new Renter("name2","surname2","username2","password2");
        rental = new Rental("name","address","city","postcode","description","type",renter,new HashSet<>());
        tenant = new Tenant("name","surname","username","password");
        review = new Review(tenant,"Very good",5);
    }

    @Test
    public void addRental() {
        renter.addRental(rental);
        assertTrue(renter.rentals.contains(rental));
    }

    @Test
    public void deleteRental() {
        renter.addRental(rental);
        renter.deleteRental(rental);
        assertTrue(renter.rentals.isEmpty());
    }

    @Test
    public void getRentals() {
        assertTrue(renter.getRentals().isEmpty());
        renter.addRental(rental);
        assertTrue(renter.getRentals().contains(rental));
        renter.deleteRental(rental);
        assertTrue(renter.getRentals().isEmpty());
    }

    @Test
    public void getRatingAmount() {
        assertEquals(0,renter.getRatingAmount());
        rental.addReview(review);
        renter.addRental(rental);
        assertEquals(1,renter.getRatingAmount());
    }

    @Test
    public void getRating() {
        assertEquals(0,renter.getRating(),0.001);
        rental.addReview(review);
        renter.addRental(rental);
        assertEquals(5,renter.getRating(),0.001);
    }
}