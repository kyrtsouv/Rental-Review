package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {
    CardLayout cardLayout;

    public MainGUI(LoginPanel loginPanel, RegisterPanel registerPanel) {

        cardLayout = new CardLayout();

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFocusable(true);
        setLayout(cardLayout);
        add(loginPanel, "login");
        add(registerPanel, "register");

        cardLayout.show(getContentPane(), "login");

        pack();
        setResizable(false);
        setVisible(true);

        loginPanel.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegister();
                loginPanel.setError("");
            }
        });

        registerPanel.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLogin();
                registerPanel.setError("");
            }
        });
    }

    public void showLogin() {
        cardLayout.show(getContentPane(), "login");
        setTitle("Login");
    }

    public void showRegister() {
        cardLayout.show(getContentPane(), "register");
        setTitle("Register");
    }

    public static void main(String[] args) {

        LoginPanel loginPanel = new LoginPanel();
        RegisterPanel registerPanel = new RegisterPanel();
        new MainGUI(loginPanel, registerPanel);

        loginPanel.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPanel.setError("Login");
            }
        });

        registerPanel.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerPanel.setError("Register");
            }
        });
    }

}
