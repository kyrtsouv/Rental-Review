package api;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.Serializable;

/*Κλάση για την αξιολόγηση καταλύματος/ιδιοκτησίας*/
public class Review implements Serializable {
    Tenant user;
    private String comment;
    private String date;
    private int rating;

    /*Αρχικά ξεκινάμε φυσικά με αρχικοποίηση πληροφοριών*/
    public Review(Tenant user, String comment, int rating) {
        this.user = user;
        this.comment = comment;
        this.rating = rating;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    /*Κατόπιν έχουμε "getters" για την επιστροφή πληροφοριών του ονόματος του χρήστη, του σχολίου, της αξιολόγησης και φυσικά της ημερομηνίας*/
    public Tenant getUser() {
        return user;
    }
    public String getComment() {
        return comment;
    }
    public int getRating() {
        return rating;
    }
    public String getDate() {
        return date;
    }


    /*Και εν τέλη έχουμε τη διαδικασία επεξεργασίας πληροφοριών ήδη υπάρχοντας αξιολόγησης*/
    public void editReview(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

}
