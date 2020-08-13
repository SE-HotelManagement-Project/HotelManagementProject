package com.project.hotelmanagementproject.controller.guestController;

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
import com.project.hotelmanagementproject.controller.systemAccessController.LoginActivity;
import com.project.hotelmanagementproject.model.database.DbMgr;
import com.project.hotelmanagementproject.model.DAO.HotelRoom;
import com.project.hotelmanagementproject.model.DAO.Reservation;
import com.project.hotelmanagementproject.model.DAO.Session;
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
import static com.project.hotelmanagementproject.utilities.ConstantUtils.STANDARD_ROOM;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.SUITE_ROOM;

public class GuestReservationModifyActivity extends AppCompatActivity {

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
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_modify_reservation);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        retainActivityState();
        dbMgr = DbMgr.getInstance(getApplicationContext());
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
        userName = new Session(getApplicationContext()).getUserName();
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
        String newCheckinDate = upCheckInDate.getText().toString();
        String newCheckoutDate = upCheckOutDate.getText().toString();
        startTime = upStartTime.getText().toString();
        numRooms = upNumOfRooms.getText().toString();
        //   numNights = up.getText().toString();
        numAdults = upNumOfAdultsandChildren.getText().toString();


        if (Reservation.isValidDate(newCheckinDate) && Reservation.isValidDate(newCheckoutDate)
                && Reservation.isValidRange(newCheckinDate, newCheckoutDate) && Reservation.isValidStartTime(startTime)
                && Reservation.isValidNumAdults(numAdults) && Reservation.isValidNumRooms(numRooms)) {

            getModifiedReservation(newCheckinDate, newCheckoutDate);

        } else {
            if (!Reservation.isValidNumRooms(numRooms))
                upNumOfRooms.setError("invalid Number of Rooms");
            if (!Reservation.isValidNumAdults(numAdults))
                upNumOfAdultsandChildren.setError("invalid Number of Adults & Children");
            if (!Reservation.isValidStartTime(startTime))
                upStartTime.setError("invalid start time");
            if (!Reservation.isValidDate(newCheckinDate))
                upCheckInDate.setError("invalid checkin date");
            if (!Reservation.isValidDate(newCheckoutDate))
                upCheckOutDate.setError("invalid checkout date");
            if (!Reservation.isValidRange(newCheckinDate, newCheckoutDate))
                Toast.makeText(getApplicationContext(), "invalid date range", Toast.LENGTH_SHORT).show();
        }

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

    public void getModifiedReservation(final String newCheckinDate, final String newCheckoutDate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GuestReservationModifyActivity.this);
        builder.setTitle("Update Reservation")
                .setMessage("Are you sure you want modify the reservation?" +
                        "The new price will be modified from your credit card.")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        modifyReservation(newCheckinDate, newCheckoutDate);
                    }
                }).create().show();

        Log.i(APP_TAG, hotelName + userName + newRoomType);
    }

    private void modifyReservation(String newCheckinDate, String newCheckoutDate) {
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
                Toast.makeText(getApplicationContext(), "Reservation Modified successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(GuestReservationModifyActivity.this, GuestReservationDetailsActivity.class);
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
            Intent i = new Intent(GuestReservationModifyActivity.this, GuestReservationDetailsActivity.class);
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