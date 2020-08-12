package com.project.hotelmanagementproject.controller;

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
import com.project.hotelmanagementproject.model.User;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_RESV_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_TIME;


public class MgrResvDetailsActivity extends AppCompatActivity {
    private Button btnDone;
    private String userName, firstName, lastName;
    private String resvId, hotelName, startDate, startTime, returnIntent;
    private TextView tvHotelName, tvResvId, tvUserName, tvFirstName, tvLastName,
            tvStartDate, tvStartTime,
            tvCheckInDate, tvCheckOutDate, tvRoomType,
            tvNumRooms, tvNumAdults, tvNumNights, tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_resv_details);
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
            resvId = bundle.getString(MGR_RESV_ID);
            hotelName = bundle.getString(MGR_HOTEL_NAME);
            startDate = bundle.getString(MGR_START_DATE);
            startTime = bundle.getString(MGR_START_TIME);
            returnIntent = bundle.getString(ACTIVITY_RETURN_STATE);
            Log.i(APP_TAG, resvId + ", " + hotelName + ", " + startDate + ", " + returnIntent);
        }
    }

    public void init() {
        tvHotelName = findViewById(R.id.tvMgrRsvDHotelName);
        tvResvId = findViewById(R.id.tvMgrRsvDReservId);

        tvUserName = findViewById(R.id.tvMgrRsvDUserName);
        tvFirstName = findViewById(R.id.tvMgrRsvDFirstName);
        tvLastName = findViewById(R.id.tvMgrRsvDLastName);

        tvNumRooms = findViewById(R.id.tvMgrRsvDNumRooms);
        tvNumAdults = findViewById(R.id.tvMgrRsvDNumAdults);
        tvNumNights = findViewById(R.id.tvMgrRsvDNumNights);

        tvRoomType = findViewById(R.id.tvMgrRsvDRoomType);
        tvTotalPrice = findViewById(R.id.tvMgrRsvDRoomPrice);

        tvStartDate = findViewById(R.id.tvMgrRsvDStartDate);
        tvStartTime = findViewById(R.id.tvMgrRsvDStartTime);
        tvCheckInDate = findViewById(R.id.tvMgrRsvDCheckIn);
        tvCheckOutDate = findViewById(R.id.tvMgrRsvDCheckOut);

        btnDone = findViewById(R.id.btnMgrRsvDDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MgrResvDetailsActivity.this, HomeActivity.class));
            }
        });
        getResvDataAndPopulateUi();
    }

    private void getResvDataAndPopulateUi() {
        Reservation reservation;
        DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
        reservation = dbMgr.mgrGetResvDetails(hotelName, startDate, resvId);
        User user = dbMgr.getUserDetails(reservation.getResvUserName());
        Log.i(APP_TAG, reservation.getResvUserName() + user.getFirstName());

        tvHotelName.setText(reservation.getResvHotelName());
        tvResvId.setText(reservation.getReservationId());

        tvUserName.setText(reservation.getResvUserName());
        tvFirstName.setText(user.getFirstName());
        tvLastName.setText(user.getLastName());

        tvRoomType.setText(reservation.getResvRoomType());
        tvTotalPrice.setText(reservation.getTotalPrice());

        tvStartDate.setText(reservation.getStartDate());
        tvCheckInDate.setText(reservation.getResvCheckInDate());
        tvStartTime.setText(reservation.getResvStartTime());
        tvCheckOutDate.setText(reservation.getResevCheckOutDate());

        tvNumNights.setText(reservation.getResvNumNights());
        tvNumAdults.setText(reservation.getResvNumAdultsChildren());
        tvNumRooms.setText(reservation.getResvNumOfRooms());
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

    private void onBackClick() {
        Intent backIntent = new Intent(MgrResvDetailsActivity.this, MgrSearchResvActivity.class);
        backIntent.putExtra(MGR_HOTEL_NAME, hotelName);
        backIntent.putExtra(MGR_START_DATE, startDate);
        backIntent.putExtra(MGR_START_TIME, startTime);
        backIntent.putExtra(ACTIVITY_RETURN_STATE, returnIntent);
        startActivity(backIntent);
    }

    public void logout() {
        Intent i = new Intent(MgrResvDetailsActivity.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        onBackClick();
    }
}