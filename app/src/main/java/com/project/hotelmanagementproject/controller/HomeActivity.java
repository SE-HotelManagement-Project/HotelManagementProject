package com.project.hotelmanagementproject.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DbMgr;
import com.project.hotelmanagementproject.model.Session;
import com.project.hotelmanagementproject.model.User;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.ACTIVITY_RETURN_STATE;
import static com.project.hotelmanagementproject.utilities.ConstantUtils.HOME_ACTIVITY;

public class HomeActivity extends AppCompatActivity {
    LinearLayout gvBookHotel, gvChangePw, gvViewReservation, gvViewProfile;
    LinearLayout avSearchUser, avViewProfile;
    LinearLayout guestView, managerView, adminView;

    LinearLayout mvReservationList, mvAvailableRoom, mvSearchRoom, mvViewProfile;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session = new Session(getApplicationContext());
        DbMgr userDbMgr = DbMgr.getInstance(getApplicationContext());
        User user = userDbMgr.getUserDetails(session.getUserName());
        session.setUserRole(user.getUserRole());
        session.setHotelAssigned(user.getHotelAssigned());
        initView();
    }

    public void initView() {
        guestView = findViewById(R.id.GuestView);
        managerView = findViewById(R.id.ManagerView);
        adminView = findViewById(R.id.AdminView);

        String userRole = session.getUserRole();

        if (userRole != null) {
            if (userRole.equalsIgnoreCase("Manager")) {
                managerView.setVisibility(View.VISIBLE);
                adminView.setVisibility(View.GONE);
                guestView.setVisibility(View.GONE);
            } else if (userRole.equalsIgnoreCase("Admin")) {
                adminView.setVisibility(View.VISIBLE);
                guestView.setVisibility(View.GONE);
                managerView.setVisibility(View.GONE);
            } else {
                guestView.setVisibility(View.VISIBLE);
                adminView.setVisibility(View.GONE);
                managerView.setVisibility(View.GONE);
            }
        }

        adminFunctions();
        managerFunctions();
        guestFunctions();
    }

    public void adminFunctions() {
        avViewProfile = findViewById(R.id.avViewProfile);
        avSearchUser = findViewById(R.id.avSearchUser);

        //AdminFunctions
        avSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  showToastMessage("Feature Under Construction");
                Intent i = new Intent(HomeActivity.this, AdminSearchUserActivity.class);
                i.putExtra(ACTIVITY_RETURN_STATE, HOME_ACTIVITY);
                startActivity(i);
            }
        });
        avViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   showToastMessage("Feature Under Construction");
                startActivity(new Intent(HomeActivity.this, ViewProfileActivity.class));
            }
        });
    }

    public void guestFunctions() {
        gvBookHotel = findViewById(R.id.gvbookHotel);
        gvChangePw = findViewById(R.id.changePw);
        gvViewReservation = findViewById(R.id.gvViewReservations);
        gvViewProfile = findViewById(R.id.gvViewProfile);

        ///GuestFunctions
        gvBookHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, GuestRequestReservationActivity.class);
                i.putExtra(ACTIVITY_RETURN_STATE, HOME_ACTIVITY);
                startActivity(i);
            }
        });
        gvViewReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, GuestReservationSummActivity.class);
                i.putExtra(ACTIVITY_RETURN_STATE, HOME_ACTIVITY);
                startActivity(i);

            }
        });
        gvViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ViewProfileActivity.class);
                i.putExtra(ACTIVITY_RETURN_STATE, HOME_ACTIVITY);
                startActivity(i);
            }
        });

    }

    public void managerFunctions() {
        mvViewProfile = findViewById(R.id.mvViewProfile);
        mvSearchRoom = findViewById(R.id.mvSearchRoom);
        mvReservationList = findViewById(R.id.mvReservationList);
        mvAvailableRoom = findViewById(R.id.mvViewReservations);

        //ManagerFunctions
        mvAvailableRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MgrAvlblRoomsActivity.class);
                i.putExtra(ACTIVITY_RETURN_STATE, HOME_ACTIVITY);
                startActivity(i);

            }
        });
        mvReservationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MgrSearchResvActivity.class);
                i.putExtra(ACTIVITY_RETURN_STATE, HOME_ACTIVITY);
                startActivity(i);

            }
        });
        mvSearchRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MgrSearchRoomActivity.class);
                i.putExtra(ACTIVITY_RETURN_STATE, HOME_ACTIVITY);
                startActivity(i);
            }
        });
        mvViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ViewProfileActivity.class));

            }
        });
    }

    public void showToastMessage(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        View view = toast.getView();
//       view.setBackgroundResource(R.drawable.toast_frame);
//        view.setBackgroundColor(Color.BLACK);
//        TextView text = (TextView) view.findViewById(android.R.id.message);
        toast.show();

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
        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
        session.setLoginStatus(false);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {// code here to show dialog
        // super.onBackPressed();  // optional depending on your needs

        new AlertDialog.Builder((new ContextThemeWrapper(this, R.style.AlertDialog)))
                .setTitle("Quit App")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//Change Here
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}