package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI {
    JFrame frame;
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    CardLayout cardLayout;

    public MainGUI() {
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();

        cardLayout = new CardLayout();

        frame = new JFrame("Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setLayout(cardLayout);
        frame.add(loginPanel, "login");
        frame.add(registerPanel, "register");

        cardLayout.show(frame.getContentPane(), "login");

        frame.pack();
        frame.setMinimumSize(frame.getSize());
        System.out.println(frame.getSize());
        frame.setVisible(true);
    }

    public void setLoginError(String text) {
        loginPanel.setError(text);
    }

    public void setRegisterError(String text) {
        registerPanel.setError(text);
    }

    public void showLogin() {
        cardLayout.show(frame.getContentPane(), "login");
        setRegisterError("");
    }

    public void showRegister() {
        cardLayout.show(frame.getContentPane(), "register");
        setLoginError("");
    }

    public void addLoginForRegisterListener(ActionListener listener) {
        registerPanel.addLoginListener(listener);
    }

    public void addRegisterForLoginListener(ActionListener listener) {
        loginPanel.addRegisterListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        loginPanel.addLoginListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerPanel.addRegisterListener(listener);
    }

    public static void main(String[] args) {
        MainGUI main = new MainGUI();
        for (Component comp : main.frame.getContentPane().getComponents()) {
            System.out.println(comp);
        }
        main.addLoginForRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showLogin();
            }
        });
        main.addRegisterForLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showRegister();
            }
        });

        main.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setLoginError("Login");
            }
        });

        main.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setRegisterError("Register");
            }
        });
    }

}
