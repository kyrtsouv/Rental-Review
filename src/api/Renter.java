package api;

import java.util.HashSet;

/**
 * Class that represents a Renter that can publish rentals to be reviewd
 * Apart from the fields of a User it also has a set of rentals and a
 * totalReviews field that represent the total
 * number of reviews of all the rentals of the Renter
 */
public class Renter extends User {
	HashSet<Rental> rentals;
	int totalReviews;

	/**
	 * Constructor:
	 * Initializes the field variables with the corresponding parameters using the
	 * super constructor of the User class
	 * Rentals gets initialized with an empty HashSet
	 * totalReviews gets initialized with 0
	 * 
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 */
	public Renter(String name, String surname, String username, String password) {
		super(name, surname, username, password);
		rentals = new HashSet<>();
		totalReviews = 0;
	}

	/**
	 * Returns the number of reviews of the Renter
	 * 
	 * @return totalReviews
	 */
	public int getTotalReviews() {
		return totalReviews;
	}

	/**
	 * Returns the set of rentals
	 * 
	 * @return rentals
	 */
	public HashSet<Rental> getRentals() {
		return rentals;
	}

	/**
	 * Adds to the set of rentals the rental that is passed as a parameter
	 * 
	 * @param rental
	 */
	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	/**
	 * Removes from the set of rentals the rental that is passed as a parameter
	 * 
	 * @param rental
	 */
	public void removeRental(Rental rental) {
		rentals.remove(rental);
	}

	/**
	 * Updates the rating by calculating the average rating of each review of all
	 * the rentals and updates the number of reviews by adding the number of reviews
	 * of all the rentals
	 */
	public void updateRating() {
		rating = 0;
		totalReviews = 0;
		for (Rental rental : rentals) {
			for (Review review : rental.getReviews().values()) {
				rating += review.getRating();
			}
			totalReviews += rental.getTotalReviews();
		}
		if (totalReviews > 0)
			rating /= totalReviews;
	}
}
