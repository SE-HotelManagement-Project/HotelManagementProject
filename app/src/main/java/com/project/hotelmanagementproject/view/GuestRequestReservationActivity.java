package com.project.hotelmanagementproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.hotelmanagementproject.R;

public class GuestRequestReservationActivity extends AppCompatActivity {

    LinearLayout llSearchRoomIp, llSearchRoomOp;
    LinearLayout llGuestRrInput,llGuestRrOutput;
    CardView cvRoomCard;
    Button btnSearchHotel;
    Button btnGuestRrSearchRoom;
    CardView cvGuestRoomSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_request_reservation);
        init();
    }

    public void initUi(View v) {

        llSearchRoomIp = findViewById(R.id.llGuestRrInput);
        llSearchRoomOp = findViewById(R.id.llGuestRrOutput);

        btnSearchHotel = findViewById(R.id.btnGuestRsSearch);

    }
    public void init(){
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
                startActivity(new Intent(GuestRequestReservationActivity.this, GuestRequestHotelActivity.class));

            }
        });
    }
}