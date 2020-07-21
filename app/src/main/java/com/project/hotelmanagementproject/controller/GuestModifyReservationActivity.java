package com.project.hotelmanagementproject.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class GuestModifyReservationActivity extends AppCompatActivity {
    Button btnGuestModifyReservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_modify_reservation);
        init();
    }
    public void init(){
        btnGuestModifyReservation = findViewById(R.id.btnGuestModifyReservation);

        btnGuestModifyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GuestModifyReservationActivity.this);

                        builder
                        .setTitle("Update Reservation")
                        .setMessage("Are you sure you want save the changes?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                startActivity(new Intent(GuestModifyReservationActivity.this, GuestReservationDetailsActivity.class));
                            }
                        }).create().show();
            }
        });


    }
}