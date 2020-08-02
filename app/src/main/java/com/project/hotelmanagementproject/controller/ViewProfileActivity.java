package com.project.hotelmanagementproject.controller;

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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;
//
public class ViewProfileActivity extends AppCompatActivity {
    private Button btnAdminVpModify;

    private TextView tvUserName, tvPassword, tvRole, tvLastName, tvFirstName, tvPhone, tvEmail;
    private TextView tvStreetAddress, tvCity, tvState, tvZipcode;
    private TextView tvCreditCardNum, tvCreditCardExpiry, tvCardType;
    private Button btnModify;

    // String userName, password, role, lastName, firstName, phone, email;
    //  String streetAddress, city, state, zipcode;
    //  String ccn = "", ccexp = "";
    private LinearLayout llCCN, llCCExp, llCCType;
    private User userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        DbMgr userDbMgr = DbMgr.getInstance(getApplicationContext());
        userinfo = userDbMgr.getUserDetails(new Session(getApplicationContext()).getUserName());
        initUi();
    }

    public void initUi() {
        tvUserName = findViewById(R.id.tvVpUserId);
        tvPassword = findViewById(R.id.tvVpPassword);
        tvRole = findViewById(R.id.tvVpRole);

        tvLastName = findViewById(R.id.tvVpLastName);
        tvFirstName = findViewById(R.id.tvVpFirstName);
        tvPhone = findViewById(R.id.tvVpPhone);
        tvEmail = findViewById(R.id.tvVpEmail);

        tvState = findViewById(R.id.tvVpState);
        tvStreetAddress = findViewById(R.id.tvVpStreetAddress);
        tvCity = findViewById(R.id.tvVpCity);
        tvZipcode = findViewById(R.id.tvVpZip);

        llCCN = findViewById(R.id.llVpCCN);
        llCCExp = findViewById(R.id.llVpCCEx);
        llCCType = findViewById(R.id.llVpCCType);
        tvCreditCardExpiry = findViewById(R.id.tvVpCardExpiryDate);
        tvCreditCardNum = findViewById(R.id.tvVpCreditCarddNum);
        tvCardType = findViewById(R.id.tvVpCardType);

        tvUserName.setText(userinfo.getUserName());
        tvRole.setText(userinfo.getUserRole());
        tvPassword.setText(userinfo.getPassword());
        tvEmail.setText(userinfo.getEmail());
        tvPhone.setText(userinfo.getPhone());
        tvFirstName.setText(userinfo.getFirstName());
        tvLastName.setText(userinfo.getLastName());

        tvStreetAddress.setText(userinfo.getStreetAddress());
        tvState.setText(userinfo.getState());
        tvCity.setText(userinfo.getCity());
        tvZipcode.setText(userinfo.getZipCode());

        if (userinfo.getUserRole().equalsIgnoreCase("Guest")) {
            Log.i("ccn: ", userinfo.getCreditCardNum());
            llCCExp.setVisibility(View.VISIBLE);
            llCCN.setVisibility(View.VISIBLE);
            tvCreditCardExpiry.setText(userinfo.getCreditCardExp());
            tvCreditCardNum.setText(userinfo.getCreditCardNum());
            tvCardType.setText(userinfo.getCreditCardtype());

        } else {
            llCCN.setVisibility(View.GONE);
            llCCExp.setVisibility(View.GONE);
            llCCType.setVisibility(View.GONE);
        }

        btnModify = findViewById(R.id.btnVpModify);

        if (userinfo.getUserRole().equalsIgnoreCase("Admin")) {
            btnModify.setVisibility(View.GONE);
        } else {
            btnModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ViewProfileActivity.this, UpdateProfileActivity.class));
                }
            });
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
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(ViewProfileActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
