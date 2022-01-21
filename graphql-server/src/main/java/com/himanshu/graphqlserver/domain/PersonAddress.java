package com.himanshu.graphqlserver.domain;

public class PersonAddress {

    private String streetName;
    private String houseNumber;
    private String city;
    private String country;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PersonAddress(String streetName, String houseNumber, String city, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }
}
