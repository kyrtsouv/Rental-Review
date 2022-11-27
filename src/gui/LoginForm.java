package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Η συγκεκριμένη κλάση αποτελεί την διαδικασία ελέγχου κάθε χρήστη

public class LoginForm implements ActionListener {
    private static JLabel password1, label;
    private static JTextField username;
    private static JButton button;
    private static JPasswordField Password;

    void CreateLoginForm(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JFrame frame = new JFrame();
        frame.setTitle("LOGIN PAGE");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Δημιουργία της ταμπέλας username
        label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        panel.add(label);
        label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        panel.add(label);


        //ίδια διαδικασία με πριν γίνεται ξανά τώρα για το κωδικό
        password1 = new JLabel("Password");
        password1.setBounds(100, 55, 70, 20);
        panel.add(password1);
        Password = new JPasswordField();
        Password.setBounds(100, 75, 193, 28);
        panel.add(Password);


        button = new JButton("Login");
        button.setBounds(100, 110, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.addActionListener(this);
        panel.add(button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String Username = username.getText();
        String Password1 = Password.getText();

        if (Username.equals("section.io") && Password1.equals("123")) JOptionPane.showMessageDialog(null, "Login Successful");
        else JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
    }
}