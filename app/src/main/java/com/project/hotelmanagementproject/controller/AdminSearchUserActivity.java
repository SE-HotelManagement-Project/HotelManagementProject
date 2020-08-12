package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.controller.adapters.AdminSearchUserAdapter;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ADMIN_IP_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ADMIN_SEARCH_USER_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.ADMIN_USER_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.HOME_ACTIVITY;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_END_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_HOTEL_NAME;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_SEARCH_ROOM_IP;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_DATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.MGR_START_TIME;

public class AdminSearchUserActivity extends AppCompatActivity {
    LinearLayout adminSearchUser, adminSearchUserResult;
    EditText etAdminSuLastName;
    ArrayList<User> userList;
    ListView lvAdSrUserList;
    AdminSearchUserAdapter adminSearchUserAdpater;
    String userName;
    String lastNameIp;
    String ADM_EDIT_USER = "admin_edit_user";
    String returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_users);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        getReturnState();
    }

    private void getReturnState() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            returnIntent = bundle.getString(ACTIVITY_RETURN_STATE);
            if (!returnIntent.equalsIgnoreCase(HOME_ACTIVITY)) {
                lastNameIp = bundle.getString(MGR_SEARCH_ROOM_IP);
                getUserList();
            }
        }
        Log.i(APP_TAG, "return state: " + returnIntent);
    }

    public void initView() {
        etAdminSuLastName = findViewById(R.id.etAdminSuLastName);
        adminSearchUser = findViewById(R.id.adminSearchUser);
        adminSearchUser.setVisibility(View.VISIBLE);
        adminSearchUserResult = findViewById(R.id.adminSearchUserResult);
        adminSearchUserResult.setVisibility(View.GONE);
        userList = new ArrayList<>();
        lvAdSrUserList = findViewById(R.id.lvAdSrUserList);
        Button btnAdminUserSearch = findViewById(R.id.btnAdminUserSearch);
        btnAdminUserSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserList();
            }
        });

        lvAdSrUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent userNameIntent = new Intent(AdminSearchUserActivity.this, AdminViewUserDetails.class);
                User item = (User) adapterView.getItemAtPosition(i);
                userNameIntent.putExtra(ADMIN_USER_NAME, item.getUserName());
                userNameIntent.putExtra(ADMIN_IP_NAME, lastNameIp);
                userNameIntent.putExtra(ACTIVITY_RETURN_STATE, ADMIN_SEARCH_USER_ACTIVITY);
                startActivity(userNameIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void getUserList() {
        DbMgr UserDbMgr = DbMgr.getInstance(getApplicationContext());
        lastNameIp = etAdminSuLastName.getText().toString();

        if (User.isNullorEmpty(lastNameIp)) {
            etAdminSuLastName.setError("invalid last name");
            // Toast.makeText(getApplicationContext(), "invalid last name", Toast.LENGTH_LONG).show();
        } else {
            adminSearchUser.setVisibility(View.GONE);
            adminSearchUserResult.setVisibility(View.VISIBLE);
            userList = UserDbMgr.getAdminSearchUser(lastNameIp);
            adminSearchUserAdpater = new AdminSearchUserAdapter(this, userList);
            lvAdSrUserList.setAdapter(adminSearchUserAdpater);
            adminSearchUserAdpater.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if (id == android.R.id.home) {
            if (adminSearchUserResult.getVisibility() == View.VISIBLE) {
                adminSearchUser.setVisibility(View.VISIBLE);
                adminSearchUserResult.setVisibility(View.GONE);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(AdminSearchUserActivity.this, LoginActivity.class);
        new Session(getApplicationContext()).setLoginStatus(false);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        if (adminSearchUserResult.getVisibility() == View.VISIBLE) {
            adminSearchUser.setVisibility(View.VISIBLE);
            adminSearchUserResult.setVisibility(View.GONE);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}