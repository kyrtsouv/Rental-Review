package gui;

import java.awt.Color;

import javax.swing.*;
import javax.swing.text.*;

public class AddReview extends JDialog {
    JPanel commentPanel;
    JPanel ratingPanel;

    JTextArea comment;
    SpinnerNumberModel rating;
    JSpinner ratingSpinner;

    JButton submitButton;

    public AddReview(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string,
                    AttributeSet attr)
                    throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= 500) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (fb.getDocument().getLength() + text.length() - length <= 500) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        comment = new JTextArea();
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        comment.setDocument(doc);
        comment.setBorder(BorderFactory.createLineBorder(Color.black));

        commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.PAGE_AXIS));
        commentPanel.setBorder(BorderFactory.createTitledBorder("Comment"));
        commentPanel.add(comment);

        SpinnerNumberModel rating = new SpinnerNumberModel(5, 1, 5, 1);
        ratingSpinner = new JSpinner(rating);

        ratingPanel = new JPanel();
        ratingPanel.setBorder(BorderFactory.createTitledBorder("Rating"));
        ratingPanel.add(ratingSpinner);

        submitButton = new JButton("OK");
        submitButton.addActionListener(e -> {
            dispose();
        });
        submitButton.setAlignmentX(CENTER_ALIGNMENT);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(commentPanel);
        add(ratingPanel);
        add(submitButton);

        pack();
        setMinimumSize(getSize());
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setVisible(true);

    }

}
