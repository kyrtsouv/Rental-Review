package gui;

import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.View;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.List;

public class Search extends JFrame implements DocumentListener {
    //θα πρεπει να εχουμε ενα απλο search bar + φιλτραρισμα στοιχειων για εμφανιση συγκεκριμενου κομματιου
    private View view;
    private static final long serialVersionUID = -1662279563193298340L;
    private JList<String> list;
    private List data = new List<>();

    private DefaultListModel<String> model;
    private JTextField searchField;

    public Search(){
        //setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        //this.view=new view;
        super("test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(this);
        add(searchField, BorderLayout.PAGE_START);

        createData();

        list = new JList<>(model = new DefaultListModel<>());
        //data.forEach(model::addElement);
        for(Object temp:data) model.addElement((String) temp);
        add(new JScrollPane(list), BorderLayout.CENTER);

        setSize(500, 500);
        setLocationByPlatform(true);
    }
    private void createData() {
        for (int i = 0; i < 1000; i++) {
            String s = "String: " + i + ".";
            data.add(s);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Search example = new Search();
            example.setVisible(true);
        });
    }



    @Override
    public void removeUpdate(DocumentEvent e) {
        search();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        search();
    }
    private void search() {
        Collectors Collectors = null;
        List<String> filtered = (List<String>) data.stream().filter(s -> s.toString().toLowerCase().contains(searchField.getText().toLowerCase())).collect(Collectors.toList());
        model.removeAllElements();
        for (String temp :filtered) model.addElement(temp);
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        search();
    }
}

