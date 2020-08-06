package com.project.hotelmanagementproject.utilities;

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
        return (str != null &&  !ConstantUtils.EMPTY.equals(str)) ? true : false;
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
//    public static String getNumOfDaysForStartAndEndDate(String startDate, String startTime, String endDate,String endTime){
//        String numOfDays = "";
//        Date startSate  = DateTimeGenerator.getNumOfNightsForDates(startDate, startTime);
//        Date endSate  = DateTimeGenerator.getNumOfNightsForDates(endDate, endTime);
//
//        return numOfDays;
//    }

    public static String calculateTotalReservationPrice( String startDate, String startTime, String endDate, String endTime, String weekDayPricePerDay, String WeekednPricePerDay ,String taxPercent){
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

        String total_price =  (totalReservationPrice + totalReservationPrice * ((new Double(taxPercent))/100) )+ "";
        String[] total_price_split = total_price.split(".");
        if(total_price_split.length==2 && total_price_split[1].length()>1){
            total_price ="$ "+  total_price_split[0]+"."+ total_price_split[1].substring(0,1);
        }
        else{
            total_price ="$ "+total_price;
        }
        return total_price;

    }
}
