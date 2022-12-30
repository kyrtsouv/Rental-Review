import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import api.*;

public class test {

    public static void main(String[] args) {

        Renter renter = new Renter("John", "Doe", "johndoe", "password");
        Tenant tenant = new Tenant("Jane", "Doey", "janedo", "password");

        Rental rental = new Rental("rentalName", "type", "address", "city", "zipcode", "description", new HashSet<>(),
                renter);

        Review review = new Review(4, "very good", SDate.dateToString(), tenant);

        Data data = new Data();
        renter.addRental(rental);
        tenant.addReview(rental, review);
        rental.addReview(review);

        System.out.println(renter.getRentals().size());

        data.addUser(renter);
        data.addUser(tenant);
        data.addRental(rental);

        try (ObjectOutputStream in = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            in.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
