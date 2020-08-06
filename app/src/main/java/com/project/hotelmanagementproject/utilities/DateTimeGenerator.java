package com.project.hotelmanagementproject.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DateTimeGenerator {

    public static String getDateString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    public static String getTommrrowDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        Date dtTomorrow = c.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(dtTomorrow);
    }


    public static String getDefaultTimeForReservRoom() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY,12);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date date = cal.getTime();
        return new SimpleDateFormat("HH:mm").format(date);
    }



    public static Date getDateWithTimeForDateString(String date , String time)  {
//        /Date should be in the format of yyyy-MM-dd
        //time should be in the format of HH:mm

        Date date1 = new Date();
        Calendar cal = Calendar.getInstance();
        time = time.toString();
        try {

            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            cal.set(Calendar.HOUR_OF_DAY, new Integer(time.split(":")[0]));
            cal.set(Calendar.MINUTE,new Integer(time.split(":")[1]));
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            date1 = cal.getTime();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(date1);
        return date1;
    }

    public static String getNumOfNightsForDates(String startDate , String startTime, String endDate , String endTime)  {

        Date date1 = getDateWithTimeForDateString(startDate , startTime);
        Date date2 = getDateWithTimeForDateString(endDate , endTime);
//        System.out.println("date1="+ date1);
//        System.out.println("date1="+ date2);
        long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
        long diffCeil = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//        System.out.println("diff="+ diff);
        return ""+diffCeil;
    }

}
