package gui;

import java.util.HashSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TenantDashboardPanel extends JPanel {
    JPanel outerPanel;
    JPanel topPanel;
    JPanel previewsPanel;

    JLabel ratingLabel;

    MyButton searchButton;
    MyButton logoutButton;
    MyButton invisibleButton;

    public TenantDashboardPanel(float rating, HashSet<PreviewPanel> rentalPreviews) {
        searchButton = new MyButton("Αναζήτηση");

        ratingLabel = new JLabel(
                "<html>" + "Μέσος όρος Αξιολογήσεων: " + String.valueOf((float) Math.round(rating * 100) / 100)
                        + "</html>");
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(16.0f));

        previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.PAGE_AXIS));
        for (PreviewPanel previewPanel : rentalPreviews) {
            previewsPanel.add(previewPanel);
        }
        logoutButton = new MyButton("Έξοδος");
        logoutButton.setBackground(Color.RED);
        invisibleButton = new MyButton("Έξοδος");
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setForeground(getBackground());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(invisibleButton);
        topPanel.add(ratingLabel);
        topPanel.add(logoutButton);

        outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(topPanel, BorderLayout.PAGE_START);
        outerPanel.add(previewsPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(searchButton, BorderLayout.PAGE_START);
        add(outerPanel, BorderLayout.CENTER);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
