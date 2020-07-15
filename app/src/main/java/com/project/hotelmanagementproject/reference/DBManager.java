package com.project.hotelmanagementproject.reference;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    private static final String dbname = "VendingVehicleMachine.db";

    public DBManager(Context context) {
        super(context, dbname, null, 1);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table tbl_registerUser(firstname text, " +
                "lastname text, username text primary key,password text,usertype text,email text,phone text,address text," +
                "city text,state text,zipcode text,secques text, secans text)";
        db.execSQL(qry);
        qry = "create table Location (locationId text primary key, description text)";
        ContentValues cv = new ContentValues();
        db.execSQL(qry);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_registerUser");
        onCreate(db);
    }

    public String addRecord(String p1, String p2, String p3, String p4, String p5, String p6,
                            String p7, String p8, String p9, String p10, String p11, String p12, String p13) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("data", p1);
        // put remainder of data stored here

        long res = db.insert("tbl_registerUser", null, cv);
        if (res == -1)
            return "failed";
        else
            return "Account Created Successfully";

    }
}
