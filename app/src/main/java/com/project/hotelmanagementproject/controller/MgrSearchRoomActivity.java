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

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.SearchRoomAdpater;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MgrSearchRoomActivity extends AppCompatActivity {
    Button btnMgrSrSearch;
    LinearLayout llSearchRoomIp;
    LinearLayout llSearchRoomOp;
    EditText etNumRooms;
    Spinner spnrSrHotel;
    ListView lvRoomList;
    ArrayList<HotelRoom> roomList;
    SearchRoomAdpater searchRoomAdapter;
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
    }

    private void viewDataInList() {
        DbMgr hotelRoomDbMgr = DbMgr.getInstance(getApplicationContext());
        String roomNum = etNumRooms.getText().toString();
        String startDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        if (null == roomNum || roomNum.isEmpty() || roomNum.equalsIgnoreCase("")) {
            etNumRooms.setError("enter valid input");
            Toast.makeText(getApplicationContext(), "invalid room number", Toast.LENGTH_LONG).show();
        } else {
            llSearchRoomIp.setVisibility(View.GONE);
            llSearchRoomOp.setVisibility(View.VISIBLE);
            roomList = hotelRoomDbMgr.getSearchRoomList(roomNum, startDate, hotelName);
            searchRoomAdapter = new SearchRoomAdpater(this, roomList);
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
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return true;
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
        super.onBackPressed();
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