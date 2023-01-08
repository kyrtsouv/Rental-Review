package api;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.Serializable;

public class Review implements Serializable {
    private Tenant user;
    private String comment;
    private String date;
    private int rating;

    public Review(Tenant user, String comment, int rating) {
        this.user = user;
        this.comment = comment;
        this.rating = rating;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

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

    public void editReview(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

}
