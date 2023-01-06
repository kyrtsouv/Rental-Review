package gui;

import api.*;

public class Controller {
    Database db;
    User user;

    public Controller() {
        db = Database.load();

        // Ένα αντικείμενο τύπου LoginForm που θα δημιουργήσει το παράθυρο login &
        // register και θα ελέγξει τα στοιχεία του χρήστη που θα εισάγει.
        LoginForm loginForm = new LoginForm(db);

        // user = loginForm.getUser();
        // buildProfile(user);

    }
}
