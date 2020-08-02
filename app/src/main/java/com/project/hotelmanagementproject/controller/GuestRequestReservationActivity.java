package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;

//This is test commit
public class GuestRequestReservationActivity extends AppCompatActivity {

    LinearLayout llGuestRrInput,llGuestRrOutput;

    Button btnSearchHotel;
    Button btnGuestRrSearchRoom;
    CardView cvGuestRoomSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_request_reservation);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(GuestRequestReservationActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }


    public void init() {
        llGuestRrInput = findViewById(R.id.llGuestRrInput);
        llGuestRrInput.setVisibility(View.VISIBLE);
        llGuestRrOutput = findViewById(R.id.llGuestRrOutput);
        llGuestRrOutput.setVisibility(View.GONE);
        btnGuestRrSearchRoom = findViewById(R.id.btnGuestRrSearchRoom);
        btnGuestRrSearchRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llGuestRrInput.setVisibility(View.GONE);
                llGuestRrOutput.setVisibility(View.VISIBLE);

            }
        });
        cvGuestRoomSearchList = findViewById(R.id.cvGuestRoomSearchList);
        cvGuestRoomSearchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuestRequestReservationActivity.this, GuestRequestHotelDetailsActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}