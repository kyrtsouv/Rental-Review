package gui;

import java.util.HashMap;
import java.util.HashSet;

public class EditRentalDialog extends AddRentalDialog {

    public EditRentalDialog(java.awt.Frame parent, boolean modal, HashMap<String, HashSet<String>> amenities,
            HashSet<String> type) {
        super(parent, modal, amenities, type);
    }
}
