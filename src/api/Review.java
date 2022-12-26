package api;

import java.io.Serializable;

/**
 * Class that represents a review of a rental
 * It has a rating, a comment and the Tenant that wrote the review
 */
public class Review implements Serializable {
    int rating;
    String comment;
    Tenant reviewer;

    /**
     * Constructor:
     * Initializes the field variables with the corresponding parameters
     * 
     * @param rating
     * @param comment
     * @param reviewer
     */
    public Review(int rating, String comment, Tenant reviewer) {
        this.rating = rating;
        this.comment = comment;
        this.reviewer = reviewer;
    }

    /**
     * Returns the rating of the review
     * 
     * @return rating
     */
    public int getRating() {
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
     * Returns the Tenant that wrote the review
     * 
     * @return reviewer
     */
    public Tenant getTenant() {
        return reviewer;
    }

    /**
     * Updates the rating field according to the parameter
     * 
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Updates the comment field according to the parameter
     * 
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
