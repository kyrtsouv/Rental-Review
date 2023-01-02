package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginPanel extends JPanel {
    final Dimension outerDimension = new Dimension(300, 300);
    final Dimension innerDimension = new Dimension(230, 300);
    final Color grey = new Color(210, 210, 210);

    JPanel outerPanel;
    JPanel inputPanel;
    JPanel registerPanel;

    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel errorLabel;
    JLabel registerLabel;

    JTextField username;
    JPasswordField password;

    MyButton loginButton;
    MyButton register;

    public LoginPanel() {
        usernameLabel = new JLabel("Όνομα χρήστη:");
        passwordLabel = new JLabel("Κωδικός:");
        username = new JTextField(10);
        password = new JPasswordField(10);

        inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(usernameLabel);
        inputPanel.add(username);
        inputPanel.add(passwordLabel);
        inputPanel.add(password);

        errorLabel = new JLabel();
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        errorLabel.setForeground(Color.RED);

        loginButton = new MyButton("Είσοδος");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.setContentAreaFilled(false);

        registerLabel = new JLabel("Δεν έχετε λογαριασμό;");

        register = new MyButton("Εγγραφή");
        register.setForeground(Color.BLUE.brighter());
        register.setContentAreaFilled(false);

        registerPanel = new JPanel();
        registerPanel.add(registerLabel);
        registerPanel.add(register);

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.setMaximumSize(innerDimension);
        outerPanel.add(inputPanel);
        outerPanel.add(errorLabel);
        outerPanel.add(loginButton);
        outerPanel.add(registerPanel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(outerDimension);
        add(Box.createVerticalGlue());
        add(outerPanel);
        add(Box.createVerticalGlue());
    }

    public void addLoginListener(ActionListener listener) {
        username.addActionListener(listener);
        password.addActionListener(listener);
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        register.addActionListener(listener);
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

    public void clear() {
        username.setText("");
        password.setText("");
        errorLabel.setText("");
    }
}
