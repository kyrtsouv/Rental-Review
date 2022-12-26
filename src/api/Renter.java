package api;

import java.util.HashSet;

/**
 * Class that represents a Renter that can publish rentals to be reviewd
 * Apart from the fields of a User it also has a set of rentals and a
 * numOfReviews field that represent the total
 * number of reviews of all the rentals of the Renter
 */
public class Renter extends User {
	HashSet<Rental> rentals;
	int numOfReviews;

	/**
	 * Constructor:
	 * Initializes the field variables with the corresponding parameters using the
	 * super constructor of the User class
	 * Rentals gets initialized with an empty HashSet of rentals
	 * numOfReviews gets initialized with 0
	 * 
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 */
	public Renter(String name, String surname, String username, String password) {
		super(name, surname, username, password);
		rentals = new HashSet<>();
		numOfReviews = 0;
	}

	/**
	 * Returns the number of reviews of the Renter
	 * 
	 * @return numOfReviews
	 */
	public int getNumOfReviews() {
		return numOfReviews;
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
	 * Adds to the set of rentals the rental that is passed as a parameter *
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
	 * Updates the rating according to the average rating of all the rentals and
	 * updates the number of reviews by adding the number of reviews of all the
	 * rentals
	 *
	 */
	public void updateRating() {
		rating = 0;
		for (Rental rental : rentals) {
			rating += rental.getRating();
			numOfReviews += rental.getNumOfReviews();
		}
		if (numOfReviews > 0)
			rating = rating / numOfReviews;
		else
			rating = 0;
	}
}
