package com.project.hotelmanagementproject.utilities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_TYPE;

public class UtilityFunctions {
    public static boolean isNullOrEmpty(String str) {
        return (str != null || !ConstantUtils.EMPTY.equals(str)) ? false : true;
    }

    public static boolean isNullOrEmpty(List list) {
        return (list != null || !list.isEmpty()) ?  false : true;
    }

    public static boolean isNotNullAndEmpty(String str) {
        return (str != null &&  !ConstantUtils.EMPTY.equals(str.trim())) ? true : false;
    }

    public static boolean isNotNullAndEmpty(List list) {
        return (list != null && !list.isEmpty()) ?  true : false;
    }

    public static StringBuffer appendRoomTypesToStringBuffer(List<String> roomTypesList){
        StringBuffer searchRoomGroupBy = new StringBuffer("");
        if(isNotNullAndEmpty(roomTypesList)){
            int size = roomTypesList.size();
            searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_TYPE + " in (");
            for(int i=0; i < size; i++){
                searchRoomGroupBy.append("'").append(roomTypesList.get(i)).append("' ");
                if(i  < (size -1)){
                    searchRoomGroupBy.append(",");
                }
            }
            searchRoomGroupBy.append(")");
        }
        return searchRoomGroupBy;
    }


    public static StringBuffer appendHotelNameToStringBuffer(List<String> hotelNameList){
        StringBuffer searchRoomGroupBy = new StringBuffer("");
        if(isNotNullAndEmpty(hotelNameList)){
            int size = hotelNameList.size();
            searchRoomGroupBy.append(" and "+ COL_HOTEL_NAME + " in (");
            for(int i=0; i< size; i++){
                searchRoomGroupBy.append("'").append(hotelNameList.get(i)).append("' ");
                if(i  < (size -1)){
                    searchRoomGroupBy.append(",");
                }
            }
            searchRoomGroupBy.append(")");
        }
        return searchRoomGroupBy;
    }


    public static StringBuffer appendStringWithListAndCol(List<String> list, String colName){
        StringBuffer searchRoomGroupBy = new StringBuffer("");

            int size = list.size();
            searchRoomGroupBy.append(" and "+ colName + " in (");
            for(int i=0; i< size; i++){
                searchRoomGroupBy.append("'").append(list.get(i)).append("' ");
                if(i  < (size -1)){
                    searchRoomGroupBy.append(",");
                }
            }
            searchRoomGroupBy.append(")");
        return searchRoomGroupBy;
    }
//    public static String getNumOfDaysForStartAndEndDate(String startDate, String startTime, String endDate,String endTime){
//        String numOfDays = "";
//        Date startSate  = DateTimeGenerator.getNumOfNightsForDates(startDate, startTime);
//        Date endSate  = DateTimeGenerator.getNumOfNightsForDates(endDate, endTime);
//
//        return numOfDays;
//    }

    public static String calculateTotalReservationPrice( String startDate, String startTime, String endDate,
                                  String endTime, String weekDayPricePerDay, String WeekednPricePerDay ,String taxPercent , String numOfRooms){
        double totalReservationPrice = 0;
//        TO DO the computation of total room price
        Date date1 = DateTimeGenerator.getDateWithTimeForDateString(startDate, startTime);
        Date date2 = DateTimeGenerator.getDateWithTimeForDateString(endDate, endTime);
        long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
        long diffCeil = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
//        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        for(int i=0 ; i< diffCeil; i++){

            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek ==1 || dayOfWeek ==7){
                totalReservationPrice = new Double(totalReservationPrice) + new Double (WeekednPricePerDay);
            }
            else{
                totalReservationPrice = new Double(totalReservationPrice) + new Double (weekDayPricePerDay);
            }
            c.add(Calendar.DATE, 1);
        }

        double total_priceDouble = (totalReservationPrice + (totalReservationPrice * ((new Double(taxPercent)) / 100))) * (Integer.parseInt(numOfRooms))  ;

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedPrice = df.format(total_priceDouble);

        String total_price = "$ "+ formattedPrice;
//
        return total_price;
    }

    public static List<String> populateHotelNamesList(String search_hotel_name){
        List<String> hotelNamesList = new ArrayList<String>();
        if(ConstantUtils.ALL.equals(search_hotel_name)){
            hotelNamesList.add(ConstantUtils.HM_MAVERICK);
            hotelNamesList.add(ConstantUtils.HM_RANGER);
            hotelNamesList.add(ConstantUtils.HM_WILLIAMS);
            hotelNamesList.add(ConstantUtils.HM_SHARD);
            hotelNamesList.add(ConstantUtils.HM_LIBERTY);
        }
        else{
            hotelNamesList.add(search_hotel_name.trim().toUpperCase());
        }
        return hotelNamesList;
    }

    public static List<String> populateRoomsTypeList(String search_room_type_standard , String search_room_type_deluxe, String search_room_type_suite){
        List<String> roomTypesList = new ArrayList<String>();
        if( UtilityFunctions.isNotNullAndEmpty(search_room_type_standard)){
            roomTypesList.add(search_room_type_standard);
        }
        if(UtilityFunctions.isNotNullAndEmpty(search_room_type_deluxe) ){
            roomTypesList.add(search_room_type_deluxe);
        }
        if( UtilityFunctions.isNotNullAndEmpty(search_room_type_suite)){
            roomTypesList.add(search_room_type_suite);
        }

        return roomTypesList;
    }
}
