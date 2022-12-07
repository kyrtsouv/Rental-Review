package api;

public class Review {
    private String user;
    private String comment;
    private int rating;

    public Review(String user, String comment, int rating) {
        this.user = user;
        this.comment = comment;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public void editReview(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
    }

}
