package com.project.hotelmanagementproject.model.DAO;

import android.content.Context;
import android.content.SharedPreferences;
//import com.google.gson.Gson;

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

    public String getHotelAssigned() {
        return prefs.getString("HotelAccess", null);
    }

    public void setHotelAssigned(String hotelAssigned) {
        editor.putString("HotelAccess", hotelAssigned).commit();
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
//    // ankit code changes starts
//    public void setObjectInSession( String key , Object obj) {
//        gson = new Gson();
//        String json = gson.toJson(obj);
//        editor.putString(key, json);
//        editor.apply();
//    }
//
//    //    Type type = new TypeToken<UserModel>() {}.getType();
////    Type typeOfObjectsList = new TypeToken<ArrayList<UserModel>>() {}.getType();
////    UserModel userModel = gson.fromJson( session.getObjectFromSession("loggedInUserModel" , HomeActivity.this) , type);
//    //invoke this code using the above two lines for list
//    public String getObjectFromSession(String key ) {
//        gson = new Gson();
//        String json = prefs.getString(key, null);
//        return json;
//    }
//
    // ankit code changes ends
}