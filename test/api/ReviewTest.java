package api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    Tenant tenant;
    Review review;
    Rental rental;


    @Before
    public void setUp() throws Exception {
        tenant = new Tenant("name","surname","username","password");
        review = new Review(tenant,"Very good",5);

    }

    @Test
    public void getUser() {
        assertEquals(tenant,review.getUser());
    }

    @Test
    public void getComment() {
        assertEquals("Very good",review.getComment());
    }

    @Test
    public void getRating() {
        assertEquals(5,review.getRating());
    }

    @Test
    public void editReview() {
        review.editReview("Very bad",2);
        assertEquals("Very bad",review.getComment());
        assertEquals(2,review.getRating());
    }
}