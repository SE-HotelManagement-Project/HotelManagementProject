package com.project.hotelmanagementproject.controller.systemAccessController;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.database.DbMgr;
import com.project.hotelmanagementproject.model.DAO.User;

public class RegisterActivity extends AppCompatActivity {

    String ccType;
    private EditText etUserName, etPassword, etEmail, etFirstName, etLastName, etPhone, etStreetAddress, etCity, etState, etZipcode, etCCN, etCCExpiry;
    private Button btnRegister;
    private Spinner spnrCardType;

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
        spnrCardType = findViewById(R.id.spnrRegCreditCardType);
        spnrCardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ccType = spnrCardType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ccType = "VISA";
            }
        });

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
                            "Guest", "ALL",
                            etEmail.getText().toString(),
                            etPhone.getText().toString(),
                            etStreetAddress.getText().toString(),
                            etCity.getText().toString(),
                            etState.getText().toString(),
                            etZipcode.getText().toString(),
                            etCCN.getText().toString(),
                            etCCExpiry.getText().toString(),
                            ccType
                    );
                    DbMgr userDbMgr = DbMgr.getInstance(getApplicationContext());
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