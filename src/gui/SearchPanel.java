package gui;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;
import java.util.HashSet;

public class SearchPanel extends JPanel {
    SidePanel sidePanel;

    JPanel innerPanel;
    JPanel searchPanel;
    JPanel previewsPanel;

    JTextField searchField;

    MyButton dashboardButton;

    boolean searchFieldAccessed;

    public SearchPanel(HashSet<PreviewPanel> previewPanels, HashMap<String, HashSet<String>> amenities) {
        dashboardButton = new MyButton("Dashboard");

        sidePanel = new SidePanel(previewPanels, amenities);

        searchFieldAccessed = false;
        searchField = new JTextField();
        searchField
                .setBorder(
                        BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 3), "Αναζήτηση"));

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

    public void addDocumentListener(DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        sidePanel.addSearchListener(listener);
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public String getAmenities() {
        return sidePanel.getAmenities();
    }

    public void updateSearchResults(HashSet<PreviewPanel> previewPanels) {
        previewsPanel.removeAll();
        for (PreviewPanel previewPanel : previewPanels) {
            previewsPanel.add(previewPanel);
        }
        revalidate();
        repaint();
    }
}
