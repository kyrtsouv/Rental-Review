package gui;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import api.Rental;
import api.Review;
import api.Tenant;

public class AddReview extends JDialog {
    JLabel commentLabel;
    JLabel ratingLabel;
    JLabel errorLabel;

    JTextArea comment;
    JSpinner rating;

    JButton submitButton;

    public AddReview(java.awt.Frame parent, boolean modal, Tenant tenant, Rental rental) {
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

        commentLabel = new JLabel("Σχόλιο");
        commentLabel.setAlignmentX(CENTER_ALIGNMENT);

        comment = new JTextArea();
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        comment.setDocument(doc);
        comment.setBorder(BorderFactory.createLineBorder(Color.black));

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

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        submitButton = new JButton("OK");
        submitButton.setAlignmentX(CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comment.getText().length() == 0) {
                    errorLabel.setText("Το σχόλιο είναι υποχρεωτικό");
                } else {
                    Review review = new Review(tenant, comment.getText(), (int) rating.getValue());
                    tenant.addReview(rental, review);
                    rental.addReview(review);
                    dispose();
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(commentLabel);
        add(comment);
        add(ratingLabel);
        add(rating);
        add(errorLabel);
        add(submitButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }
}
