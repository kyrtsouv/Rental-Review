package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReviewPanel extends JPanel {
    JPanel blackBorderPanel;
    JPanel innerPanel;
    JPanel topPanel;

    JLabel usernameLabel;
    JLabel ratingLabel;
    JLabel commentLabel;
    JLabel dateLabel;

    MyButton editButton;
    MyButton deleteButton;

    public ReviewPanel(String username, float rating, String comment, String date, String viewer, String owner) {
        usernameLabel = new JLabel(username);
        editButton = new MyButton("Επεξεργασία");
        deleteButton = new MyButton("Διαγραφή");
        deleteButton.setBackground(Color.RED);
        if (!viewer.equals(owner)) {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setAlignmentX(LEFT_ALIGNMENT);
        topPanel.add(usernameLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        ratingLabel = new JLabel("Βαθμολογία: " + rating);
        commentLabel = new JLabel("<html>" + comment + "</html>");
        dateLabel = new JLabel(date);
        dateLabel.setForeground(getForeground().brighter().brighter().brighter());

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
        innerPanel.setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        innerPanel.add(topPanel);
        innerPanel.add(ratingLabel);
        innerPanel.add(commentLabel);
        innerPanel.add(dateLabel);

        blackBorderPanel = new JPanel();
        blackBorderPanel.setLayout(new BoxLayout(blackBorderPanel, BoxLayout.PAGE_AXIS));
        blackBorderPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        blackBorderPanel.add(innerPanel);

        setBorder(BorderFactory.createMatteBorder(1, 3, 1, 3, getBackground()));
        setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(blackBorderPanel);
    }

    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public float getRating() {
        return (float) Double.parseDouble(ratingLabel.getText().substring(12));
    }

    public String getUsername() {
        return usernameLabel.getText();
    }

    public void updateReview(float rating, String comment, String date) {
        ratingLabel.setText("Βαθμολογία: " + rating);
        commentLabel.setText("<html>" + comment + "</html>");
        dateLabel.setText(date);

        revalidate();
        repaint();
    }
}
