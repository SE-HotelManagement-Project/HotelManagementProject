package com.project.hotelmanagementproject.controller.systemAccessController;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.database.DbMgr;
import com.project.hotelmanagementproject.model.DAO.Session;
import com.project.hotelmanagementproject.model.DAO.User;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class UpdateProfileActivity extends AppCompatActivity {

    TextView tvUserName, tvRole;
    TextInputEditText etPassword, etLastName, etFirstName, etPhone, etEmail;
    TextInputEditText etStreetAddress, etCity, etState, etZipcode;
    TextInputEditText etCreditCardNum, etCreditCardExpiry;
    Spinner spnrCCType;
    Button btnSave, btnCancel;

    String userName, password, role, lastName, firstName, phone, email;
    String streetAddress, city, state, zipcode;
    String ccn, ccexp, cctype;
    String hotelAssigned;
    LinearLayout llCCN, llCCExp, llCCType;

    DbMgr userDbMgr;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        userDbMgr = DbMgr.getInstance(getApplicationContext());
        userName = new Session(getApplicationContext()).getUserName();
        user = userDbMgr.getUserDetails(userName);
        role = user.getUserRole();
        initUi();
    }

    public void initUi() {
        tvUserName = findViewById(R.id.tvUpUserName);
        etPassword = findViewById(R.id.etUpPassword);
        tvRole = findViewById(R.id.tvUpRole);

        etLastName = findViewById(R.id.etUpLastName);
        etFirstName = findViewById(R.id.etUpFirstName);
        etPhone = findViewById(R.id.etUpPhone);
        etEmail = findViewById(R.id.etUpEmail);

        etState = findViewById(R.id.etUpState);
        etStreetAddress = findViewById(R.id.etUpStreetAddress);
        etCity = findViewById(R.id.etUpCity);
        etZipcode = findViewById(R.id.etUpZipCode);

        llCCN = findViewById(R.id.llUpCCN);
        llCCExp = findViewById(R.id.llUpCCEx);
        llCCType = findViewById(R.id.llUpCCType);

        etCreditCardExpiry = findViewById(R.id.etUpCreditCardExpiry);
        etCreditCardNum = findViewById(R.id.etUpCreditCardNumber);
        spnrCCType = findViewById(R.id.spnrUpCreditCardType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.card_type, android.R.layout.simple_spinner_item);

        spnrCCType.setSelection(adapter.getPosition(user.getCreditCardtype()));

        spnrCCType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cctype = spnrCCType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cctype = user.getCreditCardtype();
            }
        });
        tvUserName.setText(user.getUserName());
        tvRole.setText(user.getUserRole());
        etPassword.setText(user.getPassword());
        etEmail.setText(user.getEmail());
        etPhone.setText(user.getPhone());
        etFirstName.setText(user.getFirstName());
        etLastName.setText(user.getLastName());

        etStreetAddress.setText(user.getStreetAddress());
        etState.setText(user.getState());
        etCity.setText(user.getCity());
        etZipcode.setText(user.getZipCode());

        hotelAssigned = new Session(getApplicationContext()).getHotelAssigned();

        Log.i("Role: ", user.getUserRole());

        if (user.getUserRole().equalsIgnoreCase("Guest")) {
            llCCExp.setVisibility(View.VISIBLE);
            llCCN.setVisibility(View.VISIBLE);
            etCreditCardExpiry.setText(user.getCreditCardExp());
            etCreditCardNum.setText(user.getCreditCardNum());

        } else {
            llCCN.setVisibility(View.GONE);
            llCCExp.setVisibility(View.GONE);
        }

        btnSave = findViewById(R.id.btnUpSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUpdatedUserData();
            }
        });
    }

    public void getUpdatedUserData() {
        Boolean isCommonValuesValid = false, isGuestValuesValid = false;
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        password = etPassword.getText().toString();
        phone = etPhone.getText().toString();
        email = etEmail.getText().toString();
        streetAddress = etStreetAddress.getText().toString();
        city = etCity.getText().toString();
        state = etState.getText().toString();
        zipcode = etZipcode.getText().toString();

        if (role.equalsIgnoreCase("Guest")) {
            ccn = etCreditCardNum.getText().toString();
            ccexp = etCreditCardExpiry.getText().toString();
            cctype = spnrCCType.getSelectedItem().toString();
            if (User.isNullorEmpty(ccn) || User.isNullorEmpty(ccexp)
                    || (!User.isValidExpiryDate(ccexp)) || ccexp.length() != 5 || ccn.length() != 16) {
                if (User.isNullorEmpty(ccn) || ccn.length() != 16)
                    etCreditCardNum.setError("invalid Credit Card");
                if (User.isNullorEmpty(ccexp) || ccexp.length() != 5 || (!User.isValidExpiryDate(ccexp)))
                    etCreditCardExpiry.setError("invalid expiry date");
                isGuestValuesValid = false;
            } else {
                isGuestValuesValid = true;
            }
            Log.i(APP_TAG, "role: Guest; " + cctype);
        } else {
            isGuestValuesValid = true;
            Log.i(APP_TAG, "role: notGuest; " + role);
        }
        if (User.isNullorEmpty(password) || User.isNullorEmpty(phone) || (phone.length() != 10)
                || User.isNullorEmpty(email) || (!User.isValidEmail(email))
                || User.isNullorEmpty(streetAddress) || User.isNullorEmpty(city)
                || User.isNullorEmpty(state) || User.isNullorEmpty(zipcode) || zipcode.length() != 5) {

            Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            if (User.isNullorEmpty(password)) etPassword.setError("invalid password");
            if (User.isNullorEmpty(email) || (!User.isValidEmail(email)))
                etEmail.setError("invalid email id");
            if (User.isNullorEmpty(phone) || phone.length() != 10)
                etPhone.setError("invalid phone number");
            if (User.isNullorEmpty(streetAddress))
                etStreetAddress.setError("invalid street address");
            if (User.isNullorEmpty(state)) etState.setError("invalid state");
            if (User.isNullorEmpty(firstName)) etFirstName.setError("invalid firstname");
            if (User.isNullorEmpty(lastName)) etLastName.setError("invalid lastname");
            if (User.isNullorEmpty(city)) etCity.setError("invalid city");
            if (User.isNullorEmpty(zipcode) || zipcode.length() != 5)
                etZipcode.setError("invalid zipcode");
            isCommonValuesValid = false;
        } else {
            isCommonValuesValid = true;
        }

        if (isCommonValuesValid && isGuestValuesValid) {
            modifyUserProfile();
        }
    }

    private void modifyUserProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfileActivity.this);
        builder.setTitle("Update Profile!")
                .setMessage("Are you sure you want to update?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        user = new User(userName, firstName, lastName, password, role, hotelAssigned, email, phone, streetAddress, city, state, zipcode, ccn, ccexp, cctype);
                        boolean isUpdated = userDbMgr.updateProfile(user);
                        if (isUpdated) {
                            Toast.makeText(getApplicationContext(), "profile Updated Successfully!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(UpdateProfileActivity.this, ViewProfileActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }).create().show();
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
            Intent intent = new Intent(this, ViewProfileActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(UpdateProfileActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ViewProfileActivity.class);
        startActivity(intent);
        // super.onBackPressed();
    }
}