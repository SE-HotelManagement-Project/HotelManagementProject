package com.project.hotelmanagementproject.controller.systemAccessController;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.database.DbMgr;
import com.project.hotelmanagementproject.model.DAO.Session;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvRegister;
    private TextInputEditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        etPassword = findViewById(R.id.etPassword);
        etUserName = findViewById(R.id.etUserName);

        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLogin = false;
                try {
                    DbMgr userDbMgr = DbMgr.getInstance(getApplicationContext());
                    isLogin = userDbMgr.checkPassword(etUserName.getText().toString(), etPassword.getText().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isLogin) {
                    Session session = new Session(getApplicationContext());
                    session.setLoginStatus(true);
                    session.setUserName(etUserName.getText().toString());
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                    etPassword.setError("invalid password");
                }
            }
        });
    }

    public void validateLogin() {

    }
}