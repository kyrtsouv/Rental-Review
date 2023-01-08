package api;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class RentalTest {

    Rental rental;
    Review review;
    Renter renter;
    Tenant tenant;
    @Before
    public void setUp() throws Exception {
        renter = new Renter("name2","surname2","username2","password2");
        tenant = new Tenant("name","surname","username","password");
        review = new Review(tenant,"Very good",5);
        HashSet<String> amenities = new HashSet<>(){{add("am1");add("am2");}};
        rental = new Rental("name","address","city","postcode","description","type",renter,amenities);
    }

    @Test
    public void getSearchFilters() {
    }

    @Test
    public void get() {
        assertEquals("name",rental.get("name"));
        assertEquals("address",rental.get("address"));
    }

    @Test
    public void getOwner() {
        assertEquals(renter,rental.getOwner());
    }

    @Test
    public void getRating() {
        assertEquals(0,rental.getRating(),0.001);
        rental.addReview(review);
        assertEquals(5,rental.getRating(),0.001);
    }

    @Test
    public void getAmenities() {
        assertEquals(2,rental.getAmenities().size());
        assertTrue(rental.getAmenities().contains("am1"));
        assertTrue(rental.getAmenities().contains("am2"));
    }

    @Test
    public void getReviews() {
        assertTrue(rental.getReviews().isEmpty());
        rental.addReview(review);
        assertTrue(rental.getReviews().containsValue(review));
    }

    @Test
    public void addReview() {
        rental.addReview(review);
        assertTrue(rental.reviews.containsValue(review));
    }

    @Test
    public void deleteReview() {
        rental.addReview(review);
        rental.deleteReview(review.getUser());
        assertTrue(rental.getReviews().isEmpty());
    }

    @Test
    public void editRental() {
        rental.editRental("name2","address2","city2","postcode2","description2","type2",new HashSet<>());
        assertEquals("name2",rental.get("name"));
        assertTrue(rental.getAmenities().isEmpty());
    }
}