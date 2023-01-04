package api;

import java.util.HashMap;

public class User {
    HashMap<String, String> details;

    public User(String name, String surname, String username, String password) {

        details = new HashMap<String, String>() {
            {
                put("name", name);
                put("surname", surname);
                put("username", username);
                put("password", password);
            }
        };
    }

    public String getName() {
        return details.get("name");
    }

    public String getSurname() {
        return details.get("surname");
    }

    public String getUsername() {
        return details.get("username");
    }

    public String getPassword() {
        return details.get("password");
    }

}
