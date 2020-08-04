package com.project.hotelmanagementproject.controller;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class AdminEditUserDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user_profile);
        View parentLayout = findViewById(android.R.id.content);
        init();
    }

    public void init(){

    }
}
