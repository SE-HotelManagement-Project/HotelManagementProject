package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.AdminSearchUserAdapter;
import com.project.hotelmanagementproject.controller.adapters.GuestReservationSummaryAdapter;
import com.project.hotelmanagementproject.controller.adapters.ManagerRoomListAdpater;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.GUEST_RESV_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.GUEST_RESV_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.GUEST_RESV_START_TIME;

public class GuestReservationSummActivity extends AppCompatActivity {
    CardView cvGuestReservationSummary;
    LinearLayout llGuestRsIp;
    LinearLayout llGuestRsOp;

    String returnIntent, endDate, startTime, searchRoomIp, startDate;
    //  String etGuestRsStartDateStr,etGuestRsStartTimeStr;
    TextInputEditText etGuestRsStartDate, etGuestRsStartTime;
    ArrayList<Reservation> reservationList;
    ListView lvReservationList;
    GuestReservationSummaryAdapter guestReservationSummaryAdapter;
    String Guest_Rsr_Summary= "guest_rsr";
    User userInfo;
    DbMgr DbManager;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reservation_summary);
        actionBarHandler();
        init();

    }

    public void init() {
        cvGuestReservationSummary = findViewById(R.id.cvGuestReservationSummary);
        llGuestRsIp = findViewById(R.id.llGuestRsIp);
        llGuestRsOp = findViewById(R.id.llGuestRsOp);
        llGuestRsIp.setVisibility(View.VISIBLE);
        llGuestRsOp.setVisibility(View.GONE);
        etGuestRsStartDate = findViewById(R.id.etGuestRsStartDate);
        etGuestRsStartTime = findViewById(R.id.etGuestRsStartTime);
        reservationList = new ArrayList<>();
        populateDetaultSearchValues();
        lvReservationList = findViewById(R.id.lvGuestAvlblRoomList);
        DbManager = DbMgr.getInstance(getApplicationContext());
        userInfo = DbManager.getUserDetails(new Session(getApplicationContext()).getUserName());
        userName = new Session(getApplicationContext()).getUserName();
        Button btnGuestRsSearch = findViewById(R.id.btnGuestRsSearch);
        btnGuestRsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchGuestReservation();
            }
        });
    }

    public void populateDetaultSearchValues() {
        Date startDateFormat = new Date(System.currentTimeMillis());
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(startDateFormat);
        startTime = new SimpleDateFormat("hh:mm", Locale.US).format(startDateFormat);

        etGuestRsStartDate.setText(startDate);
        etGuestRsStartTime.setText(startTime);
    }

    public void searchGuestReservation() {

//        String etGuestRsStartDateStr = etGuestRsStartDate.getText().toString();
//        String etGuestRsStartTimeStr =

        startDate = etGuestRsStartDate.getText().toString();
        startTime = etGuestRsStartTime.getText().toString();

        if (null == startDate || startDate.isEmpty() || startDate.equalsIgnoreCase("")) {
            etGuestRsStartDate.setError("enter valid input");
            Toast.makeText(getApplicationContext(), "invalid start date", Toast.LENGTH_LONG).show();
        } else if (null == startTime || startTime.isEmpty() || startTime.equalsIgnoreCase("")) {
            etGuestRsStartDate.setError("enter valid input");
            Toast.makeText(getApplicationContext(), "invalid start time", Toast.LENGTH_LONG).show();
        } else {
            llGuestRsIp.setVisibility(View.GONE);
            llGuestRsOp.setVisibility(View.VISIBLE);
            reservationList = DbManager.guestGetReservationSummaryList(userName, startDate, startTime);
            guestReservationSummaryAdapter = new GuestReservationSummaryAdapter(this, reservationList);
            lvReservationList.setAdapter(guestReservationSummaryAdapter);
            guestReservationSummaryAdapter.notifyDataSetChanged();
        }

        lvReservationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent guestRsvDetailsIntent = new Intent(GuestReservationSummActivity.this, GuestReservationDetailsActivity.class);
                Reservation item = (Reservation) adapterView.getItemAtPosition(i);
                guestRsvDetailsIntent.putExtra(GUEST_RESV_ID, item.getReservationId());
                guestRsvDetailsIntent.putExtra(GUEST_RESV_START_DATE, startDate);
                //  guestRsvDetailsIntent.putExtra(GUEST_RESV_START_TIME,startTime);
                startActivity(guestRsvDetailsIntent);
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
            if (llGuestRsOp.getVisibility() == View.VISIBLE) {
                llGuestRsIp.setVisibility(View.VISIBLE);
                llGuestRsOp.setVisibility(View.GONE);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(GuestReservationSummActivity.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (llGuestRsOp.getVisibility() == View.VISIBLE) {
            llGuestRsIp.setVisibility(View.VISIBLE);
            llGuestRsOp.setVisibility(View.GONE);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}