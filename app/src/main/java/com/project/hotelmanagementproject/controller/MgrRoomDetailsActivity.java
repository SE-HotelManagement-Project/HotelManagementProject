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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_AVLBL_ROOM_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_END_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_OCCUPIED_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_DELUXE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_STD;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_SUITE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_SEARCH_ROOM_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_SEARCH_ROOM_IP;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_TIME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.OCCUPANCY_NOT_CHECKED;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ROOM_NOT_OCCUPIED;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ROOM_OCCUPIED;

public class MgrRoomDetailsActivity extends AppCompatActivity {
    private Button btnMgrRmDEdit;
    private String roomId, hotelName, startDate, endDate, startTime, occupiedStatus, returnIntent;
    private TextView tvHotelName, tvRoomNum, tvRoomType, tvStartDate, tvEndDate, tvStartTime, tvPriceWeekday, tvPriceWeekend, tvTax, tvFloorNum, tvAvailabilityStatus, tvOccupiedStatus;
    private String searchRoomIp = "", stdRoom = "", deluxeRoom = "", suiteRoom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_room_details);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void getRoomData() {
        Bundle bundle = getIntent().getExtras();
        HotelRoom hr;
        if (bundle != null) {
            roomId = bundle.getString(MGR_ROOM_ID);
            hotelName = bundle.getString(MGR_HOTEL_NAME);
            startDate = bundle.getString(MGR_START_DATE);
            endDate = bundle.getString(MGR_END_DATE);
            startTime = bundle.getString(MGR_START_TIME);
            occupiedStatus = bundle.getString(MGR_OCCUPIED_STATUS);
            returnIntent = bundle.getString(MGR_ACTIVITY_RETURN_STATE);
            if (returnIntent.equalsIgnoreCase(MGR_SEARCH_ROOM_ACTIVITY)) {
                searchRoomIp = bundle.getString(MGR_SEARCH_ROOM_IP);
            } else if (returnIntent.equalsIgnoreCase(MGR_AVLBL_ROOM_ACTIVITY)) {
                stdRoom = bundle.getString(MGR_ROOM_STD);
                deluxeRoom = bundle.getString(MGR_ROOM_DELUXE);
                suiteRoom = bundle.getString(MGR_ROOM_SUITE);
                Log.i(APP_TAG, "return state-1: " + returnIntent + deluxeRoom + suiteRoom + startDate + startTime);

            }
            Log.i(APP_TAG, roomId + hotelName + startDate + returnIntent);
        }
        DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
        if (occupiedStatus.equalsIgnoreCase(OCCUPANCY_NOT_CHECKED)) {
            Reservation reservation = dbMgr.getResvDates(hotelName, startDate, roomId);
            if (reservation == null) {
                Log.i(APP_TAG, "ci null," + " co null," + " sd " + startDate);
                occupiedStatus = ROOM_NOT_OCCUPIED;
            } else {
                Log.i(APP_TAG, "ci " + reservation.getResvCheckInDate()
                        + " co " + reservation.getResevCheckOutDate() + " sd " + startDate);
                startDate = reservation.getResvCheckInDate();
                endDate = reservation.getResevCheckOutDate();
                startTime = reservation.getResvStartTime();
                occupiedStatus = ROOM_OCCUPIED;
            }
        }
        hr = dbMgr.mgrGetRoomDetails(roomId, startDate, endDate, startTime, occupiedStatus);

        tvHotelName.setText(hr.getHotelName());
        tvRoomNum.setText(hr.getRoomNum());
        tvRoomType.setText(hr.getRoomType());
        tvStartDate.setText(hr.getStartDate());
        tvEndDate.setText(hr.getEndDate());
        tvStartTime.setText(hr.getStartTime());
        tvPriceWeekend.setText(hr.getRoomPriceWeekend());
        tvPriceWeekday.setText(hr.getRoomPriceWeekDay());
        tvTax.setText(hr.getHotelTax());
        tvFloorNum.setText(hr.getFloorNum());
        tvAvailabilityStatus.setText(hr.getAvailabilityStatus());
        tvOccupiedStatus.setText(hr.getOccupiedStatus());
    }

    public void init() {
        tvHotelName = findViewById(R.id.tvMgrRmDHotel);
        tvRoomNum = findViewById(R.id.tvMgrRmDRoomNumRd);
        tvRoomType = findViewById(R.id.tvMgrRmDRoomType);
        tvStartDate = findViewById(R.id.tvMgrRmDDateFrom);
        tvEndDate = findViewById(R.id.tvMgrRmDDateTo);
        tvStartTime = findViewById(R.id.tvMgrRmDStartTime);
        tvPriceWeekday = findViewById(R.id.tvMgrRmDRoomPriceWeekday);
        tvPriceWeekend = findViewById(R.id.tvMgrRmDRoomPriceWeekend);
        tvFloorNum = findViewById(R.id.tvMgrRmDFloorNumRd);
        tvTax = findViewById(R.id.tvMgrRmDRoomTax);
        tvAvailabilityStatus = findViewById(R.id.tvMgrRmDAvailabilityStatus);
        tvOccupiedStatus = findViewById(R.id.tvMgrRmDOccupiedStatus);

        getRoomData();

        btnMgrRmDEdit = findViewById(R.id.btnMgrRmDEdit);
        btnMgrRmDEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mgrModifyRoomIntent = new Intent(MgrRoomDetailsActivity.this, MgrModifyRoomActivity.class);
                mgrModifyRoomIntent.putExtra(MGR_ROOM_ID, roomId);
                mgrModifyRoomIntent.putExtra(MGR_HOTEL_NAME, hotelName);
                mgrModifyRoomIntent.putExtra(MGR_START_DATE, startDate);
                mgrModifyRoomIntent.putExtra(MGR_END_DATE, endDate);
                mgrModifyRoomIntent.putExtra(MGR_START_TIME, startTime);
                mgrModifyRoomIntent.putExtra(MGR_OCCUPIED_STATUS, occupiedStatus);
                mgrModifyRoomIntent.putExtra(MGR_ACTIVITY_RETURN_STATE, returnIntent);
                if (returnIntent.equalsIgnoreCase(MGR_AVLBL_ROOM_ACTIVITY)) {
                    mgrModifyRoomIntent.putExtra(MGR_ROOM_STD, stdRoom);
                    mgrModifyRoomIntent.putExtra(MGR_ROOM_DELUXE, deluxeRoom);
                    mgrModifyRoomIntent.putExtra(MGR_ROOM_SUITE, suiteRoom);
                    Log.i(APP_TAG, "return state2: " + returnIntent + deluxeRoom + suiteRoom + startDate + startTime);
                } else {
                    mgrModifyRoomIntent.putExtra(MGR_SEARCH_ROOM_IP, searchRoomIp);
                }
                startActivity(mgrModifyRoomIntent);
            }
        });
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
            Intent backIntent;
            if (returnIntent.equalsIgnoreCase(MGR_AVLBL_ROOM_ACTIVITY)) {
                backIntent = new Intent(this, MgrAvlblRoomsActivity.class);
                backIntent.putExtra(MGR_ROOM_STD, stdRoom);
                backIntent.putExtra(MGR_ROOM_DELUXE, deluxeRoom);
                backIntent.putExtra(MGR_ROOM_SUITE, suiteRoom);
                Log.i(APP_TAG, "return state-2: " + returnIntent + deluxeRoom + suiteRoom + startDate + startTime);

            } else {
                backIntent = new Intent(this, MgrSearchRoomActivity.class);
                backIntent.putExtra(MGR_SEARCH_ROOM_IP, searchRoomIp);
            }
            backIntent.putExtra(MGR_HOTEL_NAME, hotelName);
            backIntent.putExtra(MGR_START_DATE, startDate);
            backIntent.putExtra(MGR_END_DATE, endDate);
            backIntent.putExtra(MGR_START_TIME, startTime);
            backIntent.putExtra(MGR_ACTIVITY_RETURN_STATE, returnIntent);
            startActivity(backIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(MgrRoomDetailsActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent;
        if (returnIntent.equalsIgnoreCase(MGR_AVLBL_ROOM_ACTIVITY)) {
            backIntent = new Intent(this, MgrAvlblRoomsActivity.class);
            backIntent.putExtra(MGR_ROOM_STD, stdRoom);
            backIntent.putExtra(MGR_ROOM_DELUXE, deluxeRoom);
            backIntent.putExtra(MGR_ROOM_SUITE, suiteRoom);
            Log.i(APP_TAG, "return state-2: " + returnIntent + deluxeRoom + suiteRoom + startDate + startTime);

        } else {
            backIntent = new Intent(this, MgrSearchRoomActivity.class);
            backIntent.putExtra(MGR_SEARCH_ROOM_IP, searchRoomIp);
        }
        backIntent.putExtra(MGR_HOTEL_NAME, hotelName);
        backIntent.putExtra(MGR_START_DATE, startDate);
        backIntent.putExtra(MGR_END_DATE, endDate);
        backIntent.putExtra(MGR_START_TIME, startTime);
        backIntent.putExtra(MGR_ACTIVITY_RETURN_STATE, returnIntent);
        startActivity(backIntent);
    }
}