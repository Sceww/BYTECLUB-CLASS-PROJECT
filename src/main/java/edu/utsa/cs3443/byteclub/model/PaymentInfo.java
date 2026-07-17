package edu.utsa.cs3443.byteclub.model;

public class PaymentInfo {
    private String creditCard, ccv, exp, city, state, country, address1, address2, zipcode;

    public PaymentInfo(String exp, String creditCard, String ccv, String city, String state, String country, String address1, String address2, String zipcode) {
        this.exp = exp;
        this.creditCard = creditCard;
        this.ccv = ccv;
        this.city = city;
        this.state = state;
        this.country = country;
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }

    public String getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCcv() {
        return ccv;
    }
    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getExp() {
        return exp;
    }
    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
