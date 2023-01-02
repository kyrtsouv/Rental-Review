package api;

import java.io.Serializable;

/**
 * Parent class of Renter and Tenant that has all their common fields and their
 * common getters
 */
public class User implements Serializable {
    protected String name;
    protected String surname;
    protected String username;
    protected String password;
    protected int rating;

    /**
     * Constructor:
     * Initializes the field variables with the corresponding parameters
     * Rating gets initialized with 0
     *
     * @param name
     * @param surname
     * @param username
     * @param password
     */
    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.rating = 0;
    }

    /**
     * Returns the name of the user
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the user
     * 
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the username of the user
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the rating of the user
     * 
     * @return rating
     */
    public int getRating() {
        return rating;
    }
}
