package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;

public class GuestReservationSummActivity extends AppCompatActivity {
    CardView cvGuestReservationSummary;
    Button btnGuestCancelReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reservation_summary);
        actionBarHandler();
        init();

    }

    public void init() {
        cvGuestReservationSummary = findViewById(R.id.cvGuestReservationSummary);
        cvGuestReservationSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuestReservationSummActivity.this, GuestReservationDetailsActivity.class));

            }
        });


    }

    public void actionBarHandler() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setIcon(R.drawable.ic_baseline_lock_24);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

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
            // app icon in action bar clicked; go home
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(GuestReservationSummActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}