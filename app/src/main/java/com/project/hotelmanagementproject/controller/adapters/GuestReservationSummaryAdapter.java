package com.project.hotelmanagementproject.controller.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.hotelmanagementproject.R;
import com.project.hotelmanagementproject.model.Reservation;
import com.project.hotelmanagementproject.model.User;

import java.util.ArrayList;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class GuestReservationSummaryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Reservation> reservationList;

    public GuestReservationSummaryAdapter(Context context, ArrayList<Reservation> reservationList) {
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
        view = inflater.inflate(R.layout.list_view_guest_reservation_summary, null);
        TextView tvHotelName = view.findViewById(R.id.tvLvGstSRsvLHotel);
        TextView tvNumRooms = view.findViewById(R.id.tvLvGstSRsvLNumRooms);
        TextView tvNumNights = view.findViewById(R.id.tvLvGstSRsvLNumNights);
        TextView tvResvID = view.findViewById(R.id.tvLvGstSRsvLResvId);
        TextView tvStartDate = view.findViewById(R.id.tvLvGstSRsvLStartDate);
        TextView tvCheckInDate = view.findViewById(R.id.tvLvGstSRsvLCheckInDate);
        ImageView ivHotelName = view.findViewById(R.id.ivLvGstSRsvLHotel);
      TextView tvGstSumNoOfRooms = view.findViewById(R.id.tvGstSumNoOfRooms);
        TextView tvGstSumCheckInDate = view.findViewById(R.id.tvGstSumCheckInDate);
        TextView tvGstSumStartTime = view.findViewById(R.id.tvGstSumStartTime);
        TextView tvGstSumNoOfNights = view.findViewById(R.id.tvGstSumNoOfNights);
        ImageView ivGstSumHotel = view.findViewById(R.id.ivGstSumHotel);

        Reservation reservation = reservationList.get(i);



//        Log.i(APP_TAG, "Username: " + reservation.getResvHotelName() + "    ||||||  UserLastName: " + reservation.getResvHotelName() );
        tvGstSumNoOfRooms.setText(reservation.getResvNumOfRooms());
        tvGstSumCheckInDate.setText(reservation.getResvCheckInDate());
        tvGstSumStartTime.setText(reservation.getResvStartTime());
        tvGstSumNoOfNights.setText(reservation.getResvNumNights());

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
