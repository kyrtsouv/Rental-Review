package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginPanel extends JPanel {
    final Dimension colorDim = new Dimension(300, 300);
    final Dimension outerDim = new Dimension(230, 150);
    final Color grey = new Color(210, 210, 210);

    JPanel colorPanel;
    JPanel outerPanel;
    JPanel innerPanel;
    JPanel errorPanel;
    JPanel loginPanel;
    JPanel registerPanel;

    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel errorLabel;
    JLabel registerLabel;

    JTextField username;
    JPasswordField password;

    JButton login;
    JButton register;

    public LoginPanel() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(CENTER_ALIGNMENT);
        setPreferredSize(colorDim);
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        registerLabel = new JLabel("Don't have an account?");
        username = new JTextField(10);
        password = new JPasswordField(10);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        login = new JButton("Login");
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);

        register = new JButton("Register");
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.setFocusPainted(false);
        register.setForeground(Color.BLUE.brighter());

        innerPanel = new JPanel(new GridLayout(2, 2));
        innerPanel.add(usernameLabel);
        innerPanel.add(username);
        innerPanel.add(passwordLabel);
        innerPanel.add(password);

        errorPanel = new JPanel();
        errorPanel.add(errorLabel);

        loginPanel = new JPanel();
        loginPanel.add(login);

        registerPanel = new JPanel();
        registerPanel.add(registerLabel);
        registerPanel.add(register);

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.setPreferredSize(outerDim);
        outerPanel.setMaximumSize(outerDim);
        outerPanel.add(innerPanel);
        outerPanel.add(errorPanel);
        outerPanel.add(loginPanel);
        outerPanel.add(registerPanel);

        colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.PAGE_AXIS));
        colorPanel.setPreferredSize(colorDim);
        colorPanel.setMaximumSize(colorDim);
        colorPanel.add(Box.createVerticalGlue());
        colorPanel.add(outerPanel);
        colorPanel.add(Box.createVerticalGlue());

        paint(grey);

        add(Box.createVerticalGlue());
        add(colorPanel);
        add(Box.createVerticalGlue());

    }

    private void paint(Color color) {
        colorPanel.setBackground(color);
        outerPanel.setOpaque(false);
        innerPanel.setOpaque(false);
        errorPanel.setOpaque(false);
        loginPanel.setOpaque(false);
        registerPanel.setOpaque(false);
    }

    public void addLoginListener(ActionListener listener) {
        login.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        register.addActionListener(listener);
    }

    public void setError(String text) {
        errorLabel.setText(text);
    }
}
