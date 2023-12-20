package gui;

import javax.swing.*;

import api.*;

import java.awt.event.*;
import java.awt.*;

public class TenantGUI extends RefreshablePanel {
    JPanel previews;
    JButton searchButton;
    JButton logoutButton;
    Tenant tenant;
    MainGUI main;
    Database db;

    public TenantGUI(Tenant tenant, MainGUI main, Database db) {
        this.tenant = tenant;
        this.main = main;
        this.db = db;
        buildUI();
    }

    @Override
    public void buildUI() {
        removeAll();
        searchButton = new JButton("Αναζήτηση");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.show(new SearchGUI(db.getAmenities(), db.getRentals(), tenant, main, db));
            }
        });
        logoutButton = new JButton("Έξοδος");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.show(main.login);
            }
        });
        logoutButton.setBackground(Color.RED);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(
                new JLabel("<html>" + "Μέσος όρος Αξιολογήσεων: " + Rounder.round(tenant.getRating()) + "</html>"));
        topPanel.add(searchButton);
        topPanel.add(logoutButton);
        topPanel.setAlignmentX(CENTER_ALIGNMENT);

        previews = new JPanel();
        previews.setLayout(new BoxLayout(previews, BoxLayout.PAGE_AXIS));
        for (Rental rental : tenant.getReviews().keySet()) {
            TenantPreviewGUI preview = new TenantPreviewGUI(rental, main, db, tenant, rental.getOwner());
            preview.setAlignmentX(CENTER_ALIGNMENT);
            previews.add(preview);
        }

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(topPanel);
        add(previews);

        refresh();
    }
}
