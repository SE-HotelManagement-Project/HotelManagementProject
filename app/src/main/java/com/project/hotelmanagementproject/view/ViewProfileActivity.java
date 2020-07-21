package com.project.hotelmanagementproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


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

public class ViewProfileActivity extends AppCompatActivity {
    private Button btnAdminVpModify;

    TextView tvUserName, tvPassword, tvRole, tvLastName, tvFirstName, tvPhone, tvEmail;
    TextView tvStreetAddress, tvCity, tvState, tvZipcode;
    TextView tvCreditCardNum, tvCreditCardExpiry;
    Button btnModify;

    String userName, password, role, lastName, firstName, phone, email;
    String streetAddress, city, state, zipcode;
    String ccn = "", ccexp = "";
    LinearLayout llCCN, llCCExp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        UserDBHelper userDbHelper = new UserDBHelper(ViewProfileActivity.this);
        Cursor c = userDbHelper.getUserProfile(new Session(getApplicationContext()).getUserName());
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
        tvCreditCardExpiry = findViewById(R.id.tvVpCardExpiryDate);
        tvCreditCardNum = findViewById(R.id.tvVpCreditCarddNum);

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

        btnModify = findViewById(R.id.btnVpModify);

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfileActivity.this, UpdateProfileActivity.class));
            }
        });
    }
}