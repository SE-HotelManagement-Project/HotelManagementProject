package com.project.hotelmanagementproject.model;

public class User {
    String userName;
    String firstName;
    String lastName;
    String password;
    String userRole;
    String hotelAssigned;
    String email;
    String phone;
    String streetAddress;
    String city;
    String state;
    String zipCode;
    String creditCardNum;
    String creditCardExp;
    String creditCardtype;

    public User(String userName, String firstName, String lastName, String password, String userRole, String hotelAssigned, String email, String phone, String streetAddress, String city, String state, String zipCode, String creditCardNum, String creditCardExp, String creditCardtype) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
        this.hotelAssigned = hotelAssigned;
        this.email = email;
        this.phone = phone;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.creditCardNum = creditCardNum;
        this.creditCardExp = creditCardExp;
        this.creditCardtype = creditCardtype;
    }

    public String getHotelAssigned() {
        return hotelAssigned;
    }

    public void setHotelAssigned(String hotelAssigned) {
        this.hotelAssigned = hotelAssigned;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public String getCreditCardExp() {
        return creditCardExp;
    }

    public void setCreditCardExp(String creditCardExp) {
        this.creditCardExp = creditCardExp;
    }

    public String getCreditCardtype() {
        return creditCardtype;
    }

    public void setCreditCardtype(String creditCardtype) {
        this.creditCardtype = creditCardtype;
    }


}
