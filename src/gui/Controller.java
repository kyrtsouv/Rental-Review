package gui;

import api.*;

import java.awt.event.*;

public class Controller {
    Database db;
    User user;
    MainGUI main;

    public Controller() {
        db = Database.load();

        LoginGUI login = makeLogin();
        RegisterGUI register = makeRegister();

        main = makeMain(login, register);
    }

    private LoginGUI makeLogin() {
        LoginGUI login = new LoginGUI();
        login.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = login.getUsername();
                String password = login.getPassword();

                if (db.getUsers().containsKey(username) && db.getUsers().get(username).getPassword().equals(password)) {
                    user = db.getUsers().get(username);
                    if (user instanceof Renter) {
                        main.show(new RenterGUI((Renter) user, main, db));
                    } else {
                        main.show(new TenantGUI((Tenant) user, main, db));
                    }
                } else {
                    login.setError("Λάθος στοιχεία");
                }
            }
        });
        return login;
    }

    private RegisterGUI makeRegister() {
        RegisterGUI register = new RegisterGUI();
        register.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = register.getName();
                String surname = register.getSurname();
                String username = register.getUsername();
                String password = register.getPassword();
                String type = register.getType();

                if (name.equals("") || surname.equals("") || username.equals("") || password.equals("")) {
                    register.setError("Παρακαλώ συμπληρώστε όλα τα πεδία");
                } else if (db.getUsers().containsKey(username)) {
                    register.setError("Το όνομα χρήστη υπάρχει ήδη");
                } else {
                    if (type.equals("Πάροχος")) {
                        user = new Renter(name, surname, username, password);
                        main.show(new RenterGUI((Renter) user, main, db));
                    } else {
                        user = new Tenant(name, surname, username, password);
                        main.show(new TenantGUI((Tenant) user, main, db));
                    }
                    db.addUser(user);
                }
            }
        });
        return register;
    }

    private MainGUI makeMain(LoginGUI login, RegisterGUI register) {
        MainGUI main = new MainGUI(login, register);
        main.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                db.save();
            }
        });
        return main;
    }
}
