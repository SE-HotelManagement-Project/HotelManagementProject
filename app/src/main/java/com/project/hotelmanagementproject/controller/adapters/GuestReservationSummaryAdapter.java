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

        if (reservation.getResvHotelName().equalsIgnoreCase("MAVERICK")) {
            ivGstSumHotel.setImageResource(R.drawable.ic_hotel_maverick);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("LIBERTY")) {
            ivGstSumHotel.setImageResource(R.drawable.ic_hotel_liberty);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("SHARD")) {
            ivGstSumHotel.setImageResource(R.drawable.ic_hotel_shard);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("RANGER")) {
            ivGstSumHotel.setImageResource(R.drawable.ic_hotel_ranger);
        } else if (reservation.getResvHotelName().equalsIgnoreCase("WILLIAMS")) {
            ivGstSumHotel.setImageResource(R.drawable.ic_hotel_williams);
        }


        return view;
    }
}
