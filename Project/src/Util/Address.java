package Util;

public class Address {
    private String city;
    private String street;
    private int number;
    private String postalCode;
    private String secondLine;      // apartment, floor etc.

    public Address(String city, String street, int number, String postalCode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }

    public Address(String city, String street, int number, String postalCode, String secondLine) {
        this(city, street, number, postalCode);
        this.secondLine = secondLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }
}
