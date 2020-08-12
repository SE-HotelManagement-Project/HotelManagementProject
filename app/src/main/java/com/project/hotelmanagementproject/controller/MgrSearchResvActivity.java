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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.ManagerResvListAdapter;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.HOME_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_RESV_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_RESV_LIST_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_TIME;

public class MgrSearchResvActivity extends AppCompatActivity {
    Button btnMgrSrRsvSearch;
    LinearLayout llSearchResvIp;
    LinearLayout llSearchResvOp;
    TextInputEditText etStartDate;
    TextInputEditText etStartTime;
    TextView tvRsvHotel;
    ListView lvResvList;
    ArrayList<Reservation> resvList;
    ManagerResvListAdapter resvListAdapter;
    String returnIntent, startDate, startTime;
    String hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_search_resv);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        getReturnState();
    }

    private void getReturnState() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            returnIntent = bundle.getString(ACTIVITY_RETURN_STATE);
            if (!returnIntent.equalsIgnoreCase(HOME_ACTIVITY)) {
                hotelName = bundle.getString(MGR_HOTEL_NAME);
                startDate = bundle.getString(MGR_START_DATE);
                startTime = bundle.getString(MGR_START_TIME);
                viewDataInList();
                llSearchResvIp.setVisibility(View.GONE);
                llSearchResvOp.setVisibility(View.VISIBLE);

            }
        }

        Log.i(APP_TAG, "return state: " + returnIntent);
    }

    public void init() {
        etStartDate = findViewById(R.id.etMgrSRsvStartDate);
        etStartTime = findViewById(R.id.etMgrSRsvStartTime);
        tvRsvHotel = findViewById(R.id.tvMgrSRsvHotelName);
        llSearchResvIp = findViewById(R.id.llMgrSRsvIp);
        llSearchResvOp = findViewById(R.id.llMgrSRsvOp);
        llSearchResvIp.setVisibility(View.VISIBLE);
        llSearchResvOp.setVisibility(View.GONE);
        lvResvList = findViewById(R.id.lvMgrResvList);
        btnMgrSrRsvSearch = findViewById(R.id.btnMgrSRsvSearch);

        resvList = new ArrayList<>();
        populateInputFields();

        btnMgrSrRsvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDate = etStartDate.getText().toString();
                startTime = etStartTime.getText().toString();

                Log.i(APP_TAG, startDate + "; valid: " + Reservation.isValidDate(startDate));
                Log.i(APP_TAG, startTime + "; valid: " + Reservation.isValidStartTime(startTime));

                if (Reservation.isValidStartTime(startTime) && Reservation.isValidDate(startDate)) {
                    viewDataInList();
                    llSearchResvIp.setVisibility(View.GONE);
                    llSearchResvOp.setVisibility(View.VISIBLE);
                } else {
                    if (!Reservation.isValidDate(startDate)) etStartDate.setError("Invalid date");
                    if (!Reservation.isValidStartTime(startTime))
                        etStartTime.setError("Invalid time");
                }
            }
        });

        lvResvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent roomDetailsIntent = new Intent(MgrSearchResvActivity.this, MgrResvDetailsActivity.class);
                Reservation item = (Reservation) adapterView.getItemAtPosition(i);
                Log.i(APP_TAG, "RoomID: " + item.getReservationId());
                roomDetailsIntent.putExtra(MGR_RESV_ID, item.getReservationId());
                roomDetailsIntent.putExtra(MGR_HOTEL_NAME, item.getResvHotelName());
                roomDetailsIntent.putExtra(MGR_START_DATE, item.getStartDate());
                roomDetailsIntent.putExtra(MGR_START_TIME, item.getResvStartTime());
                roomDetailsIntent.putExtra(ACTIVITY_RETURN_STATE, MGR_RESV_LIST_ACTIVITY);
                startActivity(roomDetailsIntent);
            }
        });
    }

    public void populateInputFields() {
        hotelName = new Session(getApplicationContext()).getHotelAssigned();
        Date startDateFormat = new Date(System.currentTimeMillis());
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(startDateFormat);
        startTime = new SimpleDateFormat("hh:mm", Locale.US).format(startDateFormat);

        etStartDate.setText(startDate);
        etStartTime.setText(startTime);
        tvRsvHotel.setText(hotelName);

        Log.i(APP_TAG, "time format: " + Reservation.isValidStartTime(startTime));
        Log.i(APP_TAG, "time format: " + startTime);
        Log.i(APP_TAG, "ci format: " + startDate);
        Log.i(APP_TAG, "hotel assigned: " + new Session(getApplicationContext()).getHotelAssigned());
    }

    private void viewDataInList() {
        DbMgr resvDbMgr = DbMgr.getInstance(getApplicationContext());
        resvList = resvDbMgr.mgrGetReservationList(hotelName, startDate, startTime);
        resvListAdapter = new ManagerResvListAdapter(this, resvList);
        lvResvList.setAdapter(resvListAdapter);
        resvListAdapter.notifyDataSetChanged();
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
            if (llSearchResvOp.getVisibility() == View.VISIBLE) {
                llSearchResvIp.setVisibility(View.VISIBLE);
                llSearchResvOp.setVisibility(View.GONE);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(MgrSearchResvActivity.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (llSearchResvOp.getVisibility() == View.VISIBLE) {
            llSearchResvIp.setVisibility(View.VISIBLE);
            llSearchResvOp.setVisibility(View.GONE);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}