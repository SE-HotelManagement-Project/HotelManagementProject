package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.utilities.ConstantUtils;
import com.project.hotelmanagementproject.utilities.DateTimeGenerator;
import com.project.hotelmanagementproject.utilities.UtilityFunctions;

import java.util.ArrayList;
import java.util.List;

public class GuestRequestReservationActivity extends AppCompatActivity {

    LinearLayout llGuestRrInput,llGuestRrOutput;

    Button btnSearchHotel;
    Button btnGuestRrSearchRoom;
    CardView cvGuestRoomSearchList;
    Spinner spnrGuestSrHotelName;
    TextInputEditText etGuestRrCheckInFrom , etGuestRrCheckOutDateTo , etStartTime, etnumAdultAndChild;

    CheckBox cbGuestRrStandard, cbGuestRrDeluxe , cbGuestRrSuite;
    EditText etGuestRrtNumberOfRoom;
    Session session;
    String search_hotel_name , check_in_date, start_time, check_out_date,num_of_adult_and_child, num_of_rooms ;
    String search_room_type_standard, search_room_type_deluxe, search_room_type_suite;
    List<String> roomTypeSpinnerList = new ArrayList<String>();
    String toastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_request_reservation);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init() {
        llGuestRrInput = findViewById(R.id.llGuestRrInput);
        llGuestRrInput.setVisibility(View.VISIBLE);
//        llGuestRrOutput = findViewById(R.id.llGuestRrOutput);
//        llGuestRrOutput.setVisibility(View.GONE);
        spnrGuestSrHotelName = findViewById(R.id.spnrGuestSrHotelName);

        etGuestRrCheckInFrom = findViewById(R.id.etGuestRrCheckInFrom);
        etGuestRrCheckInFrom.setText(DateTimeGenerator.getDateString());

        etStartTime = findViewById(R.id.etStartTime);
        etStartTime.setText(DateTimeGenerator.getDefaultTimeForReservRoom());

        etGuestRrCheckOutDateTo = findViewById(R.id.etGuestRrCheckOutDateTo);
        etGuestRrCheckOutDateTo.setText(DateTimeGenerator.getTommrrowDate());

        etnumAdultAndChild = findViewById(R.id.etnumAdultAndChild);
        etnumAdultAndChild.setText("2");

        cbGuestRrStandard = (CheckBox) findViewById(R.id.cbGuestRrStandard);
        cbGuestRrDeluxe = (CheckBox) findViewById(R.id.cbGuestRrDeluxe);
        cbGuestRrSuite = (CheckBox) findViewById(R.id.cbGuestRrSuite);
        etGuestRrtNumberOfRoom = findViewById(R.id.etGuestRrtNumberOfRoom);
        etGuestRrtNumberOfRoom.setText("1");
        btnGuestRrSearchRoom = findViewById(R.id.btnGuestRrSearchRoom);

        performPageLoadParametersSetOperations();


        btnGuestRrSearchRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(!ConstantUtils.EMPTY.equals(validateInputFields())){
//                    Intent intent = new Intent(GuestRequestReservationActivity.this, GuestRequestReservationActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(GuestRequestReservationActivity.this, " Please enter valid values for form fields", Toast.LENGTH_LONG).show();
//                }
//                else{
                    performOperationForSearchButtonClicked();
//                }


    }});

    }

    public void performOperationForSearchButtonClicked() {

        search_hotel_name = spnrGuestSrHotelName.getSelectedItem().toString().toUpperCase();
        check_in_date  = etGuestRrCheckInFrom.getText().toString();
        start_time = etStartTime.getText().toString();
        check_out_date = etGuestRrCheckOutDateTo.getText().toString();
        num_of_adult_and_child = etnumAdultAndChild.getText().toString();
        search_room_type_standard = cbGuestRrStandard.isChecked()? ConstantUtils.STANDARD_ROOM: ConstantUtils.EMPTY;
        search_room_type_deluxe  = cbGuestRrDeluxe.isChecked()? ConstantUtils.DELUXE_ROOM: ConstantUtils.EMPTY;
        search_room_type_suite  = cbGuestRrSuite.isChecked()? ConstantUtils.SUITE_ROOM: ConstantUtils.EMPTY;
        num_of_rooms = etGuestRrtNumberOfRoom.getText().toString();

        Intent i = new Intent(GuestRequestReservationActivity.this, GuestRequestReservationResultActivity.class);
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_HOTEL_NAME, search_hotel_name );
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE, check_in_date );
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_START_TIME, start_time );
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE, check_out_date);
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_ADULT_AND_CHLD , num_of_adult_and_child);
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_TYPE_STANDARD , search_room_type_standard  );
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_DELUXE , search_room_type_deluxe );
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_ROOM_TYPE_SUITE , search_room_type_suite );
        i.putExtra(ConstantUtils.GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS, num_of_rooms);
        startActivity(i);

    }

    public void performPageLoadParametersSetOperations(){




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
        }

        roomTypeSpinnerList.add(ConstantUtils.ALL);
        roomTypeSpinnerList.add(ConstantUtils.HM_MAVERICK);
        roomTypeSpinnerList.add(ConstantUtils.HM_RANGER);
        roomTypeSpinnerList.add(ConstantUtils.HM_WILLIAMS);
        roomTypeSpinnerList.add(ConstantUtils.HM_SHARD);
        roomTypeSpinnerList.add(ConstantUtils.HM_LIBERTY);

        if(UtilityFunctions.isNotNullAndEmpty(search_hotel_name)){
            spnrGuestSrHotelName.setSelection(roomTypeSpinnerList.indexOf(search_hotel_name));
        }
        if( UtilityFunctions.isNotNullAndEmpty(check_in_date)){
            etGuestRrCheckInFrom.setText(check_in_date);
        }
        if(UtilityFunctions.isNotNullAndEmpty(start_time)){
            etStartTime.setText(start_time);
        }
        if(UtilityFunctions.isNotNullAndEmpty(check_out_date)){
            etGuestRrCheckOutDateTo.setText(check_out_date);
        }
        if(UtilityFunctions.isNotNullAndEmpty(num_of_adult_and_child)){
            etnumAdultAndChild.setText(num_of_adult_and_child);
        }
        if(UtilityFunctions.isNotNullAndEmpty(search_room_type_standard)){
            cbGuestRrStandard.setChecked(true);
        }
        if(UtilityFunctions.isNotNullAndEmpty(search_hotel_name)){
            cbGuestRrDeluxe.setChecked(true);
        }
        if(UtilityFunctions.isNotNullAndEmpty(search_room_type_suite)){
            cbGuestRrSuite.setChecked(true);
        }
        if(UtilityFunctions.isNotNullAndEmpty(num_of_rooms)){
            etGuestRrtNumberOfRoom.setText(num_of_rooms);
        }
    }

    public String validateInputFields(){
        String alertMessage="";

            if (ConstantUtils.EMPTY.equals(etGuestRrCheckInFrom.getText())) {
                alertMessage = "Please select a valid check in date";
            }
            if (ConstantUtils.EMPTY.equals(etStartTime.getText())) {
                alertMessage = "Please select a valid start time";
            }
            if (ConstantUtils.EMPTY.equals(etGuestRrCheckOutDateTo.getText())) {
                alertMessage = "Please select a valid check out date";
            }
            if (ConstantUtils.EMPTY.equals(etnumAdultAndChild.getText())) {
                alertMessage = "Please select a valid number for number of adults and child";
            }
            if (ConstantUtils.EMPTY.equals(etGuestRrtNumberOfRoom.getText())) {
                alertMessage = "Please select a valid number of room";
            }
            if ((!cbGuestRrStandard.isChecked() && !cbGuestRrSuite.isChecked() && !cbGuestRrDeluxe.isChecked())) {
                alertMessage = "Please select a valid room type";
            }
        return alertMessage;
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
        Intent i = new Intent(GuestRequestReservationActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}