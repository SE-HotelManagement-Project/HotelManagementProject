package com.project.hotelmanagementproject.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_HOTEL_ROOM_TYPE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_LAST_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_ADULTS_AND_CHILDREN;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_NIGHTS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_NUM_OF_ROOMS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_OCCUPIED_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PASSWORD;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PHONE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKDAY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PRICE_WEEKEND;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RESERV_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_RESERV_ID;
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

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "HotelManagementProject.db";
    private static DBHelper mInstance = null;
    Context context;

//    public  DBHelper(Context context) {
//        super(context, DB_NAME, null, 1);
//        this.context = context;
//    }

    /**
     * constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    private DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    public static synchronized DBHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTblQry = "create table " + TABLE_USER_DATA + "(" + COL_FIRST_NAME + " text, " +
                COL_LAST_NAME + " text, " + COL_USER_NAME + " text primary key," + COL_PASSWORD + " text,"
                + COL_USER_ROLE + " text," + COL_EMAIL + " text," + COL_PHONE + " text," + COL_STREET_ADDRESS + " text,"
                + COL_CITY + " text," + COL_STATE + " text," + COL_ZIP_CODE + " text," + COL_CREDIT_CARD_NUM + " text, "
                + COL_CREDIT_CARD_EXP + " text," + COL_CARD_TYPE + " text)";
        db.execSQL(userTblQry);

        String reservationTblQry = "create table " + TABLE_RESERV_DATA + "(" + COL_GUEST_FIRST_NAME + " text, " +
                COL_GUEST_USER_NAME + " text, " + COL_RESERV_ID + " text primary key," + COL_GUEST_LAST_NAME + " text,"
                + COL_RESERV_HOTEL_NAME + " text," + COL_ROOM_TYPE + " text," + COL_NUM_OF_ROOMS + " text,"
                + COL_NUM_OF_ADULTS_AND_CHILDREN + " text," + COL_NUM_OF_NIGHTS + " text," + COL_CHECKIN_DATE + " text,"
                + COL_CHECKOUT_DATE + " text, " + COL_START_TIME + " text," + COL_TOTAL_PRICE + " text)";
        db.execSQL(reservationTblQry);

        String hotelTableQry = "create table " + TABLE_HOTEL_DATA + "(" + COL_HOTEL_NAME + " text, " +
                COL_ROOM_NUM + " text, " + COL_HOTEL_ROOM_ID + " text primary key," + COL_PRICE_WEEKDAY + " text,"
                + COL_PRICE_WEEKEND + " text," + COL_HOTEL_ROOM_TYPE + " text," + COL_AVAILABILITY_STATUS + " text,"
                + COL_OCCUPIED_STATUS + " text," + COL_FLOOR_NUM + " text," + COL_TAX + " text)";
        db.execSQL(hotelTableQry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERV_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOTEL_DATA);
        onCreate(db);
    }
}
