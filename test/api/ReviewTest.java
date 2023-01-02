package api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    Review review;
    Rental rental;
    Renter renter;
    Tenant tenant;

    @Before
    public void setUp() throws Exception {
        tenant = new Tenant("name", "surname", "username", "password");
        review = new Review(4, "very good", SDate.dateToString(), tenant);
    }

    @Test
    public void getRating() {
        assertEquals(4, review.getRating());
    }

    @Test
    public void getComment() {
        assertEquals("very good", review.getComment());
    }

    @Test
    public void getTenant() {
        assertEquals(tenant, review.getTenant());
    }

    @Test
    public void setRating() {
        review.setRating(5);
        assertEquals(5, review.getRating());
    }

    @Test
    public void setComment() {
        review.setComment("not bad");
        assertEquals("not bad", review.getComment());
    }
}