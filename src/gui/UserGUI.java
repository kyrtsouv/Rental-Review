package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Αυτή η κλάση απλά εμφανίζει το profile του χρήστη με όλες τις πληροφορίες,
// καθώς και όλα τα σχόλια ή/και rentals που έχει κάνει ο χρήστης
public class UserGUI extends JFrame implements ActionListener {
    private static JTextField username,Surname,Name;
    private String user,surname,name;
    public JFrame userframe=new JFrame();
    public UserGUI(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Δημιουργία αρχικού Frame & Panel
        userframe.setTitle("PROFILE");
        userframe.setLocation(new Point(400, 200));
        userframe.add(panel);
        userframe.setSize(new Dimension(800, 500));
        userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //Δημιουργία της ταμπέλας username με την συγκεκριμένη πληροφορία του χρήστη
        JLabel label = new JLabel("Username");
        label.setBounds(200, 8, 70, 20);
        panel.add(label);



        //Δημιουργία της ταμπέλας Name με την συγκεκριμένη πληροφορία του χρήστη
        JLabel label2 = new JLabel("Name");
        label.setBounds(200, 60, 70, 20);
        panel.add(label);

        //Δημιουργία της ταμπέλας Surname με την συγκεκριμένη πληροφορία του χρήστη
        JLabel label3 = new JLabel("Surname");
        label.setBounds(200, 80, 70, 20);
        panel.add(label);

        userframe.add(panel);
        userframe.setVisible(true);




    }

    //actionPerformed για την υλοποίηση του πρακτικού κομματιού της κλάσης
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
