package gui;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class MainGUI extends JFrame {
    RefreshablePanel mainPanel;

    JScrollPane scrollPane;

    LoginGUI login;
    RegisterGUI register;

    RefreshablePanel previous;
    RefreshablePanel current;

    public MainGUI(LoginGUI login, RegisterGUI register) {
        super("Login/Register");
        mainPanel = new RefreshablePanel();
        this.login = login;
        this.register = register;

        authenticationHandling();
        scrollPane = new JScrollPane(mainPanel);

        show(login);
        previous = login;
        current = login;

        add(scrollPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();

        setVisible(true);
    }

    public void show(RefreshablePanel panel) {
        previous = current;
        current = panel;

        mainPanel.removeAll();
        panel.buildUI();
        mainPanel.add(panel);
        mainPanel.refresh();
        setPreferredSize(new Dimension(800, 800));
    }

    public void showPrevious() {
        show(previous);
    }

    public void authenticationHandling() {
        login.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show(register);
                previous = login;
            }
        });

        register.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show(login);
                previous = register;
            }
        });
    }
}
