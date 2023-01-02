package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TenantPanel extends JPanel {

    public TenantPanel(TenantDashboardPanel dashboardPanel, SearchPanel searchPanel) {
        CardLayout cardLayout = new CardLayout();

        setLayout(cardLayout);
        add(dashboardPanel, "dashboard");
        add(searchPanel, "search");

        cardLayout.show(this, "dashboard");

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
}
