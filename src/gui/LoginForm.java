package gui;

import api.Database;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
//Η συγκεκριμένη κλάση αποτελεί τη διαδικασία ελέγχου κάθε χρήστη

public class LoginForm{
    private static JTextField username;
    private static JPasswordField Password;
    public JFrame frame = new JFrame();
    //Εδώ γίνεται η απλή δημιουργία ενός Panel και Frame για τη δημιουργία παραθύρου login & register.
    // Αποτελείται απο δυο JPanels για να πάρουμε τις πληροφορίες και απο δυο JButtons για να επιλέγει ο χρήστης είτε να κάνει register είτε να κάνει login.
    public LoginForm() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //JFrame frame = new JFrame();
        frame.setTitle("LOGIN PAGE");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 250));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //Δημιουργία της ταμπέλας username
        JLabel label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        panel.add(label);
        username = new JTextField();
        username.setBounds(100, 32, 193, 28);
        panel.add(username);


        //Ίδια διαδικασία με πριν γίνεται ξανά τώρα για το κωδικό
        JLabel password1 = new JLabel("Password");
        password1.setBounds(100, 60, 70, 20);
        panel.add(password1);
        Password = new JPasswordField();
        Password.setBounds(100, 84, 193, 28);
        panel.add(Password);

        //Δημιουργία της επιλογής Login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 120, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.addActionListener(e -> loginListener());
        panel.add(loginButton);

        //Δημιουργία της επιλογής Register
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 150, 90, 25);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.BLACK);
        registerButton.addActionListener(e -> registerListener());
        panel.add(registerButton);
        frame.setVisible(true);

    }
    //Το loginListener είναι συνάρτηση που δέχεται τις πληροφορίες σύνδεσης απο τον χρήστη και ελέγχει μέσω του database αν οι πληροφορίες είναι όντως σωστές.
    // Αν είναι τότε η σύνδεση είναι επιτυχής αλλιώς ο χρήστης επιστρέφει στην αρχική σελίδα
    private void loginListener() {
        String Username = username.getText();
        char[] Password1 = Password.getPassword();

        if(Username.contains(Database.load()) && Arrays.toString(Password1).contains(Database.load())){
            JOptionPane.showMessageDialog(null, "Login Successful");
        }
        else JOptionPane.showMessageDialog(null, "Wrong Input");
        this.frame.dispose();
    }


    //Παρόμοια με την προηγούμενη συνάρτηση, αυτή δέχεται πληροφορίες σύνδεσης απο τον χρήστη και ελέγχει αρχικά να μην υπάρχουν ήδη στο database.
    //Έπειτα εφόσον δεν υπάρχουν ήδη, αποθηκεύει αυτές τις καινούργιες πληροφορίες στο database για επόμενη χρήση.
    private void registerListener() {
        String Username = username.getText();
        char[] Password1 = Password.getPassword();
        if(Username.contains(Database.load()) && Arrays.toString(Password1).contains(Database.load())){
            JOptionPane.showMessageDialog(null, "Wrong Input");
        }
        else JOptionPane.showMessageDialog(null, "Register Successful");
        this.frame.dispose();

    }

}