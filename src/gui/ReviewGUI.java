package gui;

import javax.swing.*;

import api.*;

import java.awt.*;
import java.awt.event.*;

public class ReviewGUI extends JButton {
    JLabel usernameLabel;
    JLabel ratingLabel;
    JLabel commentLabel;
    JLabel dateLabel;

    Review review;
    User viewer;
    Tenant tenant;
    Rental rental;
    MainGUI main;

    public ReviewGUI(Review review, User viewer, Tenant tenant, Rental rental, MainGUI main) {
        this.review = review;
        this.viewer = viewer;
        this.tenant = tenant;
        this.rental = rental;
        this.main = main;

        buildUI();
    }

    public void buildUI() {
        removeAll();
        if (!viewer.equals(tenant)) {
            setEnabled(false);
        }

        usernameLabel = new JLabel(review.getUser().getUsername());
        ratingLabel = new JLabel("Βαθμολογία: " + review.getRating());
        commentLabel = new JLabel("<html>" + review.getComment() + "</html>");
        dateLabel = new JLabel(review.getDate());
        dateLabel.setForeground(new Color(150, 150, 150));

        setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(usernameLabel);
        add(commentLabel);
        add(ratingLabel);
        add(dateLabel);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditReview(main, true, review, rental, tenant);
                main.showPrevious();
            }
        });
    }
}
