package com.project.hotelmanagementproject.reference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDAORef extends SQLiteOpenHelper {
    private static final String dbname = "User.db";

    public UserDAORef(Context context) {
        super(context, dbname, null, 1);
    }

    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{"firstname", "lastname", "username", "usertype", "email", "phone", "address", "city", "state", "zipcode", "secques", "secans"};
        Cursor cursor = db.query("tbl_registerUser", columns, "username = '" + username + "'", null, null, null, null);
        return cursor;
    }

    public void updateProfile(String username, String firstname, String lastname, String phone, String email, String address, String city, String state, String zipCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstname", firstname);
        cv.put("lastname", lastname);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("address", address);
        cv.put("city", city);
        cv.put("state", state);
        cv.put("zipcode", zipCode);
        db.update("tbl_registerUser", cv, "username = '" + username + "'", null);
    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from tbl_registerUser where username = '" + username + "' and password = '" + password + "'";
        Cursor cursor = sqldb.rawQuery(queryForCheckingPassword, null);
        return cursor.getCount() > 0;
    }

    public void changePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        db.update("tbl_registerUser", cv, "username = '" + username + "'", null);
    }

    public String getUserFullName(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{"firstname", "lastname"};
        Cursor cursor = db.query("tbl_registerUser", columns, "username = '" + username + "'", null, null, null, null);
        while (cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex("firstname")) + " " + cursor.getString(cursor.getColumnIndex("lastname"));
        }
        return "";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
