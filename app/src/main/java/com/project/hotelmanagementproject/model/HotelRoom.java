package com.project.hotelmanagementproject.model;

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

    public HotelRoom(String hotelRoomId, String hotelName,
                     String roomNum, String roomType, String floorNum,
                     String roomPriceWeekDay, String roomPriceWeekend, String hotelTax,
                     String availabilityStatus, String occupiedStatus) {
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

}
