package gui;

import javax.swing.*;
import java.awt.event.*;

import api.*;

public class RentalGUI extends RefreshablePanel {
    JPanel top;
    JPanel addPanel;
    JPanel reviews;
    JLabel title;
    JLabel location;
    JLabel description;
    JLabel totalReviews;
    JLabel rating;
    JTextArea amenitiesText;
    JButton editButton;
    JButton backButton;
    JButton addButton;

    Rental rental;
    User viewer;
    Renter renter;
    MainGUI main;
    Database db;

    public RentalGUI(Rental rental, User viewer, Renter renter, MainGUI main, Database db) {
        this.viewer = viewer;
        this.renter = renter;
        this.rental = rental;
        this.main = main;
        this.db = db;

        buildUI();
    }

    public void buildUI() {
        removeAll();
        title = new JLabel("<html>" + rental.get("name") + "</html>");
        title.setFont(title.getFont().deriveFont(20.0f));

        editButton = new JButton("Επεξεργασία");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditRental(main, true, db.getAmenities(), db.getRentalTypes(), rental, renter, db);
                buildUI();
                main.showPrevious();
            }
        });
        if (!viewer.equals(renter)) {
            editButton.setVisible(false);
        }
        backButton = new JButton("Πίσω");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showPrevious();
            }
        });

        top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
        top.add(title);

        top.add(editButton);
        top.add(backButton);
        top.setAlignmentX(LEFT_ALIGNMENT);

        location = new JLabel(rental.get("location"));

        description = new JLabel(rental.get("description"));

        String amenities = "Παροχές:  ";
        for (String amenity : rental.getAmenities()) {
            amenities += amenity + ", ";
        }
        amenities = amenities.substring(0, amenities.length() - 2);

        amenitiesText = new JTextArea(amenities);
        amenitiesText.setAlignmentX(LEFT_ALIGNMENT);
        amenitiesText.setEditable(false);
        amenitiesText.setLineWrap(true);
        amenitiesText.setWrapStyleWord(true);
        amenitiesText.setBackground(getBackground());

        rating = new JLabel("<html>" + "Βαθμολογία: " + Rounder.round(rental.getRating()) + "</html>");
        rating.setFont(rating.getFont().deriveFont(20.0f));
        rating.setHorizontalAlignment(SwingConstants.CENTER);

        totalReviews = new JLabel(
                "<html>" + "Συνολικές Αξιολογήσεις: " + rental.getReviews().size() + "</html>");
        totalReviews.setFont(totalReviews.getFont().deriveFont(20.0f));
        totalReviews.setHorizontalAlignment(SwingConstants.CENTER);

        addButton = new JButton("Προσθήκη Αξιολόγησης");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddReview(main, true, (Tenant) viewer, rental);
                buildUI();
            }
        });

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
        addPanel.setAlignmentX(LEFT_ALIGNMENT);
        addPanel.add(Box.createHorizontalGlue());
        addPanel.add(addButton);
        addPanel.add(Box.createHorizontalGlue());

        reviews = new JPanel();
        reviews.setLayout(new BoxLayout(reviews, BoxLayout.PAGE_AXIS));
        reviews.setAlignmentX(Box.LEFT_ALIGNMENT);

        if (!viewer.equals(renter))
            if (!rental.getReviews().containsKey(viewer)) {
                reviews.add(addPanel);
            } else {
                reviews.add(new ReviewGUI(rental.getReviews().get(viewer), viewer, (Tenant) viewer, rental, main));
            }
        for (Review review : rental.getReviews().values()) {
            if (!review.getUser().equals(viewer)) {
                reviews.add(new ReviewGUI(review, viewer, review.getUser(), rental, main));
            }
        }
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(top);
        add(location);
        add(description);
        add(amenitiesText);
        add(rating);
        add(totalReviews);
        add(reviews);

        refresh();
    }
}
