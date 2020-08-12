package com.project.hotelmanagementproject.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputEditText etOldPassword, etNewPassword, etConfirmPassword;
    Button btnSave;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        session = new Session(getApplicationContext());
        initUi();
    }

    public void initUi() {
        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSave = findViewById(R.id.btnSavePw);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePw(etOldPassword.getText().toString(), etNewPassword.getText().toString(), etConfirmPassword.getText().toString());
            }
        });

    }

    public void savePw(String oldPw, String newPw, String confirmPw) {
        //check if oldPw is correct
        boolean isOldPwCorrect = false;
        try {
            DbMgr userDbMgr = DbMgr.getInstance(getApplicationContext());
            isOldPwCorrect = userDbMgr.checkPassword(session.getUserName(), oldPw);
            Log.i(APP_TAG, "old password status: " + isOldPwCorrect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isOldPwCorrect) {
            //check if newPw and confirm pw is same
            if (newPw.equalsIgnoreCase(confirmPw)) {
                try {
                    // update pw in db
                    DbMgr userDbMgr = DbMgr.getInstance(getApplicationContext());
                    boolean pwUpdateStatus = userDbMgr.changePassword(session.getUserName(), newPw);
                    Log.i(APP_TAG, "new password is updated");

                    // save and land into login page
                    if (pwUpdateStatus) {
                        Toast.makeText(getApplicationContext(), "new password is updated", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "unable to update password", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.i(APP_TAG, "password didn't match");
                Toast.makeText(getApplicationContext(), "New Password didn't match to confirm Password", Toast.LENGTH_LONG).show();

                etNewPassword.setError("New Password didn't match to confirm Password");
                etConfirmPassword.setError("New Password didn't match to confirm Password");
            }
        }
        Toast.makeText(getApplicationContext(), "old Password status " + isOldPwCorrect, Toast.LENGTH_LONG).show();

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
        Intent i = new Intent(this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        session.setLoginStatus(false);
        startActivity(i);
    }

}