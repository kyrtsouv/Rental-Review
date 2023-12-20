package api;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TenantTest {
    Tenant tenant;
    Renter renter;
    Review review,review2;
    Rental rental,rental2;

    @Before
    public void setUp() throws Exception {
        renter = new Renter("name2","surname2","username2","password2");
        tenant = new Tenant("name","surname","username","password");
        review = new Review(tenant,"Very good",5);
        review2 = new Review(tenant,"Very good2",4);
        rental = new Rental("name","address","city","postcode","description","type",renter,new HashSet<>());
        rental2 = new Rental("name2","address2","city2","postcode2","2","type",renter,new HashSet<>());
    }

    @Test
    public void addReview() {
        tenant.addReview(rental,review);
        assertTrue(tenant.reviews.containsValue(review));
    }

    @Test
    public void deleteReview() {
        tenant.addReview(rental,review);
        tenant.deleteReview(rental);
        assertTrue(tenant.reviews.isEmpty());
    }

    @Test
    public void getReviews() {
        assertTrue(tenant.getReviews().isEmpty());
        tenant.addReview(rental,review);
        assertTrue(tenant.getReviews().containsValue(review));
    }

    @Test
    public void getRating() {
        tenant.addReview(rental,review);
        tenant.addReview(rental2,review2);
        assertEquals(4.5,tenant.getRating(),0.001);
    }
}