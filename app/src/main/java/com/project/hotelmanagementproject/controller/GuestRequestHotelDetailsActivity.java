package com.project.hotelmanagementproject.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

//This is test
public class GuestRequestHotelDetailsActivity extends AppCompatActivity {
    private Button btnGuestRmDContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_room_details);
        init();
    }

    public void init() {
        btnGuestRmDContinue = findViewById(R.id.btnGuestRmDContinue);
        btnGuestRmDContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToastMessage("Feature Under Construction");
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