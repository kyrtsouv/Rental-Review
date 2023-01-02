package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.HashSet;

public class RentalPanel extends JPanel {
    JPanel titlePanel;
    JPanel detailsPanel;
    JPanel amenitiesPanel;
    JPanel addPanel;
    JPanel reviewsPanel;
    HashSet<ReviewPanel> reviewPanels;

    JLabel titleLabel;
    JLabel locationLabel;
    JLabel descriptionLabel;
    JLabel amenitiesLabel;
    JLabel totalReviewsLabel;
    JLabel ratingLabel;

    MyButton editButton;
    MyButton deleteButton;
    MyButton addButton;

    String amenities;

    public RentalPanel(String name, String type, String location, String description, ArrayList<String> amenities,
            int totalReviews, int rating, HashSet<ReviewPanel> reviews) {
        titleLabel = new JLabel("<html>" + name + " - " + type + "</html>");
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));

        editButton = new MyButton("Edit");
        deleteButton = new MyButton("Delete");
        deleteButton.setBackground(Color.RED);

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.add(titleLabel);
        titlePanel.add(editButton);
        titlePanel.add(deleteButton);
        titlePanel.setAlignmentX(LEFT_ALIGNMENT);
        titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));

        locationLabel = new JLabel("<html>" + location + "</html>");
        locationLabel.setFont(locationLabel.getFont().deriveFont(16.0f));
        locationLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        descriptionLabel = new JLabel("<html>" + description + "</html>");
        descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(14.0f));
        descriptionLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        this.amenities = "Amenities: ";
        for (String amenity : amenities) {
            this.amenities += amenity + ", ";
        }
        this.amenities = this.amenities.substring(0, this.amenities.length() - 2);
        amenitiesLabel = new JLabel("<html>" + this.amenities + "</html>");

        amenitiesPanel = new JPanel();
        amenitiesPanel.setLayout(new BoxLayout(amenitiesPanel, BoxLayout.LINE_AXIS));
        amenitiesPanel.add(amenitiesLabel);
        amenitiesPanel.setAlignmentX(LEFT_ALIGNMENT);
        amenitiesPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
        detailsPanel.setAlignmentX(Box.LEFT_ALIGNMENT);
        detailsPanel.add(titlePanel);
        detailsPanel.add(locationLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(amenitiesPanel);
        detailsPanel.add(Box.createVerticalStrut(10));

        ratingLabel = new JLabel("<html>" + "Rating: " + Integer.toString(rating) + "</html>");
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(16.0f));
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        totalReviewsLabel = new JLabel("<html>" + "Total Reviews: " + Integer.toString(totalReviews) + "</html>");
        totalReviewsLabel.setFont(totalReviewsLabel.getFont().deriveFont(16.0f));
        totalReviewsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        addButton = new MyButton("Add Review");

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
        addPanel.setAlignmentX(LEFT_ALIGNMENT);
        addPanel.add(Box.createHorizontalGlue());
        addPanel.add(addButton);
        addPanel.add(Box.createHorizontalGlue());

        reviewPanels = reviews;

        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.PAGE_AXIS));
        reviewsPanel.setAlignmentX(Box.LEFT_ALIGNMENT);
        for (JPanel reviewPanel : reviewPanels) {
            reviewsPanel.add(reviewPanel);
        }

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(detailsPanel);
        add(ratingLabel);
        add(totalReviewsLabel);
        add(addPanel);
        add(reviewsPanel);
    }

    public void addButtonEditListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addButtonDeleteListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addButtonAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addReview(ReviewPanel review) {

        reviewPanels.add(review);
        reviewsPanel.add(review);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        ArrayList<String> amenities = new ArrayList<>();

        amenities.add("Wifi");
        amenities.add("Parking");
        amenities.add("Pool");
        amenities.add("Gym");
        amenities.add("Laundry");

        ReviewPanel review = new ReviewPanel("makis", 5, "very good", "5/10/2020");
        ReviewPanel review2 = new ReviewPanel("nikos", 2, "there is no way i'm going back there", "5/10/2022");

        RentalPanel rental = new RentalPanel("Rental", "Apartment", "Location",
                "Description", amenities, 5, 4, new HashSet<>() {
                    {
                        add(review);
                        add(review2);
                    }
                });
        frame.add(rental);

        frame.pack();
        frame.setMinimumSize(frame.getSize());

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
