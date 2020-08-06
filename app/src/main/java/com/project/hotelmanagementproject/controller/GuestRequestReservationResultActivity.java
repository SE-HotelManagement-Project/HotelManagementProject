package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.ReserveRoomSearchResultAdapter;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.utilities.ConstantUtils;
import com.project.hotelmanagementproject.utilities.DateTimeGenerator;
import com.project.hotelmanagementproject.utilities.UtilityFunctions;

import java.util.ArrayList;
import java.util.List;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class GuestRequestReservationResultActivity extends AppCompatActivity {


    LinearLayout llGuestRrOutput;
    ReserveRoomSearchResultAdapter reserveRoomSearchResultAdapter;
    ListView  lvToReserveRoomList;

    Session session;
    String search_hotel_name , check_in_date , start_time ;

    String check_out_date;
    String num_of_adult_and_child;
    String search_room_type_standard;
    String search_room_type_deluxe;
    String search_room_type_suite;
    String num_of_rooms;
    String numOfNights;
    String totalPrice;
    String selectedHotelName, selectedRoomType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_request_reservation_result);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    public void init() {

        llGuestRrOutput = findViewById(R.id.llGuestRrOutput);
        //ankit changes
        llGuestRrOutput.setVisibility(View.VISIBLE);
        lvToReserveRoomList = findViewById(R.id.lvToReserveRoomList);
        List<String> hotelNamesList = new ArrayList<String>();
        List<String> roomTypesList = new ArrayList<String>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            search_hotel_name = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_HOTEL_NAME);
            check_in_date = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE);
            start_time = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME);
            check_out_date = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE);
            num_of_adult_and_child   = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_ADULT_AND_CHLD);
            search_room_type_standard = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_TYPE_STANDARD);
            search_room_type_deluxe = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_DELUXE);
            search_room_type_suite = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_SUITE);
            num_of_rooms = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS);
            Log.i( "ResultActivity", "0809 L 112 ResultActivity"+ search_hotel_name);
            if(ConstantUtils.ALL.equals(search_hotel_name)){
                hotelNamesList.add(ConstantUtils.HM_MAVERICK);
                hotelNamesList.add(ConstantUtils.HM_RANGER);
                hotelNamesList.add(ConstantUtils.HM_WILLIAMS);
                hotelNamesList.add(ConstantUtils.HM_SHARD);
                hotelNamesList.add(ConstantUtils.HM_LIBERTY);
            }
            else{
                hotelNamesList.add(search_hotel_name.trim().toUpperCase());
            }
            if( UtilityFunctions.isNotNullAndEmpty(search_room_type_standard)){
                roomTypesList.add(search_room_type_standard);
            }
            if(UtilityFunctions.isNotNullAndEmpty(search_room_type_deluxe) ){
                roomTypesList.add(search_room_type_deluxe);
            }
            if( UtilityFunctions.isNotNullAndEmpty(search_room_type_suite)){
                roomTypesList.add(search_room_type_suite);
            }
            numOfNights = DateTimeGenerator.getNumOfNightsForDates(check_in_date, start_time, check_out_date, start_time );
            DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
//            List<HotelRoom> allAvailableRoomsForReqReservation = dbMgr.getAllAvailableRoomsForReqReservation(hotelNamesList,  roomTypesList);
//            List<Reservation> reservedRoomsForStartAndEndDate = dbMgr.getReservedRoomsForStartAndEndDate( hotelNamesList , roomTypesList ,
//                    check_in_date, check_out_date,  start_time, num_of_rooms);
            List<HotelRoom> roomsAvailableForReqResvAndGrouped = dbMgr.getRoomsAvailableForReqResvAndGrouped( hotelNamesList ,  roomTypesList,  check_in_date,  check_out_date,  start_time, num_of_rooms) ;
//            List<HotelRoom> nonReservedRoomsList = findNonReservedRoomsFromAllRooms( allAvailableRoomsForReqReservation ,  reservedRoomsForStartAndEndDate);
            if(UtilityFunctions.isNotNullAndEmpty(roomsAvailableForReqResvAndGrouped)){
                reserveRoomSearchResultAdapter = new ReserveRoomSearchResultAdapter(this, roomsAvailableForReqResvAndGrouped);
                lvToReserveRoomList.setAdapter(reserveRoomSearchResultAdapter);
                reserveRoomSearchResultAdapter.notifyDataSetChanged();
            }
            else{
                Toast.makeText(getApplicationContext(), "No rooms available for selected input items", Toast.LENGTH_LONG).show();
            }

            lvToReserveRoomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent reqResvDetailsIntent = new Intent(GuestRequestReservationResultActivity.this, GuestRequestReservationDetailsActivity.class);
                    HotelRoom selectedHotelRoom = (HotelRoom) adapterView.getItemAtPosition(i);
                    totalPrice = UtilityFunctions.calculateTotalReservationPrice( check_in_date, start_time, check_out_date, ConstantUtils.GUEST_REQ_RESV_SEARCH_END_TIME_VALUE  , selectedHotelRoom.getRoomPriceWeekDay(),selectedHotelRoom.getRoomPriceWeekend(), selectedHotelRoom.getHotelTax() );
                    Log.i(APP_TAG,"RoomID: "+selectedHotelRoom.getHotelRoomId());
                    selectedHotelName = selectedHotelRoom.getHotelName();
                    selectedRoomType = selectedHotelRoom.getRoomType();

                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_HOTEL_NAME, search_hotel_name );
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE, check_in_date );
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME, start_time );
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE, check_out_date);
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_ADULT_AND_CHLD , num_of_adult_and_child);
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_TYPE_STANDARD , search_room_type_standard  );
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_DELUXE , search_room_type_deluxe );
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_SUITE , search_room_type_suite );
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS, num_of_rooms.toString());

                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME, selectedHotelName);
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE, selectedRoomType);
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS , numOfNights );
                    reqResvDetailsIntent.putExtra(ConstantUtils.GUEST_REQ_RESV_TOTAL_PRICE , totalPrice );
                    startActivity(reqResvDetailsIntent);
                }
            });
        }
        Log.i("gst_req_rsv_hotel_name", "" + search_hotel_name);
        Log.i("guest_req_resv_check_in", "" + check_in_date);
        Log.i("gst_req_rsv__start_time", "" + start_time);
        Log.i("check_out_date", "" + check_out_date);
        Log.i("room_type_standard", "" + search_room_type_standard);
        Log.i("room_type_deluxe", "" + search_room_type_deluxe);
        Log.i("room_type_suite", "" + search_room_type_suite);
        Log.i("num_of_rooms", "" + num_of_rooms);

    }

    public List<HotelRoom> findNonReservedRoomsFromAllRooms(List<HotelRoom> allAvailableRoomsForReqReservation , List<Reservation> reservedRoomsForStartAndEndDate){
       List<HotelRoom> nonReservedHotelRooms = new ArrayList<HotelRoom>();
        Reservation reservation = null;
        HotelRoom hotelRoom = null;
        if(allAvailableRoomsForReqReservation != null && !allAvailableRoomsForReqReservation.isEmpty()){
            for (int i=0; i < allAvailableRoomsForReqReservation.size(); i++){
                hotelRoom  = allAvailableRoomsForReqReservation.get(i) ;
                boolean roomReserved = false;
                if(reservedRoomsForStartAndEndDate!= null ){
                    for(int j= 0; j < reservedRoomsForStartAndEndDate.size(); j++){
                        reservation = reservedRoomsForStartAndEndDate.get(j);
                        if(hotelRoom!= null && reservation!= null && hotelRoom.getHotelName().equals(reservation.getResvHotelName()) && hotelRoom.getRoomType().equals(reservation.getResvRoomType())){
                            roomReserved = true;
                            break;
                        }
                    }
                    if(!roomReserved){
                        nonReservedHotelRooms.add(hotelRoom);
                    }
                }
            }
        }
        return nonReservedHotelRooms;
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
            Intent intent = new Intent(this, GuestRequestReservationActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void logout() {
        Intent i = new Intent(GuestRequestReservationResultActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}