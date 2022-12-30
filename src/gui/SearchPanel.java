package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchPanel extends JPanel {
    SidePanel sidePanel;

    JPanel innerPanel;
    JPanel searchPanel;
    JPanel previewsPanel;

    JTextField searchField;

    MyButton dashboardButton;

    boolean searchFieldAccessed;

    public SearchPanel(ArrayList<PreviewPanel> previewPanels, HashMap<String, ArrayList<String>> amenities) {
        dashboardButton = new MyButton("Dashboard");

        sidePanel = new SidePanel(previewPanels, amenities);

        searchFieldAccessed = false;
        searchField = new JTextField("Search");
        searchField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!searchFieldAccessed) {
                    searchField.setText("");
                    searchFieldAccessed = true;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().equals("")) {
                    searchField.setText("Search");
                    searchFieldAccessed = false;
                }
            }
        });

        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createLineBorder(getBackground(), 3));
        searchPanel.add(searchField, BorderLayout.CENTER);

        previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.PAGE_AXIS));
        for (PreviewPanel previewPanel : previewPanels) {
            previewsPanel.add(previewPanel);
        }

        innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(Color.red);
        innerPanel.add(searchPanel, BorderLayout.PAGE_START);
        innerPanel.add(previewsPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(dashboardButton, BorderLayout.PAGE_START);
        add(sidePanel, BorderLayout.LINE_START);
        add(innerPanel, BorderLayout.CENTER);
    }

    public void addDashboardListener(ActionListener listener) {
        dashboardButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Search Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        ArrayList<PreviewPanel> previewPanels = new ArrayList<PreviewPanel>();
        for (int i = 0; i < 10; i++) {
            previewPanels.add(new PreviewPanel("name" + i, "type" + i, "location" + i, i));
        }

        HashMap<String, ArrayList<String>> amenities = new HashMap<String, ArrayList<String>>();
        ArrayList<String> amenitiesList = new ArrayList<String>();
        amenitiesList.add("Amenity 1");
        amenitiesList.add("Amenity 2");
        amenitiesList.add("Amenity 3");
        amenities.put("Amenity", amenitiesList);

        frame.add(new SearchPanel(previewPanels, amenities));
        frame.setVisible(true);
    }
}
