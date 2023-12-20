package gui;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class AddReviewDialog extends JDialog {
    JPanel commentPanel;
    JPanel ratingPanel;

    JLabel errorLabel;

    JTextArea comment;

    SpinnerNumberModel rating;
    JSpinner ratingSpinner;

    JButton submitButton;

    public AddReviewDialog(java.awt.Frame parent, boolean modal) {
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
                commentPanel.setBorder(
                        BorderFactory.createTitledBorder("Σχόλιο: " + comment.getText().length() + "/500"));
            }
        });

        commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.PAGE_AXIS));
        commentPanel.setBorder(BorderFactory.createTitledBorder("Σχόλιο: 0/500"));
        commentPanel.add(comment);

        SpinnerNumberModel rating = new SpinnerNumberModel(5, 1, 5, 1);
        ratingSpinner = new JSpinner(rating);

        ratingPanel = new JPanel();
        ratingPanel.setBorder(BorderFactory.createTitledBorder("Βαθμολογία:"));
        ratingPanel.add(ratingSpinner);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        submitButton = new JButton("OK");
        submitButton.setAlignmentX(CENTER_ALIGNMENT);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(commentPanel);
        add(ratingPanel);
        add(errorLabel);
        add(submitButton);

        pack();
        setMinimumSize(getSize());
        setSize(400, 400);
        setLocationRelativeTo(parent);
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public String getComment() {
        return comment.getText();
    }

    public int getRating() {
        return (int) ratingSpinner.getValue();
    }

    public void setError(String error) {
        errorLabel.setText(error);
        pack();
        setMinimumSize(getSize());
    }
}
