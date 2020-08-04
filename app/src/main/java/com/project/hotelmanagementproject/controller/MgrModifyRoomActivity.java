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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Session;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.DELUXE_ROOM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_END_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_OCCUPIED_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_TIME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.STANDARD_ROOM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.SUITE_ROOM;

public class MgrModifyRoomActivity extends AppCompatActivity {
    Button btnMgrRdSave;
    Spinner spnrAvailabilityStatus;
    DbMgr dbMgr;
    private String roomId, hotelName, startDate, endDate, startTime, occupiedStatus;
    private String priceWeekend, priceWeekDay, tax, floorNum, roomNum, roomType, availabilityStatus;
    private TextView tvHotelName, tvRoomNum, tvStartDate, tvEndDate, tvStartTime, tvFloorNum, tvOccupiedStatus;
    private TextInputEditText etPriceWeekday, etPriceWeekend, etTax;
    private RadioButton rbStandard, rbDeluxe, rbSuite;
    private RadioGroup rgRoomTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_room_modify);
        View parentLayout = findViewById(android.R.id.content);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init() {
        tvHotelName = findViewById(R.id.tvMgrMRDHotel);
        tvRoomNum = findViewById(R.id.tvMgrMRDRoomNum);
        tvFloorNum = findViewById(R.id.tvMgrMRDFloorNum);
        tvStartDate = findViewById(R.id.tvMgrMRDStartDate);
        tvEndDate = findViewById(R.id.tvMgrMRDEndDate);
        tvStartTime = findViewById(R.id.tvMgrMRDStartTime);
        tvOccupiedStatus = findViewById(R.id.tvMgrMRDOccupiedStatus);

        etPriceWeekday = findViewById(R.id.etMgrRdRoomPriceWeekday);
        etPriceWeekend = findViewById(R.id.etMgrMRdPriceWeekend);
        etTax = findViewById(R.id.etMgrMRdRoomTax);
        spnrAvailabilityStatus = findViewById(R.id.spnrMgrMRdAvlblStatus);

        rbStandard = findViewById(R.id.rbMgrMRdStandard);
        rbDeluxe = findViewById(R.id.rbMgrMRdDeluxe);
        rbSuite = findViewById(R.id.rbMgrMRdSuite);

        rgRoomTypes = findViewById(R.id.rgMgrMRdRoomTypes);

        HotelRoom hr = getRoomDetails();

        roomNum = hr.getRoomNum();
        floorNum = hr.getFloorNum();
        priceWeekDay = hr.getRoomPriceWeekDay();
        priceWeekend = hr.getRoomPriceWeekend();
        tax = hr.getHotelTax();
        roomType = hr.getRoomType();

        tvHotelName.setText(hotelName);
        tvRoomNum.setText(roomNum);
        tvFloorNum.setText(floorNum);
        tvStartDate.setText(startDate);
        tvEndDate.setText(endDate);
        tvStartTime.setText(startTime);
        tvOccupiedStatus.setText(occupiedStatus);

        roomTypeChangeListener();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.availability_status, android.R.layout.simple_spinner_item);

        spnrAvailabilityStatus.setSelection(adapter.getPosition(hr.getAvailabilityStatus()));

        etPriceWeekday.setText(priceWeekDay);
        etPriceWeekend.setText(priceWeekend);
        etTax.setText(tax);

        spnrAvailabilityStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                availabilityStatus = spnrAvailabilityStatus.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnMgrRdSave = findViewById(R.id.btnMgrRdSave);
        btnMgrRdSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MgrModifyRoomActivity.this);
                builder.setTitle("Modify Room Details!")
                        .setMessage("Are you sure you want modify the room details?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                modifyRoomDetails();
                            }
                        }).create().show();
            }
        });
    }

    private void modifyRoomDetails() {
        priceWeekend = etPriceWeekend.getText().toString();
        priceWeekDay = etPriceWeekday.getText().toString();
        tax = etTax.getText().toString();

        HotelRoom modifiedDetails = new HotelRoom(roomId, hotelName, roomNum, roomType, floorNum, priceWeekDay, priceWeekend, tax,
                availabilityStatus, occupiedStatus, startDate, endDate, startTime);

        boolean isRoomDataUpdated = dbMgr.updateRoomDetails(modifiedDetails);
        if (isRoomDataUpdated) {
            Toast.makeText(getApplicationContext(), "Room Details Modified Successfully", Toast.LENGTH_LONG).show();
            Intent mgrRoomDetailsIntent = new Intent(MgrModifyRoomActivity.this, MgrRoomDetailsActivity.class);
            mgrRoomDetailsIntent.putExtra(MGR_ROOM_ID, roomId);
            mgrRoomDetailsIntent.putExtra(MGR_HOTEL_NAME, hotelName);
            mgrRoomDetailsIntent.putExtra(MGR_START_DATE, startDate);
            mgrRoomDetailsIntent.putExtra(MGR_END_DATE, endDate);
            mgrRoomDetailsIntent.putExtra(MGR_START_TIME, startTime);
            mgrRoomDetailsIntent.putExtra(MGR_OCCUPIED_STATUS, occupiedStatus);
            startActivity(mgrRoomDetailsIntent);
        }
    }

    private void roomTypeChangeListener() {
        if (roomType.equalsIgnoreCase(STANDARD_ROOM)) {
            rgRoomTypes.check(R.id.rbMgrMRdStandard);
        } else if (roomType.equalsIgnoreCase(DELUXE_ROOM)) {
            rgRoomTypes.check(R.id.rbMgrMRdDeluxe);
        } else {
            rgRoomTypes.check(R.id.rbMgrMRdSuite);
        }

        rgRoomTypes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbMgrMRdStandard) {
                    Toast.makeText(getApplicationContext(), "choice: A", Toast.LENGTH_SHORT).show();
                    roomType = STANDARD_ROOM;
                } else if (checkedId == R.id.rbMgrMRdDeluxe) {
                    Toast.makeText(getApplicationContext(), "choice: B", Toast.LENGTH_SHORT).show();
                    roomType = DELUXE_ROOM;
                } else if (checkedId == R.id.rbMgrMRdSuite) {
                    Toast.makeText(getApplicationContext(), "choice: C", Toast.LENGTH_SHORT).show();
                    roomType = SUITE_ROOM;
                }
            }
        });
    }

    private HotelRoom getRoomDetails() {
        HotelRoom hr = null;
        dbMgr = DbMgr.getInstance(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            roomId = bundle.getString(MGR_ROOM_ID);
            hotelName = bundle.getString(MGR_HOTEL_NAME);
            startDate = bundle.getString(MGR_START_DATE);
            endDate = bundle.getString(MGR_END_DATE);
            startTime = bundle.getString(MGR_START_TIME);
            occupiedStatus = bundle.getString(MGR_OCCUPIED_STATUS);
            Log.i(APP_TAG, roomId + hotelName + startDate);
        }
        hr = dbMgr.mgrGetRoomDetails(roomId, startDate, endDate, startTime, occupiedStatus);
        return hr;
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
            Intent backIntent = new Intent(MgrModifyRoomActivity.this, MgrRoomDetailsActivity.class);
            backIntent.putExtra(MGR_ROOM_ID, roomId);
            backIntent.putExtra(MGR_HOTEL_NAME, hotelName);
            backIntent.putExtra(MGR_START_DATE, startDate);
            backIntent.putExtra(MGR_END_DATE, endDate);
            backIntent.putExtra(MGR_START_TIME, startTime);
            backIntent.putExtra(MGR_OCCUPIED_STATUS, occupiedStatus);
            startActivity(backIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(MgrModifyRoomActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}