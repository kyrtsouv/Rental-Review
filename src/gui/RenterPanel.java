package gui;

import javax.swing.*;
import java.util.HashSet;

public class RenterPanel extends JPanel {
    JPanel previewsPanel;

    JLabel totalReviewsLabel;
    JLabel ratingLabel;

    public RenterPanel(int totalReviews, int rating, HashSet<PreviewPanel> rentalPreviewPanels) {
        totalReviewsLabel = new JLabel("<html>" + "Number of Reviews: " + totalReviews + "</html>");
        totalReviewsLabel.setFont(totalReviewsLabel.getFont().deriveFont(16.0f));
        totalReviewsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ratingLabel = new JLabel("<html>" + "Rating: " + rating + "</html>");
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(16.0f));
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.PAGE_AXIS));
        for (PreviewPanel previewPanel : rentalPreviewPanels) {
            previewsPanel.add(previewPanel);
        }

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(totalReviewsLabel);
        add(ratingLabel);
        add(previewsPanel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        PreviewPanel previewPanel = new PreviewPanel("name", "type", "location", 5);
        PreviewPanel previewPanel2 = new PreviewPanel("name", "type", "location", 4);
        HashSet<PreviewPanel> previewPanels = new HashSet<PreviewPanel>();
        previewPanels.add(previewPanel);
        previewPanels.add(previewPanel2);
        frame.add(new RenterPanel(0, 0, previewPanels));
        frame.setVisible(true);
    }
}
