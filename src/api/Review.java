package api;

public class Review {
    private int rating;
    private String comment;
    private Rental rental;
    private User user;

    public Review(int rating, String comment, User user, Rental rental) {
        this.rating = rating;
        this.comment = comment;
        this.rental = rental;
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public void setRating(int rating) {
        this.rating = rating;
        rental.updateRating();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
