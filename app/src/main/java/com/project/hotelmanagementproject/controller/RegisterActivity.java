package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.User;
import com.project.hotelmanagementproject.model.UserDbMgr;

public class RegisterActivity extends AppCompatActivity {

    EditText etUserName, etPassword, etEmail, etFirstName, etLastName, etPhone, etStreetAddress, etCity, etState, etZipcode, etCCN, etCCExpiry;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    public void initView() {
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etFirstName = findViewById(R.id.etfirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhoneNumber);
        etState = findViewById(R.id.etState);
        etStreetAddress = findViewById(R.id.etStreetAddress);
        etCity = findViewById(R.id.etcity);
        etZipcode = findViewById(R.id.etZipCode);
        etCCN = findViewById(R.id.etCreditCardNumber);
        etCCExpiry = findViewById(R.id.etCCExpiry);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User newUserDetails = new User(
                            etUserName.getText().toString(),
                            etFirstName.getText().toString(),
                            etLastName.getText().toString(),
                            etPassword.getText().toString(),
                            "Guest",
                            etEmail.getText().toString(),
                            etPhone.getText().toString(),
                            etStreetAddress.getText().toString(),
                            etCity.getText().toString(),
                            etState.getText().toString(),
                            etZipcode.getText().toString(),
                            etCCN.getText().toString(),
                            etCCExpiry.getText().toString(),
                            "VISA");
                    UserDbMgr userDbMgr = UserDbMgr.getInstance(getApplicationContext());
                    boolean isDataInserted = userDbMgr.addNewGuest(newUserDetails);
                    if (isDataInserted) {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        Toast.makeText(RegisterActivity.this, "guest account created succesfully ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "failed to create guest account ", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, "error creating new guest account", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    //   e.toString();
                }
            }
        });

    }
}