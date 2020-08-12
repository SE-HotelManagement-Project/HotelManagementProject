package com.project.hotelmanagementproject.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;

public class AdminEditUserDetails extends AppCompatActivity {

    TextView tvUgpUserName, tvUgpRole,tvUgpFirstName,tvUgpLastName;
    TextInputEditText etUgpPassword, etUgpPhone, etUgpEmail;
    TextInputEditText etUgpStreetAddress, etUgpCity, etUgpState, etUgpZipCode;

    Button btnUpSave;

    String userName, password, role, lastName, firstName, phone, email;
    String streetAddress, city, state, zipcode;
    String ccn, ccexp, cctype;
    LinearLayout llCCN, llCCExp, llCCType;
    String ADM_EDIT_USER= "adm_edit_user";

    DbMgr userDbMgr;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user_profile);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        userDbMgr = DbMgr.getInstance(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            userName = bundle.getString(ADM_EDIT_USER);
        }

        userDbMgr =  DbMgr.getInstance(getApplicationContext());

        user = userDbMgr.getUserDetails(userName);
        role = user.getUserRole();
        initUi();
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

        tvUgpUserName.setText(user.getUserName());
        tvUgpRole.setText(user.getUserRole());
        etUgpPassword.setText(user.getPassword());
        etUgpEmail.setText(user.getEmail());
        etUgpPhone.setText(user.getPhone());
        tvUgpFirstName.setText(user.getFirstName());
        tvUgpLastName.setText(user.getLastName());

        etUgpStreetAddress.setText(user.getStreetAddress());
        etUgpState.setText(user.getState());
        etUgpCity.setText(user.getCity());
        etUgpZipCode.setText(user.getZipCode());

//        Log.i("Role: ", user.getUserRole());

        btnUpSave = findViewById(R.id.btnUpSave);
        final String hotelAssigned = new Session(getApplicationContext()).getHotelAssigned();
        btnUpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminEditUserDetails.this);
                builder.setTitle("Update Profile!")
                        .setMessage("Are you sure you want to update?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                getUpdatedUserData();
                                user = new User(userName, firstName, lastName, password, role, hotelAssigned, email, phone, streetAddress, city, state, zipcode, ccn, ccexp, cctype);
                                boolean isUpdated = userDbMgr.updateProfile(user);
                                if (isUpdated) {
                                    Toast.makeText(getApplicationContext(), "profile Updated Successfully!", Toast.LENGTH_LONG).show();
                                    Intent userNameIntent = new Intent(AdminEditUserDetails.this, AdminViewUserDetails.class);

                                    userNameIntent.putExtra(ADM_EDIT_USER,user.getUserName());

                                    startActivity(userNameIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).create().show();
            }
        });
    }

    public void getUpdatedUserData() {
        firstName = tvUgpFirstName.getText().toString();
        lastName = tvUgpLastName.getText().toString();
        password = etUgpPassword.getText().toString();
        phone = etUgpPhone.getText().toString();
        email = etUgpEmail.getText().toString();
        streetAddress = etUgpStreetAddress.getText().toString();
        city = etUgpCity.getText().toString();
        state = etUgpState.getText().toString();
        zipcode = etUgpZipCode.getText().toString();
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
            backIntent.putExtra(ADM_EDIT_USER, userName);
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
        backIntent.putExtra(ADM_EDIT_USER, userName);
        startActivity(backIntent);
    }
}
