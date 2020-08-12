package com.project.hotelmanagementproject.controller.guestController;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.systemAccessController.LoginActivity;
import com.project.hotelmanagementproject.controller.adapters.ReserveRoomSearchResultAdapter;
import com.project.hotelmanagementproject.model.database.DbMgr;
import com.project.hotelmanagementproject.model.DAO.HotelRoom;
import com.project.hotelmanagementproject.model.DAO.Reservation;
import com.project.hotelmanagementproject.model.DAO.Session;
import com.project.hotelmanagementproject.utilities.ConstantUtils;
import com.project.hotelmanagementproject.utilities.DateTimeGenerator;
import com.project.hotelmanagementproject.utilities.UtilityFunctions;

import java.util.ArrayList;
import java.util.List;

public class GuestRequestReservationListActivity extends AppCompatActivity {


    LinearLayout llGuestRrOutput;
    ReserveRoomSearchResultAdapter reserveRoomSearchResultAdapter;
    ListView lvToReserveRoomList;
    ImageView ivGuestRrImageHeaderIcon;
    TextView tvGuestReqResvDetailsHeader;

    Session session;
    String search_hotel_name, check_in_date, start_time, check_out_date, num_of_adult_and_child, num_of_rooms;
    String search_room_type_standard , search_room_type_deluxe , search_room_type_suite ;
    String  numOfNights;
    String totalPrice;
    String selectedHotelName, selectedRoomType;
    String cardType , cardNum , cardExpiryDate , cardCvvNum ;
    String joint_room_reservation_id;
    String selectedRoomTax ;
    String guest_user_name , guest_first_name , guest_last_name ;
    String selected_room_price_weekDay , selectedRoomPriceWeekend  ;


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
        ivGuestRrImageHeaderIcon = findViewById(R.id.ivGuestRrImageHeaderIcon);
//        ivGuestRrImageHeaderIcon.setImageResource(R.drawable.hotel);
        tvGuestReqResvDetailsHeader = findViewById(R.id.tvGuestReqResvDetailsHeader);
//        tvGuestReqResvDetailsHeader.setText(ConstantUtils.GUEST_REQ_RESV_SELECTED_RESERV_DETAILS_TEXT);
        List<String> hotelNamesList = new ArrayList<String>();
        List<String> roomTypesList = new ArrayList<String>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            search_hotel_name  = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_HOTEL_NAME);
            check_in_date  = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE);
            start_time  = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME);
            check_out_date  = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE);
            num_of_adult_and_child   = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_ADULT_AND_CHLD);
            search_room_type_standard   = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_TYPE_STANDARD);

            search_room_type_deluxe    = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_DELUXE);
            search_room_type_suite   = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_SUITE);

            num_of_rooms  = extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS);
            numOfNights =  extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS  );
            totalPrice= extras.getString(ConstantUtils.GUEST_REQ_RESV_TOTAL_PRICE );

            selectedHotelName   = extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME );
            selectedRoomType  = extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE );

            cardType  = extras.getString(ConstantUtils.GUEST_REQ_RESV_CARD_TYPE   );
            cardCvvNum = extras.getString(ConstantUtils.GUEST_REQ_RESV_CARD_NUM   );
            cardExpiryDate  = extras.getString(ConstantUtils.GUEST_REQ_RESV_CARD_EXPIRY_DT   );
            cardCvvNum = extras.getString(ConstantUtils.GUEST_REQ_RESV_CARD_CVV  );
            joint_room_reservation_id = extras.getString(ConstantUtils.GUEST_REQ_RESV_RESERVID   );

            selectedRoomTax = extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TAX   );
            guest_user_name  = extras.getString(ConstantUtils.GUEST_REQ_RESV_GUEST_USER_NAME   );
            guest_first_name  = extras.getString(ConstantUtils.GUEST_REQ_RESV_GUEST_FIRST_NAME    );
            guest_last_name  = extras.getString(ConstantUtils.GUEST_REQ_RESV_GUEST_LAST_NAME    );
            selected_room_price_weekDay = extras.getString(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_DAY    );
            selectedRoomPriceWeekend= extras.getString(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_END    );

            Log.i( "ResultActivity", "0809 L 112 ResultActivity"+ search_hotel_name);
            hotelNamesList = UtilityFunctions.populateHotelNamesList(search_hotel_name);
            roomTypesList = UtilityFunctions.populateRoomsTypeList(search_room_type_standard, search_room_type_deluxe, search_room_type_suite);

            numOfNights = DateTimeGenerator.getNumOfNightsForDates(check_in_date, start_time, check_out_date, start_time );
            DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());

            List<HotelRoom> roomsAvailableForReqResvAndGrouped = dbMgr.getRoomsAvailableForReqResvAndGrouped( hotelNamesList ,
                    roomTypesList,  check_in_date,  check_out_date,  start_time, num_of_rooms ) ;
            roomsAvailableForReqResvAndGrouped = populateOtherDetails( roomsAvailableForReqResvAndGrouped,  check_in_date,
                    start_time,  check_out_date, num_of_rooms , numOfNights);
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
                    Intent intent = new Intent(GuestRequestReservationListActivity.this, GuestPendingReservationDetailsActivity.class);
                    HotelRoom selectedHotelRoom = (HotelRoom) adapterView.getItemAtPosition(i);
                    totalPrice = UtilityFunctions.calculateTotalReservationPrice( check_in_date, start_time, check_out_date, ConstantUtils.GUEST_REQ_RESV_SEARCH_END_TIME_VALUE  ,
                                                                        selectedHotelRoom.getRoomPriceWeekDay(),selectedHotelRoom.getRoomPriceWeekend(),
                                                                            selectedHotelRoom.getHotelTax(), num_of_rooms );

                    selectedHotelName = selectedHotelRoom.getHotelName();
                    selectedRoomType = selectedHotelRoom.getRoomType();
                    selectedRoomTax= selectedHotelRoom.getHotelTax();
                    selected_room_price_weekDay = selectedHotelRoom.getRoomPriceWeekDay();
                    selectedRoomPriceWeekend = selectedHotelRoom.getRoomPriceWeekend();
//                    Log.i(APP_TAG,"RoomID: "+selectedHotelRoom.getHotelRoomId());

                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_HOTEL_NAME, search_hotel_name );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE, check_in_date );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME, start_time );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE, check_out_date);
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_ADULT_AND_CHLD , num_of_adult_and_child);
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_TYPE_STANDARD , search_room_type_standard  );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_DELUXE , search_room_type_deluxe );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_SUITE , search_room_type_suite );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS, num_of_rooms);

                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME, selectedHotelName);
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE, selectedRoomType);

                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS , numOfNights );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_TOTAL_PRICE , totalPrice );

                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_TYPE   , cardType );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_NUM  , cardNum );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_EXPIRY_DT  , cardExpiryDate  );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_CVV  , cardCvvNum );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_RESERVID   , joint_room_reservation_id );

                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TAX   , selectedRoomTax );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_GUEST_USER_NAME   , guest_user_name  );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_GUEST_FIRST_NAME   , guest_first_name );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_GUEST_LAST_NAME    , guest_last_name  );

                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_DAY    , selected_room_price_weekDay  );
                    intent.putExtra(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_END     , selectedRoomPriceWeekend   );

                    startActivity(intent);
                }
            });
        }
//        Log.i("gst_req_rsv_hotel_name", "" + search_hotel_name);
//        Log.i("guest_req_resv_check_in", "" + check_in_date);
//        Log.i("gst_req_rsv__start_time", "" + start_time);
//        Log.i("check_out_date", "" + check_out_date);
//        Log.i("room_type_standard", "" + search_room_type_standard);
//        Log.i("room_type_deluxe", "" + search_room_type_deluxe);
//        Log.i("room_type_suite", "" + search_room_type_suite);
//        Log.i("num_of_rooms", "" + num_of_rooms);

    }

    public List<HotelRoom> populateOtherDetails(List<HotelRoom> roomsAvailableForReqResvAndGrouped, String startDate,
                                                String startTime, String endDate,
                                                 String numOfRooms , String numOfNights)
    {
        List<HotelRoom> hotelRooms = new ArrayList<HotelRoom>();
        String totalPrice="";
        if(UtilityFunctions.isNotNullAndEmpty(roomsAvailableForReqResvAndGrouped)){
            for(HotelRoom hotelRoom: roomsAvailableForReqResvAndGrouped){
                hotelRoom.setNumOfNights(numOfNights);
                hotelRoom.setTotalPrice(UtilityFunctions.calculateTotalReservationPrice( startDate,  startTime,  endDate,
                         startTime,  hotelRoom.getRoomPriceWeekDay(),  hotelRoom.getRoomPriceWeekend() , hotelRoom.getHotelTax() ,  numOfRooms));

                hotelRooms.add(hotelRoom);
            }
        }
        return hotelRooms;
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
            onBackClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(GuestRequestReservationListActivity.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        onBackClick();
        //    super.onBackPressed();
    }

    private void onBackClick() {
        Intent intent = new Intent(this, GuestRequestReservationActivity.class);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_HOTEL_NAME, search_hotel_name);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE, check_in_date);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME, start_time);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE, check_out_date);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_ADULT_AND_CHLD, num_of_adult_and_child);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_TYPE_STANDARD, search_room_type_standard);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_DELUXE, search_room_type_deluxe);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_SUITE, search_room_type_suite);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS, num_of_rooms);

        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME, selectedHotelName);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE, selectedRoomType);

        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS, numOfNights);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_TOTAL_PRICE, totalPrice);

        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_TYPE, cardType);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_NUM, cardNum);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_EXPIRY_DT, cardExpiryDate);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_CARD_CVV, cardCvvNum);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_RESERVID, joint_room_reservation_id);

        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TAX, selectedRoomTax);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_GUEST_USER_NAME, guest_user_name);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_GUEST_FIRST_NAME, guest_first_name);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_GUEST_LAST_NAME, guest_last_name);

        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_DAY, selected_room_price_weekDay);
        intent.putExtra(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_END, selectedRoomPriceWeekend);
        startActivity(intent);
    }

}