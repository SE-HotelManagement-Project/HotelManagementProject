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
import static com.project.hotelmanagementproject.utilities.ConstantUtils.PAID;
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
        cv.put(COL_PAYMENT_STATUS, reservation.getResvPaymentStatus());
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
                    String resvPaymentStatus = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));

                    hotelResv = new Reservation(resvId, resvRoomId, null, resvHotelName, null,
                            null, null, null,
                            resvPrice, resvCheckInDate, resvCheckOutDate, resvTime, startDate, resvPaymentStatus);

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
                    String resvPaymentStatus = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));
                    if (!resvIdList.containsKey(resvId)) {
                        resvIdList.put(resvId, 1);
                        Reservation reservation = new Reservation(resvId, null, null, hotelName, roomType,
                                null, numOfNights, numOfRooms, null, checkInDate, null, resvStartTime, startDate, resvPaymentStatus);
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
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String numAdults = c.getString(c.getColumnIndex(COL_NUM_OF_ADULTS_AND_CHILDREN));
                    String numRooms = c.getString(c.getColumnIndex(COL_NUM_OF_ROOMS));
                    String numNights = c.getString(c.getColumnIndex(COL_NUM_OF_NIGHTS));

                    String reservationId = c.getString(c.getColumnIndex(COL_RESERV_ID));
                    String resvPrice = c.getString(c.getColumnIndex(COL_TOTAL_PRICE));
                    String resvTime = c.getString(c.getColumnIndex(COL_START_TIME));
                    String resvRoomId = c.getString(c.getColumnIndex(COL_RESERV_ROOM_ID));
                    String resvHotelName = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));
                    String resvPaymentStatus = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));

                    return new Reservation(reservationId, resvRoomId, userName, resvHotelName, roomType,
                            numAdults, numNights, numRooms, resvPrice,
                            resvCheckInDate, resvCheckOutDate, resvTime, startDate, resvPaymentStatus);

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
                    String resvPaymentStatus = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));
                    if (!resvIdList.containsKey(resvId)) {
                        resvIdList.put(resvId, 1);
                        Reservation reservation = new Reservation(resvId, null, null, hotelName, roomType,
                                null, numOfNights, numOfRooms, null, checkInDate, null, resvStartTime, startDate, resvPaymentStatus);
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
    public Reservation guestGetResvDetails(String resvId, String startDate) {
        Reservation hotelResv = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String searchRoom = "Select * from " + TABLE_RESERV_DATA
                + " where " + COL_RESERV_ID + " = '" + resvId + "'";

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
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String numAdults = c.getString(c.getColumnIndex(COL_NUM_OF_ADULTS_AND_CHILDREN));
                    String numRooms = c.getString(c.getColumnIndex(COL_NUM_OF_ROOMS));
                    String numNights = c.getString(c.getColumnIndex(COL_NUM_OF_NIGHTS));

                    String reservationId = c.getString(c.getColumnIndex(COL_RESERV_ID));
                    String resvPrice = c.getString(c.getColumnIndex(COL_TOTAL_PRICE));
                    String resvTime = c.getString(c.getColumnIndex(COL_START_TIME));
                    String resvRoomId = c.getString(c.getColumnIndex(COL_RESERV_ROOM_ID));
                    String resvHotelName = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));
                    String resvPaymentStatus = c.getString(c.getColumnIndex(COL_RESERV_HOTEL_NAME));

                    return new Reservation(reservationId, resvRoomId, userName,
                            resvHotelName, roomType,
                            numAdults, numNights, numRooms, resvPrice,
                            resvCheckInDate, resvCheckOutDate, resvTime, startDate, resvPaymentStatus);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelResv;
    }

    public List<HotelRoom> getRoomsAvailableForReqResvAndGrouped(List<String> hotelNameList,
                                                                 List<String> roomTypesList, String checkInDate,
                                                                 String checkOutDate, String startTime, String numOfRooms) {
        List<HotelRoom> hotelRoomsList = new ArrayList<HotelRoom>();
        SQLiteDatabase sqldb = this.getReadableDatabase();

        String query1 = "SELECT count(*) as room_count , hotel_name , room_type , room_price_per_night_weekday , " +
                "room_price_per_night_weekend , available_status , " +
                "tax from  hm_hotel_data where available_status = 'Yes'  ";
        query1 = query1 + UtilityFunctions.appendStringWithListAndCol(hotelNameList, COL_HOTEL_NAME);
        query1 = query1 + UtilityFunctions.appendStringWithListAndCol(roomTypesList, COL_ROOM_TYPE);

        String query2 = " and hotel_room_id not in ( select reservation_room_id from hm_reservation_data where ";
//        String query3 = " ( (check_in_date between"+ " '"+checkInDate+"' "+" and  +'"+ checkOutDate + "' ) or (check_out_date between '" +checkInDate +"' and  '"+checkOutDate+"') ) ";
        String query3 = "(((check_in_date between '" + checkInDate + "'  and  '" + checkOutDate + "' ) and (check_out_date between '" + checkInDate + "' and  '" + checkOutDate + "')) or (check_in_date <= '" + checkInDate + "'  and   (check_out_date  between '" + checkInDate + "'  and  '" + checkOutDate + "' )) or  ((check_in_date between '" + checkInDate + "'  and  '" + checkOutDate + "' ) and  check_out_date  >='" + checkOutDate + "') or ((check_in_date <='" + checkInDate + "'  ) and  check_out_date  >='" + checkOutDate + "') )";
        query3 = query3 + UtilityFunctions.appendStringWithListAndCol(hotelNameList, COL_RESERV_HOTEL_NAME);

        query3 = query3 + UtilityFunctions.appendStringWithListAndCol(roomTypesList, COL_ROOM_TYPE);
        String query4 = "  ) group by hotel_name , " +
                "room_type , room_price_per_night_weekday , room_price_per_night_weekend , available_status order by room_price_per_night_weekday";

        String searchRoomGroupBy = query1 + query2 + query3 + query4;
        Log.i("0809L627", "searchRoomGroupBy" + searchRoomGroupBy);

//        StringBuffer searchRoomGroupBy = new StringBuffer("SELECT count(*) as room_count , "+ COL_HOTEL_NAME +" , "+  COL_HOTEL_ROOM_TYPE+ " , " + COL_PRICE_WEEKDAY+ " ," + COL_PRICE_WEEKEND
//                +" , "+ COL_AVAILABILITY_STATUS + " , "+ COL_TAX
//                +" from  " + TABLE_HOTEL_DATA + " where " + COL_AVAILABILITY_STATUS + " = 'Yes' " );
//
//
//        searchRoomGroupBy.append(UtilityFunctions.appendHotelNameToStringBuffer(hotelNameList));
//
//        if(!ConstantUtils.EMPTY.equals(UtilityFunctions.appendRoomTypesToStringBuffer(roomTypesList).toString())){
//            searchRoomGroupBy.append(UtilityFunctions.appendRoomTypesToStringBuffer(roomTypesList));
//        }
//        else{
//            searchRoomGroupBy.append(UtilityFunctions.appendRoomTypesToStringBufferForEmpty(roomTypesList));
//        }
//
//        searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_ID + " not in ");
//
//        searchRoomGroupBy.append("( select ").append(COL_RESERV_ROOM_ID).append(" from ").append(TABLE_RESERV_DATA). append(" where ")
//                .append(" ( ").append(COL_CHECKIN_DATE).append(" >= ").append("'").append(checkInDate).append("'").append( " and ").append(COL_CHECKIN_DATE).append( " <= ").append("'").append(checkOutDate).append("'")
//                .append(" or ").append(COL_CHECKOUT_DATE).append(" >= ").append("'").append(checkInDate).append("'").append( " and ").append(COL_CHECKOUT_DATE).append( " <= ").append("'").append(checkOutDate).append("'").append(" ) ");
//
//        searchRoomGroupBy.append(UtilityFunctions.appendHotelNameToStringBuffer(hotelNameList));
//
////        searchRoomGroupBy.append(UtilityFunctions.appendRoomTypesToStringBuffer(roomTypesList));
//
//        if(!ConstantUtils.EMPTY.equals(UtilityFunctions.appendRoomTypesToStringBuffer(roomTypesList).toString())){
//            searchRoomGroupBy.append(UtilityFunctions.appendRoomTypesToStringBuffer(roomTypesList));
//        }
//        else{
//            searchRoomGroupBy.append(UtilityFunctions.appendRoomTypesToStringBufferForEmpty(roomTypesList));
//        }
//        searchRoomGroupBy.append(" )");
//        searchRoomGroupBy.append(" group by "+ COL_HOTEL_NAME +" , "+ COL_HOTEL_ROOM_TYPE + " , "+ COL_PRICE_WEEKDAY + " , "+ COL_PRICE_WEEKEND + " , " + COL_AVAILABILITY_STATUS)
//        .append(" order by ").append(COL_PRICE_WEEKDAY);
//        Log.i("DBMgr L 634", "0809630DBMgrL634  "+ searchRoomGroupBy.toString());
//        Log.i("DBMgr L 634", "0809630DBMgrL634  "+ searchRoomGroupBy.toString());
        HotelRoom hotelRoom = null;
        int countOfavailableRooms = -1;
        String roomType, hotelName, roomPricePerNightWeekday, roomPricePerNightWeekend, availabilityStatus = "", tax = "";
        try {
            Cursor c = sqldb.rawQuery(searchRoomGroupBy, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "searchRoomGroupBy Query Err: No data");
            } else {
                while (c.moveToNext()) {
                    countOfavailableRooms = c.getInt(c.getColumnIndex("room_count"));
                    if (countOfavailableRooms >= Integer.parseInt(numOfRooms)) {
                        roomType = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_TYPE));
                        hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
                        roomPricePerNightWeekday = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
                        roomPricePerNightWeekend = c.getString(c.getColumnIndex(COL_PRICE_WEEKEND));
                        availabilityStatus = c.getString(c.getColumnIndex(COL_AVAILABILITY_STATUS));
                        tax = c.getString(c.getColumnIndex(COL_TAX));
                        hotelRoom = new HotelRoom(null, hotelName, null, roomType, null,
                                roomPricePerNightWeekday, roomPricePerNightWeekend, tax, availabilityStatus
                                , null, null, null, null);
                        hotelRoom.setCountOfavailableRooms(new Integer(countOfavailableRooms));
                        hotelRoom.setNumOfRooms(numOfRooms);
                        hotelRoom.setStartDate(checkInDate);

                        hotelRoomsList.add(hotelRoom);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("0809L658", "0809L658roomlist.size =" + hotelRoomsList.size());
        return hotelRoomsList;
    }

    public List<HotelRoom> getRoomsForReqResv(String selectedHotelName,
                                              String selectedRoomType, String checkInDate,
                                              String checkOutDate, String startTime) {
        List<HotelRoom> hotelRoomsList = new ArrayList<HotelRoom>();
        SQLiteDatabase sqldb = this.getReadableDatabase();

//        StringBuffer searchRoomGroupBy = new StringBuffer("SELECT * from  " + TABLE_HOTEL_DATA + " where " + COL_AVAILABILITY_STATUS + " = 'Yes' " );
//
//
//        searchRoomGroupBy.append(" and "+ COL_HOTEL_NAME +" = "+ "'"+selectedHotelName+"'");
//        searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_TYPE +" = "+ "'"+ selectedRoomType+"'");
//
//        searchRoomGroupBy.append(" and "+ COL_HOTEL_ROOM_ID + " not in ");
//
//        searchRoomGroupBy.append("( select ").append(COL_RESERV_ROOM_ID).append(" from ").append(TABLE_RESERV_DATA). append(" where ")
//                .append(" ( ").append(COL_CHECKIN_DATE).append(" >= ").append("'").append(checkInDate).append("'").append( " and ").append(COL_CHECKIN_DATE).append( " <= ").append("'").append(checkOutDate).append("'")
//                .append(" or ").append(COL_CHECKOUT_DATE).append(" >= ").append("'").append(checkInDate).append("'").append( " and ").append(COL_CHECKOUT_DATE).append( " <= ").append("'").append(checkOutDate).append("'").append(" ) ");
//
//        searchRoomGroupBy.append(" and "+  COL_RESERV_HOTEL_NAME+" = "+ "'"+selectedHotelName+"'");
//
//        searchRoomGroupBy.append(" and "+  COL_ROOM_TYPE +" = "+ "'"+ selectedRoomType+"'");
//
//        searchRoomGroupBy.append(" )");
//        searchRoomGroupBy.append(" order by ").append(COL_ROOM_NUM);
        String query1 = "SELECT * from  hm_hotel_data where available_status = 'Yes'  and " + COL_HOTEL_NAME + " = '" + selectedHotelName + "' and " + COL_ROOM_TYPE + " ='" + selectedRoomType;


        String query2 = "' and hotel_room_id not in ( select reservation_room_id from hm_reservation_data where ";
        String query3 = "(((check_in_date between '" + checkInDate + "'  and  '" + checkOutDate + "' ) and (check_out_date between '" + checkInDate + "' and  '" + checkOutDate + "')) or (check_in_date <= '" + checkInDate + "'  and   (check_out_date  between '" + checkInDate + "'  and  '" + checkOutDate + "' )) or  ((check_in_date between '" + checkInDate + "'  and  '" + checkOutDate + "' ) and  check_out_date  >='" + checkOutDate + "') or ((check_in_date <='" + checkInDate + "'  ) and  check_out_date  >='" + checkOutDate + "') )";
        query3 = query3 + "and " + COL_RESERV_HOTEL_NAME + " = '" + selectedHotelName + "' and " + COL_ROOM_TYPE + " ='" + selectedRoomType;
        String query4 = "'  ) order by " + COL_ROOM_NUM;

        String searchRoomGroupBy = query1 + query2 + query3 + query4;
        Log.i("0809 Dbmgr", "L736" + searchRoomGroupBy);
        HotelRoom hotelRoom = null;


        String roomType, hotelName, roomNum, hotel_room_id, floorNum, roomWkDayPricePerNight, roomWkEndPricePerNight, availableStatus, tax;
        try {
            Cursor c = sqldb.rawQuery(searchRoomGroupBy, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "searchRoomGroupBy Query Err: No data");
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
        Log.i("0809 727", "roomlist.size =" + hotelRoomsList.size());
        return hotelRoomsList;
    }

    public ArrayList<HotelRoom> getGuestPendingResevationList(String userName) {
        ArrayList<HotelRoom> pendingRsvHotelRoom = new ArrayList<HotelRoom>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String qry = "select a.* , b.* from " + TABLE_RESERV_DATA + " as a inner JOIN " + TABLE_HOTEL_DATA + " as b on a.reservation_room_id = b.hotel_room_id \n" +
                " where a.guest_user_name ='" + userName + "' and  a.payment_status ='PENDING' group by a.reservation_id  order by a.check_in_date";

        Log.i(APP_TAG, "search pending reservation Query: " + qry);
        try {
            Cursor c = sqldb.rawQuery(qry, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
                return pendingRsvHotelRoom;
            } else {
                while (c.moveToNext()) {
                    String startDate = c.getString(c.getColumnIndex(COL_CHECKIN_DATE));
                    String roomNumber = c.getString(c.getColumnIndex(COL_ROOM_NUM));
                    String roomType = c.getString(c.getColumnIndex(COL_ROOM_TYPE));
                    String hotelName = c.getString(c.getColumnIndex(COL_HOTEL_NAME));
                    String floorNum = c.getString(c.getColumnIndex(COL_FLOOR_NUM));
                    String price = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
                    String priceWeekend = c.getString(c.getColumnIndex(COL_PRICE_WEEKEND));
                    String numOfRooms = c.getString(c.getColumnIndex(COL_NUM_OF_ROOMS));
                    String endDate = c.getString(c.getColumnIndex(COL_CHECKOUT_DATE));
                    String numOfAdultsChildren = c.getString(c.getColumnIndex(COL_NUM_OF_ADULTS_AND_CHILDREN));
                    String startTime = c.getString(c.getColumnIndex(COL_START_TIME));
                    String hotelTax = c.getString(c.getColumnIndex(COL_TAX));

                    HotelRoom hotelRoom = new HotelRoom(null, hotelName, roomNumber, roomType, floorNum, price, priceWeekend, hotelTax, null
                            , null, startDate, endDate, startTime);
                    hotelRoom.setNumOfRooms(numOfRooms);
                    hotelRoom.setNumOfAdultChildren(numOfAdultsChildren);
                    pendingRsvHotelRoom.add(hotelRoom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pendingRsvHotelRoom;
    }

    public HotelRoom getRoomTypePrices(String roomType, String hotelName) {
        HotelRoom hotelRoom = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();

        String searchRoom = "Select * from " + TABLE_HOTEL_DATA + " where " + COL_HOTEL_NAME + " = '" + hotelName
                + "' and " + COL_ROOM_TYPE + " = '" + roomType + "'";

        try {
            Cursor c = sqldb.rawQuery(searchRoom, null);
            if (c.getCount() == 0) {
                Log.e(APP_TAG, "search room Query Err: No data");
                return hotelRoom;
            } else {
                while (c.moveToNext()) {
                    String hotelRoomId = c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID));
                    String roomNumber = c.getString(c.getColumnIndex(COL_ROOM_NUM));
                    String floorNum = c.getString(c.getColumnIndex(COL_FLOOR_NUM));
                    String price = c.getString(c.getColumnIndex(COL_PRICE_WEEKDAY));
                    String priceWeekend = c.getString(c.getColumnIndex(COL_PRICE_WEEKEND));
                    String hotelTax = c.getString(c.getColumnIndex(COL_TAX));
                    String availabilityStatus = c.getString(c.getColumnIndex(COL_AVAILABILITY_STATUS));

                    hotelRoom = new HotelRoom(null, hotelName, null, roomType, null,
                            price, priceWeekend, hotelTax, availabilityStatus,
                            null, null, null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelRoom;
    }

    public boolean guestUpdateResvWithNoNewAddition(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_RESERV_HOTEL_NAME, reservation.getResvHotelName());
        cv.put(COL_RESERV_ID, reservation.getReservationId());
        cv.put(COL_GUEST_USER_NAME, reservation.getResvUserName());
        cv.put(COL_NUM_OF_NIGHTS, reservation.getResvNumNights());
        cv.put(COL_NUM_OF_ADULTS_AND_CHILDREN, reservation.getResvNumAdultsChildren());
        cv.put(COL_TOTAL_PRICE, reservation.getTotalPrice());
        cv.put(COL_CHECKIN_DATE, reservation.getResvCheckInDate());
        cv.put(COL_CHECKOUT_DATE, reservation.getResevCheckOutDate());
        cv.put(COL_START_TIME, reservation.getResvStartTime());

        int update = db.update(TABLE_RESERV_DATA, cv, COL_RESERV_ID + " = '" + reservation.getReservationId() + "'", null);
        return update == 1;
    }

    public boolean updateResvPaid(String reservationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_PAYMENT_STATUS, PAID);

        int update = db.update(TABLE_RESERV_DATA, cv, COL_RESERV_ID + " = '" + reservationId + "'", null);

        return update == 1;
    }
}
