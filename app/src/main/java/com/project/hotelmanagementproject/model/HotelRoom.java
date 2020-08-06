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
    String startDate;
    String endDate;
    String startTime;
    int countOfavailableRooms;
	 String NumOfRooms;

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
        NumOfRooms = numOfRooms;
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

    
}
