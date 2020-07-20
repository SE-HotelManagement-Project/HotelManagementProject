package com.project.hotelmanagementproject.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.hotelmanagementproject.R;

public class GuestRequestReservationActivity extends AppCompatActivity {

    LinearLayout llSearchRoomIp, llSearchRoomOp;
    CardView cvRoomCard;
    Button btnSearchHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_request_reservation);
    }

    public void initUi(View v) {

        llSearchRoomIp = findViewById(R.id.llGuestRrInput);
        llSearchRoomOp = findViewById(R.id.llGuestRrOutput);

        btnSearchHotel = findViewById(R.id.btnGuestRsSearch);

    }
}