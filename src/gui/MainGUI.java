package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {
    CardLayout cardLayout;

    JScrollPane scrollPane;

    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    RenterPanel renterPanel;
    TenantPanel tenantPanel;

    JPanel innerPanel;

    public MainGUI(LoginPanel loginPanel, RegisterPanel registerPanel) {

        this.loginPanel = loginPanel;
        this.registerPanel = registerPanel;

        cardLayout = new CardLayout();

        innerPanel = new JPanel();

        innerPanel.setLayout(cardLayout);
        innerPanel.add(loginPanel, "login");
        innerPanel.add(registerPanel, "register");

        scrollPane = new JScrollPane(innerPanel);

        cardLayout.show(innerPanel, "login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login");
        add(scrollPane);
        setSize(new Dimension(800, 800));
        setResizable(false);
        setVisible(true);

        loginPanel.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegister();
                loginPanel.clear();
            }
        });

        registerPanel.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLogin();
                registerPanel.clear();
            }
        });
    }

    public void showLogin() {
        cardLayout.show(innerPanel, "login");
        loginPanel.clear();
        setTitle("Login");
    }

    public void showRegister() {
        cardLayout.show(innerPanel, "register");
        registerPanel.clear();
        setTitle("Register");
    }

    public void showRenter(RenterPanel renterPanel) {
        this.renterPanel = renterPanel;
        setResizable(true);
        innerPanel.add(renterPanel, "renter");
        cardLayout.show(innerPanel, "renter");
        setTitle("Renter");
    }

    public void showTenant(TenantPanel tenantPanel) {
        this.tenantPanel = tenantPanel;
        setResizable(true);
        innerPanel.add(tenantPanel, "tenant");
        cardLayout.show(innerPanel, "tenant");
        setTitle("Tenant");
    }
}
