package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.HashMap;
import java.util.HashSet;

public class RentalDialog extends JDialog {
    JPanel outerPanel;
    JPanel titlePanel;
    JPanel detailsPanel;
    JPanel addPanel;
    JPanel myReviewPanel;
    JPanel reviewsPanel;
    HashMap<String, ReviewPanel> reviewPanels;
    HashMap<String, ReviewPanel> reviews;

    JLabel titleLabel;
    JLabel locationLabel;
    JLabel descriptionLabel;
    JLabel totalReviewsLabel;
    JLabel ratingLabel;

    JTextArea amenitiesText;

    MyButton editButton;
    MyButton deleteButton;
    MyButton addButton;

    JScrollPane scrollPane;

    String amenities;
    String viewer;

    public RentalDialog(Frame parent, boolean modal, String name, String type, String location, String description,
            HashSet<String> amenities, int totalReviews, float rating, HashMap<String, ReviewPanel> reviews,
            String viewer, String owner) {
        super(parent, modal);

        reviewPanels = new HashMap<>(reviews);
        this.viewer = viewer;

        titleLabel = new JLabel("<html>" + name + " - " + type + "</html>");
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));

        editButton = new MyButton("Edit");
        deleteButton = new MyButton("Delete");
        deleteButton.setBackground(Color.RED);
        if (!viewer.equals(owner)) {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }

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

        this.amenities = "Amenities:  ";
        for (String amenity : amenities) {
            this.amenities += amenity + ", ";
        }
        this.amenities = this.amenities.substring(0, this.amenities.length() - 2);
        amenitiesText = new JTextArea(this.amenities);
        amenitiesText.setAlignmentX(LEFT_ALIGNMENT);
        amenitiesText.setEditable(false);
        amenitiesText.setLineWrap(true);
        amenitiesText.setWrapStyleWord(true);
        amenitiesText.setBackground(getBackground());

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
        detailsPanel.setAlignmentX(Box.LEFT_ALIGNMENT);
        detailsPanel.add(titlePanel);
        detailsPanel.add(locationLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(amenitiesText);
        detailsPanel.add(Box.createVerticalStrut(10));

        ratingLabel = new JLabel("<html>" + "Rating: " + (float) Math.round(rating * 100) / 100 + "</html>");
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
        if (viewer.equals(owner)) {
            addPanel.setVisible(false);
        }

        myReviewPanel = new JPanel();
        myReviewPanel.setLayout(new BoxLayout(myReviewPanel, BoxLayout.PAGE_AXIS));
        myReviewPanel.setAlignmentX(Box.LEFT_ALIGNMENT);

        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.PAGE_AXIS));
        reviewsPanel.setAlignmentX(Box.LEFT_ALIGNMENT);

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.add(detailsPanel);
        outerPanel.add(ratingLabel);
        outerPanel.add(totalReviewsLabel);
        outerPanel.add(addPanel);
        outerPanel.add(myReviewPanel);
        outerPanel.add(reviewsPanel);

        scrollPane = new JScrollPane(outerPanel);

        add(scrollPane);
        setSize(800, 800);
        setMinimumSize(getSize());
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void updateRentalDialog(String name, String type, String location, String description,
            HashSet<String> amenities) {
        titleLabel.setText("<html>" + name + " - " + type + "</html>");
        locationLabel.setText("<html>" + location + "</html>");
        descriptionLabel.setText("<html>" + description + "</html>");

        this.amenities = "Amenities: ";
        for (String amenity : amenities) {
            this.amenities += amenity + ", ";
        }
        this.amenities = this.amenities.substring(0, this.amenities.length() - 2);
        amenitiesText.setText(this.amenities);

        revalidate();
        repaint();
    }

    public void addEditRentalListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addDeleteRentalListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addAddReviewListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addReview(ReviewPanel review) {

        reviews.put(viewer, review);
        myReviewPanel.add(review);

        int totalReviews = reviews.size();
        float rating = 0;

        for (ReviewPanel reviewPanel : reviews.values()) {
            rating += reviewPanel.getRating();
        }
        if (totalReviews != 0) {
            rating = rating / totalReviews;
        }

        totalReviewsLabel.setText("<html>" + "Total Reviews: " + String.valueOf(totalReviews) + "</html>");
        ratingLabel.setText("<html>" + "Rating: " + (float) Math.round(rating * 100) / 100 + "</html>");
        addPanel.setVisible(false);
        revalidate();
        repaint();
    }

    public void removeReview(String username) {
        myReviewPanel.removeAll();
        reviews.remove(username);
        reviewPanels.remove(username);

        addPanel.setVisible(true);

        int totalReviews = reviewPanels.size();
        float rating = 0;

        for (ReviewPanel reviewPanel : reviewPanels.values()) {
            rating += reviewPanel.getRating();
        }
        if (totalReviews != 0) {
            rating = rating / totalReviews;
        }

        totalReviewsLabel.setText("<html>" + "Total Reviews: " + String.valueOf(totalReviews) + "</html>");
        ratingLabel.setText("<html>" + "Rating: " + (float) Math.round(rating * 100) / 100 + "</html>");
        addPanel.setVisible(true);
        revalidate();
        repaint();
    }

    public void addReviews(HashMap<String, ReviewPanel> reviews) {
        reviewPanels = new HashMap<>(reviews);
        this.reviews = reviews;
        if (reviewPanels.containsKey(viewer)) {
            addPanel.setVisible(false);
            myReviewPanel.add(reviewPanels.remove(viewer));
        }

        for (JPanel reviewPanel : reviewPanels.values()) {
            reviewsPanel.add(reviewPanel);
        }

        int totalReviews = reviews.size();
        float rating = 0;

        for (ReviewPanel reviewPanel : reviews.values()) {
            rating += reviewPanel.getRating();
        }
        if (totalReviews != 0) {
            rating = rating / totalReviews;
        }

        totalReviewsLabel.setText("<html>" + "Total Reviews: " + String.valueOf(totalReviews) + "</html>");
        ratingLabel.setText("<html>" + "Rating: " + String.valueOf((float) Math.round(rating * 100) / 100) + "</html>");
        revalidate();
        repaint();
    }

}
