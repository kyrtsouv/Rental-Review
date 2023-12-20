package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends RefreshablePanel {
    JPanel inputPanel;
    JLabel errorLabel;
    JTextField username;
    JPasswordField password;
    JButton login;
    JButton register;
    ActionListener loginListener;
    ActionListener registerListener;

    public LoginGUI() {
        buildUI();
    }

    @Override
    public void buildUI() {
        removeAll();
        username = new JTextField(10);
        password = new JPasswordField(10);
        login = new JButton("Είσοδος");
        login.addActionListener(loginListener);
        register = new JButton("Εγγραφή");
        register.addActionListener(registerListener);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Όνομα χρήστη:"), c);
        c.gridx = 1;
        add(username, c);
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Κωδικός:"), c);
        c.gridx = 1;
        add(password, c);
        c.gridx = 0;
        c.gridy = 2;
        add(login, c);
        c.gridx = 1;
        add(register, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(errorLabel, c);

        refresh();
    }

    public void addLoginListener(ActionListener listener) {
        loginListener = listener;
        login.addActionListener(loginListener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerListener = listener;
        register.addActionListener(registerListener);
    }

    public void setError(String text) {
        errorLabel.setText(text);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }
}
