package com.project.hotelmanagementproject.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.GUEST_RESV_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.GUEST_RESV_START_DATE;

public class GuestReservationDetailsActivity extends AppCompatActivity {
    private Button btnGuestModifyReservation;
    private Button btnGuestCancelReservation;

    private TextView tvReservationId, tvHotelName, tvGuestNumOfRooms, tvGuestStartDate, tvGuestCheckInDate, tvCheckOutDate, tvGuestStartTime,
            tvGuestNumAdults, tvGuestNumOfNights, tvGuestRoomType, tvGuestTotalPrice;

    private String reservationId, hotelName, roomType,
            numAdults, numNights, numberRooms, totalPrice,
            checkinDate, checkoutDate, startTime, startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reservation_details);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        retainActivityState();
        init();
    }

    private void retainActivityState() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            reservationId = bundle.getString(GUEST_RESV_ID);
            startDate = bundle.getString(GUEST_RESV_START_DATE);
            // startTime = bundle.getString(GUEST_RESV_START_TIME);
            Log.i(APP_TAG, reservationId + ", " + startDate + ", ");
        }
    }

    public void init() {
        tvReservationId = findViewById(R.id.tvGuestRdReservId);
        tvHotelName = findViewById(R.id.tvGuestRdHotelName);
        tvGuestStartDate = findViewById(R.id.tvGuestRdStartDate);
        tvGuestCheckInDate = findViewById(R.id.tvGuestRdCheckIn);
        tvCheckOutDate = findViewById(R.id.tvGuestRdCheckOut);
        tvGuestStartTime = findViewById(R.id.tvGuestRdStartTime);
        tvGuestNumOfRooms = findViewById(R.id.tvGuestRdNumRooms);
        tvGuestNumAdults = findViewById(R.id.tvGuestRdNumAdults);
        tvGuestNumOfNights = findViewById(R.id.tvGuestRdNumNights);
        tvGuestRoomType = findViewById(R.id.tvGuestRdRoomType);
        tvGuestTotalPrice = findViewById(R.id.tvGuestRdRoomPrice);

        PopulateUI();
        btnGuestModifyReservation = findViewById(R.id.btnGuestModifyReservation);
        btnGuestModifyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resvModifyIntent = new Intent(GuestReservationDetailsActivity.this, GuestModifyReservationActivity.class);
                resvModifyIntent.putExtra(GUEST_RESV_ID, reservationId);
                resvModifyIntent.putExtra(GUEST_RESV_START_DATE, startDate);
                //   resvModifyIntent.putExtra(GUEST_RESV_START_TIME,starttime);
                startActivity(resvModifyIntent);
            }
        });

        btnGuestCancelReservation = findViewById(R.id.btnGuestCancelReservation);
        btnGuestCancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelResvClick();
            }
        });
    }

    private void onCancelResvClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GuestReservationDetailsActivity.this);
        builder.setTitle("Cancel Reservation")
                .setMessage("Are you sure you want cancel the reservation?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
                        int delres = dbMgr.deleteReservation(reservationId);
                        if (delres == 1) {
                            Toast.makeText(getApplicationContext(), "Reservation Cancelled Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Reservation Cancellation was Unsuccessful", Toast.LENGTH_LONG).show();
                        }
                        Intent backIntent = new Intent(GuestReservationDetailsActivity.this, GuestReservationSummActivity.class);
                        startActivity(backIntent);
                    }
                }).create().show();
    }

    private void PopulateUI() {
        Reservation reservation;
        DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
        reservation = dbMgr.guestGetResvDetails(reservationId, startDate);

        hotelName = reservation.getResvHotelName();
        checkinDate = reservation.getResvCheckInDate();
        checkoutDate = reservation.getResevCheckOutDate();
        startTime = reservation.getResvStartTime();
        numberRooms = reservation.getResvNumOfRooms();
        numAdults = reservation.getResvNumAdultsChildren();
        numNights = reservation.getResvNumNights();
        roomType = reservation.getResvRoomType();
        totalPrice = reservation.getTotalPrice();

        tvReservationId.setText(reservationId);
        tvHotelName.setText(hotelName);
        tvGuestStartDate.setText(startDate);
        tvGuestCheckInDate.setText(checkinDate);
        tvCheckOutDate.setText(checkoutDate);
        tvGuestStartTime.setText(startTime);
        tvGuestNumOfRooms.setText(numberRooms);
        tvGuestNumAdults.setText(numAdults);
        tvGuestNumOfNights.setText(numNights);
        tvGuestRoomType.setText(roomType);
        tvGuestTotalPrice.setText(totalPrice);
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
            onBackClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        onBackClick();
    }

    private void onBackClick() {
        Intent backIntent = new Intent(GuestReservationDetailsActivity.this, GuestReservationSummActivity.class);
        startActivity(backIntent);
    }
}