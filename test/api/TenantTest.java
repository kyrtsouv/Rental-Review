package api;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TenantTest {

    private Tenant tenant;
    private Renter renter;
    private Rental rental;
    private Review review;

    @Before
    public void setUp() throws Exception {
        renter = new Renter("name", "surname", "username", "password");
        tenant = new Tenant("name", "surname", "username2", "password");
        rental = new Rental("rentalName", "type", "address", "city", "zipcode", "description", new HashSet<>(), renter);
        review = new Review(4, "very good", SDate.dateToString(), tenant);
    }

    @Test
    public void addReview() {
        tenant.addReview(rental, review);
        assertTrue(tenant.getReviews().containsValue(review));
    }

    @Test
    public void removeReview() {
        tenant.addReview(rental, review);
        tenant.removeReview(rental);
        assertFalse(tenant.reviews.containsValue(review));
    }

    @Test
    public void getReviews() {
        assertEquals(0, tenant.getReviews().size());
        tenant.addReview(rental, review);
        assertEquals(1, tenant.getReviews().size());
        assertTrue(tenant.getReviews().containsValue(review));
    }
}