package com.project.hotelmanagementproject.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DAO.HotelRoom;

import java.util.ArrayList;

public class ManagerRoomListAdpater extends BaseAdapter {
    Context context;
    ArrayList<HotelRoom> roomList;

    public ManagerRoomListAdpater(Context context, ArrayList<HotelRoom> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @Override
    public int getCount() {
        return this.roomList.size();
    }

    @Override
    public Object getItem(int i) {
        return roomList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view_search_room, null);
        TextView tvSrlHotelName = view.findViewById(R.id.tvSrlHotelName);
        TextView tvSrlRoomNum = view.findViewById(R.id.tvSrlRoomNum);
        TextView tvSrlFloorNum = view.findViewById(R.id.tvSrlFloorNum);
        TextView tvSrlRoomType = view.findViewById(R.id.tvSrlRoomType);
        TextView tvStartDate = view.findViewById(R.id.tvSrlStartDate);
        ImageView ivSrlHotelName = view.findViewById(R.id.ivSrlHotelIcon);

        HotelRoom hrSrl = roomList.get(i);

        //  Log.i(APP_TAG, "hotelName: " + hrSrl.getHotelName() + "    ||||||  roomId: " + hrSrl.getHotelRoomId());
        tvSrlHotelName.setText(hrSrl.getHotelName());
        tvSrlRoomNum.setText(hrSrl.getRoomNum());
        tvSrlRoomType.setText(hrSrl.getRoomType());
        tvSrlFloorNum.setText(hrSrl.getFloorNum());
        tvStartDate.setText(hrSrl.getStartDate());

        if (hrSrl.getHotelName().equalsIgnoreCase("MAVERICK")) {
            ivSrlHotelName.setImageResource(R.drawable.ic_hotel_maverick);
        } else if (hrSrl.getHotelName().equalsIgnoreCase("LIBERTY")) {
            ivSrlHotelName.setImageResource(R.drawable.ic_hotel_liberty);
        } else if (hrSrl.getHotelName().equalsIgnoreCase("SHARD")) {
            ivSrlHotelName.setImageResource(R.drawable.ic_hotel_shard);
        } else if (hrSrl.getHotelName().equalsIgnoreCase("RANGER")) {
            ivSrlHotelName.setImageResource(R.drawable.ic_hotel_ranger);
        } else if (hrSrl.getHotelName().equalsIgnoreCase("WILLIAMS")) {
            ivSrlHotelName.setImageResource(R.drawable.ic_hotel_williams);
        }

        return view;
    }
}
