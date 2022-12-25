package api;

import java.util.HashSet;

public class Renter extends User {
	HashSet<Rental> rentals;
	int numOfRatings;

	public Renter(String name, String surname, String username, String password) {
		super(name, surname, username, password);
		rentals = new HashSet<>();
		numOfRatings = 0;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public void removeRental(Rental rental) {
		rentals.remove(rental);
		updateRating();
	}

	public HashSet<Rental> getRentals() {
		return rentals;
	}

	public void updateRating() {
		rating = 0;
		for (Rental rental : rentals) {
			rating += rental.getRating();
			numOfRatings += rental.getNumOfRatings();
		}
		if (numOfRatings > 0)
			rating = rating / numOfRatings;
		else
			rating = 0;
	}
}
