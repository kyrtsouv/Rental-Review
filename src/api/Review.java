package api;

import java.io.Serializable;

/**
 * Class that represents a review of a rental
 * It has a rating, a comment, the date the review was created or edited and the
 * Tenant that wrote the review
 */
public class Review implements Serializable {
    float rating;
    String comment;
    String date;
    Tenant reviewer;

    /**
     * Constructor:
     * Initializes the field variables with the corresponding parameters
     * 
     * @param rating
     * @param comment
     * @param reviewer
     */
    public Review(float rating, String comment, String date, Tenant reviewer) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.reviewer = reviewer;
    }

    /**
     * Returns the rating of the review
     * 
     * @return rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Returns the comment of the review
     * 
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the date the review was created or edited
     * 
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the Tenant that wrote the review
     * 
     * @return reviewer
     */
    public Tenant getTenant() {
        return reviewer;
    }

    /**
     * Updates the review with the according parameters
     * 
     * @param rating
     * @param comment
     * @param date
     */
    public void updateReview(float rating, String comment, String date) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }
}
