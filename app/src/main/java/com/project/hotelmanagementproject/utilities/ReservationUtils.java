package com.project.hotelmanagementproject.utilities;

import android.content.Context;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.PAID;

public class ReservationUtils {
    Context context;
    Reservation reservation;

    public ReservationUtils(Context context, Reservation reservation) {
        this.context = context;
        this.reservation = reservation;
    }

    public Reservation reserveRoom() {
        DbMgr dbMgr = DbMgr.getInstance(context);
        List<HotelRoom> hotelRoomsList = dbMgr.getRoomsForReqResv(reservation.getResvHotelName(), reservation.getResvRoomType(),
                reservation.getResvCheckInDate(), reservation.getResevCheckOutDate(), reservation.getResvStartTime());

        Reservation resv = null;
        HotelRoom hotelRoom;
        String reservationId = null;
        int numOfRoomsInt = Integer.parseInt(reservation.getResvNumOfRooms());
        boolean makeReserveSucess = false;

        if (UtilityFunctions.isNotNullAndEmpty(hotelRoomsList) && numOfRoomsInt <= hotelRoomsList.size()) {
            for (int i = 0; i < numOfRoomsInt; i++) {
                hotelRoom = hotelRoomsList.get(i);
                //  reservationId = reservation.getReservationId();
                if (reservationId == null) {
                    reservationId = createReservationId(hotelRoom.getHotelName());
                }

                resv = new Reservation(reservationId, hotelRoom.getHotelRoomId(), reservation.getResvUserName(), reservation.getResvHotelName(),
                        reservation.getResvRoomType(), reservation.getResvNumAdultsChildren(), reservation.getResvNumNights(), reservation.getResvNumOfRooms(),
                        reservation.getTotalPrice(), reservation.getResvCheckInDate(), reservation.getResevCheckOutDate(), reservation.getResvStartTime(), null, PAID);

//                resv = new Reservation();
//                resv.setReservationId(reservationId);
//                resv.setResvRoomId(hotelRoom.getHotelRoomId());
//                resv.setResvNumNights(reservation.getResvNumNights());
//                resv.setResvNumAdultsChildren(reservation.getResvNumAdultsChildren());
//                resv.setResvStartTime(reservation.getResvStartTime());
//                resv.setResvCheckInDate(reservation.getResvCheckInDate());
//                resv.setResevCheckOutDate(reservation.getResevCheckOutDate());
//                resv.setTotalPrice(reservation.getTotalPrice());
//                resv.setResvRoomType(reservation.getResvRoomType());
//                resv.setResvHotelName(reservation.getResvHotelName());
//                resv.setResvUserName(reservation.getResvUserName());
//                resv.setResvNumOfRooms(reservation.getResvNumOfRooms());
//                resv.setResvPaymentStatus(PAID);

                makeReserveSucess = dbMgr.addNewReserv(resv);
            }
        }
        if (makeReserveSucess) {
            return resv;
        } else {
            return null;
        }
    }

    public String createReservationId(String hotel_name) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateString = formatter.format(date);
//        System.out.println("dateformat for naming "+formatter.format(new SimpleDateFormat("MMddyyyy_HHmmss")));
        String[] dateSplit1 = dateString.split("-");
        String[] dateSplit2 = dateString.split(":");

        return (hotel_name.toLowerCase().substring(0, 3) + "_" + dateSplit1[1] + dateSplit1[2].substring(0, 2) + dateSplit1[0].substring(2) +
                "_" + dateSplit2[0].substring(dateSplit2[0].length() - 2) + dateSplit2[1] + dateSplit2[2]);
    }

}
