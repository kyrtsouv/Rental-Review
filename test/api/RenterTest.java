package api;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class RenterTest {
    private Renter renter;
    private Rental rental;

    @Before
    public void setUp() throws Exception {
        renter = new Renter("name", "surname", "username", "password");
        rental = new Rental("rentalName", "type", "address", "city", "zipcode", "description", new HashSet<>(), renter);
    }

    @Test
    public void addRental() {
        renter.addRental(rental);
        assertTrue(renter.rentals.contains(rental));
    }

    @Test
    public void removeRental() {
        renter.addRental(rental);
        renter.removeRental(rental);
        assertFalse(renter.rentals.contains(rental));
    }

    @Test
    public void getRentals() {
        assertEquals(0, renter.getRentals().size());
        renter.addRental(rental);
        assertEquals(1, renter.getRentals().size());
        assertTrue(renter.getRentals().contains(rental));
    }

    @Test
    public void updateRating() {
        renter.addRental(rental);
        assertEquals(0, renter.getRating());
        rental.addReview(new Review(4, "very good", SDate.dateToString(),
                new Tenant("name", "surname", "username", "password")));
        rental.updateRating();
        renter.updateRating();
        assertEquals(4, renter.getRating());
    }
}