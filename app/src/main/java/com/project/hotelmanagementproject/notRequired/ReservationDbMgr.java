//package com.project.hotelmanagementproject.model;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CHECKIN_DATE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CHECKOUT_DATE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_GUEST_FIRST_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_GUEST_LAST_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_GUEST_USER_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_ADULTS_AND_CHILDREN;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_NIGHTS;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_ROOMS;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RESERV_HOTEL_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RESERV_ID;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ROOM_TYPE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_START_TIME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_TOTAL_PRICE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_RESERV_DATA;
//
//
//public class ReservationDbMgr {
//    private static ReservationDbMgr mInstance = null;
//    Context context;
//    DBHelper dbHelper;
//
//    private ReservationDbMgr(Context context) {
//        this.context = context;
//        dbHelper = DBHelper.getInstance(context);
//    }
//
//    public static synchronized ReservationDbMgr getInstance(Context ctx) {
//        if (mInstance == null) {
//            mInstance = new ReservationDbMgr(ctx.getApplicationContext());
//        }
//        return mInstance;
//    }
//
//    public boolean addNewReserv(Reservation reservation) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COL_RESERV_ID, reservation.getReservationId());
//        cv.put(COL_GUEST_USER_NAME, reservation.getResvUserName());
//        cv.put(COL_GUEST_FIRST_NAME, reservation.getResvFirstName());
//        cv.put(COL_GUEST_LAST_NAME, reservation.getResvLastName());
//        cv.put(COL_RESERV_HOTEL_NAME, reservation.getResvHotelName());
//        cv.put(COL_ROOM_TYPE, reservation.getResvRoomType());
//        cv.put(COL_NUM_OF_ROOMS, reservation.getResvNumRooms());
//        cv.put(COL_NUM_OF_ADULTS_AND_CHILDREN, reservation.getResvNumAdultsChildren());
//        cv.put(COL_NUM_OF_NIGHTS, reservation.getResvNumNights());
//        cv.put(COL_CHECKIN_DATE, reservation.getResvCheckInDate());
//        cv.put(COL_CHECKOUT_DATE, reservation.getResevCheckOutDate());
//        cv.put(COL_START_TIME, reservation.getResvStartTime());
//        cv.put(COL_TOTAL_PRICE, reservation.getTotalPrice());
//        long res = db.insert(TABLE_RESERV_DATA, null, cv);
////        if(res== -1)
//////            return false;
//////        else
//////            return true;
//        return res != -1;
//    }
//
//
//
//}