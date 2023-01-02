package gui;

import javax.swing.*;

public class MyButton extends JButton {

    public MyButton(String text) {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public MyButton() {
        super();
        setFocusPainted(false);
        setBorderPainted(false);
    }

}
