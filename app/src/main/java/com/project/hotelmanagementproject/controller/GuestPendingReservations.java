package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.GuestPendingResevationAdapter;
import com.project.hotelmanagementproject.controller.adapters.GuestReservationSummaryAdapter;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;
import com.project.hotelmanagementproject.utilities.ConstantUtils;
import com.project.hotelmanagementproject.utilities.UtilityFunctions;

import java.util.ArrayList;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class GuestPendingReservations extends AppCompatActivity {

    private User userinfo;
    private DbMgr DbManager;
    String search_hotel_name  = "", check_in_date , start_time , check_out_date , num_of_adult_and_child , num_of_rooms ,selectedRoomTax,selected_room_price_weekDay,selectedRoomPriceWeekend;
    ArrayList<HotelRoom> pendingReservationList;
    ListView lvToPendingReservationList;
    GuestPendingResevationAdapter guestPendingResevationAdapter;
    String totalPrice;
    String search_room_type_standard= "" , search_room_type_deluxe= "" , search_room_type_suite= "" ;
    String  numOfNights;
    String cardType  = "" , cardNum = "", cardExpiryDate = "", cardCvvNum = "",joint_room_reservation_id = "";
    String guest_user_name= "" , guest_first_name= "" , guest_last_name= "" ;

    String selectedHotelName, selectedRoomType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_pending_reservations);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        DbManager = DbMgr.getInstance(getApplicationContext());
        userinfo = DbManager.getUserDetails(new Session(getApplicationContext()).getUserName());
        lvToPendingReservationList = findViewById(R.id.lvToPendingReservationList);
        showPendingReservations();
    }

    public void showPendingReservations(){
        pendingReservationList = DbManager.getGuestPendingResevationList(userinfo.getUserName());
        guestPendingResevationAdapter = new GuestPendingResevationAdapter(this, pendingReservationList);
        lvToPendingReservationList.setAdapter(guestPendingResevationAdapter);
        guestPendingResevationAdapter.notifyDataSetChanged();
        //lvToPendingReservationList
        lvToPendingReservationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GuestPendingReservations.this, GuestRequestReservationDetailsActivity.class);
                HotelRoom selectedHotelRoom = (HotelRoom) adapterView.getItemAtPosition(i);
                check_in_date = selectedHotelRoom.getStartDate();
                start_time = selectedHotelRoom.getStartTime();
                check_out_date = selectedHotelRoom.getEndDate();
                num_of_rooms = selectedHotelRoom.getNumOfRooms();
                selectedHotelName = selectedHotelRoom.getHotelName();
                selectedRoomType = selectedHotelRoom.getRoomType();
                num_of_adult_and_child = selectedHotelRoom.getNumOfAdultChildren();
                selectedRoomTax= selectedHotelRoom.getHotelTax();
                selected_room_price_weekDay = selectedHotelRoom.getRoomPriceWeekDay();
                selectedRoomPriceWeekend = selectedHotelRoom.getRoomPriceWeekend();


                totalPrice = UtilityFunctions.calculateTotalReservationPrice( check_in_date, start_time, check_out_date, ConstantUtils.GUEST_REQ_RESV_SEARCH_END_TIME_VALUE  ,
                        selectedHotelRoom.getRoomPriceWeekDay(),selectedHotelRoom.getRoomPriceWeekend(),
                        selectedHotelRoom.getHotelTax(), num_of_rooms );
                Log.i(APP_TAG,"RoomID: "+selectedHotelRoom.getHotelRoomId());


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if (id == android.R.id.home) {
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(GuestPendingReservations.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

}
