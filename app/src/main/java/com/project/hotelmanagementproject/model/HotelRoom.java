package com.project.hotelmanagementproject.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelRoom {

    String hotelRoomId;
    String hotelName;
    String roomNum;
    String roomType;
    String floorNum;
    String roomPriceWeekDay;
    String roomPriceWeekend;
    String hotelTax;
    String availabilityStatus;
    String occupiedStatus;
    String startDate;
    String endDate;
    String startTime;
    int countOfavailableRooms;

    String NumOfRooms;
    String totalPrice;

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getNumOfNights() {
        return numOfNights;
    }

    public void setNumOfNights(String numOfNights) {
        this.numOfNights = numOfNights;
    }

    String numOfNights;


    String numOfAdultChildren;


    //    public String getCheckInDate() {
//        return checkInDate;
//    }
//
//    public void setCheckInDate(String checkInDate) {
//        this.checkInDate = checkInDate;
//    }
//
//    String checkInDate;
    public String getNumOfRooms() {
        return NumOfRooms;
    }

    public void setNumOfRooms(String numOfRooms) {
        this.NumOfRooms = numOfRooms;
    }

    public String getNumOfAdultChildren() {
        return numOfAdultChildren;
    }

    public void setNumOfAdultChildren(String numOfAdultChildren) {
        this.numOfAdultChildren = numOfAdultChildren;
    }

    public int getCountOfavailableRooms() {
        return countOfavailableRooms;
    }

    public void setCountOfavailableRooms(int countOfavailableRooms) {
        this.countOfavailableRooms = countOfavailableRooms;
    }

    public HotelRoom() {
    }

    public HotelRoom(String hotelRoomId, String hotelName,
                     String roomNum, String roomType, String floorNum,
                     String roomPriceWeekDay, String roomPriceWeekend, String hotelTax,
                     String availabilityStatus, String occupiedStatus, String startDate, String endDate, String startTime) {
        this.hotelRoomId = hotelRoomId;
        this.hotelName = hotelName;
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.floorNum = floorNum;
        this.roomPriceWeekDay = roomPriceWeekDay;
        this.roomPriceWeekend = roomPriceWeekend;
        this.hotelTax = hotelTax;
        this.availabilityStatus = availabilityStatus;
        this.occupiedStatus = occupiedStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
    }

    public String getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(String hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getRoomPriceWeekDay() {
        return roomPriceWeekDay;
    }

    public void setRoomPriceWeekDay(String roomPriceWeekDay) {
        this.roomPriceWeekDay = roomPriceWeekDay;
    }

    public String getRoomPriceWeekend() {
        return roomPriceWeekend;
    }

    public void setRoomPriceWeekend(String roomPriceWeekend) {
        this.roomPriceWeekend = roomPriceWeekend;
    }

    public String getHotelTax() {
        return hotelTax;
    }

    public void setHotelTax(String hotelTax) {
        this.hotelTax = hotelTax;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getOccupiedStatus() {
        return occupiedStatus;
    }

    public void setOccupiedStatus(String occupiedStatus) {
        this.occupiedStatus = occupiedStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public static boolean isNullorEmpty(String ip) {
        return ip.equalsIgnoreCase(null) || ip.equalsIgnoreCase("")
                || ip.equalsIgnoreCase(" ") || ip.isEmpty();
    }


}
