package gui;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import api.Rental;
import api.Review;
import api.Tenant;

public class EditReview extends JDialog {
    JPanel submitPanel;
    JLabel commentLabel;
    JLabel ratingLabel;
    JLabel errorLabel;
    JTextArea comment;
    JSpinner rating;
    JButton editButton;
    JButton deleteButton;

    public EditReview(java.awt.Frame parent, boolean modal, Review review, Rental rental, Tenant tenant) {
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
        comment.setDocument(doc);
        comment.setText(review.getComment());
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        comment.setBorder(BorderFactory.createLineBorder(Color.black));

        commentLabel = new JLabel("Σχόλιο: " + comment.getText().length() + "/500");
        commentLabel.setAlignmentX(CENTER_ALIGNMENT);

        Document d = comment.getDocument();
        d.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            private void update() {
                commentLabel.setText("Σχόλιο: " + comment.getText().length() + "/500");
            }
        });
        ratingLabel = new JLabel("Βαθμολογία");
        ratingLabel.setAlignmentX(CENTER_ALIGNMENT);

        rating = new JSpinner(new SpinnerNumberModel(5, 1, 5, 1));
        rating.setValue(review.getRating());

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        editButton = new JButton("OK");
        editButton.setAlignmentX(CENTER_ALIGNMENT);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comment.getText().length() == 0) {
                    errorLabel.setText("Παρακαλώ συμπληρώστε τα πεδία");
                } else {
                    review.editReview(comment.getText(), (int) rating.getValue());
                    dispose();
                }
            }
        });

        deleteButton = new JButton("Διαγραφή");
        deleteButton.setAlignmentX(CENTER_ALIGNMENT);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tenant.deleteReview(rental);
                rental.deleteReview(tenant);
                dispose();
            }
        });

        submitPanel = new JPanel();
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.LINE_AXIS));
        submitPanel.add(Box.createHorizontalGlue());
        submitPanel.add(editButton);
        submitPanel.add(Box.createHorizontalStrut(5));
        submitPanel.add(deleteButton);
        submitPanel.add(Box.createHorizontalGlue());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(commentLabel);
        add(comment);
        add(ratingLabel);
        add(rating);
        add(errorLabel);
        add(submitPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }
}
