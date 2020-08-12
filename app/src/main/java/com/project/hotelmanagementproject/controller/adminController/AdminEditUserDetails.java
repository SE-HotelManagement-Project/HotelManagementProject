package com.project.hotelmanagementproject.controller.adminController;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.systemAccessController.LoginActivity;
import com.project.hotelmanagementproject.model.database.DbMgr;
import com.project.hotelmanagementproject.model.DAO.Session;
import com.project.hotelmanagementproject.model.DAO.User;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ADMIN_IP_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ADMIN_SEARCH_USER_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ADMIN_USER_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class AdminEditUserDetails extends AppCompatActivity {

    TextView tvUgpUserName, tvUgpRole, tvUgpFirstName, tvUgpLastName;
    TextInputEditText etUgpPassword, etUgpPhone, etUgpEmail;
    TextInputEditText etUgpStreetAddress, etUgpCity, etUgpState, etUgpZipCode;

    Button btnUpSave;

    String userName, password, role, lastName, firstName, phone, email;
    String streetAddress, city, state, zipcode;
    String ccn, ccexp, cctype;
    LinearLayout llCCN, llCCExp, llCCType;
    //String ADM_EDIT_USER= "adm_edit_user";

    DbMgr userDbMgr;
    User user;
    String returnIntent, lastNameIp;
    String hotelAssigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user_profile);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        userDbMgr = DbMgr.getInstance(getApplicationContext());
        getIntentData();
        userDbMgr = DbMgr.getInstance(getApplicationContext());
        user = userDbMgr.getUserDetails(userName);
        role = user.getUserRole();
        initUi();
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            returnIntent = bundle.getString(ACTIVITY_RETURN_STATE);
            lastNameIp = bundle.getString(ADMIN_IP_NAME);
            userName = bundle.getString(ADMIN_USER_NAME);
        }
        Log.i(APP_TAG, "return state: " + returnIntent);
    }

    public void initUi() {
        tvUgpUserName = findViewById(R.id.tvUgpUserName);
        etUgpPassword = findViewById(R.id.etUgpPassword);
        tvUgpRole = findViewById(R.id.tvUgpRole);

        tvUgpLastName = findViewById(R.id.tvUgpLastName);
        tvUgpFirstName = findViewById(R.id.tvUgpFirstName);
        etUgpPhone = findViewById(R.id.etUgpPhone);
        etUgpEmail = findViewById(R.id.etUgpEmail);

        etUgpState = findViewById(R.id.etUgpState);
        etUgpStreetAddress = findViewById(R.id.etUgpStreetAddress);
        etUgpCity = findViewById(R.id.etUgpCity);
        etUgpZipCode = findViewById(R.id.etUgpZipCode);

        getUserData();
        populateUserInfoUI();

        btnUpSave = findViewById(R.id.btnUpSave);
        hotelAssigned = new Session(getApplicationContext()).getHotelAssigned();
        btnUpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUpdatedDataAndSave();
            }
        });
    }

    private void getUserData() {
        userName = user.getUserName();
        role = user.getUserRole();
        password = user.getPassword();
        email = user.getEmail();
        phone = user.getPhone();
        hotelAssigned = user.getHotelAssigned();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        streetAddress = user.getStreetAddress();
        state = user.getState();
        city = user.getCity();
        zipcode = user.getZipCode();
        ccn = user.getCreditCardNum();
        ccexp = user.getCreditCardExp();
        cctype = user.getCreditCardtype();
    }

    private void populateUserInfoUI() {
        tvUgpUserName.setText(userName);
        tvUgpRole.setText(role);
        etUgpPassword.setText(password);
        etUgpEmail.setText(email);
        etUgpPhone.setText(phone);
        tvUgpFirstName.setText(firstName);
        tvUgpLastName.setText(lastName);
        etUgpStreetAddress.setText(streetAddress);
        etUgpState.setText(state);
        etUgpCity.setText(city);
        etUgpZipCode.setText(zipcode);
    }

    private void updateUserProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminEditUserDetails.this);
        builder.setTitle("Update Profile!")
                .setMessage("Are you sure you want to update?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        user = new User(userName, firstName, lastName, password, role,
                                hotelAssigned, email, phone, streetAddress,
                                city, state, zipcode, ccn, ccexp, cctype);
                        userName = user.getUserName();
                        boolean isUpdated = userDbMgr.updateProfile(user);
                        if (isUpdated) {
                            Toast.makeText(getApplicationContext(), "profile Updated Successfully!", Toast.LENGTH_LONG).show();
                            Intent userNameIntent = new Intent(AdminEditUserDetails.this, AdminViewUserDetails.class);
                            userNameIntent.putExtra(ADMIN_USER_NAME, user.getUserName());
                            userNameIntent.putExtra(ADMIN_IP_NAME, lastNameIp);
                            userNameIntent.putExtra(ACTIVITY_RETURN_STATE, ADMIN_SEARCH_USER_ACTIVITY);
                            startActivity(userNameIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }).create().show();
    }


    public void getUpdatedDataAndSave() {
        firstName = tvUgpFirstName.getText().toString();
        lastName = tvUgpLastName.getText().toString();
        password = etUgpPassword.getText().toString();
        phone = etUgpPhone.getText().toString();
        email = etUgpEmail.getText().toString();
        streetAddress = etUgpStreetAddress.getText().toString();
        city = etUgpCity.getText().toString();
        state = etUgpState.getText().toString();
        zipcode = etUgpZipCode.getText().toString();

        Log.i(APP_TAG, password + phone + email + zipcode);
        Log.i(APP_TAG, email + User.isValidEmail(email));

        if (User.isNullorEmpty(password) || User.isNullorEmpty(phone) || (phone.length() != 10)
                || User.isNullorEmpty(email) || (!User.isValidEmail(email))
                || User.isNullorEmpty(streetAddress) || User.isNullorEmpty(city)
                || User.isNullorEmpty(state) || User.isNullorEmpty(zipcode) || zipcode.length() != 5) {

            Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            if (User.isNullorEmpty(password)) etUgpPassword.setError("invalid password");
            if (User.isNullorEmpty(email) || (!User.isValidEmail(email)))
                etUgpEmail.setError("invalid email id");
            if (User.isNullorEmpty(phone) || phone.length() != 10)
                etUgpPhone.setError("invalid phone number");
            if (User.isNullorEmpty(streetAddress))
                etUgpStreetAddress.setError("invalid street address");
            if (User.isNullorEmpty(state)) etUgpState.setError("invalid state");
            if (User.isNullorEmpty(city)) etUgpCity.setError("invalid city");
            if (User.isNullorEmpty(zipcode) || zipcode.length() != 5)
                etUgpZipCode.setError("invalid zipcode");
        } else {
            updateUserProfile();
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
            Intent backIntent = new Intent(AdminEditUserDetails.this, AdminViewUserDetails.class);
            backIntent.putExtra(ADMIN_USER_NAME, userName);
            backIntent.putExtra(ADMIN_IP_NAME, lastNameIp);
            backIntent.putExtra(ACTIVITY_RETURN_STATE, ADMIN_SEARCH_USER_ACTIVITY);
            startActivity(backIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(AdminEditUserDetails.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(AdminEditUserDetails.this, AdminViewUserDetails.class);
        backIntent.putExtra(ADMIN_USER_NAME, userName);
        backIntent.putExtra(ADMIN_IP_NAME, lastNameIp);
        backIntent.putExtra(ACTIVITY_RETURN_STATE, ADMIN_SEARCH_USER_ACTIVITY);
        startActivity(backIntent);
    }
}
