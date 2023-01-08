package gui;

import api.Rental;
import api.User;
import api.Database;
import api.Renter;
import api.Rounder;

import java.awt.event.*;

import javax.swing.*;

public class PreviewGUI extends JButton implements ActionListener {
    JLabel titleLabel;
    JLabel typeLabel;
    JLabel locationLabel;
    JLabel ratingLabel;

    Rental rental;
    MainGUI main;
    Database db;
    User viewer;
    Renter renter;

    public PreviewGUI(Rental rental, MainGUI main, Database db, User viewer, Renter renter) {

        this.rental = rental;
        this.main = main;
        this.db = db;
        this.viewer = viewer;
        this.renter = renter;

        buildUI();
    }

    public void buildUI() {
        removeAll();
        titleLabel = new JLabel(rental.get("name"));
        titleLabel.setFont(titleLabel.getFont().deriveFont(14.0f));

        typeLabel = new JLabel(rental.get("type"));
        typeLabel.setFont(typeLabel.getFont().deriveFont(12.0f));

        locationLabel = new JLabel(rental.get("location"));
        locationLabel.setFont(locationLabel.getFont().deriveFont(12.0f));

        ratingLabel = new JLabel("<html>" + "Βαθμολογία: " + Rounder.round(rental.getRating()) + "</html>");
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(13.0f));

        addActionListener(this);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        add(titleLabel);
        add(typeLabel);
        add(locationLabel);
        add(ratingLabel);
        refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.show(new RentalGUI(rental, viewer, renter, main, db));
    }

    public void addPreviewListener(ActionListener listener) {
        addActionListener(listener);
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
