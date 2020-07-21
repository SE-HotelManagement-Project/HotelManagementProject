package com.project.hotelmanagementproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.hotelmanagementproject.R;

public class GuestReservationSummActivity extends AppCompatActivity {
    CardView cvGuestReservationSummary;
    Button btnGuestCancelReservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reservation_summary);
        init();
    }

    public void init(){
        cvGuestReservationSummary = findViewById(R.id.cvGuestReservationSummary);
        cvGuestReservationSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuestReservationSummActivity.this, GuestReservationDetailsActivity.class));

            }
        });




    }
}