package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TenantPanel extends JPanel {

    public TenantPanel(TenantDashboardPanel dashboardPanel, SearchPanel searchPanel) {
        CardLayout cardLayout = new CardLayout();

        setLayout(cardLayout);
        add(dashboardPanel, "dashboard");
        add(searchPanel, "search");

        cardLayout.show(this, "search");

        searchPanel.addDashboardListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TenantPanel.this, "dashboard");
            }
        });

        dashboardPanel.addSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TenantPanel.this, "search");
            }
        });
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

        SearchPanel searchPanel = new SearchPanel(previewPanels, amenities);

        PreviewPanel previewPanel = new PreviewPanel("name", "type", "location", 5);
        PreviewPanel previewPanel2 = new PreviewPanel("name", "type", "location", 4);
        HashSet<PreviewPanel> reviewPanels = new HashSet<PreviewPanel>();

        reviewPanels.add(previewPanel);
        reviewPanels.add(previewPanel2);

        TenantDashboardPanel tenantDashboardPanel = new TenantDashboardPanel(4, reviewPanels);
        TenantPanel tenantPanel = new TenantPanel(tenantDashboardPanel, searchPanel);

        frame.add(tenantPanel);

        frame.setVisible(true);
    }
}
