package api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;

    @Before
    public void setUp() throws Exception {
        this.user = new User("name", "surname", "username", "password");
    }

    @Test
    public void getName() {
        assertEquals("name", user.getName());
    }

    @Test
    public void getSurname() {
        assertEquals("surname", user.getSurname());
    }

    @Test
    public void getUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void getRating() {
        assertEquals(0, user.getRating());
    }
}