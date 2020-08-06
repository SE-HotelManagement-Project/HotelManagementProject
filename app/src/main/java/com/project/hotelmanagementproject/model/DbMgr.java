package com.project.hotelmanagementproject.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.hotelmanagementproject.utilities.UtilityFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_AVAILABILITY_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CARD_TYPE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CHECKIN_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CHECKOUT_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CREDIT_CARD_EXP;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CREDIT_CARD_NUM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_EMAIL;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_FIRST_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_FLOOR_NUM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_GUEST_FIRST_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_GUEST_LAST_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_GUEST_USER_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ACCESS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_TYPE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_LAST_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_ADULTS_AND_CHILDREN;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_NIGHTS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_ROOMS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PASSWORD;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PAYMENT_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PHONE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKDAY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKEND;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RESERV_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RESERV_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RESERV_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ROOM_NUM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ROOM_TYPE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_START_TIME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_STREET_ADDRESS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_TAX;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_TOTAL_PRICE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_USER_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_USER_ROLE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ZIP_CODE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_HOTEL_DATA;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_RESERV_DATA;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_USER_DATA;

public class DbMgr extends SQLiteOpenHelper {

    private static final String DB_NAME = "HotelManagementProject.db";
    private static DbMgr mInstance = null;
    Context context;

    /**
     * constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    private DbMgr(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    public static synchronized DbMgr getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DbMgr(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTblQry = "create table " + TABLE_USER_DATA + "(" + COL_FIRST_NAME + " text, " +
                COL_LAST_NAME + " text, " + COL_USER_NAME + " text primary key," + COL_PASSWORD + " text,"
                + COL_USER_ROLE + " text," + COL_HOTEL_ACCESS + " text," + COL_EMAIL + " text," + COL_PHONE + " text," + COL_STREET_ADDRESS + " text,"
                + COL_CITY + " text," + COL_STATE + " text," + COL_ZIP_CODE + " text," + COL_CREDIT_CARD_NUM + " text, "
                + COL_CREDIT_CARD_EXP + " text," + COL_CARD_TYPE + " text)";
        db.execSQL(userTblQry);

        String hotelTableQry = "create table " + TABLE_HOTEL_DATA + "(" + COL_HOTEL_NAME + " text, " +
                COL_ROOM_NUM + " text, " + COL_HOTEL_ROOM_ID + " text primary key," + COL_PRICE_WEEKDAY + " text,"
                + COL_PRICE_WEEKEND + " text," + COL_HOTEL_ROOM_TYPE + " text," + COL_AVAILABILITY_STATUS + " text,"
                + COL_FLOOR_NUM + " text," + COL_TAX + " text)";
        db.execSQL(hotelTableQry);

        String reservationTblQry = "create table " + TABLE_RESERV_DATA + "(" + COL_RID + " integer primary key autoincrement, "
                + COL_GUEST_FIRST_NAME + " text, " + COL_GUEST_LAST_NAME + " text," + COL_GUEST_USER_NAME + " text, "
                + COL_RESERV_ID + " text," + COL_RESERV_HOTEL_NAME + " text," + COL_ROOM_TYPE + " text," + COL_NUM_OF_ROOMS + " text,"
                + COL_NUM_OF_ADULTS_AND_CHILDREN + " text," + COL_NUM_OF_NIGHTS + " text," + COL_CHECKIN_DATE + " date,"
                + COL_CHECKOUT_DATE + " date, " + COL_START_TIME + " text," + COL_TOTAL_PRICE + " text, " + COL_RESERV_ROOM_ID + " text, "
                + COL_PAYMENT_STATUS + " text,"
                + "foreign key(" + COL_RESERV_ROOM_ID + ") references " + TABLE_HOTEL_DATA + " (" + COL_HOTEL_ROOM_ID + "))";
        db.execSQL(reservationTblQry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERV_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOTEL_DATA);
        onCreate(db);
    }

    public boolean addNewGuest(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USER_NAME, user.getUserName());
        cv.put(COL_PASSWORD, user.getPassword());
        cv.put(COL_FIRST_NAME, user.getFirstName());
        cv.put(COL_LAST_NAME, user.getLastName());
        cv.put(COL_EMAIL, user.getEmail());
        cv.put(COL_PHONE, user.getPhone());
        cv.put(COL_STREET_ADDRESS, user.getStreetAddress());
        cv.put(COL_CITY, user.getCity());
        cv.put(COL_STATE, user.getState());
        cv.put(COL_ZIP_CODE, user.getZipCode());
        cv.put(COL_CREDIT_CARD_NUM, user.getCreditCardNum());
        cv.put(COL_CREDIT_CARD_EXP, user.getCreditCardExp());
        cv.put(COL_CARD_TYPE, user.getCreditCardtype());
        cv.put(COL_USER_ROLE, "Guest");

        long res = db.insert(TABLE_USER_DATA, null, cv);
//        if(res== -1)
////            return false;
////        else
////            return true;
        return res != -1;
    }

    public User getUserDetails(String username) {
        String userName = null, password = null, role = null, hotelAccess = null, lastName = null, firstName = null, phone = null, email = null;
        String streetAddress = null, city = null, state = null, zipcode = null, ccn = null, ccexp = null, cctype = null;

        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_DATA + " where " + COL_USER_NAME + " = '" + username + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);

        while (c.moveToNext()) {
            role = c.getString(c.getColumnIndex(COL_USER_ROLE));
            hotelAccess = c.getString(c.getColumnIndex(COL_HOTEL_ACCESS));
            password = c.getString(c.getColumnIndex(COL_PASSWORD));
            userName = c.getString(c.getColumnIndex(COL_USER_NAME));
            email = c.getString(c.getColumnIndex(COL_EMAIL));
            phone = c.getString(c.getColumnIndex(COL_PHONE));
            lastName = c.getString(c.getColumnIndex(COL_LAST_NAME));
            firstName = c.getString(c.getColumnIndex(COL_FIRST_NAME));
            streetAddress = c.getString(c.getColumnIndex(COL_STREET_ADDRESS));
            city = c.getString(c.getColumnIndex(COL_CITY));
            state = c.getString(c.getColumnIndex(COL_STATE));
            zipcode = c.getString(c.getColumnIndex(COL_ZIP_CODE));
            if (role.equalsIgnoreCase("Guest")) {
                ccn = c.getString(c.getColumnIndex(COL_CREDIT_CARD_NUM));
                ccexp = c.getString(c.getColumnIndex(COL_CREDIT_CARD_EXP));
                cctype = c.getString(c.getColumnIndex(COL_CARD_TYPE));
            }
        }
        c.close();
        return new User(userName, firstName, lastName, password, role, hotelAccess, email, phone, streetAddress, city, state, zipcode, ccn, ccexp, cctype);
    }

    public boolean updateProfile(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FIRST_NAME, user.getFirstName());
        cv.put(COL_PASSWORD, user.getPassword());
        cv.put(COL_LAST_NAME, user.getLastName());
        cv.put(COL_EMAIL, user.getEmail());
        cv.put(COL_PHONE, user.getPhone());
        cv.put(COL_STREET_ADDRESS, user.getStreetAddress());
        cv.put(COL_CITY, user.getCity());
        cv.put(COL_STATE, user.getState());
        cv.put(COL_ZIP_CODE, user.getZipCode());
        cv.put(COL_CREDIT_CARD_NUM, user.getCreditCardNum());
        cv.put(COL_CREDIT_CARD_EXP, user.getCreditCardExp());
        cv.put(COL_CARD_TYPE, user.getCreditCardtype());
        int update = db.update(TABLE_USER_DATA, cv, COL_USER_NAME + " = '" + user.getUserName() + "'", null);
        return update == 1;
    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_DATA + " where " + COL_USER_NAME + " = '" + username + "' and " + COL_PASSWORD + " = '" + password + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);
        return c.getCount() > 0;
    }

    public String userRole(String username) {
        String role = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_DATA + " where " + COL_USER_NAME + " = '" + username + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                role = c.getString(c.getColumnIndex(COL_USER_ROLE));
                c.moveToNext();
            }
        } else {
            Log.e(APP_TAG, "db not reached");
        }
        c.close();
        return role;
    }

    public boolean changePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_PASSWORD, newPassword);
        int update = db.update(TABLE_USER_DATA, cv, COL_USER_NAME + " = '" + username + "'", null);
        Log.i(APP_TAG, String.valueOf(update));
        return update == 1;
    }

    public boolean addNewReserv(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_RESERV_ID, reservation.getReservationId());
        cv.put(COL_GUEST_USER_NAME, reservation.getResvUserName());
        cv.put(COL_GUEST_FIRST_NAME, reservation.getResvFirstName());
        cv.put(COL_GUEST_LAST_NAME, reservation.getResvLastName());
        cv.put(COL_RESERV_HOTEL_NAME, reservation.getResvHotelName());
        cv.put(COL_ROOM_TYPE, reservation.getResvRoomType());
        cv.put(COL_NUM_OF_ROOMS, reservation.getResvNumOfRooms());
        cv.put(COL_NUM_OF_ADULTS_AND_CHILDREN, reservation.getResvNumAdultsChildren());
        cv.put(COL_NUM_OF_NIGHTS, reservation.getResvNumNights());
        cv.put(COL_CHECKIN_DATE, reservation.getResvCheckInDate());
        cv.put(COL_CHECKOUT_DATE, reservation.getResevCheckOutDate());
        cv.put(COL_START_TIME, reservation.getResvStartTime());
        cv.put(COL_TOTAL_PRICE, reservation.getTotalPrice());
        cv.put(COL_RESERV_ROOM_ID, reservation.getResvRoomId());
        cv.put(COL_PAYMENT_STATUS, reservation.getPaymentStatus());
        long res = db.insert(TABLE_RESERV_DATA, null, cv);

        return res != -1;
    }

    public ArrayList<HotelRoom> getSearchRoomList(String hotelNameIp, String roomNum,
                                                  String startDate, String endDate, String startTime) {
        ArrayList<HotelRoom> roomList = new ArrayList<>();

        SQLiteDatabase sqldb = this.getReadableDatabase();
        String searchRoom1 = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_ROOM_NUM + " like '%" + roomNum + "%'";
        String searchRoom2 = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_HOTEL_NAME + " in ('" + hotelNameIp + "' ,'RANGER','WILLIAMS','SHARD','LIBERTY') and " + COL_ROOM_NUM + " like '%" + roomNum + "%'";
        String searchRoom = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_HOTEL_NAME + " = '" + hotelNameIp + "' and " + COL_ROOM_NUM + " like '%" + roomNum + "%'";
        try {
            Cursor c = sqldb.rawQuery(searchRoom, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    String roomId = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID));
                    String roomNumber = c.getString(c.getColumnIndex(COL_ROOM_NUM));
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
                    String floorNum = c.getString(c.getColumnIndex(COL_FLOOR_NUM));
                    String price = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));

                    HotelRoom hotelRoom = new HotelRoom(roomId, hotelName, roomNumber, roomType, floorNum, price,
                            null, null, null
                            , null, startDate, endDate, startTime);
                    roomList.add(hotelRoom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public Reservation getResvDates(String hotelName, String startDate, String roomId) {
        Reservation hotelResv = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String searchResvDates = "Select * from " + TABLE_RESERV_DATA
                + " where " + COL_RESERV_HOTEL_NAME + " = '" + hotelName
                + "' and " + COL_RESERV_ROOM_ID + " = '" + roomId + "' and "
                + COL_CHECKIN_DATE + " <= '" + startDate
                + "' and " + COL_CHECKOUT_DATE + " >= '" + startDate + "'";
        Log.i(APP_TAG, "search Resv Dates exist Query : " + searchResvDates);
        try {
            Cursor c = sqldb.rawQuery(searchResvDates, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    String resvCheckInDate = c.getString(c.getColumnIndex(COL_CHECKIN_DATE));
                    String resvCheckOutDate = c.getString(c.getColumnIndex(COL_CHECKOUT_DATE));
                    String resvId = c.getString(c.getColumnIndex(COL_RESERV_ID));
                    String resvPrice = c.getString(c.getColumnIndex(COL_TOTAL_PRICE));
                    String resvTime = c.getString(c.getColumnIndex(COL_START_TIME));
                    String resvRoomId = c.getString(c.getColumnIndex(COL_RESERV_ROOM_ID));
                    String resvHotelName = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));

                    hotelResv = new Reservation(resvId, resvRoomId, resvRoomId, null,
                            null, null, resvHotelName, null,
                            null, null, null,
                            resvPrice, resvCheckInDate, resvCheckOutDate, resvTime, startDate);

                    Log.i(APP_TAG, "hotelResv: " + hotelResv.toString() + " "
                            + hotelResv.getResvCheckInDate() + " " + hotelResv.getResvRoomId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelResv;
    }

    public HotelRoom mgrGetRoomDetails(String roomId, String startDate, String endDate, String startTime, String occupiedStatus) {

        HotelRoom hotelRoom = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();

        String searchRoom = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_HOTEL_ROOM_ID + " = '" + roomId + "'";
        try {
            Cursor c = sqldb.rawQuery(searchRoom, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
                return hotelRoom;
            } else {
                while (c.moveToNext()) {
                    String hotelRoomId = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID));
                    String roomNumber = c.getString(c.getColumnIndex(COL_ROOM_NUM));
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
                    String floorNum = c.getString(c.getColumnIndex(COL_FLOOR_NUM));
                    String price = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
                    String priceWeekend = c.getString(c.getColumnIndex(COL_PRICE_WEEKEND));
                    String hotelTax = c.getString(c.getColumnIndex(COL_TAX));
                    String availabilityStatus = c.getString(c.getColumnIndex(COL_AVAILABILITY_STATUS));

                    hotelRoom = new HotelRoom(hotelRoomId, hotelName, roomNumber, roomType, floorNum,
                            price, priceWeekend, hotelTax, availabilityStatus,
                            occupiedStatus, startDate, endDate, startTime);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelRoom;
    }

    public boolean mgrUpdateRoomDetails(HotelRoom hotelRoom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_HOTEL_NAME, hotelRoom.getHotelName());
        cv.put(COL_HOTEL_ROOM_ID, hotelRoom.getHotelRoomId());
        cv.put(COL_FLOOR_NUM, hotelRoom.getFloorNum());
        cv.put(COL_ROOM_NUM, hotelRoom.getRoomNum());
        cv.put(COL_ROOM_TYPE, hotelRoom.getRoomType());
        cv.put(COL_PRICE_WEEKEND, hotelRoom.getRoomPriceWeekend());
        cv.put(COL_PRICE_WEEKDAY, hotelRoom.getRoomPriceWeekDay());
        cv.put(COL_TAX, hotelRoom.getHotelTax());
        cv.put(COL_AVAILABILITY_STATUS, hotelRoom.getAvailabilityStatus());
        int update = db.update(TABLE_HOTEL_DATA, cv, COL_HOTEL_ROOM_ID + " = '" + hotelRoom.getHotelRoomId() + "'", null);
        return update == 1;
    }

    public ArrayList<HotelRoom> mgrGetAvailableRoomList(String hotelNameIp, String stdRoom, String deluxeRoom, String suiteRoom,
                                                        String startDate, String endDate, String startTime) {
        ArrayList<HotelRoom> roomList = new ArrayList<>();

        SQLiteDatabase sqldb = this.getReadableDatabase();
        String searchAvlblRoom = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_HOTEL_NAME + " = '" + hotelNameIp
                + "' and " + COL_ROOM_TYPE + " in ('" + stdRoom + "', '" + deluxeRoom + "', '" + suiteRoom + "')"
                + " and " + COL_HOTEL_ROOM_ID + " not in ( Select " + COL_RESERV_ROOM_ID + " from " + TABLE_RESERV_DATA + " where "
                + COL_CHECKIN_DATE + " between '" + startDate + "' and '" + endDate + "')";

        //SELECT * From hm_hotel_data WHERE hotel_name = 'MAVERICK' AND hotel_room_id not in
        // (SELECT reservation_room_id FROM hm_reservation_data WHERE check_n_date BETWEEN '2020-08-03'  AND  '2020-08-14');

        Log.i(APP_TAG, "search room Query: " + searchAvlblRoom);
        try {
            Cursor c = sqldb.rawQuery(searchAvlblRoom, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
                return roomList;
            } else {
                while (c.moveToNext()) {
                    String roomId = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID));
                    String roomNumber = c.getString(c.getColumnIndex(COL_ROOM_NUM));
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
                    String floorNum = c.getString(c.getColumnIndex(COL_FLOOR_NUM));
                    String price = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));

                    HotelRoom hotelRoom = new HotelRoom(roomId, hotelName, roomNumber, roomType, floorNum, price,
                            null, null, null
                            , null, startDate, endDate, startTime);
                    roomList.add(hotelRoom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public ArrayList<User> getAdminSearchUser(String lastname) {

        ArrayList<User> userList = new ArrayList<>();
        String userName = null, password = null, role = null, hotelAccess = null, lastName = null, firstName = null, phone = null, email = null;
        String streetAddress = null, city = null, state = null, zipcode = null, ccn = null, ccexp = null, cctype = null;

        SQLiteDatabase sqldb = this.getReadableDatabase();

        String searchUser = " Select * from " + TABLE_USER_DATA + " where " + COL_LAST_NAME + " like '%" + lastname + "%'";

        try {
            Cursor c = sqldb.rawQuery(searchUser, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search user Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    userName = c.getString(c.getColumnIndex(COL_USER_NAME));
                    lastName = c.getString(c.getColumnIndex(COL_LAST_NAME));
                    firstName = c.getString(c.getColumnIndex(COL_FIRST_NAME));
                    phone = c.getString(c.getColumnIndex(COL_PHONE));
                    email = c.getString(c.getColumnIndex(COL_EMAIL));

                    User user = new User(userName, firstName, lastName, password, role, hotelAccess,
                            email, phone, streetAddress, city, state, zipcode, ccn, ccexp, cctype);
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public int deleteUserProfile(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{COL_USER_NAME};
        return db.delete(TABLE_USER_DATA, COL_USER_NAME + " = ?", new String[]{username});
    }

    public int deleteReservation(String reservationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_RESERV_DATA, COL_RESERV_ID + " = ?", new String[]{reservationId});
    }

    public ArrayList<Reservation> mgrGetReservationList(String hotelNameIp,
                                                        String startDate, String startTime) {
        ArrayList<Reservation> resvList = new ArrayList<>();

        Map<String, Integer> resvIdList = new HashMap<>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String searchResv = "Select * from " + TABLE_RESERV_DATA
                + " where " + COL_RESERV_HOTEL_NAME + " = '" + hotelNameIp
                + "' and " + COL_PAYMENT_STATUS + " = 'PAID'"
                + " and " + COL_CHECKIN_DATE + " >= '" + startDate
                + "' and " + COL_START_TIME + " >= '" + startTime + "'";

        Log.i(APP_TAG, "search resv Query: " + searchResv);

        try {
            Cursor c = sqldb.rawQuery(searchResv, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    String resvId = c.getString(c.getColumnIndex(COL_RESERV_ID));
                    String numOfRooms = c.getString(c.getColumnIndex(COL_NUM_OF_ROOMS));
                    String numOfNights = c.getString(c.getColumnIndex(COL_NUM_OF_NIGHTS));
                    String hotelName = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));
                    String checkInDate = c.getString(c.getColumnIndex(COL_CHECKIN_DATE));
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String resvStartTime = c.getString(c.getColumnIndex(COL_START_TIME));
                    if (!resvIdList.containsKey(resvId)) {
                        resvIdList.put(resvId, 1);
                        Reservation reservation = new Reservation(resvId, null, null, null, null, null, hotelName, roomType,
                                null, numOfNights, numOfRooms, null, checkInDate, null, resvStartTime, startDate);
                        resvList.add(reservation);
                    }
                }
                resvIdList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resvList;
    }

    public Reservation mgrGetResvDetails(String hotelName, String startDate, String resvId) {
        Reservation hotelResv = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String searchRoom = "Select * from " + TABLE_RESERV_DATA
                + " where " + COL_RESERV_HOTEL_NAME + " = '" + hotelName
                + "' and " + COL_RESERV_ID + " = '" + resvId + "'";
        Log.i(APP_TAG, "search Dates exist Query : " + searchRoom);
        try {
            Cursor c = sqldb.rawQuery(searchRoom, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    String resvCheckInDate = c.getString(c.getColumnIndex(COL_CHECKIN_DATE));
                    String resvCheckOutDate = c.getString(c.getColumnIndex(COL_CHECKOUT_DATE));
                    String userName = c.getString(c.getColumnIndex(COL_GUEST_USER_NAME));
                    String firstName = c.getString(c.getColumnIndex(COL_GUEST_FIRST_NAME));
                    String lastName = c.getString(c.getColumnIndex(COL_GUEST_LAST_NAME));
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String numAdults = c.getString(c.getColumnIndex(COL_NUM_OF_ADULTS_AND_CHILDREN));
                    String numRooms = c.getString(c.getColumnIndex(COL_NUM_OF_ROOMS));
                    String numNights = c.getString(c.getColumnIndex(COL_NUM_OF_NIGHTS));

                    String reservationId = c.getString(c.getColumnIndex(COL_RESERV_ID));
                    String resvPrice = c.getString(c.getColumnIndex(COL_TOTAL_PRICE));
                    String resvTime = c.getString(c.getColumnIndex(COL_START_TIME));
                    String resvRoomId = c.getString(c.getColumnIndex(COL_RESERV_ROOM_ID));
                    String resvHotelName = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));

                    return new Reservation(reservationId, null, resvRoomId, userName,
                            firstName, lastName, resvHotelName, roomType,
                            numAdults, numNights, numRooms, resvPrice,
                            resvCheckInDate, resvCheckOutDate, resvTime, startDate);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelResv;
    }

    public ArrayList<Reservation> guestGetReservationSummaryList(String userName,
                                                                 String startDate, String startTime) {
        ArrayList<Reservation> resvList = new ArrayList<>();
        Map<String, Integer> resvIdList = new HashMap<>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String searchResv = "Select * from " + TABLE_RESERV_DATA
                + " where " + COL_GUEST_USER_NAME + " = '" + userName
                + "' and " + COL_PAYMENT_STATUS + " = 'PAID'"
                + " and " + COL_CHECKIN_DATE + " >= '" + startDate
                + "' and " + COL_START_TIME + " >= '" + startTime + "'";

        Log.i(APP_TAG, "search resv Query: " + searchResv);

        try {
            Cursor c = sqldb.rawQuery(searchResv, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    String resvId = c.getString(c.getColumnIndex(COL_RESERV_ID));
                    String numOfRooms = c.getString(c.getColumnIndex(COL_NUM_OF_ROOMS));
                    String numOfNights = c.getString(c.getColumnIndex(COL_NUM_OF_NIGHTS));
                    String hotelName = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));
                    String checkInDate = c.getString(c.getColumnIndex(COL_CHECKIN_DATE));
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String resvStartTime = c.getString(c.getColumnIndex(COL_START_TIME));
                    if (!resvIdList.containsKey(resvId)) {
                        resvIdList.put(resvId, 1);
                        Reservation reservation = new Reservation(resvId, null, null, null, null, null, hotelName, roomType,
                                null, numOfNights, numOfRooms, null, checkInDate, null, resvStartTime, startDate);
                        resvList.add(reservation);
                    }
                }
                resvIdList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resvList;
    }
	
	//Ankit changes for request reservation starts

//    SELECT count(*) as room_count , hotel_name,  room_type , room_price_per_night_weekday, room_price_per_night_weekend
//from hm_hotel_data where  available_status ='Yes'  and hotel_name in ('WILLIAMS', 'SHARD', 'RANGER','LIBERTY','MAVERICK' )
//and room_type in ('Standard','Deluxe', 'Suite')
// and hotel_room_id not in
//
//(
//select reservation_room_id from hm_reservation_data where check_in_date >=  '2020-08-04' and check_out_date <= '2020-08-08' and
//start_time >= '10:00' and reservation_hotel_name in ('WILLIAMS', 'SHARD', 'RANGER','LIBERTY','MAVERICK')
//and room_type in ('Standard','Deluxe', 'Suite')  and payment_status  = 'PAID'
//)
//
//group by hotel_name, room_type,
// room_price_per_night_weekday, room_price_per_night_weekend , available_status  order by room_price_per_night_weekday

    public List<HotelRoom> getRoomsAvailableForReqResvAndGrouped(List<String> hotelNameList ,
                    List<String> roomTypesList, String checkInDate,
                    String checkOutDate, String startTime , String numOfRooms) {
        List<HotelRoom> hotelRoomsList = new ArrayList<HotelRoom>();
        SQLiteDatabase sqldb = this.getReadableDatabase();

        StringBuffer searchRoomGroupBy = new StringBuffer("SELECT count(*) as room_count , "+ COL_HOTEL_NAME +" , "+  COL_HOTEL_ROOM_TYPE+ " , " + COL_PRICE_WEEKDAY+ " ," + COL_PRICE_WEEKEND
                +" , "+ COL_AVAILABILITY_STATUS + " , "+ COL_TAX
                +" from  " + TABLE_HOTEL_DATA + " where " + COL_AVAILABILITY_STATUS + " = 'Yes' " );


        searchRoomGroupBy.append(UtilityFunctions.appendHotelNameToStringBuffer(hotelNameList));

        searchRoomGroupBy.append(UtilityFunctions.appendRoomTypesToStringBuffer(roomTypesList));


        searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_ID + " not in ");

        searchRoomGroupBy.append("( select ").append(COL_RESERV_ROOM_ID).append(" from ").append(TABLE_RESERV_DATA). append(" where ").append(COL_CHECKIN_DATE).append(" >= ")
        .append("'").append(checkInDate).append("'").append(" and ").append(COL_CHECKOUT_DATE).append(" <= ").append("'").append(checkOutDate).append("'").append(" and ")
                .append(COL_START_TIME).append(" >= ").append("'").append(startTime).append("'");

        searchRoomGroupBy.append(UtilityFunctions.appendHotelNameToStringBuffer(hotelNameList));

        searchRoomGroupBy.append(UtilityFunctions.appendRoomTypesToStringBuffer(roomTypesList));

        searchRoomGroupBy.append(" )");
        searchRoomGroupBy.append(" group by "+ COL_HOTEL_NAME +" , "+ COL_HOTEL_ROOM_TYPE + " , "+ COL_PRICE_WEEKDAY + " , "+ COL_PRICE_WEEKEND + " , " + COL_AVAILABILITY_STATUS)
        .append(" order by ").append(COL_PRICE_WEEKDAY);
//        Log.i("DBMgr L 634", "0809 630  "+ searchRoomGroupBy.toString());

        HotelRoom hotelRoom =null;
        int countOfavailableRooms=-1;
        String roomType ,  hotelName , roomPricePerNightWeekday ,  roomPricePerNightWeekend, availabilityStatus="" , tax ="";
        try {
            Cursor c = sqldb.rawQuery(searchRoomGroupBy.toString(), null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "0809 L642 searchRoomGroupBy Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    countOfavailableRooms = c.getInt(c.getColumnIndex("room_count"));
                    if(countOfavailableRooms >= Integer.parseInt(numOfRooms)){
                        roomType = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_TYPE));
                        hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
                        roomPricePerNightWeekday = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
                        roomPricePerNightWeekend = c.getString(c.getColumnIndex(COL_PRICE_WEEKEND));
                        availabilityStatus = c.getString(c.getColumnIndex(COL_AVAILABILITY_STATUS));
                        tax = c.getString(c.getColumnIndex(COL_TAX));
                        hotelRoom = new HotelRoom(null, hotelName, null, roomType, null, roomPricePerNightWeekday,
                                roomPricePerNightWeekend, tax, availabilityStatus
                                , null, null, null, null);
                        hotelRoom.setCountOfavailableRooms(new Integer(countOfavailableRooms));
                        hotelRoom.setNumOfRooms(numOfRooms);
                        hotelRoom.setStartDate(checkInDate);
//                    hotelRoom.setCheckInDate(checkInDate);
                        Log.i("0809 L659","checking hotel name attribute not coming "+  hotelName.toString());
                        hotelRoomsList.add(hotelRoom);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("0809 L658", "roomlist.size ="+ hotelRoomsList.size());
        return hotelRoomsList;
    }

    public List<HotelRoom> getRoomsForReqResv(String  selectedHotelName ,
                                                                 String selectedRoomType, String checkInDate,
                                                                 String checkOutDate, String startTime ) {
        List<HotelRoom> hotelRoomsList = new ArrayList<HotelRoom>();
        SQLiteDatabase sqldb = this.getReadableDatabase();

        StringBuffer searchRoomGroupBy = new StringBuffer("SELECT * from  " + TABLE_HOTEL_DATA + " where " + COL_AVAILABILITY_STATUS + " = 'Yes' " );


        searchRoomGroupBy.append(" and "+ COL_HOTEL_NAME +" = "+ "'"+selectedHotelName+"'");
        searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_TYPE +" = "+ "'"+ selectedRoomType+"'");

        searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_ID + " not in ");

        searchRoomGroupBy.append("( select ").append(COL_RESERV_ROOM_ID).append(" from ").append(TABLE_RESERV_DATA). append(" where ").append(COL_CHECKIN_DATE).append(" >= ")
                .append("'").append(checkInDate).append("'").append(" and ").append(COL_CHECKOUT_DATE).append(" <= ").append("'").append(checkOutDate).append("'").append(" and ")
                .append(COL_START_TIME).append(" >= ").append("'").append(startTime).append("'");

        searchRoomGroupBy.append(" and "+  COL_RESERV_HOTEL_NAME+" = "+ "'"+selectedHotelName+"'");

        searchRoomGroupBy.append(" and "+  COL_ROOM_TYPE +" = "+ "'"+ selectedRoomType+"'");

        searchRoomGroupBy.append(" )");
        searchRoomGroupBy.append(" order by ").append(COL_ROOM_NUM);
        Log.i("DBMgr L 695", "0809 695 query 1 for reserve "+ searchRoomGroupBy.toString());

        HotelRoom hotelRoom =null;

        String roomType ,  hotelName ,roomNum,  hotel_room_id ,  floorNum, roomWkDayPricePerNight, roomWkEndPricePerNight, availableStatus, tax;
        try {
            Cursor c = sqldb.rawQuery(searchRoomGroupBy.toString(), null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "0809 L642 searchRoomGroupBy Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
                    roomNum = c.getString(c.getColumnIndex(COL_ROOM_NUM));
                    hotel_room_id = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID));
                    roomType = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_TYPE));
                    floorNum = c.getString(c.getColumnIndex(COL_FLOOR_NUM));
                    roomWkDayPricePerNight = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
                    roomWkEndPricePerNight = c.getString(c.getColumnIndex(COL_PRICE_WEEKEND));
                    availableStatus = c.getString(c.getColumnIndex(COL_AVAILABILITY_STATUS));
                    tax = c.getString(c.getColumnIndex(COL_TAX));
                    hotelRoom = new HotelRoom(hotel_room_id, hotelName, roomNum, roomType, floorNum, roomWkDayPricePerNight,
                            roomWkEndPricePerNight, tax, availableStatus
                            , null, null, null, null);
                    hotelRoom.setStartDate(checkInDate);
                    hotelRoom.setEndDate(checkOutDate);
                    hotelRoom.setStartTime(startTime);
                    hotelRoomsList.add(hotelRoom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("0809 727", "roomlist.size ="+ hotelRoomsList.size());
        return hotelRoomsList;
    }

//    public List<HotelRoom> getRoomsAvailableForReqResvAndGrouped2( List<String> hotelNameList , List<String> roomTypesList) {
//    List<HotelRoom> hotelRoomsList = new ArrayList<HotelRoom>();
//        SQLiteDatabase sqldb = this.getReadableDatabase();
//        //select count(*) as room_count, room_type, hotel_name from  hm_hotel_data where available_status='y' group by room_type, hotel_name
//        StringBuffer searchRoomGroupBy = new StringBuffer("select count(*) as room_count, "+ COL_HOTEL_ROOM_TYPE +" ,"+ COL_HOTEL_NAME + " ," + COL_PRICE_WEEKDAY+ " ," + COL_PRICE_WEEKEND
//                +" from  " + TABLE_HOTEL_DATA + " where " + COL_AVAILABILITY_STATUS + " = 'Yes' " );
//
//
//        if(roomTypesList!= null && !roomTypesList.isEmpty()){
//            int size = roomTypesList.size();
//            searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_TYPE + "in (");
//            for(int i=0; i < size; i++){
//                searchRoomGroupBy.append("'").append(roomTypesList.get(i)).append("' ");
//                if(i  < (size -1)){
//                    searchRoomGroupBy.append(",");
//                }
//            }
//            searchRoomGroupBy.append(")");
//        }
//
//        if(hotelNameList!= null && !hotelNameList.isEmpty()){
//            int size = hotelNameList.size();
//            searchRoomGroupBy.append(" and "+ COL_HOTEL_NAME + "in (");
//            for(int i=0; i< size; i++){
//                searchRoomGroupBy.append("'").append(hotelNameList.get(i)).append("' ");
//                if(i  < (size -1)){
//                    searchRoomGroupBy.append(",");
//                }
//            }
//            searchRoomGroupBy.append(")");
//        }
//
//        searchRoomGroupBy.append(" group by "+ COL_HOTEL_ROOM_TYPE+" , "+ TABLE_HOTEL_DATA);
//        Log.i("DBMgr L 315", "0809 searchRoomGroupBy "+ searchRoomGroupBy.toString());
//
//        HotelRoom hotelRoom =null;
//        int roomCount=-1;
//        String roomType ,  hotelName , roomPricePerNightWeekday ,  roomPricePerNightWeekend, availabilityStatus="";
//        try {
//            Cursor c = sqldb.rawQuery(searchRoomGroupBy.toString(), null);
//            if (c.getCount() == 0) {
//                Log.e(APP_TAG, "0809 searchRoomGroupBy Query Err: No data");
//            } else {
//                while (c.moveToNext()) {
//                     roomCount = c.getInt(c.getColumnIndex("room_count"));
//                     roomType = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_TYPE));
//                     hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
//                     roomPricePerNightWeekday = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
//                     roomPricePerNightWeekend = c.getString(c.getColumnIndex(COL_PRICE_WEEKEND));
//                     availabilityStatus = c.getString(c.getColumnIndex(COL_AVAILABILITY_STATUS));
//                    hotelRoom = new HotelRoom(null, hotelName, null, roomType, null, roomPricePerNightWeekday,
//                            roomPricePerNightWeekend, null, availabilityStatus
//                            , null, null, null, null);
//                    hotelRoom.setCountOfavailableRooms(roomCount);
//                    hotelRoomsList.add(hotelRoom);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return hotelRoomsList;
//    }

//    public List<HotelRoom> getAllAvailableRoomsForReqReservation( List<String> hotelNameList , List<String> roomTypesList ) {
//        List<HotelRoom> hotelRoomsList = new ArrayList<HotelRoom>();
//        SQLiteDatabase sqldb = this.getReadableDatabase();
//        //select count(*) as room_count, room_type, hotel_name from  hm_hotel_data where available_status='y' group by room_type, hotel_name
//        StringBuffer searchRoomGroupBy = new StringBuffer("select * from "+ TABLE_HOTEL_DATA + " where "+  COL_AVAILABILITY_STATUS + " = 'Yes' " );
//
//        if(roomTypesList!= null && !roomTypesList.isEmpty()){
//            int size = roomTypesList.size();
//            searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_TYPE + " in ( ");
//            for(int i=0; i < size; i++){
//                searchRoomGroupBy.append("'").append(roomTypesList.get(i)).append("' ");
//                if(i  < (size -1)){
//                    searchRoomGroupBy.append(",");
//                }
//            }
//            searchRoomGroupBy.append(" ) ");
//        }
//
//        if(hotelNameList!= null && !hotelNameList.isEmpty()){
//            int size = hotelNameList.size();
//            searchRoomGroupBy.append(" and "+ COL_HOTEL_NAME + " in ( ");
//            for(int i=0; i< size; i++){
//                searchRoomGroupBy.append("'").append(hotelNameList.get(i)).append("' ");
//                if(i  < (size -1)){
//                    searchRoomGroupBy.append(",");
//                }
//            }
//            searchRoomGroupBy.append(")");
//        }
//        searchRoomGroupBy.append(" order by "+ COL_HOTEL_ROOM_ID);
//        Log.i("DBMgr L374", "0809 searchRoomGroupBy :"+ searchRoomGroupBy.toString());
//
//        HotelRoom hotelRoom =null;
//
//        String roomType ,  hotelName , roomPricePerNightWeekday ,  roomPricePerNightWeekend, availabilityStatus="";
//        try {
//            Cursor c = sqldb.rawQuery(searchRoomGroupBy.toString(), null);
//            if (c.getCount() == 0) {
//                Log.e(APP_TAG, "searchRoomGroupBy Query Err: No data");
//            } else {
//                while (c.moveToNext()) {
//                    hotelRoom = new HotelRoom();
//                    hotelRoom.setHotelName(c.getString(c.getColumnIndex(COL_HOTEL_NAME)));
//                    hotelRoom.setRoomNum(c.getString(c.getColumnIndex(COL_ROOM_NUM)));
//                    hotelRoom.setHotelRoomId(c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID)));
//                    hotelRoom.setRoomPriceWeekDay(c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY)));
//                    hotelRoom.setRoomPriceWeekend(c.getString(c.getColumnIndex(COL_PRICE_WEEKEND)));
//                    hotelRoom.setRoomType(c.getString(c.getColumnIndex(COL_ROOM_TYPE)));
//                    hotelRoom.setAvailabilityStatus(c.getString(c.getColumnIndex(COL_AVAILABILITY_STATUS)));
//                    hotelRoom.setFloorNum(c.getString(c.getColumnIndex(COL_FLOOR_NUM)));
//                    hotelRoom.setHotelTax(c.getString(c.getColumnIndex(COL_TAX)));
////                    hotelRoom.setNumOfRooms(numOfRooms);
//                    hotelRoomsList.add(hotelRoom);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Log.i("0809 L 403 ", "0809 hotelRoomsList size "+ hotelRoomsList.size());
//        return hotelRoomsList;
//    }
//
//    public List<Reservation> getReservedRoomsForStartAndEndDate( List<String> hotelNameList , List<String> roomTypesList , String checkInDate, String checkOutDate, String startTime, String numOfRooms) {
//        List<Reservation> reservationsList = new ArrayList<Reservation>();
//        SQLiteDatabase sqldb = this.getReadableDatabase();
//        //select count(*) as room_count, room_type, hotel_name from  hm_hotel_data where available_status='y' group by room_type, hotel_name
//        StringBuffer searchReservations = new StringBuffer("select * from "+ TABLE_RESERV_DATA + " where " + COL_CHECKIN_DATE + " >= '" + checkInDate+ "'"  + "and "
//        + COL_CHECKOUT_DATE + " <= '" + checkOutDate + "'");
//        if(roomTypesList!= null && !roomTypesList.isEmpty()){
//            int size = roomTypesList.size();
//            searchReservations.append(" and "+ COL_HOTEL_ROOM_TYPE + " in (");
//            for(int i=0; i < size; i++){
//                searchReservations.append("'").append(roomTypesList.get(i)).append("' ");
//                if(i  < (size -1)){
//                    searchReservations.append(",");
//                }
//            }
//            searchReservations.append(")");
//        }
//
//        if(hotelNameList!= null && !hotelNameList.isEmpty()){
//            int size = hotelNameList.size();
//            searchReservations.append(" and "+ COL_RESERV_HOTEL_NAME + " in (");
//            for(int i=0; i< size; i++){
//                searchReservations.append("'").append(hotelNameList.get(i)).append("' ");
//                if(i  < (size -1)){
//                    searchReservations.append(",");
//                }
//            }
//            searchReservations.append(")");
//        }
//        searchReservations.append(" order by "+ COL_CHECKIN_DATE + "," + COL_START_TIME);
//
//        Log.i("DBMgr L 436", " 0809 searchReservations:"+ searchReservations.toString());
//
//        Reservation reservation =null;
//        String roomType ,  hotelName , roomPricePerNightWeekday ,  roomPricePerNightWeekend, availabilityStatus="";
//        try {
//            Cursor c = sqldb.rawQuery(searchReservations.toString(), null);
//            if (c.getCount() == 0) {
//                Log.e(APP_TAG, "searchRoomGroupBy Query Err: No data");
//            } else {
//                while (c.moveToNext()) {
//                    reservation.setReservationId(c.getString(c.getColumnIndex("COL_RESERV_ID")));
//                    reservation = new Reservation();
//                    reservation.setResvUserName(c.getString(c.getColumnIndex("COL_GUEST_USER_NAME")));
//                    reservation.setResvFirstName(c.getString(c.getColumnIndex("COL_GUEST_FIRST_NAME")));
//                    reservation.setResvLastName(c.getString(c.getColumnIndex("COL_GUEST_LAST_NAME")));
//
//                    reservation.setResvHotelName(c.getString(c.getColumnIndex("COL_RESERV_HOTEL_NAME")));
//                    reservation.setResvRoomType(c.getString(c.getColumnIndex("COL_ROOM_TYPE")));
//                    reservation.setResvNumOfRooms(c.getString(c.getColumnIndex("COL_NUM_OF_ROOMS")));
//                    reservation.setTotalPrice(c.getString(c.getColumnIndex("COL_TOTAL_PRICE")));
//                    reservation.setResvCheckInDate(c.getString(c.getColumnIndex("COL_CHECKIN_DATE")));
//                    reservation.setResevCheckOutDate(c.getString(c.getColumnIndex("COL_CHECKOUT_DATE")));
//                    reservation.setResvStartTime(c.getString(c.getColumnIndex("COL_START_TIME")));
//                    reservation.setResvNumAdultsChildren(c.getString(c.getColumnIndex("COL_NUM_OF_ADULTS_AND_CHILDREN")));
//                    reservation.setResvNumNights(c.getString(c.getColumnIndex("COL_NUM_OF_NIGHTS")));
//
//                    reservationsList.add(reservation);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Log.i("0809 L 469 ", "0809 reservationsList size "+ reservationsList.size());
//        return reservationsList;
//    }
    //Ankit changes for request reservation ends


}
