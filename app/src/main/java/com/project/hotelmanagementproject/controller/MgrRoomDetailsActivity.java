package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class MgrRoomDetailsActivity extends AppCompatActivity {
    Button btnMgrRmDEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_room_details);
        init();
    }

    public void init(){
        btnMgrRmDEdit = findViewById(R.id.btnMgrRmDEdit);
        btnMgrRmDEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MgrRoomDetailsActivity.this, MgrModifyRoomActivity.class));
            }
        });
    }
}