package gui;

import javax.swing.*;

import api.Database;
import api.Rental;
import api.Renter;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;

public class AddRental extends JDialog implements ActionListener {
    JPanel inputPanel;
    JPanel amenitiesPanel;
    JPanel innerAmenityPanel;
    JComboBox<String> typeComboBox;
    JTextField name;
    JTextField address;
    JTextField city;
    JTextField postcode;
    JTextField description;
    JLabel descriptionLabel;
    JLabel errorLabel;
    JButton submitButton;

    Database db;
    Renter renter;

    public AddRental(Frame parent, boolean modal, HashMap<String, HashSet<String>> amenities,
            HashSet<String> type, Database db, Renter renter) {
        super(parent, modal);
        this.db = db;
        this.renter = renter;

        name = new JTextField(50);

        typeComboBox = new JComboBox<>();
        for (String s : type) {
            typeComboBox.addItem(s);
        }

        address = new JTextField(20);

        city = new JTextField(20);

        postcode = new JTextField(20);

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
                innerAmenityPanel.add(checkBox);
            }
            amenityPanel.add(innerAmenityPanel);
            amenityPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
            amenitiesPanel.add(amenityPanel);
        }

        descriptionLabel = new JLabel("Περιγραφή*");
        descriptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        description = new JTextField();

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        submitButton = new JButton("OK");
        submitButton.addActionListener(this);
        submitButton.setAlignmentX(CENTER_ALIGNMENT);

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
        add(submitButton);
        add(Box.createVerticalStrut(5));

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

            Rental rental = new Rental(name.getText(), address.getText(), city.getText(), postcode.getText(),
                    description.getText(), typeComboBox.getSelectedItem().toString(), renter, getAmenities());
            db.addRental(rental);
            renter.addRental(rental);
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
