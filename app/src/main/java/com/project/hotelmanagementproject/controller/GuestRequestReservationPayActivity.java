package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;
import com.project.hotelmanagementproject.utilities.ConstantUtils;
import com.project.hotelmanagementproject.utilities.UtilityFunctions;

public class GuestRequestReservationPayActivity extends AppCompatActivity {


    ImageView ivGuestReqRsvPayHotelIcon;
    TextInputEditText tvGuestReqRsvPayCvvNum;
    Button btnGuestReqRsvPayReservation;
    EditText tvGuestReqRsvPayCardNum , tvGuestReqRsvPayCardType , tvGuestReqRsvPayCardExpiry;

    String search_hotel_name , check_in_date , start_time , check_out_date , num_of_adult_and_child , num_of_rooms ;
    String search_room_type_standard , search_room_type_deluxe , search_room_type_suite ;
    String numOfNights ,  totalPrice;
    String selectedHotelName, selectedRoomType;
    String cardType, cardNum, cardExpiryDate , cardCvvNum;
    DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
    Session session ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_request_reservation_pay);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }
    public void init() {
        btnGuestReqRsvPayReservation = findViewById(R.id.btnGuestReqRsvPayReservation);
        tvGuestReqRsvPayCardNum = findViewById(R.id.tvGuestReqRsvPayCardNum);
        tvGuestReqRsvPayCardType = findViewById(R.id.tvGuestReqRsvPayCardType);
        tvGuestReqRsvPayCardExpiry = findViewById(R.id.tvGuestReqRsvPayCardExpiry);

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
            cardCvvNum = extras.getString(ConstantUtils.GUEST_REQ_RESV_CARD_CVV  );
        }
    session = new Session(getApplicationContext());;
        User user = dbMgr.getUserDetails(session.getUserName());
        if(user!= null){
            cardExpiryDate = user.getCreditCardExp();
            cardNum= user.getCreditCardNum();
            cardType = user.getCreditCardtype();
        }

        if(UtilityFunctions.isNotNullAndEmpty(cardExpiryDate)){
            tvGuestReqRsvPayCardExpiry.setText(cardExpiryDate);
        }
        if(UtilityFunctions.isNotNullAndEmpty(cardNum)){
            tvGuestReqRsvPayCardNum.setText(cardNum);
        }
        if(UtilityFunctions.isNotNullAndEmpty(cardType)){
            tvGuestReqRsvPayCardType.setText(cardType);
        }
        if(UtilityFunctions.isNotNullAndEmpty(cardCvvNum)){
            tvGuestReqRsvPayCvvNum.setText(cardCvvNum);
        }



        btnGuestReqRsvPayReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DbMgr DbMgr = com.project.hotelmanagementproject.model.DbMgr.getInstance(getApplication());
                Bundle extras = getIntent().getExtras();
                Intent i = new Intent(GuestRequestReservationPayActivity.this, GuestReqResvPayConfirmRoomDetailsActivity.class);
                if (extras != null) {
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME, extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME));
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE , extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE));
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE , extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE) );
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME, extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME));
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS, extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS));
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS,extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS));
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE, extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE));
                    i.putExtra(ConstantUtils.GUEST_REQ_RESV_TOTAL_PRICE,extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_TOTAL_PRICE));
                    startActivity(i);
                }

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
            Intent intent = new Intent(this, GuestRequestReservationDetailsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void logout() {
        Intent i = new Intent(GuestRequestReservationPayActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}