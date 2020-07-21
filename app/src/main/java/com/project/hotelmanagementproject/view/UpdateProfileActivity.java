package com.project.hotelmanagementproject.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.UserDBHelper;

import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_CITY;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_CREDIT_CARD_EXP;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_CREDIT_CARD_NUM;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_EMAIL;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_FIRST_NAME;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_LAST_NAME;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_PASSWORD;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_PHONE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_STATE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_STREET_ADDRESS;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_USER_NAME;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_USER_ROLE;
import static com.project.hotelmanagementproject.utils.ConstantUtils.COL_ZIP_CODE;

public class UpdateProfileActivity extends AppCompatActivity {

    TextView tvUserName, tvPassword, tvRole, tvLastName, tvFirstName, tvPhone, tvEmail;
    TextView tvStreetAddress, tvCity, tvState, tvZipcode;
    TextView tvCreditCardNum, tvCreditCardExpiry;
    Button btnSave, btnCancel;

    String userName, password, role, lastName, firstName, phone, email;
    String streetAddress, city, state, zipcode;
    String ccn = "", ccexp = "";
    LinearLayout llCCN, llCCExp;

    UserDBHelper userDbHelper;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        userDbHelper = new UserDBHelper(UpdateProfileActivity.this);
        getUserData();
        initUi();
    }

    public void getUserData() {
        c = userDbHelper.getUserProfile(new Session(getApplicationContext()).getUserName());
        while (c.moveToNext()) {
            role = c.getString(c.getColumnIndex(COL_USER_ROLE));
            password = c.getString(c.getColumnIndex(COL_PASSWORD));
            userName = c.getString(c.getColumnIndex(COL_USER_NAME));
            email = c.getString(c.getColumnIndex(COL_EMAIL));
            phone = c.getString(c.getColumnIndex(COL_PHONE));
            lastName = c.getString(c.getColumnIndex(COL_LAST_NAME));
            firstName = c.getString(c.getColumnIndex(COL_FIRST_NAME));
            streetAddress = c.getString(c.getColumnIndex(COL_STREET_ADDRESS));
            city = c.getString(c.getColumnIndex(COL_CITY));
            state = c.getString(c.getColumnIndex(COL_STATE));
            zipcode = c.getString(c.getColumnIndex(COL_ZIP_CODE));
            if (role.equalsIgnoreCase("Guest")) {
                ccn = c.getString(c.getColumnIndex(COL_CREDIT_CARD_NUM));
                ccexp = c.getString(c.getColumnIndex(COL_CREDIT_CARD_EXP));
            }
        }
        c.close();
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
        tvCreditCardExpiry = findViewById(R.id.etUpCreditCardExpiry);
        tvCreditCardNum = findViewById(R.id.etUpCreditCardNumber);

        tvUserName.setText(userName);
        tvRole.setText(role);
        tvPassword.setText(password);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);

        tvStreetAddress.setText(streetAddress);
        tvState.setText(state);
        tvCity.setText(city);
        tvZipcode.setText(zipcode);

        Log.i("Role: ", role);

        if (role.equalsIgnoreCase("Guest")) {
            Log.i("ccn: ", ccn);
            llCCExp.setVisibility(View.VISIBLE);
            llCCN.setVisibility(View.VISIBLE);
            tvCreditCardExpiry.setText(ccexp);
            tvCreditCardNum.setText(ccn);

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
                                boolean isUpdated = userDbHelper.updateProfile(userName, firstName, password, lastName, phone, email, streetAddress, city, state, zipcode, ccn, ccexp);
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
        }
    }
}