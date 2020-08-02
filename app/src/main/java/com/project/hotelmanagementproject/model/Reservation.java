package com.project.hotelmanagementproject.model;

public class Reservation {
    String reservationId;
    String resvUserName;
    String resvFirstName;
    String resvLastName;

    String resvHotelName;
    String resvRoomType;
    String resvNumAdultsChildren;
    String resvNumNights;
    String resvNumRooms;

    String totalPrice;
    String resvCheckInDate;
    String resevCheckOutDate;
    String resvStartTime;

    public Reservation(String reservationId, String resvUserName, String resvFirstName, String resvLastName,
                       String resvHotelName, String resvRoomType, String resvNumAdultsChildren, String resvNumNights, String resvNumRooms,
                       String totalPrice, String resvCheckInDate, String resevCheckOutDate, String resvStartTime) {
        this.reservationId = reservationId;
        this.resvUserName = resvUserName;
        this.resvFirstName = resvFirstName;
        this.resvLastName = resvLastName;
        this.resvHotelName = resvHotelName;
        this.resvRoomType = resvRoomType;
        this.resvNumAdultsChildren = resvNumAdultsChildren;
        this.resvNumNights = resvNumNights;
        this.resvNumRooms = resvNumRooms;
        this.totalPrice = totalPrice;
        this.resvCheckInDate = resvCheckInDate;
        this.resevCheckOutDate = resevCheckOutDate;
        this.resvStartTime = resvStartTime;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getResvUserName() {
        return resvUserName;
    }

    public void setResvUserName(String resvUserName) {
        this.resvUserName = resvUserName;
    }

    public String getResvFirstName() {
        return resvFirstName;
    }

    public void setResvFirstName(String resvFirstName) {
        this.resvFirstName = resvFirstName;
    }

    public String getResvLastName() {
        return resvLastName;
    }

    public void setResvLastName(String resvLastName) {
        this.resvLastName = resvLastName;
    }

    public String getResvHotelName() {
        return resvHotelName;
    }

    public void setResvHotelName(String resvHotelName) {
        this.resvHotelName = resvHotelName;
    }

    public String getResvRoomType() {
        return resvRoomType;
    }

    public void setResvRoomType(String resvRoomType) {
        this.resvRoomType = resvRoomType;
    }

    public String getResvNumAdultsChildren() {
        return resvNumAdultsChildren;
    }

    public void setResvNumAdultsChildren(String resvNumAdultsChildren) {
        this.resvNumAdultsChildren = resvNumAdultsChildren;
    }

    public String getResvNumNights() {
        return resvNumNights;
    }

    public void setResvNumNights(String resvNumNights) {
        this.resvNumNights = resvNumNights;
    }

    public String getResvNumRooms() {
        return resvNumRooms;
    }

    public void setResvNumRooms(String resvNumRooms) {
        this.resvNumRooms = resvNumRooms;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getResvCheckInDate() {
        return resvCheckInDate;
    }

    public void setResvCheckInDate(String resvCheckInDate) {
        this.resvCheckInDate = resvCheckInDate;
    }

    public String getResevCheckOutDate() {
        return resevCheckOutDate;
    }

    public void setResevCheckOutDate(String resevCheckOutDate) {
        this.resevCheckOutDate = resevCheckOutDate;
    }

    public String getResvStartTime() {
        return resvStartTime;
    }

    public void setResvStartTime(String resvStartTime) {
        this.resvStartTime = resvStartTime;
    }

}
