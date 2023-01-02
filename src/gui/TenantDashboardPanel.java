package gui;

import java.util.HashSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TenantDashboardPanel extends JPanel {
    JPanel innerPanel;
    JPanel previewsPanel;

    JLabel ratingLabel;

    MyButton searchButton;

    public TenantDashboardPanel(int rating, HashSet<PreviewPanel> rentalPreviews) {
        searchButton = new MyButton("Search");

        ratingLabel = new JLabel("<html>" + "Average Given Rating: " + String.valueOf(rating) + "</html>");
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(16.0f));

        previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.PAGE_AXIS));
        for (PreviewPanel previewPanel : rentalPreviews) {
            previewsPanel.add(previewPanel);
        }

        innerPanel = new JPanel(new BorderLayout());
        innerPanel.add(ratingLabel, BorderLayout.PAGE_START);
        innerPanel.add(previewsPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(searchButton, BorderLayout.PAGE_START);
        add(innerPanel, BorderLayout.CENTER);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
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
        frame.add(new TenantDashboardPanel(0, previewPanels));
        frame.setVisible(true);
    }
}
