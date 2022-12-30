// import java.io.FileInputStream;
// import java.io.ObjectInputStream;

// import api.*;

// public class testin {
// public static void main(String[] args) {

// Data data = new Data();

// try (ObjectInputStream in = new ObjectInputStream(new
// FileInputStream("data.ser"))) {
// data = (Data) in.readObject();
// } catch (Exception e) {
// e.printStackTrace();
// }

// System.out.println(data.getUsers());
// Tenant tenant = (Tenant) data.getUser("janedo");
// Rental rental = data.getRentals().get("rentalName");
// for (Review review : rental.getReviews().values()) {
// System.out.println(review.getRating());
// }
// for (Review review : tenant.getReviews().values()) {
// System.out.println(review.getRating());
// review.setRating(5);
// }
// for (Review review : rental.getReviews().values()) {
// System.out.println(review.getRating());
// }
// for (Review review : tenant.getReviews().values()) {
// System.out.println(review.getRating());
// }
// System.out.println(tenant.getReviews());
// System.out.println(rental.getReviews());
// }
// }