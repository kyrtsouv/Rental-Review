package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RegisterGUI extends RefreshablePanel {

    JLabel error;
    JTextField name;
    JTextField surname;
    JTextField username;
    JPasswordField password;
    JComboBox<String> type;
    JButton login;
    JButton register;

    ActionListener loginListener;
    ActionListener registerListener;

    public RegisterGUI() {
        buildUI();
    }

    @Override
    public void buildUI() {
        removeAll();
        name = new JTextField(10);
        surname = new JTextField(10);
        username = new JTextField(10);
        password = new JPasswordField(10);

        type = new JComboBox<>();
        type.addItem("Πάροχος");
        type.addItem("Χρήστης");

        register = new JButton("Εγγραφή");
        register.addActionListener(registerListener);
        register.setAlignmentX(CENTER_ALIGNMENT);

        login = new JButton("Είσοδος");
        login.addActionListener(loginListener);
        login.setAlignmentX(CENTER_ALIGNMENT);

        error = new JLabel();
        error.setForeground(Color.RED);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Όνομα"), c);
        c.gridx = 1;
        add(name, c);
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Επώνυμο"), c);
        c.gridx = 1;
        add(surname, c);
        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Όνομα χρήστη"), c);
        c.gridx = 1;
        add(username, c);
        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Κωδικός"), c);
        c.gridx = 1;
        add(password, c);
        c.gridx = 0;
        c.gridy = 4;
        add(new JLabel("Τύπος"), c);
        c.gridx = 1;
        add(type, c);
        c.gridx = 0;
        c.gridy = 5;
        add(login, c);
        c.gridx = 1;
        add(register, c);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 6;
        add(error, c);
        refresh();
    }

    public String getName() {
        return name.getText();
    }

    public String getSurname() {
        return surname.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public String getType() {
        return (String) type.getSelectedItem();
    }

    public void addRegisterListener(ActionListener listener) {
        registerListener = listener;
        register.addActionListener(registerListener);
    }

    public void addLoginListener(ActionListener listener) {
        loginListener = listener;
        login.addActionListener(loginListener);
    }

    public void setError(String error) {
        this.error.setText(error);
    }
}
