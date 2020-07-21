package com.project.hotelmanagementproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.hotelmanagementproject.R;

public class MgrSearchRoomActivity extends AppCompatActivity {
    Button btnMgrSrSearch;
    LinearLayout llSearchRoomIp;
    LinearLayout llSearchRoomOp;
    CardView cvAdminSearchRoomResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_search_room);
        init();
    }

    public void init(){
        llSearchRoomIp = findViewById(R.id.llSearchRoomIp);
        llSearchRoomIp.setVisibility(View.VISIBLE);
        llSearchRoomOp = findViewById(R.id.llSearchRoomOp);
        llSearchRoomOp.setVisibility(View.GONE);
        btnMgrSrSearch = findViewById(R.id.btnMgrSrSearch);
        btnMgrSrSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llSearchRoomIp.setVisibility(View.GONE);
                llSearchRoomOp.setVisibility(View.VISIBLE);

            }
        });
        cvAdminSearchRoomResult = findViewById(R.id.cvAdminSearchRoomResult);
        cvAdminSearchRoomResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MgrSearchRoomActivity.this, MgrRoomDetailsActivity.class));

            }
        });
    }
}