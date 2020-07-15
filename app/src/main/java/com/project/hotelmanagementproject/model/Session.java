package com.project.hotelmanagementproject.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    //private SharedPreferences prefs;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = cntx.getSharedPreferences("HM_PREF", Context.MODE_PRIVATE);
        editor = prefs.edit();
        // prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public boolean getLoginStatus() {
        return prefs.getBoolean("LoginStatus", false);
    }

    public void setLoginStatus(boolean islogin) {
        editor.putBoolean("LoginStatus", islogin).apply();
    }

    public String getUserName() {
        return prefs.getString("UserName", null);
    }

    public void setUserName(String userName) {
        editor.putString("UserName", userName).commit();
    }

    public String getUserRole() {
        return prefs.getString("UserRole", null);
    }

    public void setUserRole(String userRole) {
        editor.putString("UserRole", userRole).apply();
    }

    public void clear() {
        editor.clear();
    }
}