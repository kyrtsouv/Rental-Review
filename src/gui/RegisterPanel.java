package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RegisterPanel extends JPanel {
    final Dimension outerDimension = new Dimension(300, 300);
    final Dimension innerDimension = new Dimension(230, 300);
    final Color grey = new Color(210, 210, 210);

    JPanel outerPanel;
    JPanel inputPanel;
    JPanel loginPanel;

    JLabel nameLabel;
    JLabel surnameLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel errorLabel;
    JLabel loginLabel;

    JTextField name;
    JTextField surname;
    JTextField username;
    JPasswordField password;

    JComboBox<String> typeComboBox;

    MyButton loginButton;
    MyButton registerButton;

    public RegisterPanel() {
        name = new JTextField(10);
        surname = new JTextField(10);
        username = new JTextField(10);
        password = new JPasswordField(10);
        nameLabel = new JLabel("Name");
        surnameLabel = new JLabel("Surname");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(name);
        inputPanel.add(surnameLabel);
        inputPanel.add(surname);
        inputPanel.add(usernameLabel);
        inputPanel.add(username);
        inputPanel.add(passwordLabel);
        inputPanel.add(password);

        typeComboBox = new JComboBox<>();
        typeComboBox.addItem("Renter");
        typeComboBox.addItem("Tenant");

        errorLabel = new JLabel();
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        errorLabel.setForeground(Color.RED);

        registerButton = new MyButton("Register");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.setContentAreaFilled(false);

        loginLabel = new JLabel("Already have an account?");

        loginButton = new MyButton("Login");
        loginButton.setForeground(Color.BLUE.brighter());
        loginButton.setContentAreaFilled(false);

        loginPanel = new JPanel();
        loginPanel.add(loginLabel);
        loginPanel.add(loginButton);

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.setMaximumSize(innerDimension);
        outerPanel.add(inputPanel);
        outerPanel.add(Box.createVerticalStrut(4));
        outerPanel.add(typeComboBox);
        outerPanel.add(errorLabel);
        outerPanel.add(registerButton);
        outerPanel.add(loginPanel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(outerDimension);
        add(Box.createVerticalGlue());
        add(outerPanel);
        add(Box.createVerticalGlue());
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
        return (String) typeComboBox.getSelectedItem();
    }

    public void addRegisterListener(ActionListener listener) {
        name.addActionListener(listener);
        surname.addActionListener(listener);
        username.addActionListener(listener);
        password.addActionListener(listener);
        registerButton.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setError(String error) {
        errorLabel.setText(error);
    }

    public void clear() {
        name.setText("");
        surname.setText("");
        username.setText("");
        password.setText("");
        errorLabel.setText("");
    }
}
