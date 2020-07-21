package com.project.hotelmanagementproject.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.project.hotelmanagementproject.utils.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_CARD_TYPE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_CITY;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_CREDIT_CARD_EXP;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_CREDIT_CARD_NUM;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_EMAIL;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_FIRST_NAME;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_LAST_NAME;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_PASSWORD;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_PHONE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_STATE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_STREET_ADDRESS;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_USER_NAME;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_USER_ROLE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_ZIP_CODE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.TABLE_USER_ACCT;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "HotelManagementProject.db";
    Context context;

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table " + TABLE_USER_ACCT + "(" + COL_FIRST_NAME + " text, " +
                COL_LAST_NAME + " text, " + COL_USER_NAME + " text primary key," + COL_PASSWORD + " text,"
                + COL_USER_ROLE + " text," + COL_EMAIL + " text," + COL_PHONE + " text," + COL_STREET_ADDRESS + " text,"
                + COL_CITY + " text," + COL_STATE + " text," + COL_ZIP_CODE + " text," + COL_CREDIT_CARD_NUM + " text, "
                + COL_CREDIT_CARD_EXP + " text," + COL_CARD_TYPE + " text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCT);
        onCreate(db);
    }

    public boolean addNewGuest(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USER_NAME, userModel.getUserName());
        cv.put(COL_PASSWORD, userModel.getPassword());
        cv.put(COL_FIRST_NAME, userModel.getFirstName());
        cv.put(COL_LAST_NAME, userModel.getLastName());
        cv.put(COL_EMAIL, userModel.getEmail());
        cv.put(COL_PHONE, userModel.getPhone());
        cv.put(COL_STREET_ADDRESS, userModel.getStreetAddress());
        cv.put(COL_CITY, userModel.getCity());
        cv.put(COL_STATE, userModel.getState());
        cv.put(COL_ZIP_CODE, userModel.getZipCode());
        cv.put(COL_CREDIT_CARD_NUM, userModel.getCreditCardNum());
        cv.put(COL_CREDIT_CARD_EXP, userModel.getCreditCardExp());
        cv.put(COL_CARD_TYPE, userModel.getCreditCardtype());
        cv.put(COL_USER_ROLE, "Guest");

        long res = db.insert(TABLE_USER_ACCT, null, cv);

//        if(res== -1)
////            return false;
////        else
////            return true;
        return res != -1;
    }

    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{COL_FIRST_NAME, COL_LAST_NAME, COL_USER_NAME, COL_USER_ROLE, COL_EMAIL, COL_PHONE, COL_STREET_ADDRESS, COL_CITY, COL_STATE, COL_ZIP_CODE, COL_CREDIT_CARD_NUM, COL_CREDIT_CARD_EXP, COL_CARD_TYPE};
        Cursor cursor = db.query(TABLE_USER_ACCT, columns, COL_USER_NAME + " = '" + username + "'", null, null, null, null);
        return cursor;
    }

    public Cursor getUserProfile(String username) {
        String role = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_ACCT + " where " + COL_USER_NAME + " = '" + username + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);
        return c;
    }

    public boolean updateProfile(String username, String firstname, String password, String lastname, String phone, String email, String address, String city, String state, String zipCode, String creditCardNum, String cardExpiry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FIRST_NAME, firstname);
        cv.put(COL_PASSWORD, password);
        cv.put(COL_LAST_NAME, lastname);
        cv.put(COL_EMAIL, email);
        cv.put(COL_PHONE, phone);
        cv.put(COL_STREET_ADDRESS, address);
        cv.put(COL_CITY, city);
        cv.put(COL_STATE, state);
        cv.put(COL_ZIP_CODE, zipCode);
        cv.put(COL_CREDIT_CARD_NUM, creditCardNum);
        cv.put(COL_CREDIT_CARD_EXP, cardExpiry);
        int update = db.update(TABLE_USER_ACCT, cv, COL_USER_NAME + " = '" + username + "'", null);
        return update == 1;
    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_ACCT + " where " + COL_USER_NAME + " = '" + username + "' and " + COL_PASSWORD + " = '" + password + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);
        return c.getCount() > 0;
    }

    public String userRole(String username) {
        String role = null;
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_ACCT + " where " + COL_USER_NAME + " = '" + username + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                role = c.getString(c.getColumnIndex(COL_USER_ROLE));
                Log.i("user role1: ", role);
                //  new Session(context).setUserRole(role);
                //  Log.i("role frm session1: ",  new Session(context).getUserRole());
                c.moveToNext();
            }
        } else {
            Log.i("zc: ", "not reached");
        }
        c.close();
        return role;
    }


    public boolean changePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_PASSWORD, newPassword);
        int update = db.update(TABLE_USER_ACCT, cv, COL_USER_NAME + " = '" + username + "'", null);
        Log.i(APP_TAG, String.valueOf(update));
        return update == 1;
    }

    public String getUserFullName(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{COL_FIRST_NAME, COL_LAST_NAME};
        Cursor cursor = db.query(TABLE_USER_ACCT, columns, COL_USER_NAME + " = '" + username + "'", null, null, null, null);
        while (cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME)) + " " + cursor.getString(cursor.getColumnIndex(COL_LAST_NAME));
        }
        return "";
    }
}
