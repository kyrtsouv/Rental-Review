package api;

import java.io.Serializable;

//Αυτή η κλάση αποτελεί το κύριο κομμάτι αρχικοποίησης, αποθήκευσης και επιστροφής στοιχειών ενός χρήστη είτε είναι είτε δεν είναι πάροχος
public class User implements Serializable {
    String name;
    String surname;
    String username;
    String password;

    //Αποθήκευση των προσωπικών στοιχείων
    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    //Και τέλος έχουμε επιλογές επιστροφής κάθε προσωπικής πληροφορίας ξεχωριστά(name, surname, username, password)
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
