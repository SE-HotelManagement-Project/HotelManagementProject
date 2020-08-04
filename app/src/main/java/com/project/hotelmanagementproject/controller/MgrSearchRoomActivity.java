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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.ManagerRoomListAdpater;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_END_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOME_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_OCCUPIED_STATUS;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_ROOM_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_SEARCH_ROOM_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_SEARCH_ROOM_IP;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_TIME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.OCCUPANCY_NOT_CHECKED;

//
public class MgrSearchRoomActivity extends AppCompatActivity {
    Button btnMgrSrSearch;
    LinearLayout llSearchRoomIp;
    LinearLayout llSearchRoomOp;
    EditText etNumRooms;
    Spinner spnrSrHotel;
    ListView lvRoomList;
    ArrayList<HotelRoom> roomList;
    ManagerRoomListAdpater searchRoomAdapter;
    String returnIntent, startDate, endDate, startTime, searchRoomIp;
    String hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_search_room);
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
            returnIntent = bundle.getString(MGR_ACTIVITY_RETURN_STATE);
            if (!returnIntent.equalsIgnoreCase(MGR_HOME_ACTIVITY)) {
                searchRoomIp = bundle.getString(MGR_SEARCH_ROOM_IP);
                hotelName = bundle.getString(MGR_HOTEL_NAME);
                startDate = bundle.getString(MGR_START_DATE);
                endDate = bundle.getString(MGR_END_DATE);
                startTime = bundle.getString(MGR_START_TIME);
                viewDataInList();
            }
        }

        Log.i(APP_TAG, "return state: " + returnIntent);
    }

    public void init() {
        etNumRooms = findViewById(R.id.etMgrSrRoomNum);
        llSearchRoomIp = findViewById(R.id.llSearchRoomIp);
        llSearchRoomOp = findViewById(R.id.llSearchRoomOp);
        llSearchRoomIp.setVisibility(View.VISIBLE);
        llSearchRoomOp.setVisibility(View.GONE);
        lvRoomList = findViewById(R.id.lvRoomList);
        btnMgrSrSearch = findViewById(R.id.btnMgrSrSearch);
        spnrSrHotel = findViewById(R.id.spnrMgrSrHotelName);
        spnrSrHotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hotelName = spnrSrHotel.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                hotelName = "MAVERICK";
            }
        });

        roomList = new ArrayList<>();

        btnMgrSrSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDataInList();
            }
        });

        lvRoomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent roomDetailsIntent = new Intent(MgrSearchRoomActivity.this, MgrRoomDetailsActivity.class);
                HotelRoom item = (HotelRoom) adapterView.getItemAtPosition(i);
                Log.i(APP_TAG, "RoomID: " + item.getHotelRoomId());
                roomDetailsIntent.putExtra(MGR_ROOM_ID, item.getHotelRoomId());
                roomDetailsIntent.putExtra(MGR_SEARCH_ROOM_IP, searchRoomIp);
                roomDetailsIntent.putExtra(MGR_HOTEL_NAME, item.getHotelName());
                roomDetailsIntent.putExtra(MGR_START_DATE, item.getStartDate());
                roomDetailsIntent.putExtra(MGR_END_DATE, item.getEndDate());
                roomDetailsIntent.putExtra(MGR_START_TIME, item.getStartTime());
                roomDetailsIntent.putExtra(MGR_OCCUPIED_STATUS, OCCUPANCY_NOT_CHECKED);
                roomDetailsIntent.putExtra(MGR_ACTIVITY_RETURN_STATE, MGR_SEARCH_ROOM_ACTIVITY);
                startActivity(roomDetailsIntent);
            }
        });
    }

    private void viewDataInList() {
        DbMgr hotelRoomDbMgr = DbMgr.getInstance(getApplicationContext());
        Date startDateFormat = new Date(System.currentTimeMillis());
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(startDateFormat);
        Date endDateFormat = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        endDate = new SimpleDateFormat("yyyy-MM-dd").format(endDateFormat);
        if (returnIntent.equalsIgnoreCase(MGR_HOME_ACTIVITY)) {
            searchRoomIp = etNumRooms.getText().toString();
        } else {
            Log.i(APP_TAG, "srip: " + searchRoomIp);
        }

        Log.i(APP_TAG, startDate + " to " + endDate);


        if (null == searchRoomIp || searchRoomIp.isEmpty() || searchRoomIp.equalsIgnoreCase("")) {
            etNumRooms.setError("enter valid input");
            Toast.makeText(getApplicationContext(), "invalid room number", Toast.LENGTH_LONG).show();
        } else {
            llSearchRoomIp.setVisibility(View.GONE);
            llSearchRoomOp.setVisibility(View.VISIBLE);
            roomList = hotelRoomDbMgr.getSearchRoomList(hotelName, searchRoomIp, startDate, endDate, "12:00 PM");
            searchRoomAdapter = new ManagerRoomListAdpater(this, roomList);
            lvRoomList.setAdapter(searchRoomAdapter);
            searchRoomAdapter.notifyDataSetChanged();
        }
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
            if (llSearchRoomOp.getVisibility() == View.VISIBLE) {
                llSearchRoomIp.setVisibility(View.VISIBLE);
                llSearchRoomOp.setVisibility(View.GONE);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(MgrSearchRoomActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (llSearchRoomOp.getVisibility() == View.VISIBLE) {
            llSearchRoomIp.setVisibility(View.VISIBLE);
            llSearchRoomOp.setVisibility(View.GONE);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

//    private void viewDataInList(){
//        HotelRoomDbMgr hotelRoomDbMgr = HotelRoomDbMgr.getInstance(getApplicationContext());
//        Cursor c = hotelRoomDbMgr.getRoomList("102");
//        if(c.getCount() == 0){
//            Log.e("search room Query Err: ", "No data");
//        }else{
//            while (c.moveToNext()){
//                Log.i("room details: ",c.getString(c.getColumnIndex(COL_ROOM_NUM)));
//                Log.i("room details: ",c.getString(c.getColumnIndex(COL_HOTEL_ROOM_ID)));
//                Log.i("room details: ",c.getString(c.getColumnIndex(COL_HOTEL_ROOM_TYPE)));
//            }
//        }
//    }
}