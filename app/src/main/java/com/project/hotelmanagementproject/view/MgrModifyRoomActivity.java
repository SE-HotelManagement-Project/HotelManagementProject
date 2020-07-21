package com.project.hotelmanagementproject.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;

public class MgrModifyRoomActivity extends AppCompatActivity {
    Button btnMgrRdSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_room_modify);
        init();
    }

    public void init(){
        btnMgrRdSave = findViewById(R.id.btnMgrRdSave);

        btnMgrRdSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MgrModifyRoomActivity.this);

                builder
                        .setTitle("Update Room")
                        .setMessage("Are you sure you want save the changes?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                startActivity(new Intent(MgrModifyRoomActivity.this, HomeActivity.class));
                            }
                        }).create().show();
            }
        });
    }
}