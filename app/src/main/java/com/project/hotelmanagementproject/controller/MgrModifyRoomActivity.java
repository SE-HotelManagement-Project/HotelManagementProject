package com.project.hotelmanagementproject.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class MgrModifyRoomActivity extends AppCompatActivity {
    Button btnMgrRdSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_room_modify);
        View parentLayout = findViewById(android.R.id.content);
        init();
    }

    public void init(){
        btnMgrRdSave = findViewById(R.id.btnMgrRdSave);

        btnMgrRdSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MgrModifyRoomActivity.this);
                builder
                        .setTitle("Modify Room Details!")
                        .setMessage("Are you sure you want modify the room details?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(getApplicationContext(), "Room Details Modified Successfully", Toast.LENGTH_LONG).show();
                                // startActivity(new Intent(MgrModifyRoomActivity.this, HomeActivity.class));
                            }
                        }).create().show();
            }
        });
    }
}