package com.project.hotelmanagementproject.controller;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.GuestPendingResevationAdapter;
import com.project.hotelmanagementproject.controller.adapters.GuestReservationSummaryAdapter;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;

import java.util.ArrayList;

public class GuestPendingReservations extends AppCompatActivity {

    private User userinfo;
    private DbMgr DbManager;
    ArrayList<HotelRoom> pendingReservationList;
    ListView lvToPendingReservationList;
    GuestPendingResevationAdapter guestPendingResevationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_pending_reservations);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        DbManager = DbMgr.getInstance(getApplicationContext());
        userinfo = DbManager.getUserDetails(new Session(getApplicationContext()).getUserName());
        showPendingReservations();
    }

    public void showPendingReservations(){
        lvToPendingReservationList = findViewById(R.id.lvToPendingReservationList);
        pendingReservationList = DbManager.getGuestPendingResevationList(userinfo.getUserName());
        guestPendingResevationAdapter = new GuestPendingResevationAdapter(this, pendingReservationList);
        lvToPendingReservationList.setAdapter(guestPendingResevationAdapter);
        guestPendingResevationAdapter.notifyDataSetChanged();
    }

}
