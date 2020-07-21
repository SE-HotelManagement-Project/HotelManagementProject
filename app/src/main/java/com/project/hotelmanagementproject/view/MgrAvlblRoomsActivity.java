package com.project.hotelmanagementproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.hotelmanagementproject.R;

public class MgrAvlblRoomsActivity extends AppCompatActivity {
    Button btnMgrArSearch;
    LinearLayout llAvlblRoomIp;
    LinearLayout llAvlblRoomOp;
    CardView cvAdminAvRoomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_avlbl_rooms);
        init();
    }

    public void init(){
        llAvlblRoomIp = findViewById(R.id.llAvlblRoomIp);
        llAvlblRoomIp.setVisibility(View.VISIBLE);
        llAvlblRoomOp = findViewById(R.id.llAvlblRoomOp);
        llAvlblRoomOp.setVisibility(View.GONE);
        btnMgrArSearch = findViewById(R.id.btnMgrArSearch);
        btnMgrArSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llAvlblRoomIp.setVisibility(View.GONE);
                llAvlblRoomOp.setVisibility(View.VISIBLE);

            }
        });
        cvAdminAvRoomList = findViewById(R.id.cvAdminAvRoomList);
        cvAdminAvRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MgrAvlblRoomsActivity.this, MgrRoomDetailsActivity.class));

            }
        });
    }
}