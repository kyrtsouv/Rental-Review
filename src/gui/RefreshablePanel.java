package gui;

import javax.swing.JPanel;

public class RefreshablePanel extends JPanel {

    public void buildUI() {
        // Override this method to build the UI
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
