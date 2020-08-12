package com.project.hotelmanagementproject.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.HotelRoom;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.utilities.ReservationUtils;
import com.project.hotelmanagementproject.utilities.UtilityFunctions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.DELUXE_ROOM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.GUEST_RESV_ID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.GUEST_RESV_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.PAID;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ROOM_OCCUPIED;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.STANDARD_ROOM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.SUITE_ROOM;

public class GuestModifyReservationActivity extends AppCompatActivity {

    String newRoomType;
    private Button btnSaveResv;
    private TextInputEditText upCheckInDate, upCheckOutDate, upStartTime,
            upNumOfRooms, upNumOfAdultsandChildren;
    private TextView tvResvId, tvHotelName, tvStartDate, tvTotalPrice, tvNumNights;
    private RadioGroup rgGuestRoomTypes;
    private RadioButton rbGuestStandard, rbGuestDeluxe, rbGuestSuit;
    private String reservationId, hotelName, startDate, checkinDate, checkoutDate, startTime;
    private String roomType, numAdults, numNights, numRooms, totalPrice;
    private String roomId;
    private DbMgr dbMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_modify_reservation);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        retainActivityState();
        init();
    }

    private void retainActivityState() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            reservationId = bundle.getString(GUEST_RESV_ID);
            startDate = bundle.getString(GUEST_RESV_START_DATE);
            Log.i(APP_TAG, reservationId + ", " + startDate);
        }
    }

    public void init() {
        tvResvId = findViewById(R.id.tvGuestMrResvId);
        tvStartDate = findViewById(R.id.tvGuestMrStartDate);
        tvHotelName = findViewById(R.id.tvGuestMrHotel);
        tvTotalPrice = findViewById(R.id.tvGuestMrTotalPrice);

        upCheckInDate = findViewById(R.id.etGuestMrDateFrom);
        upCheckOutDate = findViewById(R.id.etGuestMrDateTo);
        upStartTime = findViewById(R.id.etGuestMrStartTime);

        upNumOfRooms = findViewById(R.id.etGuestMrNumRooms);
        tvNumNights = findViewById(R.id.etGuestMrNumNights);
        upNumOfAdultsandChildren = findViewById(R.id.etGuestMrNumAdults);
        rgGuestRoomTypes = findViewById(R.id.rgGuestMRdRoomTypes);

        PopulateUI();

        btnSaveResv = findViewById(R.id.btnGuestModifyReservation);
        btnSaveResv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveClick();
            }
        });
    }

    private void onSaveClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GuestModifyReservationActivity.this);
        builder.setTitle("Update Reservation")
                .setMessage("Are you sure you want modify the reservation?" +
                        "The new price will be modified from your credit card.")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        getModifiedReservation();
                    }
                }).create().show();
    }

    private void PopulateUI() {
        Reservation reservation;
        DbMgr dbMgr = DbMgr.getInstance(getApplicationContext());
        reservation = dbMgr.guestGetResvDetails(reservationId, startDate);

        roomId = reservation.getResvRoomId();

        hotelName = reservation.getResvHotelName();
        reservationId = reservation.getReservationId();
        startDate = reservation.getStartDate();
        totalPrice = reservation.getTotalPrice();

        checkinDate = reservation.getResvCheckInDate();
        checkoutDate = reservation.getResevCheckOutDate();
        startTime = reservation.getResvStartTime();
        numRooms = reservation.getResvNumOfRooms();
        numAdults = reservation.getResvNumAdultsChildren();
        numNights = reservation.getResvNumNights();
        roomType = reservation.getResvRoomType();
        tvResvId.setText(reservationId);
        tvHotelName.setText(hotelName);
        tvStartDate.setText(startDate);
        tvTotalPrice.setText(totalPrice);

        upCheckInDate.setText(checkinDate);
        upCheckOutDate.setText(checkoutDate);
        upStartTime.setText(startTime);
        upNumOfRooms.setText(numRooms);
        upNumOfAdultsandChildren.setText(numAdults);
        tvNumNights.setText(numNights);
        roomTypeChangeListener();
    }

    private void roomTypeChangeListener() {
        if (roomType.equalsIgnoreCase(STANDARD_ROOM)) {
            rgGuestRoomTypes.check(R.id.rbGuestMrStandard);
        } else if (roomType.equalsIgnoreCase(DELUXE_ROOM)) {
            rgGuestRoomTypes.check(R.id.rbGuestMrDeluxe);
        } else {
            rgGuestRoomTypes.check(R.id.rbGuestMrSuite);
        }

        newRoomType = roomType;
        rgGuestRoomTypes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbGuestMrStandard) {
                    newRoomType = STANDARD_ROOM;
                } else if (checkedId == R.id.rbGuestMrDeluxe) {
                    newRoomType = DELUXE_ROOM;
                } else if (checkedId == R.id.rbGuestMrSuite) {
                    newRoomType = SUITE_ROOM;
                }
            }
        });
    }

    public void getModifiedReservation() {
        String newCheckinDate = upCheckInDate.getText().toString();
        String newCheckoutDate = upCheckOutDate.getText().toString();
        startTime = upStartTime.getText().toString();
        numRooms = upNumOfRooms.getText().toString();
        //   numNights = up.getText().toString();
        numAdults = upNumOfAdultsandChildren.getText().toString();
        String userName = new Session(getApplicationContext()).getUserName();

        Log.i(APP_TAG, hotelName + userName + newRoomType);
        //Log.i(APP_TAG, hotelName + userName  + newRoomType );

        dbMgr = DbMgr.getInstance(getApplicationContext());

        if (newCheckinDate != checkinDate || newCheckoutDate != checkoutDate || roomType != newRoomType) {
            HotelRoom hr = dbMgr.getRoomTypePrices(newRoomType, hotelName);

            String totalPrice = UtilityFunctions.calculateTotalReservationPrice(newCheckinDate, startTime, newCheckoutDate,
                    startTime, hr.getRoomPriceWeekDay(), hr.getRoomPriceWeekend(), hr.getHotelTax(), numRooms);

            numNights = getNumOfNights(newCheckinDate, newCheckoutDate);

            Log.i(APP_TAG, numNights + " <- nights");

            Reservation reservation = new Reservation(null, null, userName,
                    hotelName, newRoomType, numAdults, numNights,
                    numRooms, totalPrice, newCheckinDate, newCheckoutDate, startTime, startDate, PAID);

            ReservationUtils reservationUtils = new ReservationUtils(getApplicationContext(), reservation);
            Reservation newResv = reservationUtils.reserveRoom();

            if (newResv != null) {
                Log.i(APP_TAG, "complex db change");

                dbMgr.deleteReservation(reservationId);
                // move to new Resv details page
                Intent i = new Intent(GuestModifyReservationActivity.this, GuestReservationDetailsActivity.class);
                i.putExtra(GUEST_RESV_ID, newResv.getReservationId());
                i.putExtra(GUEST_RESV_START_DATE, startDate);
                startActivity(i);

            } else {
                Toast.makeText(getApplicationContext(), "Requested Modification is not possible due to unavailability", Toast.LENGTH_LONG).show();
            }
        } else {
            Reservation rsv = new Reservation(reservationId, null, userName,
                    hotelName, newRoomType, numAdults, numNights, numRooms, totalPrice, newCheckinDate, newCheckoutDate, startTime, startDate, PAID);

            Log.i(APP_TAG, "simple change");
            dbMgr.guestUpdateResvWithNoNewAddition(rsv);
            // move to new Resv details page
            Intent i = new Intent(GuestModifyReservationActivity.this, GuestReservationDetailsActivity.class);
            i.putExtra(GUEST_RESV_ID, rsv.getReservationId());
            i.putExtra(GUEST_RESV_START_DATE, startDate);
            startActivity(i);
        }
    }

    private String getNumOfNights(String newCheckinDate, String newCheckoutDate) {
        Date date1 = null, date2 = null;
        String numOfNights = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(newCheckinDate);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(newCheckoutDate);
            Log.i(newCheckinDate, " : " + date1);
            int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
            Log.i("numNights", " : " + days);

            numOfNights = String.valueOf(days);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.i(APP_TAG, e.getLocalizedMessage());
        }

        return numOfNights;
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
            Intent i = new Intent(this, GuestReservationDetailsActivity.class);
            i.putExtra(GUEST_RESV_ID, reservationId);
            i.putExtra(GUEST_RESV_START_DATE, startDate);
            startActivity(i);
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, GuestReservationDetailsActivity.class);
        i.putExtra(GUEST_RESV_ID, reservationId);
        i.putExtra(GUEST_RESV_START_DATE, startDate);
        startActivity(i);
    }
}