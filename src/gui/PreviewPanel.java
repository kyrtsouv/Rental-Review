package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PreviewPanel extends JPanel {
    JPanel blackBorderPanel;
    JPanel whiteBorderPanel;

    JLabel titleLabel;
    JLabel typeLabel;
    JLabel locationLabel;
    JLabel ratingLabel;

    MyButton previewButton;

    public PreviewPanel(String name, String type, String location, float rating) {
        titleLabel = new JLabel("<html>" + name + " - " + type + "</html>");
        titleLabel.setFont(titleLabel.getFont().deriveFont(14.0f));

        locationLabel = new JLabel("<html>" + location + "</html>");
        locationLabel.setFont(locationLabel.getFont().deriveFont(12.0f));

        ratingLabel = new JLabel("<html>" + "Rating: " + (float) Math.round(rating * 100) / 100 + "</html>");
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(13.0f));

        whiteBorderPanel = new JPanel();
        whiteBorderPanel.setLayout(new BoxLayout(whiteBorderPanel, BoxLayout.PAGE_AXIS));
        whiteBorderPanel.setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        whiteBorderPanel.add(titleLabel);
        whiteBorderPanel.add(locationLabel);
        whiteBorderPanel.add(ratingLabel);

        blackBorderPanel = new JPanel();
        blackBorderPanel.setLayout(new BoxLayout(blackBorderPanel, BoxLayout.PAGE_AXIS));
        blackBorderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        blackBorderPanel.add(whiteBorderPanel);

        previewButton = new MyButton();
        previewButton.add(blackBorderPanel);
        previewButton.setLayout(new BoxLayout(previewButton, BoxLayout.PAGE_AXIS));
        previewButton.setBorder(BorderFactory.createEmptyBorder());

        setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(previewButton);
    }

    public void addPreviewListener(ActionListener listener) {
        previewButton.addActionListener(listener);
    }

    public void updatePreview(String name, String type, String location, float rating) {
        titleLabel.setText("<html>" + name + " - " + type + "</html>");
        locationLabel.setText("<html>" + location + "</html>");
        ratingLabel.setText("<html>" + "Rating: " + (float) Math.round(rating * 100) / 100 + "</html>");

        revalidate();
        repaint();
    }
}
