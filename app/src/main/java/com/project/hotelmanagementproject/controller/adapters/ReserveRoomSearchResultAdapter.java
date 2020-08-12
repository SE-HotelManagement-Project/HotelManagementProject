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
import com.project.hotelmanagementproject.utilities.ConstantUtils;

import java.util.List;

public class ReserveRoomSearchResultAdapter extends BaseAdapter {
    Context context;
    List<HotelRoom> roomList;
    public ReserveRoomSearchResultAdapter(Context context, List<HotelRoom> roomList) {
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
        view = inflater.inflate(R.layout.list_view_guest_req_room_resv_result, null);

        TextView tvgrrrrlHotelName = view.findViewById(R.id.tvgrrrrlHotelName);
        TextView tvgrrrrlNumOfRooms = view.findViewById(R.id.tvgrrrrlNumOfRooms);
//        TextView tvgrrrrlPricePerNightWkDay = view.findViewById(R.id.tvgrrrrlPricePerNightWkDay);
//        TextView tvgrrrrlPricePerNightWkEnd = view.findViewById(R.id.tvgrrrrlPricePerNightWkEnd);
        TextView tvgrrrrlRoomType = view.findViewById(R.id.tvgrrrrlRoomType);
        TextView tvgrrrrlCheckInDate = view.findViewById(R.id.tvgrrrrlCheckInDate);
        ImageView ivgrrrrlHotelIcon = view.findViewById(R.id.ivgrrrrlHotelIcon);
        TextView tvgrrrrlNumOfNights= view.findViewById(R.id.tvgrrrrlNumOfNights);
        TextView tvgrrrrlRoomPrice= view.findViewById(R.id.tvgrrrrlRoomPrice);

        HotelRoom hrGuestReqResHotelRoom = roomList.get(i);

//        Log.i(APP_TAG, "hotelName: " + hrGuestReqResHotelRoom.getHotelName() + "    ||||||  roomId: " + hrGuestReqResHotelRoom.getHotelRoomId());
        tvgrrrrlHotelName.setText(hrGuestReqResHotelRoom.getHotelName());
        tvgrrrrlNumOfRooms.setText(hrGuestReqResHotelRoom.getNumOfRooms());
        tvgrrrrlRoomType.setText(hrGuestReqResHotelRoom.getRoomType());
//        tvgrrrrlPricePerNightWkDay.setText(hrGuestReqResHotelRoom.getRoomPriceWeekDay());
        tvgrrrrlCheckInDate.setText(hrGuestReqResHotelRoom.getStartDate());
        tvgrrrrlNumOfNights.setText(hrGuestReqResHotelRoom.getNumOfNights());
        tvgrrrrlRoomPrice.setText(hrGuestReqResHotelRoom.getTotalPrice());

        if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_MAVERICK)) {
            ivgrrrrlHotelIcon.setImageResource(R.drawable.ic_hotel_maverick);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_LIBERTY)) {
            ivgrrrrlHotelIcon.setImageResource(R.drawable.ic_hotel_liberty);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_SHARD)) {
            ivgrrrrlHotelIcon.setImageResource(R.drawable.ic_hotel_shard);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_RANGER)) {
            ivgrrrrlHotelIcon.setImageResource(R.drawable.ic_hotel_ranger);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_WILLIAMS)) {
            ivgrrrrlHotelIcon.setImageResource(R.drawable.ic_hotel_williams);
        }
        return view;
    }

}
