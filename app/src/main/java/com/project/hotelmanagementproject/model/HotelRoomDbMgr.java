package com.project.hotelmanagementproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_AVAILABILITY_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_FLOOR_NUM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_TYPE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_OCCUPIED_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKDAY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKEND;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ROOM_NUM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_TAX;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_HOTEL_DATA;

//<<>Singleton>
public class HotelRoomDbMgr {
    private static HotelRoomDbMgr mInstance = null;
    Context context;
    DBHelper dbHelper;

    private HotelRoomDbMgr(Context context) {
        this.context = context;
        dbHelper = DBHelper.getInstance(context);
    }

    public static synchronized HotelRoomDbMgr getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new HotelRoomDbMgr(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public boolean addNewHotelRoom(HotelRoom hotelRoom) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_HOTEL_ROOM_ID, hotelRoom.getHotelRoomId());
        cv.put(COL_HOTEL_NAME, hotelRoom.getHotelName());
        cv.put(COL_ROOM_NUM, hotelRoom.getRoomNum());
        cv.put(COL_HOTEL_ROOM_TYPE, hotelRoom.getRoomType());
        cv.put(COL_FLOOR_NUM, hotelRoom.getFloorNum());
        cv.put(COL_AVAILABILITY_STATUS, hotelRoom.getAvailabilityStatus());
        cv.put(COL_OCCUPIED_STATUS, hotelRoom.getOccupiedStatus());
        cv.put(COL_PRICE_WEEKDAY, hotelRoom.getRoomPriceWeekDay());
        cv.put(COL_PRICE_WEEKEND, hotelRoom.getRoomPriceWeekend());
        cv.put(COL_TAX, hotelRoom.getHotelTax());

        long res = db.insert(TABLE_HOTEL_DATA, null, cv);
//        if(res== -1)
////            return false;
////        else
////            return true;
        return res != -1;
    }
}