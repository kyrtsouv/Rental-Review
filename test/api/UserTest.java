package api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void setUp() throws Exception {
        user = new User("name","surname","username","password");
    }

    @Test
    public void getName() {
        assertEquals("name",user.getName());
    }

    @Test
    public void getSurname() {
        assertEquals("surname",user.getSurname());
    }

    @Test
    public void getUsername() {
        assertEquals("username",user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("password",user.getPassword());
    }
}