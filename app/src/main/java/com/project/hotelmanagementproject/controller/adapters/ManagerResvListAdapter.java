package com.project.hotelmanagementproject.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.DAO.Reservation;

import java.util.ArrayList;

public class ManagerResvListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Reservation> reservationList;

    public ManagerResvListAdapter(Context context, ArrayList<Reservation> reservationList) {
        this.context = context;
        this.reservationList = reservationList;
    }

    @Override
    public int getCount() {
        return this.reservationList.size();
    }

    @Override
    public Object getItem(int i) {
        return reservationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view_mgr_search_resv, null);
        TextView tvHotelName = view.findViewById(R.id.tvLvMgrSRsvLHotel);
        TextView tvNumRooms = view.findViewById(R.id.tvLvMgrSRsvLNumRooms);
        TextView tvNumNights = view.findViewById(R.id.tvLvMgrSRsvLNumNights);
        TextView tvResvID = view.findViewById(R.id.tvLvMgrSRsvLResvId);
        TextView tvStartDate = view.findViewById(R.id.tvLvMgrSRsvLStartDate);
        TextView tvCheckInDate = view.findViewById(R.id.tvLvMgrSRsvLCheckInDate);
        ImageView ivHotelName = view.findViewById(R.id.ivLvMgrSRsvLHotel);

        Reservation reservation = reservationList.get(i);
        //  Log.i(APP_TAG, "hotelName: " + hrSrl.getHotelName() + "    ||||||  roomId: " + hrSrl.getHotelRoomId());
        tvHotelName.setText(reservation.getResvHotelName());
        tvNumRooms.setText(reservation.getResvNumOfRooms());
        tvNumNights.setText(reservation.getResvNumNights());
        tvResvID.setText(reservation.getReservationId());
        tvStartDate.setText(reservation.getStartDate());
        tvCheckInDate.setText(reservation.getResvCheckInDate());

        if (reservation.getResvHotelName().equalsIgnoreCase("MAVERICK")) {
            ivHotelName.setImageResource(R.drawable.ic_hotel_maverick);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("LIBERTY")) {
            ivHotelName.setImageResource(R.drawable.ic_hotel_liberty);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("SHARD")) {
            ivHotelName.setImageResource(R.drawable.ic_hotel_shard);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("RANGER")) {
            ivHotelName.setImageResource(R.drawable.ic_hotel_ranger);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("WILLIAMS")) {
            ivHotelName.setImageResource(R.drawable.ic_hotel_williams);
        }

        return view;
    }
}
