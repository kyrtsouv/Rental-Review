package api;

public class Location {
    private String address;
    private String city;
    private String postcode;

    public Location(String address, String city, String postcode) {
        this.address = address;
        this.city = city;
        this.postcode = postcode;
    }

    public void print() {
        System.out.println("Address: " + address);
        System.out.println("City: " + city);
        System.out.println("Postcode: " + postcode);
    }
}
