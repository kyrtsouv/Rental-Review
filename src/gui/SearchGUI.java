package gui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.*;

import api.Database;
import api.Rental;
import api.Tenant;

public class SearchGUI extends RefreshablePanel implements ActionListener {
    JPanel amenitiesPanel;
    JPanel top;
    JPanel innerPanel;
    JPanel searchPanel;
    JPanel previewsPanel;
    JTextField searchField;
    JButton searchButton;
    JButton backButton;

    HashMap<String, HashSet<String>> amenities;
    HashSet<Rental> rentals;
    Tenant tenant;
    MainGUI main;
    Database db;

    public SearchGUI(HashMap<String, HashSet<String>> amenities, HashSet<Rental> rentals, Tenant tenant, MainGUI main,
            Database db) {
        this.amenities = amenities;
        this.rentals = rentals;
        this.tenant = tenant;
        this.main = main;
        this.db = db;

        buildUI();
    }

    @Override
    public void buildUI() {
        amenitiesPanel = new JPanel();
        amenitiesPanel.setLayout(new BoxLayout(amenitiesPanel, BoxLayout.PAGE_AXIS));
        for (HashSet<String> amenityType : amenities.values()) {
            JPanel amenityPanel = new JPanel();
            amenityPanel.setLayout(new BoxLayout(amenityPanel, BoxLayout.PAGE_AXIS));
            for (String amenity : amenityType) {
                JCheckBox checkBox = new JCheckBox(amenity);
                amenityPanel.add(checkBox);
            }
            amenityPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
            amenitiesPanel.add(amenityPanel);
        }
        amenitiesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        backButton = new JButton("Πίσω");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.show(new TenantGUI(tenant, main, db));
            }
        });

        top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
        top.add(Box.createHorizontalGlue());
        top.add(backButton);

        searchField = new JTextField(30);
        searchButton = new JButton("Αναζήτηση");
        searchButton.addActionListener(this);

        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.LINE_AXIS));
        searchPanel.setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.PAGE_AXIS));
        for (Rental rental : rentals) {
            previewsPanel.add(new PreviewGUI(rental, main, db, tenant, rental.getOwner()));
        }

        innerPanel = new JPanel(new BorderLayout());
        innerPanel.add(searchPanel, BorderLayout.PAGE_START);
        innerPanel.add(previewsPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(top, BorderLayout.PAGE_START);
        add(amenitiesPanel, BorderLayout.LINE_START);
        add(innerPanel, BorderLayout.CENTER);
    }

    public HashSet<String> getAmenities() {
        HashSet<String> amenities = new HashSet<>();
        for (Component c : amenitiesPanel.getComponents()) {
            for (Component c2 : ((JPanel) c).getComponents()) {
                if (((JCheckBox) c2).isSelected()) {
                    amenities.add(((JCheckBox) c2).getText());
                }
            }
        }
        return amenities;
    }

    public void update(HashSet<Rental> rentals) {
        this.rentals = rentals;
        previewsPanel.removeAll();
        for (Rental rental : rentals) {
            previewsPanel.add(new PreviewGUI(rental, main, db, tenant, rental.getOwner()));
        }

        refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] searchFilters = searchField.getText().toLowerCase().split(" ");
        if (searchFilters.length == 1 && searchFilters[0].equals(""))
            searchFilters = new String[0];
        HashSet<String> amenitiesFilters = getAmenities();

        HashSet<Rental> searchResults = new HashSet<>();

        for (Rental rental : db.getRentals()) {
            boolean searching = true;

            for (String searchFilter : searchFilters) {
                if (!rental.getSearchFilters().contains(searchFilter)) {
                    searching = false;
                    break;
                }
            }

            if (searching) {
                for (String amenitiesFilter : amenitiesFilters) {
                    if (!rental.getAmenities().contains(amenitiesFilter)) {
                        searching = false;
                        break;
                    }
                }
                if (searching)
                    searchResults.add(rental);
            }
        }

        update(searchResults);
    }
}
