package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;

import api.*;

public class EditRental extends JDialog implements ActionListener {
    JPanel inputPanel;
    JPanel amenitiesPanel;
    JPanel innerAmenityPanel;
    JPanel submitPanel;
    JComboBox<String> typeComboBox;
    JTextField name;
    JTextField address;
    JTextField city;
    JTextField postcode;
    JTextField description;
    JLabel descriptionLabel;
    JLabel errorLabel;
    JButton editButton;
    JButton deleteButton;

    Rental rental;

    public EditRental(Frame parent, boolean modal, HashMap<String, HashSet<String>> amenities,
            HashSet<String> rentalTypes, Rental rental, Renter renter, Database db) {
        super(parent, modal);
        this.rental = rental;

        name = new JTextField(rental.get("name"));

        typeComboBox = new JComboBox<>();
        for (String s : rentalTypes) {
            typeComboBox.addItem(s);
        }

        address = new JTextField(rental.get("address"));

        city = new JTextField(rental.get("city"));

        postcode = new JTextField(rental.get("postcode"));

        inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Όνομα*"));
        inputPanel.add(name);
        inputPanel.add(new JLabel("Τύπος*"));
        inputPanel.add(typeComboBox);
        inputPanel.add(new JLabel("Διεύθυνση*"));
        inputPanel.add(address);
        inputPanel.add(new JLabel("Πόλη*"));
        inputPanel.add(city);
        inputPanel.add(new JLabel("Ταχυδρομικός Κώδικας*"));
        inputPanel.add(postcode);
        inputPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        amenitiesPanel = new JPanel();
        amenitiesPanel.setLayout(new BoxLayout(amenitiesPanel, BoxLayout.PAGE_AXIS));
        for (String amenityTitle : amenities.keySet()) {
            JPanel amenityPanel = new JPanel(new GridLayout(1, 2));
            amenityPanel.add(new JLabel(amenityTitle + ":"));

            innerAmenityPanel = new JPanel();
            innerAmenityPanel.setLayout(new BoxLayout(innerAmenityPanel, BoxLayout.PAGE_AXIS));
            for (String amenity : amenities.get(amenityTitle)) {
                JCheckBox checkBox = new JCheckBox(amenity);
                if (rental.getAmenities().contains(amenity)) {
                    checkBox.setSelected(true);
                }
                innerAmenityPanel.add(checkBox);
            }
            amenityPanel.add(innerAmenityPanel);
            amenityPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
            amenitiesPanel.add(amenityPanel);
        }

        descriptionLabel = new JLabel("Περιγραφή*");
        descriptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        description = new JTextField(rental.get("description"));

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        editButton = new JButton("OK");
        editButton.addActionListener(this);
        deleteButton = new JButton("Διαγραφή");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renter.deleteRental(rental);
                db.removeRental(rental);
                for (Tenant tenant : rental.getReviews().keySet()) {
                    tenant.deleteReview(rental);
                }
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

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(inputPanel);
        add(Box.createVerticalStrut(5));
        add(amenitiesPanel);
        add(Box.createVerticalStrut(5));
        add(descriptionLabel);
        add(description);
        add(Box.createVerticalStrut(5));
        add(errorLabel);
        add(Box.createVerticalStrut(5));
        add(submitPanel);
        add(Box.createVerticalStrut(5));

        setMinimumSize(getSize());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (name.getText().equals("") || address.getText().equals("") || city.getText().equals("")
                || postcode.getText().equals("") || description.getText().equals("")) {
            errorLabel.setText("Παρακαλώ συμπληρώστε όλα τα πεδία με αστερίσκο.");
        } else {
            rental.editRental(name.getText(), address.getText(), city.getText(), postcode.getText(),
                    description.getText(), (String) typeComboBox.getSelectedItem(), getAmenities());
            dispose();
        }
    }

    public HashSet<String> getAmenities() {
        HashSet<String> amenities = new HashSet<>() {
            {
                for (Component c : amenitiesPanel.getComponents()) {
                    JPanel amenityPanel = (JPanel) c;
                    for (Component c2 : amenityPanel.getComponents()) {
                        if (c2 instanceof JPanel) {
                            JPanel innerAmenityPanel = (JPanel) c2;
                            for (Component c3 : innerAmenityPanel.getComponents()) {
                                if (c3 instanceof JCheckBox) {
                                    JCheckBox checkBox = (JCheckBox) c3;
                                    if (checkBox.isSelected()) {
                                        add(checkBox.getText());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
        return amenities;
    }

}
