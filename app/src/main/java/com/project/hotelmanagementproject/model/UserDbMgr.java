package com.project.hotelmanagementproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CARD_TYPE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CREDIT_CARD_EXP;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_CREDIT_CARD_NUM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_EMAIL;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_FIRST_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_LAST_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PASSWORD;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_PHONE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_STREET_ADDRESS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_USER_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_USER_ROLE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.COL_ZIP_CODE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.TABLE_USER_DATA;

//<<Singleton>>
public class UserDbMgr {

    Context context;
    private static UserDbMgr mInstance = null;
    DBHelper dbHelper;

    private UserDbMgr(Context context) {
        this.context = context;
        dbHelper = DBHelper.getInstance(context);
    }

    public static synchronized UserDbMgr getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new UserDbMgr(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public boolean addNewGuest(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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

    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = new String[]{COL_FIRST_NAME, COL_LAST_NAME, COL_USER_NAME, COL_USER_ROLE, COL_EMAIL, COL_PHONE, COL_STREET_ADDRESS, COL_CITY, COL_STATE, COL_ZIP_CODE, COL_CREDIT_CARD_NUM, COL_CREDIT_CARD_EXP, COL_CARD_TYPE};
        Cursor cursor = db.query(TABLE_USER_DATA, columns, COL_USER_NAME + " = '" + username + "'", null, null, null, null);
        return cursor;
    }

    public Cursor getUserProfile(String username) {
        String role = null;
        SQLiteDatabase sqldb = dbHelper.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_DATA + " where " + COL_USER_NAME + " = '" + username + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);
        return c;
    }

    public boolean updateProfile(String username, String firstname, String password, String lastname, String phone, String email, String address, String city, String state, String zipCode, String creditCardNum, String cardExpiry) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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
        int update = db.update(TABLE_USER_DATA, cv, COL_USER_NAME + " = '" + username + "'", null);
        return update == 1;
    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase sqldb = dbHelper.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_DATA + " where " + COL_USER_NAME + " = '" + username + "' and " + COL_PASSWORD + " = '" + password + "'";
        Cursor c = sqldb.rawQuery(queryForCheckingPassword, null);

        return c.getCount() > 0;
    }

    public String userRole(String username) {
        String role = null;
        SQLiteDatabase sqldb = dbHelper.getReadableDatabase();
        String queryForCheckingPassword = "Select * from " + TABLE_USER_DATA + " where " + COL_USER_NAME + " = '" + username + "'";
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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_PASSWORD, newPassword);
        int update = db.update(TABLE_USER_DATA, cv, COL_USER_NAME + " = '" + username + "'", null);
        Log.i(APP_TAG, String.valueOf(update));
        return update == 1;
    }

    public String getUserFullName(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = new String[]{COL_FIRST_NAME, COL_LAST_NAME};
        Cursor cursor = db.query(TABLE_USER_DATA, columns, COL_USER_NAME + " = '" + username + "'", null, null, null, null);
        while (cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME)) + " " + cursor.getString(cursor.getColumnIndex(COL_LAST_NAME));
        }
        return "";
    }
}
