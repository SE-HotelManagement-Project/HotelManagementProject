package com.project.hotelmanagementproject.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class GuestRequestReservationActivity extends AppCompatActivity {

    LinearLayout llSearchRoomIp, llSearchRoomOp;
    Button btnSearchHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_request_reservation);
       // initUi();
    }

    public void initUi() {

        llSearchRoomIp = findViewById(R.id.llGuestRrInput);
        llSearchRoomOp = findViewById(R.id.llGuestRrOutput);

        btnSearchHotel = findViewById(R.id.btnGuestRsSearch);

    }
}