package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.utilities.ConstantUtils;

public class GuestReqResvPayConfirmRoomDetailsActivity extends AppCompatActivity {

    ImageView ivGuestReqResPayConfDetailIcon;
    TextView tvGuestReqResPayConfDetailReservId,tvGuestReqResPayConfDetailHotelName, tvGuestReqResPayConfDetailCheckIn,
    tvGuestReqResPayConfDetailCheckOut , tvGuestReqResPayConfDetailStartTime,tvGuestReqResPayConfDetailNumRooms,
            tvGuestReqResPayConfDetailNumNights, tvGuestReqResPayConfDetailRoomType, tvGuestReqResPayConfDetailRoomPrice,
            tvGuestReqResPayRPWeEnd,tvGuestReqResPayRPWeDay, tvGuestReqResPayTax;
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
        tvGuestReqResPayRPWeDay = findViewById(R.id.tvGuestReqResPayRPWeDay);
        tvGuestReqResPayRPWeEnd = findViewById(R.id.tvGuestReqResPayRPWeEnd);
        tvGuestReqResPayTax = findViewById(R.id.tvGuestReqResPayTax);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
//            String hotelName = extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME);
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


            if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_MAVERICK)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_maverick);
            } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_LIBERTY)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_liberty);
            } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_SHARD)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_shard);
            } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_RANGER)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_ranger);
            } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_WILLIAMS)) {
                ivGuestReqResPayConfDetailIcon.setImageResource(R.drawable.ic_hotel_williams);
            }

            tvGuestReqResPayConfDetailHotelName.setText(selectedHotelName);
            tvGuestReqResPayConfDetailCheckIn.setText(check_in_date);
            tvGuestReqResPayConfDetailCheckOut.setText(check_out_date );
            tvGuestReqResPayConfDetailStartTime.setText(start_time);
            tvGuestReqResPayConfDetailNumRooms.setText(num_of_rooms);
            tvGuestReqResPayConfDetailNumNights.setText(numOfNights);
            tvGuestReqResPayConfDetailRoomType.setText(selectedRoomType);
            tvGuestReqResPayConfDetailRoomPrice.setText(totalPrice);
            tvGuestReqResPayConfDetailReservId.setText(joint_room_reservation_id);

            tvGuestReqResPayRPWeDay.setText(selected_room_price_weekDay);
            tvGuestReqResPayRPWeEnd.setText(selectedRoomPriceWeekend);
            tvGuestReqResPayTax.setText(selectedRoomTax);
//
//commenting for now
//        tvGuestReqResPayConfDetailHotelName.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_HOTEL_NAME));
//            tvGuestReqResPayConfDetailCheckIn.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE ));
//            tvGuestReqResPayConfDetailCheckOut.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE ) );
//            tvGuestReqResPayConfDetailStartTime.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME));
//            tvGuestReqResPayConfDetailNumRooms.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS));
//            tvGuestReqResPayConfDetailNumNights.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS));
//            tvGuestReqResPayConfDetailRoomType.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TYPE));
//            tvGuestReqResPayConfDetailRoomPrice.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_TOTAL_PRICE));
//            tvGuestReqResPayConfDetailReservId.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_RESERVID));
//
//            tvGuestReqResPayRPWeDay.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_DAY ));
//            tvGuestReqResPayRPWeEnd.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_PRICE_WK_END ));
//            tvGuestReqResPayTax.setText(extras.getString(ConstantUtils.GUEST_REQ_RESV_SELECTED_ROOM_TAX));

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
            onBackClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(GuestReqResvPayConfirmRoomDetailsActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        onBackClick();
        // super.onBackPressed();
    }

    private void onBackClick() {
        Intent intent = new Intent(GuestReqResvPayConfirmRoomDetailsActivity.this, HomeActivity.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
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
        }
        startActivity(intent);
    }


}