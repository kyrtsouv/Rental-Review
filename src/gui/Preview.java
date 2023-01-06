package gui;
//Αυτή η κλάση εμφανίζει απλά τα στοιχεία ενός rental ή ενός σχολίου ανάλογα με το τι έχει ζητήσει ο χρήστης
//Έπειτα μπορεί να επιλέξει για την επεξεργασία ή διαγραφή του rental/σχολίου
import api.Rental;
import api.Review;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Preview extends JFrame implements ActionListener  {
    public JFrame previewframe=new JFrame();
    private HashMap<String, String> details;
    private HashMap<String, Review> reviews;
    public Preview(){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        //Δημιουργία panel και frame
        previewframe.setTitle("PREVIEW");
        previewframe.setLocation(new Point(400, 200));
        previewframe.add(panel);
        previewframe.setSize(new Dimension(800, 500));
        previewframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel rental =new JPanel();
        Rental rental1=new Rental("Flat in Kifisia", "Kanellopoulou 21", "Thessaloniki", "54248", "Big house", "Flat",
                "Viktor Kyrtsoudis");
        details=rental1.getPreview();
        //JLabel det = new JLabel(String.valueOf(details));
        JLabel categories=new JLabel("<html>Owner: <br/> Name: <br/> Address: <br/> City: <br/> Postcode: <br/> Description: <br/> Type:  </html>");
        categories.setBounds(10, -100, 100, 500);
        panel.add(categories);
        JLabel det = new JLabel(String.valueOf(details));
        det.setBounds(50, -100, 100, 500);
        panel.add(det);
        previewframe.setVisible(true);

    }






    //actionPerformed για την υλοποίηση του πρακτικού κομματιού της κλάσης
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
