package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class RenterPanel extends JPanel {
    JPanel topPanel;
    JPanel previewsPanel;

    JLabel totalReviewsLabel;
    JLabel ratingLabel;

    MyButton addRentalButton;
    MyButton logoutButton;
    MyButton invisibleButton;

    public RenterPanel(int totalReviews, float rating, HashSet<PreviewPanel> rentalPreviewPanels) {
        totalReviewsLabel = new JLabel("<html>" + "Συνολικές Αξιολογήσεις: " + totalReviews + "</html>");
        totalReviewsLabel.setFont(totalReviewsLabel.getFont().deriveFont(16.0f));
        totalReviewsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ratingLabel = new JLabel("<html>" + "Βαθμολογία: " + (float) Math.round(rating * 100) / 100 + "</html>");
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(16.0f));
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ratingLabel.setAlignmentX(CENTER_ALIGNMENT);

        logoutButton = new MyButton("Έξοδος");
        logoutButton.setBackground(Color.RED);
        invisibleButton = new MyButton("Έξοδος");
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setForeground(getBackground());

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(invisibleButton);
        topPanel.add(totalReviewsLabel);
        topPanel.add(logoutButton);

        addRentalButton = new MyButton("Προσθήκη Κατοικίας");
        addRentalButton.setHorizontalAlignment(SwingConstants.CENTER);
        addRentalButton.setAlignmentX(CENTER_ALIGNMENT);

        previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.PAGE_AXIS));
        for (PreviewPanel previewPanel : rentalPreviewPanels) {
            previewPanel.setAlignmentX(CENTER_ALIGNMENT);
            previewsPanel.add(previewPanel);
        }

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(topPanel);
        add(ratingLabel);
        add(addRentalButton);
        add(previewsPanel);
    }

    public void addRentalListener(ActionListener listener) {
        addRentalButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addPreview(PreviewPanel previewPanel) {
        previewPanel.setAlignmentX(CENTER_ALIGNMENT);
        previewsPanel.add(previewPanel);
        previewPanel.revalidate();
        previewPanel.repaint();
    }

    public void removePreview(PreviewPanel previewPanel) {
        previewsPanel.remove(previewPanel);
        previewsPanel.revalidate();
        previewsPanel.repaint();
    }

}
