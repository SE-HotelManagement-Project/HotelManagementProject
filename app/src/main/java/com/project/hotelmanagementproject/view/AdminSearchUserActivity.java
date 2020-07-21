package com.project.hotelmanagementproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class AdminSearchUserActivity extends AppCompatActivity {
    LinearLayout  adminSearchUser,adminSearchUserResult;
    private Button btnAdminUserSearch;
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
        btnAdminUserSearch = findViewById(R.id.btnAdminUserSearch);
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
}