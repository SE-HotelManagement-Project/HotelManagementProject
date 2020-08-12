package com.project.hotelmanagementproject.controller.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DAO.User;

import java.util.ArrayList;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class AdminSearchUserAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> userList;

    public AdminSearchUserAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view_admin_search_user, null);
        TextView tvUsrName = view.findViewById(R.id.tvUsrName);
        TextView tvUsrLastName = view.findViewById(R.id.tvUsrLastName);
        TextView tvUsrFirstName = view.findViewById(R.id.tvUsrFirstName);
        TextView tvUsrPhone = view.findViewById(R.id.tvUsrPhone);
        TextView tvUsrEmail = view.findViewById(R.id.tvUsrEmail);

        User user = userList.get(i);

        Log.i(APP_TAG, "Username: " + user.getUserName() + "    ||||||  UserLastName: " + user.getLastName());
        tvUsrName.setText(user.getUserName());
        tvUsrLastName.setText(user.getLastName());
        tvUsrFirstName.setText(user.getFirstName());
        tvUsrPhone.setText(user.getPhone());
        tvUsrEmail.setText(user.getEmail());

        return view;
    }
}
