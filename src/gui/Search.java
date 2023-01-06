package gui;

import javax.swing.*;
import java.awt.*;


//Αυτή η κλάση αποτελεί το κομμάτι του search του προγράμματος είτε μέσω ενός απλού searchbar ή φιλτραρίσματος βασισμένα στην τοποθεσία και το τύπο της ιδιοκτησίας
public class Search extends JFrame  {

    public static JTextField search;

    //Αρχικά εδώ δημιουργείται ένα παράθυρο για την αναζήτηση, το οποίο αποτελείται απο (...)
    public Search(){
        JPanel searchpanel = new JPanel();
        searchpanel.setLayout(null);
        //this view= new View;
        JFrame secondframe = new JFrame();
        secondframe.setTitle("Search");
        secondframe.setLocation(new Point(400, 200));
        secondframe.add(searchpanel);
        secondframe.setSize(new Dimension(800, 500));
        secondframe.setDefaultCloseOperation(EXIT_ON_CLOSE);



    }
//    private void createData() {
//        for (int i = 0; i < 1000; i++) {
//            String s = "String: " + i + ".";
//            data.add(s);
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Search example = new Search();
            example.setVisible(true);
        });
    }



//    @Override
//    public void removeUpdate(DocumentEvent e) {
//        search();
//    }

//    @Override
//    public void changedUpdate(DocumentEvent e) {
//        search();
//    }
//    private void search() {
//        Collectors Collectors = null;
//        List<String> filtered = (List<String>) data.stream().filter(s -> s.toString().toLowerCase().contains(searchField.getText().toLowerCase())).collect(Collectors.toList());
//        model.removeAllElements();
//        for (String temp :filtered) model.addElement(temp);
//    }
//    @Override
//    public void insertUpdate(DocumentEvent e) {
//        search();
//    }
}

