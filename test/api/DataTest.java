package api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashSet;

public class DataTest {
    Rental rental;
    Tenant tenant;
    Renter renter;
    Data data;

    @Before
    public void setUp() throws Exception {
        tenant = new Tenant("name", "surname", "username", "password");
        renter = new Renter("name2", "surname2", "username2", "password2");
        rental = new Rental("rentalName", "type", "address", "city", "zipcode", "description", new HashSet<>(), renter);
        data = new Data();
    }

    @Test
    public void getUsers() {
        data.addUser(tenant);
        data.addUser(renter);
        assertTrue(data.getUsers().containsValue(tenant));
        assertTrue(data.getUsers().containsValue(renter));
        assertEquals(2, data.getUsers().size());
    }

    @Test
    public void getRentals() {
        data.addRental(rental);
        assertTrue(data.getRentals().contains(rental));
        assertEquals(1, data.getRentals().size());
    }

    @Test
    public void addUser() {
        data.addUser(tenant);
        assertTrue(data.users.containsValue(tenant));
    }

    @Test
    public void addRental() {
        data.addRental(rental);
        assertTrue(data.rentals.contains(rental));
    }

    @Test
    public void removeRental() {
        data.addRental(rental);
        data.removeRental(rental);
        assertFalse(data.rentals.contains(rental));
    }
}