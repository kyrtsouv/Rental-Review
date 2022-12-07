package api.User;

import java.util.regex.Pattern;

//Για τους απλούς χρήστες
public class Tenant {

    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";

    private  String firstName;
    private  String lastName;
    private  String email;
    //Απλή αρχικοποίηση/αποθήκευση στοιχείων
    public Tenant(final String firstName, final String lastName, final String email) {
        this.isValidEmail(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    //έλεγχος μειλ χρήστη
    private void isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName
                + " Last Name: " + this.lastName
                + " Email: " + this.email;
    }
}
