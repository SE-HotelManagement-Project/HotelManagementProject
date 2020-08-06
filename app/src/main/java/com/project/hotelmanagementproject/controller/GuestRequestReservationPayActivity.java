package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;
import com.project.hotelmanagementproject.utilities.ConstantUtils;
import com.project.hotelmanagementproject.utilities.UtilityFunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GuestRequestReservationPayActivity extends AppCompatActivity {


    ImageView ivGuestReqRsvPayHotelIcon;
    TextInputEditText tvGuestReqRsvPayCvvNum;
    Button btnGuestReqRsvPayReservation;
    TextView tvGuestReqRsvPayCardNum , tvGuestReqRsvPayCardType , tvGuestReqRsvPayCardExpiry;

    String search_hotel_name , check_in_date , start_time , check_out_date , num_of_adult_and_child , num_of_rooms ;
    String search_room_type_standard , search_room_type_deluxe , search_room_type_suite ;
    String numOfNights ,  totalPrice;
    String selectedHotelName, selectedRoomType;
    String cardType, cardNum, cardExpiryDate , cardCvvNum;
    String guest_user_name, guest_first_name, guest_last_name;
    String joint_room_reservation_id;
    Session session ;

    List<String> hotelNamesList = new ArrayList<String>();
    List<String> roomTypesList = new ArrayList<String>();


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
        ivGuestReqRsvPayHotelIcon = findViewById(R.id.ivGuestReqRsvPayHotelIcon);
        tvGuestReqRsvPayCvvNum = findViewById(R.id.tvGuestReqRsvPayCvvNum);

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
        }

        if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_MAVERICK)) {
            ivGuestReqRsvPayHotelIcon.setImageResource(R.drawable.ic_hotel_maverick);
        } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_LIBERTY)) {
            ivGuestReqRsvPayHotelIcon.setImageResource(R.drawable.ic_hotel_liberty);
        } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_SHARD)) {
            ivGuestReqRsvPayHotelIcon.setImageResource(R.drawable.ic_hotel_shard);
        } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_RANGER)) {
            ivGuestReqRsvPayHotelIcon.setImageResource(R.drawable.ic_hotel_ranger);
        } else if (search_hotel_name.equalsIgnoreCase(ConstantUtils.HM_WILLIAMS)) {
            ivGuestReqRsvPayHotelIcon.setImageResource(R.drawable.ic_hotel_williams);
        }
    session = new Session(getApplicationContext());
        DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
        User user = dbMgr.getUserDetails(session.getUserName());
        if(user!= null){
            cardExpiryDate = user.getCreditCardExp();
            cardNum= user.getCreditCardNum();
            cardType = user.getCreditCardtype();
            guest_first_name = user.getUserName();
            guest_first_name = user.getFirstName();
            guest_last_name = user.getLastName();
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
                 joint_room_reservation_id  = reserveRoom( guest_user_name,  guest_first_name,  guest_last_name);
                Bundle extras = getIntent().getExtras();
                Intent intent = new Intent(GuestRequestReservationPayActivity.this, GuestReqResvPayConfirmRoomDetailsActivity.class);
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
                    startActivity(intent);
                }

            }
        });
    }

    public String reserveRoom(String guestUserName, String guestFirstName, String guestLastName){
//        String resvId = createReservationId(selectedHotelName);

        DbMgr DbMgr = com.project.hotelmanagementproject.model.DbMgr.getInstance(getApplication());
//        hotelNamesList = UtilityFunctions.populateHotelNamesList(search_hotel_name);
//        roomTypesList = UtilityFunctions.populateRoomsTypeList(search_room_type_standard, search_room_type_deluxe, search_room_type_suite);
        List<HotelRoom> hotelRoomsList = DbMgr.getRoomsForReqResv( selectedHotelName ,  selectedRoomType,  check_in_date,  check_out_date,  start_time) ;
        Reservation reservation;
        HotelRoom hotelRoom;
        String reservationId = ConstantUtils.EMPTY;
        int numOfRoomsInt = Integer.parseInt(num_of_rooms);
        Log.i("0809 161 PayActivity", "hotelRoomsList.size()="+ hotelRoomsList.size());
        Log.i("0809 161 PayActivity", "numOfrooms ="+ num_of_rooms);
        if(UtilityFunctions.isNotNullAndEmpty(hotelRoomsList) && numOfRoomsInt <= hotelRoomsList.size()){
            for(int i = 0;i < numOfRoomsInt; i++){
                 hotelRoom = hotelRoomsList.get(i);
//                reservationId = createReservationId(selectedHotelName);
                if(ConstantUtils.EMPTY.equals(reservationId)){
                    reservationId = createReservationId(hotelRoom.getHotelName());
                }
                Log.i("0809 174 ", "PayActivity reservationId"+ reservationId);
                reservation = new Reservation();
                reservation.setReservationId(reservationId);
                reservation.setResvRoomId(hotelRoom.getHotelRoomId());
                reservation.setResvNumNights(numOfNights);
                reservation.setResvNumAdultsChildren(num_of_adult_and_child);
                reservation.setResvStartTime(start_time);
                reservation.setResvCheckInDate(check_in_date);
                reservation.setResevCheckOutDate(check_out_date);
                reservation.setTotalPrice(totalPrice);
                reservation.setResvRoomType(selectedRoomType);
                reservation.setResvHotelName(selectedHotelName);
                reservation.setResvUserName(guestUserName);
                reservation.setResvFirstName(guestFirstName);
                reservation.setResvLastName(guestLastName);
                reservation.setResvNumOfRooms(num_of_rooms);
                reservation.setPaymentStatus(ConstantUtils.PAID);
                Log.i("0809 187 PayActivity", "reservation ="+ reservation.toString());
                DbMgr.addNewReserv(reservation);
            }
        }
        return reservationId;
    }
    public String createReservationId(String hotel_name){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateString = formatter.format(date);
        String[] dateSplit1 = dateString.split("-");
        String[] dateSplit2 = dateString.split(":");

        String reservationId = hotel_name.toLowerCase() +"_"+ dateSplit1[1] + dateSplit1[2].substring(0,2)+ dateSplit1[0].substring(2)+
                "_"+ dateSplit2[0].substring(dateSplit2[0].length()-2)+dateSplit2[1];
        System.out.println("dateString= "+ dateString);
        System.out.println("reservationId= "+ reservationId);
        return reservationId;
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