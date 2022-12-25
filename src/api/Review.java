package api;

import java.io.Serializable;

public class Review implements Serializable {
    int rating;
    String comment;
    Tenant reviewer;

    public Review(int rating, String comment, Tenant reviewer) {
        this.rating = rating;
        this.comment = comment;
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Tenant getTenant() {
        return reviewer;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
