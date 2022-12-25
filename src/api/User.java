package api;

import java.io.Serializable;

public class User implements Serializable {
    protected String name;
    protected String surname;
    protected String username;
    protected String password;
    protected int rating;

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.rating = 0;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRating() {
        return rating;
    }
}
