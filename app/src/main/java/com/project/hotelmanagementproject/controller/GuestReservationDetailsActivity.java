package com.project.hotelmanagementproject.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class GuestReservationDetailsActivity extends AppCompatActivity {
    Button btnGuestModifyReservation;
    Button btnGuestCancelReservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reservation_details);
        init();
    }

    public void init(){
        btnGuestModifyReservation = findViewById(R.id.btnGuestModifyReservation);
        btnGuestModifyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuestReservationDetailsActivity.this, GuestModifyReservationActivity.class));
            }
        });

        btnGuestCancelReservation = findViewById(R.id.btnGuestCancelReservation);

        btnGuestCancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GuestReservationDetailsActivity.this);

                builder
                        .setTitle("Cancel Reservation")
                        .setMessage("Are you sure you want cancel the reservation?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                showToastMessage("Reservation Cancelled");
                                startActivity(new Intent(GuestReservationDetailsActivity.this, GuestReservationSummActivity.class));
                            }
                        }).create().show();
            }
        });
    }

    public void showToastMessage(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        View view = toast.getView();
//       view.setBackgroundResource(R.drawable.toast_frame);
//        view.setBackgroundColor(Color.BLACK);
//        TextView text = (TextView) view.findViewById(android.R.id.message);
        toast.show();

    }
}