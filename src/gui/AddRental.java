package gui;

import java.awt.*;
import javax.swing.*;

import java.util.HashMap;
import java.util.HashSet;

public class AddRental extends javax.swing.JDialog {
    JPanel namePanel;
    JPanel typePanel;
    JPanel locationPanel;
    JPanel addressPanel;
    JPanel cityPanel;
    JPanel zipcodePanel;
    JPanel amenitiesPanel;

    JComboBox<String> typeComboBox;

    JTextArea name;
    JTextArea address;
    JTextArea city;
    JTextArea zipcode;

    JButton submitButton;

    public AddRental(java.awt.Frame parent, boolean modal, HashMap<String, HashSet<String>> amenities,
            HashSet<String> type) {
        super(parent, modal);

        name = new JTextArea();
        name.setLineWrap(true);
        name.setWrapStyleWord(true);
        name.setBorder(BorderFactory.createLineBorder(Color.black));

        namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS));
        namePanel.setBorder(BorderFactory.createTitledBorder("Comment"));
        namePanel.add(name);

        typeComboBox = new JComboBox<>();
        for (String s : type) {
            typeComboBox.addItem(s);
        }

        typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.PAGE_AXIS));
        typePanel.setBorder(BorderFactory.createTitledBorder("Type"));
        typePanel.add(typeComboBox);

        address = new JTextArea();
        address.setLineWrap(true);
        address.setWrapStyleWord(true);
        address.setBorder(BorderFactory.createLineBorder(Color.black));

        addressPanel = new JPanel();
        addressPanel.setBorder(BorderFactory.createTitledBorder("Address"));
        addressPanel.add(address);

        city = new JTextArea();
        city.setLineWrap(true);
        city.setWrapStyleWord(true);
        city.setBorder(BorderFactory.createLineBorder(Color.black));

        cityPanel = new JPanel();
        cityPanel.setBorder(BorderFactory.createTitledBorder("City"));
        cityPanel.add(city);

        zipcode = new JTextArea();
        zipcode.setLineWrap(true);
        zipcode.setWrapStyleWord(true);
        zipcode.setBorder(BorderFactory.createLineBorder(Color.black));

        zipcodePanel = new JPanel();
        zipcodePanel.setBorder(BorderFactory.createTitledBorder("Zipcode"));
        zipcodePanel.add(zipcode);

        locationPanel = new JPanel();
        locationPanel.setLayout(new FlowLayout());
        locationPanel.add(addressPanel);
        locationPanel.add(cityPanel);
        locationPanel.add(zipcodePanel);

        amenitiesPanel = new JPanel();
        amenitiesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        amenitiesPanel.setBorder(BorderFactory.createTitledBorder("Amenities"));
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
        submitButton.addActionListener(e -> {
            dispose();
        });
        submitButton.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(namePanel);
        add(Box.createVerticalStrut(5));
        add(typePanel);
        add(Box.createVerticalStrut(5));
        add(locationPanel);
        add(Box.createVerticalStrut(5));
        add(amenitiesPanel);
        add(Box.createVerticalStrut(5));
        add(submitButton);

        pack();
        setMinimumSize(getSize());
        setSize(400, 400);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
