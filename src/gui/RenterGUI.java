package gui;

import javax.swing.*;

import api.*;

import java.awt.*;
import java.awt.event.*;

public class RenterGUI extends RefreshablePanel implements ActionListener {
    JPanel topPanel;
    JPanel ratingPanel;
    JPanel previewsPanel;
    JButton logoutButton;
    JButton addRentalButton;

    Renter renter;
    ActionListener addRentalListener;
    ActionListener logoutListener;
    ActionListener previewListener;

    MainGUI main;
    Database db;

    public RenterGUI(Renter renter, MainGUI main, Database db) {
        this.renter = renter;
        this.main = main;
        this.db = db;
        buildUI();
    }

    @Override
    public void buildUI() {
        removeAll();
        ratingPanel = new JPanel();
        ratingPanel.setLayout(new BoxLayout(ratingPanel, BoxLayout.PAGE_AXIS));
        ratingPanel.add(new JLabel("<html>" + "Συνολικές Αξιολογήσεις: " + renter.getRatingAmount() + "</html>"));
        ratingPanel.add(new JLabel("<html>" + "Βαθμολογία: " + Rounder.round(renter.getRating()) + "</html>"));

        logoutButton = new JButton("Έξοδος");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.show(main.login);
            }
        });
        logoutButton.setBackground(Color.RED);

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(ratingPanel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(logoutButton);
        topPanel.setAlignmentX(CENTER_ALIGNMENT);

        addRentalButton = new JButton("Προσθήκη Κατοικίας");
        addRentalButton.addActionListener(this);
        addRentalButton.setHorizontalAlignment(SwingConstants.CENTER);
        addRentalButton.setAlignmentX(CENTER_ALIGNMENT);

        previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.PAGE_AXIS));
        for (Rental rental : renter.getRentals()) {
            PreviewGUI preview = new PreviewGUI(rental, main, db, renter, renter);
            preview.setAlignmentX(CENTER_ALIGNMENT);
            previewsPanel.add(preview);
        }

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(topPanel);
        add(addRentalButton);
        add(previewsPanel);

        refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new AddRental(main, true, db.getAmenities(), db.getRentalTypes(), db, renter);
        main.show(new RenterGUI(renter, main, db));
    }
}
