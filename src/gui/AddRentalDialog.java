package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.HashMap;
import java.util.HashSet;

public class AddRentalDialog extends JDialog {
    JPanel namePanel;
    JPanel typePanel;
    JPanel locationPanel;
    JPanel addressPanel;
    JPanel cityPanel;
    JPanel zipcodePanel;
    JPanel descriptionPanel;
    JPanel amenitiesPanel;

    JComboBox<String> typeComboBox;

    JTextField name;
    JTextField address;
    JTextField city;
    JTextField zipcode;
    JTextField description;

    JLabel errorLabel;

    JButton submitButton;

    public AddRentalDialog(java.awt.Frame parent, boolean modal, HashMap<String, HashSet<String>> amenities,
            HashSet<String> type) {
        super(parent, modal);
        name = new JTextField(50);
        name.setBorder(BorderFactory.createLineBorder(Color.black));

        namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePanel.setBorder(BorderFactory.createTitledBorder("Όνομα*"));
        namePanel.add(name);

        typeComboBox = new JComboBox<>();
        for (String s : type) {
            typeComboBox.addItem(s);
        }

        typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.PAGE_AXIS));
        typePanel.setBorder(BorderFactory.createTitledBorder("Τύπος*"));
        typePanel.add(typeComboBox);

        address = new JTextField(20);
        address.setBorder(BorderFactory.createLineBorder(Color.black));

        addressPanel = new JPanel();
        addressPanel.setBorder(BorderFactory.createTitledBorder("Διεύθυνση*"));
        addressPanel.add(address);

        city = new JTextField(20);
        city.setBorder(BorderFactory.createLineBorder(Color.black));

        cityPanel = new JPanel();
        cityPanel.setBorder(BorderFactory.createTitledBorder("Πόλη*"));
        cityPanel.add(city);

        zipcode = new JTextField(20);
        zipcode.setBorder(BorderFactory.createLineBorder(Color.black));

        zipcodePanel = new JPanel();
        zipcodePanel.setBorder(BorderFactory.createTitledBorder("Ταχυδρομικός Κώδικας*"));
        zipcodePanel.add(zipcode);

        locationPanel = new JPanel(new FlowLayout());
        locationPanel.add(addressPanel);
        locationPanel.add(cityPanel);
        locationPanel.add(zipcodePanel);

        description = new JTextField();
        description.setBorder(BorderFactory.createLineBorder(Color.black));

        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.PAGE_AXIS));
        descriptionPanel.setBorder(BorderFactory.createTitledBorder("Περιγραφή*"));
        descriptionPanel.add(description);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        amenitiesPanel = new JPanel(new FlowLayout());
        for (String amenityTitle : amenities.keySet()) {
            JPanel amenityPanel = new JPanel();
            amenityPanel.setLayout(new BoxLayout(amenityPanel, BoxLayout.PAGE_AXIS));
            amenityPanel.setBorder(BorderFactory.createTitledBorder(amenityTitle));
            for (String s : amenities.get(amenityTitle)) {
                JCheckBox checkBox = new JCheckBox(s);
                amenityPanel.add(checkBox);
            }
            amenitiesPanel.add(amenityPanel);
        }

        submitButton = new JButton("OK");
        submitButton.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(namePanel, BorderLayout.NORTH);
        add(Box.createVerticalStrut(5));
        add(typePanel);
        add(Box.createVerticalStrut(5));
        add(locationPanel);
        add(Box.createVerticalStrut(5));
        add(descriptionPanel);
        add(Box.createVerticalStrut(5));
        add(amenitiesPanel);
        add(Box.createVerticalStrut(5));
        add(errorLabel);
        add(Box.createVerticalStrut(5));
        add(submitButton);
        add(Box.createVerticalStrut(5));

        pack();
        setMinimumSize(getSize());
        setSize(400, 400);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
    }

    public void setError(String error) {
        errorLabel.setText(error);
        pack();
        setMinimumSize(getSize());
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public String getName() {
        return name.getText();
    }

    public String getRentalType() {
        return typeComboBox.getSelectedItem().toString();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getCity() {
        return city.getText();
    }

    public String getZipcode() {
        return zipcode.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public HashSet<String> getAmenities() {
        HashSet<String> amenities = new HashSet<>();
        for (Component c : amenitiesPanel.getComponents()) {
            if (c instanceof JPanel) {
                for (Component c2 : ((JPanel) c).getComponents()) {
                    if (c2 instanceof JCheckBox) {
                        if (((JCheckBox) c2).isSelected()) {
                            amenities.add(((JCheckBox) c2).getText());
                        }
                    }
                }
            }
        }
        return amenities;
    }
}
