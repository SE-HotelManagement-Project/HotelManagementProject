package com.project.hotelmanagementproject.model;

import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    String reservationId;
    String resvRoomId;
    String resvUserName;

    String resvHotelName;
    String resvRoomType;
    String resvNumAdultsChildren;
    String resvNumNights;

    String resvNumOfRooms;
    String totalPrice;
    String startDate;
    String resvCheckInDate;
    String resevCheckOutDate;
    String resvStartTime;
    String resvPaymentStatus;

    public Reservation(String reservationId, String resvRoomId, String resvUserName,
                       String resvHotelName, String resvRoomType, String resvNumAdultsChildren,
                       String resvNumNights, String resvNumOfRooms,
                       String totalPrice, String resvCheckInDate, String resevCheckOutDate,
                       String resvStartTime, String startDate, String resvPaymentStatus) {

        this.reservationId = reservationId;
        this.resvRoomId = resvRoomId;
        this.resvUserName = resvUserName;
        this.resvHotelName = resvHotelName;
        this.resvRoomType = resvRoomType;
        this.resvNumAdultsChildren = resvNumAdultsChildren;
        this.resvNumNights = resvNumNights;
        this.resvNumOfRooms = resvNumOfRooms;
        this.totalPrice = totalPrice;
        this.startDate = startDate;
        this.resvCheckInDate = resvCheckInDate;
        this.resevCheckOutDate = resevCheckOutDate;
        this.resvStartTime = resvStartTime;
        this.resvPaymentStatus = resvPaymentStatus;
    }

    public Reservation() {
    }

    public String getResvRoomId() {
        return resvRoomId;
    }

    public void setResvRoomId(String resvRoomId) {
        this.resvRoomId = resvRoomId;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public String getResvNumOfRooms() {
        return resvNumOfRooms;
    }

    public void setResvNumOfRooms(String resvNumOfRooms) {
        this.resvNumOfRooms = resvNumOfRooms;
    }

    public String getResvPaymentStatus() {
        return resvPaymentStatus;
    }

    public void setResvPaymentStatus(String resvPaymentStatus) {
        this.resvPaymentStatus = resvPaymentStatus;
    }

    public static boolean isNullorEmpty(String ip) {
        return ip.equalsIgnoreCase(null) || ip.equalsIgnoreCase("")
                || ip.equalsIgnoreCase(" ") || ip.isEmpty();
    }


    public static boolean isValidStartTime(String startTime) {
        return ((!isNullorEmpty(startTime)) && (startTime.matches("(?:[0-1][0-9]|2[0-4]):[0-5]\\d")));
    }

    public static boolean isValidDate(String text) {
        if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(text);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static boolean isValidRange(String checkin, String checkout) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(checkin);
            Date date2 = sdf.parse(checkout);
            if (date1.before(date2)) {
                System.out.println("Date1 is before Date2");
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidNumRooms(String resvNumOfRooms) {
        return !isNullorEmpty(resvNumOfRooms) && resvNumOfRooms.length() == 1
                && Integer.parseInt(resvNumOfRooms) <= 4 && Integer.parseInt(resvNumOfRooms) >= 1;
    }

    public static boolean isValidNumAdults(String resvNumAdultsChildren) {
        return !isNullorEmpty(resvNumAdultsChildren) && (resvNumAdultsChildren.length() < 3 && resvNumAdultsChildren.length() > 0)
                && Integer.parseInt(resvNumAdultsChildren) <= 16 && Integer.parseInt(resvNumAdultsChildren) >= 1;
    }

    public static boolean isValidNumNights(String resvNumNights) {
        return !isNullorEmpty(resvNumNights) && (resvNumNights.length() < 3 && resvNumNights.length() > 0)
                && Integer.parseInt(resvNumNights) <= 30 && Integer.parseInt(resvNumNights) >= 1;
    }
}
