package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Session;

public class AdminSearchUserActivity extends AppCompatActivity {
    LinearLayout  adminSearchUser,adminSearchUserResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_users);
        adminSearchUser();
        initView();
    }

    public void initView() {
        adminSearchUser = findViewById(R.id.adminSearchUser);
        adminSearchUser.setVisibility(View.VISIBLE);
        adminSearchUserResult = findViewById(R.id.adminSearchUserResult);
        adminSearchUserResult.setVisibility(View.GONE);
        Button btnAdminUserSearch = findViewById(R.id.btnAdminUserSearch);
        btnAdminUserSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminSearchUser.setVisibility(View.GONE);
                adminSearchUserResult.setVisibility(View.VISIBLE);

            }
        });
    }

    public void adminSearchUser() {

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
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(AdminSearchUserActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }
}