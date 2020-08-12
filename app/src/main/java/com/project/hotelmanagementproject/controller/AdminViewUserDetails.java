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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;


public class AdminViewUserDetails  extends AppCompatActivity {
    TextView tvAvpUserName, tvAvpRole, tvAvpLastName, tvAvpFirstName, tvAvpPhone, tvAvpEmail;
    TextView tvAvpStreetAddress, tvAvpCity, tvAvpState, tvAvpZipCode;
    Button btnAvpRemoveProfile, btnAvpUpdateProfile;
    DbMgr DbManager;


    User userDetails;

    String userName, role, lastName, firstName, phone, email, streetAddress, city, state, zipCode;
    String ADM_EDIT_USER= "adm_edit_user";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_details);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            userName = bundle.getString(ADM_EDIT_USER);
        }

         DbManager =  DbMgr.getInstance(getApplicationContext());

        userDetails = DbManager.getUserDetails(userName);
        initUi();
    }

    private void initUi() {
        tvAvpUserName = findViewById(R.id.tvAvpUserName);
        tvAvpRole = findViewById(R.id.tvAvpRole);
        tvAvpLastName = findViewById(R.id.tvAvpLastName);
        tvAvpFirstName = findViewById(R.id.tvAvpFirstName);
        tvAvpPhone = findViewById(R.id.tvAvpPhone);
        tvAvpEmail = findViewById(R.id.tvAvpEmail);
        tvAvpStreetAddress = findViewById(R.id.tvAvpStreetAddress);
        tvAvpCity = findViewById(R.id.tvAvpCity);
        tvAvpState = findViewById(R.id.tvAvpState);
        tvAvpZipCode = findViewById(R.id.tvAvpZipCode);

        tvAvpUserName.setText(userDetails.getUserName());
        tvAvpRole.setText(userDetails.getUserRole());
        tvAvpLastName.setText(userDetails.getLastName());
        tvAvpFirstName.setText(userDetails.getFirstName());
        tvAvpPhone.setText(userDetails.getPhone());
        tvAvpEmail.setText(userDetails.getEmail());
        tvAvpStreetAddress.setText(userDetails.getStreetAddress());
        tvAvpCity.setText(userDetails.getCity());
        tvAvpState.setText(userDetails.getState());
        tvAvpZipCode.setText(userDetails.getZipCode());

        btnAvpUpdateProfile = findViewById(R.id.btnAvpUpdateProfile);

        btnAvpUpdateProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent userNameIntent = new Intent(AdminViewUserDetails.this, AdminEditUserDetails.class);
                userNameIntent.putExtra(ADM_EDIT_USER,userName);

                startActivity(userNameIntent);

            }
        });

        btnAvpRemoveProfile = findViewById(R.id.btnAvpRemoveProfile);

        btnAvpRemoveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminViewUserDetails.this);

                builder
                        .setTitle("Remove Profile")
                        .setMessage("Are you sure you want to remove profile?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                int delete = DbManager.deleteUserProfile(tvAvpUserName.getText().toString());
                                if (delete == 1) {
                                    Toast.makeText(getApplicationContext(), "Profile Deleted Successfully", Toast.LENGTH_LONG ).show();
                                } else{
                                    Toast.makeText(getApplicationContext(), "Profile Delete Failed", Toast.LENGTH_LONG).show();
                                }
                                startActivity(new Intent(AdminViewUserDetails.this, AdminSearchUserActivity.class));
                            }
                        }).create().show();
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
            Intent backIntent = new Intent(AdminViewUserDetails.this, AdminSearchUserActivity.class);
            backIntent.putExtra(ADM_EDIT_USER, userName);
            startActivity(backIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(AdminViewUserDetails.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(AdminViewUserDetails.this, AdminSearchUserActivity.class);
        backIntent.putExtra(ADM_EDIT_USER, userName);
        startActivity(backIntent);
    }
}
