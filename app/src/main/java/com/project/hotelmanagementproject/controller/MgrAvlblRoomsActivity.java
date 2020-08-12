package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.ManagerRoomListAdpater;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.DELUXE_ROOM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_AVLBL_ROOM_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_END_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.HOME_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_OCCUPIED_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_DELUXE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_STD;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_SUITE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_TIME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ROOM_NOT_OCCUPIED;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.STANDARD_ROOM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.SUITE_ROOM;

public class MgrAvlblRoomsActivity extends AppCompatActivity {
    ListView lvAvlblRoomList;
    ArrayList<HotelRoom> roomList;
    String startDate;
    String endDate;
    String startTime;
    String returnIntent;
    String hotelName, stdRoom = null, deluxeRoom = null, suiteRoom = null;
    Reservation reservation;
    ManagerRoomListAdpater roomListAdpater;
    private Button btnMgrArSearch;
    private LinearLayout llAvlblRoomIp;
    private LinearLayout llAvlblRoomOp;
    private TextInputEditText etStartDate, etEndDate, etStartTime;
    private CheckBox cbStandard, cbDeluxe, cbSuite;
    private TextView tvHotelName;
    private View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_avlbl_rooms);
        parentLayout = findViewById(android.R.id.content);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        retainActivityState();
    }

    private void retainActivityState() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            returnIntent = bundle.getString(ACTIVITY_RETURN_STATE);
            if (!returnIntent.equalsIgnoreCase(HOME_ACTIVITY)) {
                hotelName = bundle.getString(MGR_HOTEL_NAME);
                startDate = bundle.getString(MGR_START_DATE);
                endDate = bundle.getString(MGR_END_DATE);
                startTime = bundle.getString(MGR_START_TIME);
                stdRoom = bundle.getString(MGR_ROOM_STD);
                deluxeRoom = bundle.getString(MGR_ROOM_DELUXE);
                suiteRoom = bundle.getString(MGR_ROOM_SUITE);
                Log.i(APP_TAG, "return state: " + returnIntent + deluxeRoom + startDate + startTime);
                viewDataInList();
                llAvlblRoomIp.setVisibility(View.GONE);
                llAvlblRoomOp.setVisibility(View.VISIBLE);

            }
            Log.i(APP_TAG, "return state: " + returnIntent);
        }
    }

    public void init() {
        llAvlblRoomIp = findViewById(R.id.llMgrAvlblRoomIp);
        llAvlblRoomIp.setVisibility(View.VISIBLE);
        llAvlblRoomOp = findViewById(R.id.llMgrAvlblRoomOp);
        llAvlblRoomOp.setVisibility(View.GONE);

        etStartDate = findViewById(R.id.etMgrArStartDate);
        etEndDate = findViewById(R.id.etMgrArEndDate);
        etStartTime = findViewById(R.id.etMgrArStartTime);

        cbStandard = findViewById(R.id.cbMgrArStandard);
        cbDeluxe = findViewById(R.id.cbMgrArDeluxe);
        cbSuite = findViewById(R.id.cbMgrArSuite);

        tvHotelName = findViewById(R.id.tvMgrArHotelName);
        btnMgrArSearch = findViewById(R.id.btnMgrArSearch);

        lvAvlblRoomList = findViewById(R.id.lvMgrAvlblRoomList);
        roomList = new ArrayList<>();
        populateInputFields();

        btnMgrArSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputData();

                if (HotelRoom.isValidDate(startDate) && HotelRoom.isValidDate(endDate) && HotelRoom.isValidStartTime(startTime) && (cbDeluxe.isChecked() || cbStandard.isChecked() || cbSuite.isChecked())) {
                    if (!HotelRoom.isValidRange(startDate, endDate)) {
                        Snackbar.make(parentLayout, "Invalid date range", Snackbar.LENGTH_LONG).show();
                    } else {
                        viewDataInList();
                        llAvlblRoomIp.setVisibility(View.GONE);
                        llAvlblRoomOp.setVisibility(View.VISIBLE);
                    }
                } else {
                    //     Toast.makeText(getApplicationContext(), "Invalid Search Params", Toast.LENGTH_LONG).show();
                    if (!HotelRoom.isValidDate(startDate)) etStartDate.setError("Invalid date");
                    if (!HotelRoom.isValidDate(endDate)) etEndDate.setError("Invalid date");
                    if (!HotelRoom.isValidStartTime(startTime))
                        etStartTime.setError("Invalid Time");
                    if (!cbDeluxe.isChecked() && !cbStandard.isChecked() && !cbSuite.isChecked()) {
                        Snackbar.make(parentLayout, "Select at least one room type", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        lvAvlblRoomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HotelRoom item = (HotelRoom) adapterView.getItemAtPosition(i);
                startRoomDetailsActivity(item);
            }
        });
    }

    private void startRoomDetailsActivity(HotelRoom item) {
        Intent roomDetailsIntent = new Intent(MgrAvlblRoomsActivity.this, MgrRoomDetailsActivity.class);

        Log.i(APP_TAG, "RoomID: " + item.getHotelRoomId());
        roomDetailsIntent.putExtra(MGR_ROOM_ID, item.getHotelRoomId());
        roomDetailsIntent.putExtra(MGR_HOTEL_NAME, item.getHotelName());
        roomDetailsIntent.putExtra(MGR_START_DATE, item.getStartDate());
        roomDetailsIntent.putExtra(MGR_END_DATE, item.getEndDate());
        roomDetailsIntent.putExtra(MGR_START_TIME, item.getStartTime());

        roomDetailsIntent.putExtra(MGR_ROOM_STD, stdRoom);
        roomDetailsIntent.putExtra(MGR_ROOM_DELUXE, deluxeRoom);
        roomDetailsIntent.putExtra(MGR_ROOM_SUITE, suiteRoom);

        roomDetailsIntent.putExtra(MGR_OCCUPIED_STATUS, ROOM_NOT_OCCUPIED);
        roomDetailsIntent.putExtra(ACTIVITY_RETURN_STATE, MGR_AVLBL_ROOM_ACTIVITY);
        startActivity(roomDetailsIntent);
    }

    public void populateInputFields() {
        Date startDateFormat = new Date(System.currentTimeMillis());
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(startDateFormat);
        Date endDateFormat = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        endDate = new SimpleDateFormat("yyyy-MM-dd").format(endDateFormat);
        startTime = new SimpleDateFormat("hh:mm", Locale.US).format(startDateFormat);

        etStartDate.setText(startDate);
        etEndDate.setText(endDate);
        etStartTime.setText(startTime);
        hotelName = new Session(getApplicationContext()).getHotelAssigned();
        tvHotelName.setText(hotelName);
        Log.i(APP_TAG, "time format: " + startTime);
        Log.i(APP_TAG, "ci format: " + startDate);
        Log.i(APP_TAG, "co format: " + endDate);
        Log.i(APP_TAG, "hotel assigned: " + new Session(getApplicationContext()).getHotelAssigned());
    }

    private void getInputData() {
        if (llAvlblRoomIp.getVisibility() == View.VISIBLE) {
            if (cbStandard.isChecked()) {
                stdRoom = STANDARD_ROOM;
            } else {
                stdRoom = null;
            }
            if (cbDeluxe.isChecked()) {
                deluxeRoom = DELUXE_ROOM;
            } else {
                deluxeRoom = null;
            }
            if (cbSuite.isChecked()) {
                suiteRoom = SUITE_ROOM;
            } else {
                suiteRoom = null;
            }

            startDate = etStartDate.getText().toString();
            endDate = etEndDate.getText().toString();
            startTime = etStartTime.getText().toString();
        }
    }

    private void viewDataInList() {
        DbMgr hotelRoomDbMgr = DbMgr.getInstance(getApplicationContext());
        getInputData();
        Log.i(APP_TAG, "list params: " + stdRoom + " " + deluxeRoom + " " + suiteRoom);
        Log.i(APP_TAG, "list params: " + startDate + " " + endDate + " " + startTime);
        roomList = hotelRoomDbMgr.mgrGetAvailableRoomList(hotelName, stdRoom, deluxeRoom, suiteRoom, startDate, endDate, startTime);
        roomListAdpater = new ManagerRoomListAdpater(this, roomList);
        lvAvlblRoomList.setAdapter(roomListAdpater);
        roomListAdpater.notifyDataSetChanged();
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
            if (llAvlblRoomOp.getVisibility() == View.VISIBLE) {
                llAvlblRoomIp.setVisibility(View.VISIBLE);
                llAvlblRoomOp.setVisibility(View.GONE);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(MgrAvlblRoomsActivity.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (llAvlblRoomOp.getVisibility() == View.VISIBLE) {
            llAvlblRoomIp.setVisibility(View.VISIBLE);
            llAvlblRoomOp.setVisibility(View.GONE);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        //super.onBackPressed();
    }
}