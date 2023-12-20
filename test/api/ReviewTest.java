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
    public void getComment() {
        assertEquals("very good", review.getComment());
    }

    @Test
    public void getTenant() {
        assertEquals(tenant, review.getTenant());
    }

    @Test
    public void getRating() {
        assertEquals(4,review.getRating(),0.001);
    }

    @Test
    public void getDate() {
        assertEquals(SDate.dateToString(),review.getDate());
    }

    @Test
    public void updateReview() {
        review.updateReview(1,"awful", SDate.dateToString());
        assertEquals(1,review.getRating(),0.001);
        assertEquals("awful",review.getComment());
        assertEquals(SDate.dateToString(),review.getDate());
    }
}