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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class UpdateProfileActivity extends AppCompatActivity {

    TextView tvUserName, tvPassword, tvRole, tvLastName, tvFirstName, tvPhone, tvEmail;
    TextView tvStreetAddress, tvCity, tvState, tvZipcode;
    TextView tvCreditCardNum, tvCreditCardExpiry;
    Spinner spnrCCType;
    Button btnSave, btnCancel;

    String userName, password, role, lastName, firstName, phone, email;
    String streetAddress, city, state, zipcode;
    String ccn, ccexp, cctype;
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
        tvPassword = findViewById(R.id.etUpPassword);
        tvRole = findViewById(R.id.tvUpRole);

        tvLastName = findViewById(R.id.etUpLastName);
        tvFirstName = findViewById(R.id.etUpFirstName);
        tvPhone = findViewById(R.id.etUpPhone);
        tvEmail = findViewById(R.id.etUpEmail);

        tvState = findViewById(R.id.etUpState);
        tvStreetAddress = findViewById(R.id.etUpStreetAddress);
        tvCity = findViewById(R.id.etUpCity);
        tvZipcode = findViewById(R.id.etUpZipCode);

        llCCN = findViewById(R.id.llUpCCN);
        llCCExp = findViewById(R.id.llUpCCEx);
        llCCType = findViewById(R.id.llVpCCType);

        tvCreditCardExpiry = findViewById(R.id.etUpCreditCardExpiry);
        tvCreditCardNum = findViewById(R.id.etUpCreditCardNumber);
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
        tvPassword.setText(user.getPassword());
        tvEmail.setText(user.getEmail());
        tvPhone.setText(user.getPhone());
        tvFirstName.setText(user.getFirstName());
        tvLastName.setText(user.getLastName());

        tvStreetAddress.setText(user.getStreetAddress());
        tvState.setText(user.getState());
        tvCity.setText(user.getCity());
        tvZipcode.setText(user.getZipCode());

        Log.i("Role: ", user.getUserRole());

        if (user.getUserRole().equalsIgnoreCase("Guest")) {
            llCCExp.setVisibility(View.VISIBLE);
            llCCN.setVisibility(View.VISIBLE);
            tvCreditCardExpiry.setText(user.getCreditCardExp());
            tvCreditCardNum.setText(user.getCreditCardNum());

        } else {
            llCCN.setVisibility(View.GONE);
            llCCExp.setVisibility(View.GONE);
        }

        btnSave = findViewById(R.id.btnUpSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfileActivity.this);
                builder.setTitle("Update Profile!")
                        .setMessage("Are you sure you want to update?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                getUpdatedUserData();
                                user = new User(userName, firstName, lastName, password, role, email, phone, streetAddress, city, state, zipcode, ccn, ccexp, cctype);
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
        });
    }

    public void getUpdatedUserData() {
        firstName = tvFirstName.getText().toString();
        lastName = tvLastName.getText().toString();
        password = tvPassword.getText().toString();
        phone = tvPhone.getText().toString();
        email = tvEmail.getText().toString();
        streetAddress = tvStreetAddress.getText().toString();
        city = tvCity.getText().toString();
        state = tvState.getText().toString();
        zipcode = tvZipcode.getText().toString();

        if (role.equalsIgnoreCase("Guest")) {
            ccn = tvCreditCardNum.getText().toString();
            ccexp = tvCreditCardExpiry.getText().toString();
            cctype = spnrCCType.getSelectedItem().toString();
            Log.i(APP_TAG, "role: notGuest; " + cctype);
        } else {
            Log.i(APP_TAG, "role: notGuest; " + role);
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
            Intent intent = new Intent(this, ViewProfileActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(UpdateProfileActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}