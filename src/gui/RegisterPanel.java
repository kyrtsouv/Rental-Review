package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RegisterPanel extends JPanel {
    final Dimension colorDim = new Dimension(300, 300);
    final Dimension outerDim = new Dimension(230, 200);
    final Color grey = new Color(210, 210, 210);

    JPanel colorPanel;
    JPanel outerPanel;
    JPanel innerPanel;
    JPanel typePanel;
    JPanel errorPanel;
    JPanel loginPanel;
    JPanel registerPanel;

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

    JRadioButton renter;
    JRadioButton tenant;
    ButtonGroup type;

    JButton login;
    JButton register;

    public RegisterPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(CENTER_ALIGNMENT);

        name = new JTextField(10);
        surname = new JTextField(10);
        username = new JTextField(10);
        password = new JPasswordField(10);

        nameLabel = new JLabel("Name");
        surnameLabel = new JLabel("Surname");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        loginLabel = new JLabel("Already have an account?");

        renter = new JRadioButton("Renter");
        renter.setOpaque(false);
        renter.setSelected(true);

        tenant = new JRadioButton("Tenant");
        tenant.setOpaque(false);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        type = new ButtonGroup();
        type.add(renter);
        type.add(tenant);

        register = new JButton("Register");
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.setFocusPainted(false);

        login = new JButton("Login");
        login.setForeground(Color.BLUE.brighter());
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);

        innerPanel = new JPanel(new GridLayout(4, 2));
        innerPanel.add(nameLabel);
        innerPanel.add(name);
        innerPanel.add(surnameLabel);
        innerPanel.add(surname);
        innerPanel.add(usernameLabel);
        innerPanel.add(username);
        innerPanel.add(passwordLabel);
        innerPanel.add(password);

        typePanel = new JPanel();
        typePanel.add(renter);
        typePanel.add(tenant);

        errorPanel = new JPanel();
        errorPanel.add(errorLabel);

        registerPanel = new JPanel();
        registerPanel.add(register);

        loginPanel = new JPanel();
        loginPanel.add(loginLabel);
        loginPanel.add(login);

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.setPreferredSize(outerDim);
        outerPanel.setMaximumSize(outerDim);
        outerPanel.add(innerPanel);
        outerPanel.add(typePanel);
        outerPanel.add(errorPanel);
        outerPanel.add(registerPanel);
        outerPanel.add(loginPanel);

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
        typePanel.setOpaque(false);
        errorPanel.setOpaque(false);
        registerPanel.setOpaque(false);
        loginPanel.setOpaque(false);
    }

    public void addRegisterListener(ActionListener listener) {
        register.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        login.addActionListener(listener);
    }

    public void setError(String error) {
        errorLabel.setText(error);
    }
}
