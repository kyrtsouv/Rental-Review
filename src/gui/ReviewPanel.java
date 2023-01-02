package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReviewPanel extends JPanel {
    JPanel blackBorderPanel;
    JPanel innerPanel;
    JPanel topPanel;

    JLabel nameLabel;
    JLabel ratingLabel;
    JLabel commentLabel;
    JLabel dateLabel;

    MyButton editButton;
    MyButton deleteButton;

    public ReviewPanel(String username, int rating, String comment, String date) {
        nameLabel = new JLabel(username);
        editButton = new MyButton("Edit");
        deleteButton = new MyButton("Delete");
        deleteButton.setBackground(Color.RED);

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setAlignmentX(LEFT_ALIGNMENT);
        topPanel.add(nameLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        ratingLabel = new JLabel("Rating: " + rating);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ReviewPanel("username", 5, "comment", "date"));
        frame.pack();
        frame.setVisible(true);
    }
}
