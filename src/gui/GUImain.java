package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Αυτή είναι η main για το κομμάτι του GUI
public class GUImain extends JFrame implements ActionListener {
    //Αποφασίσαμε να δημιουργήσουμε αυτό το κομμάτι με cardlayout ώστε η αλλαγή μεταξύ των παραθύρων να είναι πιο εύκολη
    CardLayout card;
    Container container;
    JButton Login,User,Search,Edit,View;

    public GUImain(){
        //Δημιουργούμε λοιπόν ένα κομμάτι button για κάθε κομμάτι του GUI για την εναλλαγή μεταξύ όλων των κομματιών
        container=getContentPane();
        card= new CardLayout(40,30);
        container.setLayout(card);
        Login =new JButton("");
        User=new JButton("");
        Search=new JButton("");
        Edit=new JButton("");
        View=new JButton("");
        //Φυσικά, αυτό απαιτεί actionListeners καθώς και την προσθήκη κάθε ενός κομματιού στην κάρτα εναλλαγής
        Login.addActionListener(this);
        User.addActionListener(this);
        Search.addActionListener(this);
        Edit.addActionListener(this);
        View.addActionListener(this);

        container.add("Login",Login);
        container.add("User",User);
        container.add("Search", Search);
        container.add("Edit",Edit);
        container.add("View",View);


    }
    //actionPerformed για την υλοποίηση του πρακτικού κομματιού της κλάσης
    @Override
    public void actionPerformed(ActionEvent e) {
        card.next(container);
    }
    public static void main(String[] args){
        GUImain c1=new GUImain();
        c1.setSize(400,400);
        c1.setVisible(true);
        c1.getDefaultCloseOperation();
    }
}
