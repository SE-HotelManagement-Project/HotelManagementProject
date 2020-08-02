//package com.project.hotelmanagementproject.model;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import java.util.ArrayList;
//
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_AVAILABILITY_STATUS;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CARD_TYPE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CITY;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CREDIT_CARD_EXP;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CREDIT_CARD_NUM;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_EMAIL;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_FIRST_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_FLOOR_NUM;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_ID;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_TYPE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_LAST_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_OCCUPIED_STATUS;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PASSWORD;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PHONE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKDAY;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKEND;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ROOM_NUM;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ROOM_TYPE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_STATE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_STREET_ADDRESS;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_TAX;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_USER_NAME;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_USER_ROLE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ZIP_CODE;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_HOTEL_DATA;
//import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_USER_DATA;
//
////<<>Singleton>
//public class HotelRoomDbMgr {
//    private static HotelRoomDbMgr mInstance = null;
//    Context context;
//    DBHelper dbHelper;
//
//    private HotelRoomDbMgr(Context context) {
//        this.context = context;
//        dbHelper = DBHelper.getInstance(context);
//    }
//
//    public static synchronized HotelRoomDbMgr getInstance(Context ctx) {
//        if (mInstance == null) {
//            mInstance = new HotelRoomDbMgr(ctx.getApplicationContext());
//        }
//        return mInstance;
//    }
//
//    public boolean addNewHotelRoom(HotelRoom hotelRoom) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COL_HOTEL_ROOM_ID, hotelRoom.getHotelRoomId());
//        cv.put(COL_HOTEL_NAME, hotelRoom.getHotelName());
//        cv.put(COL_ROOM_NUM, hotelRoom.getRoomNum());
//        cv.put(COL_HOTEL_ROOM_TYPE, hotelRoom.getRoomType());
//        cv.put(COL_FLOOR_NUM, hotelRoom.getFloorNum());
//        cv.put(COL_AVAILABILITY_STATUS, hotelRoom.getAvailabilityStatus());
//        cv.put(COL_OCCUPIED_STATUS, hotelRoom.getOccupiedStatus());
//        cv.put(COL_PRICE_WEEKDAY, hotelRoom.getRoomPriceWeekDay());
//        cv.put(COL_PRICE_WEEKEND, hotelRoom.getRoomPriceWeekend());
//        cv.put(COL_TAX, hotelRoom.getHotelTax());
//
//        long res = db.insert(TABLE_HOTEL_DATA, null, cv);
////        if(res== -1)
//////            return false;
//////        else
//////            return true;
//        return res != -1;
//    }
//
//    public Cursor getRoomList(String roomNum) {
//        SQLiteDatabase sqldb = dbHelper.getReadableDatabase();
//        String searchRoom = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_ROOM_NUM + " like '" + roomNum + "'";
//        String searchRoom1 = "Select * from " + TABLE_HOTEL_DATA;
//
//        Cursor c = sqldb.rawQuery(searchRoom, null);
//        return c;
//    }
//
//    public ArrayList<HotelRoom>getSearchRoomList(String roomNum){
//        ArrayList<HotelRoom> roomList  = new ArrayList<>();
//
//        SQLiteDatabase sqldb = dbHelper.getReadableDatabase();
//        String searchRoom = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_ROOM_NUM + " like '%" + roomNum + "%'";
//        String searchRoom1 = "Select * from " + TABLE_HOTEL_DATA;
//
//        try {
//            Cursor c = sqldb.rawQuery(searchRoom, null);
//            if (c.getCount() == 0) {
//                Log.e("search room Query Err: ", "No data");
//            } else {
//                while (c.moveToNext()) {
//
//                    Log.i("room details: ", c.getString(c.getColumnIndex(COL_ROOM_NUM)));
//                    Log.i("room details: ", c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID)));
//                    Log.i("room details: ", c.getString(c.getColumnIndex(COL_HOTEL_ROOM_TYPE)));
//
//                    String roomId = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID));
//                    String roomNumber  = c.getString(c.getColumnIndex(COL_ROOM_NUM));
//                    String roomType =  c.getString(c.getColumnIndex(COL_ROOM_TYPE));
//                    String hotelName =  c.getString(c.getColumnIndex(COL_HOTEL_NAME));
//                    String floorNum = c.getString(c.getColumnIndex(COL_FLOOR_NUM));
//                    String price = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
//
//                    HotelRoom hotelRoom = new HotelRoom(roomId,hotelName,roomNumber,roomType,floorNum,price,
//                            null,null,null
//                    ,null);
//                    roomList.add(hotelRoom);
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return roomList;
//    }
//
//
//
//}