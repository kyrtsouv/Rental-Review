package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SidePanel extends JPanel {
    JPanel blackBorderPanel;
    JPanel innerPanel;
    JPanel amenityPanel;
    JPanel amenityHeaderPanel;

    HashMap<String, AmenityMap> amenityMaps;

    public SidePanel(ArrayList<PreviewPanel> previewPanels, HashMap<String, ArrayList<String>> amenities) {
        amenityMaps = new HashMap<String, AmenityMap>();

        for (String amenityTitle : amenities.keySet()) {
            JLabel amenityHeaderLabel = new JLabel(amenityTitle);
            MyButton openButton = new MyButton("Open");

            amenityHeaderPanel = new JPanel();
            amenityHeaderPanel.setLayout(new BoxLayout(amenityHeaderPanel, BoxLayout.LINE_AXIS));
            amenityHeaderPanel.add(amenityHeaderLabel);
            amenityHeaderPanel.add(Box.createHorizontalGlue());
            amenityHeaderPanel.add(openButton);

            innerPanel = new JPanel();
            innerPanel.setBorder(BorderFactory.createLineBorder(getBackground(), 3));
            innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
            innerPanel.add(amenityHeaderPanel);

            blackBorderPanel = new JPanel();
            blackBorderPanel.setLayout(new BoxLayout(blackBorderPanel, BoxLayout.PAGE_AXIS));
            blackBorderPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
            blackBorderPanel.add(innerPanel);

            amenityPanel = new JPanel();
            amenityPanel.setLayout(new BoxLayout(amenityPanel, BoxLayout.PAGE_AXIS));
            amenityPanel.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 2, getBackground()));
            amenityPanel.add(blackBorderPanel);

            add(amenityPanel);

            ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
            for (String amenity : amenities.get(amenityTitle)) {
                JCheckBox amenityCheckBox = new JCheckBox(amenity);
                amenityCheckBox.setAlignmentX(RIGHT_ALIGNMENT);
                checkBoxes.add(amenityCheckBox);
            }

            amenityMaps.put(amenityTitle, new AmenityMap(innerPanel, checkBoxes, openButton, false));

            openButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AmenityMap amenityMap = amenityMaps.get(amenityTitle);
                    if (!amenityMap.buttonPressed) {
                        for (JCheckBox amenityCheckBox : amenityMap.checkBoxes) {
                            amenityMap.panel.add(amenityCheckBox);
                        }
                        amenityMap.openButton.setText("Close");
                        amenityMap.buttonPressed = true;
                        amenityMap.panel.revalidate();
                        amenityMap.panel.repaint();
                    } else {
                        for (JCheckBox amenity : amenityMap.checkBoxes) {
                            amenityMap.panel.remove(amenity);
                        }
                        amenityMap.openButton.setText("Open");
                        amenityMap.buttonPressed = false;
                        amenityMap.panel.revalidate();
                        amenityMap.panel.repaint();

                    }
                }
            });
        }
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.black));
    }

    private class AmenityMap {
        JPanel panel;
        ArrayList<JCheckBox> checkBoxes;
        MyButton openButton;

        boolean buttonPressed;

        public AmenityMap(JPanel amenityPanel, ArrayList<JCheckBox> amenityCheckBoxes, MyButton openButton,
                boolean buttonPressed) {
            this.panel = amenityPanel;
            this.checkBoxes = amenityCheckBoxes;
            this.openButton = openButton;
            this.buttonPressed = buttonPressed;
        }
    }
}