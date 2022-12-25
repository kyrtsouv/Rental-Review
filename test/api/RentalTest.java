package api;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class RentalTest {

    Renter renter;
    Rental rental;
    Review review;

    @Before
    public void setUp() throws Exception {
        renter = new Renter("name", "surname", "username", "password");
        rental = new Rental("rentalName", "type", "address", "city", "zipcode", "description", new HashSet<>(), renter);
        review = new Review(5, "very good", new Tenant("name", "surname", "username", "password"));
    }

    @Test
    public void getReviews() {
        assertEquals(new HashMap<Tenant, Review>(), rental.getReviews());
    }

    @Test
    public void getSearchId() {
        assertEquals("rentalname type address city zipcode", rental.getSearchID());
    }

    @Test
    public void getName() {
        assertEquals("rentalName", rental.getName());
    }

    @Test
    public void getType() {
        assertEquals("type", rental.getType());
    }

    @Test
    public void getAddress() {
        assertEquals("address", rental.getAddress());
    }

    @Test
    public void getCity() {
        assertEquals("city", rental.getCity());
    }

    @Test
    public void getZipcode() {
        assertEquals("zipcode", rental.getZipcode());
    }

    @Test
    public void getDescription() {
        assertEquals("description", rental.getDescription());
    }

    @Test
    public void getAmenities() {
        assertEquals(new HashSet<>(), rental.getAmenities());
    }

    @Test
    public void getOwner() {
        assertEquals(renter, rental.getOwner());
    }

    @Test
    public void setName() {
        rental.setName("newName");
        assertEquals("newName", rental.getName());
    }

    @Test
    public void setType() {
        rental.setType("newType");
        assertEquals("newType", rental.getType());
    }

    @Test
    public void setAddress() {
        rental.setAddress("newAddress");
        assertEquals("newAddress", rental.getAddress());
    }

    @Test
    public void setCity() {
        rental.setCity("newCity");
        assertEquals("newCity", rental.getCity());
    }

    @Test
    public void setZipcode() {
        rental.setZipcode("newZipcode");
        assertEquals("newZipcode", rental.getZipcode());
    }

    @Test
    public void setDescription() {
        rental.setDescription("newDescription");
        assertEquals("newDescription", rental.getDescription());
    }

    @Test
    public void setAmenities() {
        HashSet<String> newAmenities = new HashSet<>();
        newAmenities.add("newAmenity");
        rental.setAmenities(newAmenities);
        assertEquals(newAmenities, rental.getAmenities());
    }

    @Test
    public void updateSearchID() {
        rental.setName("newName");
        rental.setType("newType");
        assertEquals("newname newtype address city zipcode", rental.getSearchID());
        rental.setAddress("newAddress");
        rental.setCity("newCity");
        rental.setZipcode("newZipcode");
        assertEquals("newname newtype newaddress newcity newzipcode", rental.getSearchID());
    }

    @Test
    public void addReview() {
        rental.addReview(review);
        assertEquals(review, rental.reviews.get(review.getTenant()));
    }

    @Test
    public void updateRating() {
        rental.addReview(review);
        rental.addReview(new Review(3, "good", new Tenant("name", "surname", "username", "password")));
        rental.updateRating();
        assertEquals(4, rental.rating);
    }

    @Test
    public void removeReview() {
        rental.addReview(review);
        rental.removeReview(review);
        assertTrue(rental.reviews.isEmpty());
    }

    @Test
    public void getRating() {
        rental.addReview(review);
        rental.updateRating();
        assertEquals(5, rental.getRating());
    }

    @Test
    public void getNumOfRatings() {
        assertEquals(0, rental.getNumOfRatings());
        rental.addReview(review);
        rental.updateRating();
        assertEquals(1, rental.getNumOfRatings());
    }
}