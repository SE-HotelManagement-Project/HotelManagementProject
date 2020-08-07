package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.utilities.ConstantUtils;

public class GuestReqResvPayConfirmRoomDetailsActivity extends AppCompatActivity {

    ImageView ivGuestReqResPayConfDetailIcon;
    TextView tvGuestReqResPayConfDetailReservId,tvGuestReqResPayConfDetailHotelName, tvGuestReqResPayConfDetailCheckIn,
    tvGuestReqResPayConfDetailCheckOut , tvGuestReqResPayConfDetailStartTime,tvGuestReqResPayConfDetailNumRooms,
            tvGuestReqResPayConfDetailNumNights, tvGuestReqResPayConfDetailRoomType, tvGuestReqResPayConfDetailRoomPrice;
    String search_hotel_name , check_in_date , start_time , check_out_date , num_of_adult_and_child , num_of_rooms ;
    String search_room_type_standard , search_room_type_deluxe , search_room_type_suite ;
    String  numOfNights;
    String totalPrice;
    String selectedHotelName, selectedRoomType;
    String cardType , cardNum , cardExpiryDate , cardCvvNum ;
    String joint_room_reservation_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_req_resv_pay_confirm_room_details);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    public void init() {

        ivGuestReqResPayConfDetailIcon = findViewById(R.id.ivGuestReqResPayConfDetailIcon);
        tvGuestReqResPayConfDetailReservId = findViewById(R.id.tvGuestReqResPayConfDetailReservId);
        tvGuestReqResPayConfDetailHotelName = findViewById(R.id.tvGuestReqResPayConfDetailHotelName);
        tvGuestReqResPayConfDetailCheckIn = findViewById(R.id.tvGuestReqResPayConfDetailCheckIn);
        tvGuestReqResPayConfDetailCheckOut = findViewById(R.id.tvGuestReqResPayConfDetailCheckOut);
        tvGuestReqResPayConfDetailStartTime = findViewById(R.id.tvGuestReqResPayConfDetailStartTime);
        tvGuestReqResPayConfDetailNumRooms = findViewById(R.id.tvGuestReqResPayConfDetailNumRooms);
        tvGuestReqResPayConfDetailNumNights = findViewById(R.id.tvGuestReqResPayConfDetailNumNights);
        tvGuestReqResPayConfDetailRoomType = findViewById(R.id.tvGuestReqResPayConfDetailRoomType);
        tvGuestReqResPayConfDetailRoomPrice = findViewById(R.id.tvGuestReqResPayConfDetailRoomPrice);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String hotelName = extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME);

            if (hotelName.equalsIgnoreCase(ConstantUtils.HM_MAVERICK)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_maverick);
            } else if (hotelName.equalsIgnoreCase(ConstantUtils.HM_LIBERTY)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_liberty);
            } else if (hotelName.equalsIgnoreCase(ConstantUtils.HM_SHARD)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_shard);
            } else if (hotelName.equalsIgnoreCase(ConstantUtils.HM_RANGER)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_ranger);
            } else if (hotelName.equalsIgnoreCase(ConstantUtils.HM_WILLIAMS)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_williams);
            }

            tvGuestReqResPayConfDetailHotelName.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME));
            tvGuestReqResPayConfDetailCheckIn.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE ));
            tvGuestReqResPayConfDetailCheckOut.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE ) );
            tvGuestReqResPayConfDetailStartTime.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME));
            tvGuestReqResPayConfDetailNumRooms.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS));
            tvGuestReqResPayConfDetailNumNights.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS));
            tvGuestReqResPayConfDetailRoomType.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE));
            tvGuestReqResPayConfDetailRoomPrice.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_TOTAL_PRICE));
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
            Intent intent = new Intent(GuestReqResvPayConfirmRoomDetailsActivity.this, HomeActivity.class);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
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
            }
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void logout() {
        Intent i = new Intent(GuestReqResvPayConfirmRoomDetailsActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}