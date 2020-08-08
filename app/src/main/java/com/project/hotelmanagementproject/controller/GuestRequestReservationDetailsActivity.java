package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.utilities.ConstantUtils;

public class GuestRequestReservationDetailsActivity extends AppCompatActivity {

    TextView tvGuestReqResvDetailsHeader, tvGuestReqResvDetailsHotelName , tvGuestReqResvDetailsCheckIn ;
    TextView tvGuestReqResvDetailsCheckOut , tvGuestReqResvDetailsStartTime ,tvGuestReqResvDetailsNumRooms ;
    TextView tvGuestReqResvDetailsNumOfNights,tvGuestReqResvDetailsRoomType , tvGuestReqResvDetailsRoomPrice,
            tvGuestReqResvDetailsNumAdultAndChild ,tvGuestReqResvDetailsTax, tvGuestReqResvDetailsRPWeDay,
            tvGuestReqResvDetailsRPWeEnd;
    Button btnReq_Resv_GuestPayReservation;
    ImageView ivGuestReqResvDetailsIcon;



    String search_hotel_name , check_in_date , start_time , check_out_date , num_of_adult_and_child , num_of_rooms ;
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
        setContentView(R.layout.activity_guest_request_reservation_details);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvGuestReqResvDetailsHotelName = findViewById(R.id.tvGuestReqResvDetailsHotelName);
        tvGuestReqResvDetailsCheckIn = findViewById(R.id.tvGuestReqResvDetailsCheckIn);
        tvGuestReqResvDetailsCheckOut = findViewById(R.id.tvGuestReqResvDetailsCheckOut);
        tvGuestReqResvDetailsCheckOut = findViewById(R.id.tvGuestReqResvDetailsCheckOut);
        tvGuestReqResvDetailsStartTime = findViewById(R.id.tvGuestReqResvDetailsStartTime);
        tvGuestReqResvDetailsNumRooms = findViewById(R.id.tvGuestReqResvDetailsNumRooms);
        tvGuestReqResvDetailsNumOfNights = findViewById(R.id.tvGuestReqResvDetailsNumOfNights);
        tvGuestReqResvDetailsRoomType = findViewById(R.id.tvGuestReqResvDetailsRoomType);
        tvGuestReqResvDetailsRoomPrice = findViewById(R.id.tvGuestReqResvDetailsRoomPrice);
        ivGuestReqResvDetailsIcon = findViewById(R.id.ivGuestReqResvDetailsIcon);
        tvGuestReqResvDetailsNumAdultAndChild = findViewById(R.id.tvGuestReqResvDetailsNumAdultAndChild);
        btnReq_Resv_GuestPayReservation = findViewById(R.id.btnReq_Resv_GuestPayReservation);

        tvGuestReqResvDetailsTax = findViewById(R.id.tvGuestReqResvDetailsTax);
        tvGuestReqResvDetailsRPWeDay = findViewById(R.id.tvGuestReqResvDetailsRPWeDay);
        tvGuestReqResvDetailsRPWeEnd = findViewById(R.id.tvGuestReqResvDetailsRPWeEnd);
        init();
    }

    public void init() {
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

            if (selectedHotelName.equalsIgnoreCase(ConstantUtils.HM_MAVERICK)) {
                ivGuestReqResvDetailsIcon.setImageResource(R.drawable.ic_hotel_maverick);
            } else if (selectedHotelName.equalsIgnoreCase(ConstantUtils.HM_LIBERTY)) {
                ivGuestReqResvDetailsIcon.setImageResource(R.drawable.ic_hotel_liberty);
            } else if (selectedHotelName.equalsIgnoreCase(ConstantUtils.HM_SHARD)) {
                ivGuestReqResvDetailsIcon.setImageResource(R.drawable.ic_hotel_shard);
            } else if (selectedHotelName.equalsIgnoreCase(ConstantUtils.HM_RANGER)) {
                ivGuestReqResvDetailsIcon.setImageResource(R.drawable.ic_hotel_ranger);
            } else if (selectedHotelName.equalsIgnoreCase(ConstantUtils.HM_WILLIAMS)) {
                ivGuestReqResvDetailsIcon.setImageResource(R.drawable.ic_hotel_williams);
            }

            if (selectedHotelName != null && !ConstantUtils.EMPTY.equals(selectedHotelName)) {
                tvGuestReqResvDetailsHotelName.setText(selectedHotelName);
            }
            if (check_in_date != null && !ConstantUtils.EMPTY.equals(check_in_date)) {
                tvGuestReqResvDetailsCheckIn.setText(check_in_date);
            }
            if (start_time != null && !ConstantUtils.EMPTY.equals(start_time)) {
                tvGuestReqResvDetailsStartTime.setText(start_time);
            }
            if (check_out_date != null && !ConstantUtils.EMPTY.equals(check_out_date)) {
                tvGuestReqResvDetailsCheckOut.setText(check_out_date);
            }
            if (num_of_rooms != null && !ConstantUtils.EMPTY.equals(num_of_rooms)) {
                tvGuestReqResvDetailsNumRooms.setText(num_of_rooms);
            }
            if (num_of_adult_and_child != null && !ConstantUtils.EMPTY.equals(num_of_adult_and_child)) {
                tvGuestReqResvDetailsNumAdultAndChild.setText(num_of_adult_and_child);
            }
            if (numOfNights != null && !ConstantUtils.EMPTY.equals(numOfNights)) {
                tvGuestReqResvDetailsNumOfNights.setText(numOfNights);
            }
            if (selectedRoomType != null && !ConstantUtils.EMPTY.equals(selectedRoomType)) {
                tvGuestReqResvDetailsRoomType.setText(selectedRoomType);
            }
            if (totalPrice != null && !ConstantUtils.EMPTY.equals(totalPrice)) {
                tvGuestReqResvDetailsRoomPrice.setText(totalPrice);
            }

            if (selectedRoomTax != null && !ConstantUtils.EMPTY.equals(selectedRoomTax)) {
                tvGuestReqResvDetailsTax.setText(selectedRoomTax);
            }
            if (selected_room_price_weekDay != null && !ConstantUtils.EMPTY.equals(selected_room_price_weekDay)) {
                tvGuestReqResvDetailsRPWeDay.setText(selected_room_price_weekDay);
            }
            if (selectedRoomPriceWeekend != null && !ConstantUtils.EMPTY.equals(selectedRoomPriceWeekend)) {
                tvGuestReqResvDetailsRPWeEnd.setText(selectedRoomPriceWeekend);
            }
        }

        btnReq_Resv_GuestPayReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestRequestReservationDetailsActivity.this, GuestRequestReservationPayActivity.class);
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
            Intent intent = new Intent(GuestRequestReservationDetailsActivity.this, GuestRequestReservationResultActivity.class);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void logout() {
        Intent i = new Intent(GuestRequestReservationDetailsActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}