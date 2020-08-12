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
import com.project.hotelmanagementproject.model.DAO.HotelRoom;
import com.project.hotelmanagementproject.utilities.ConstantUtils;

import java.util.ArrayList;

import static com.project.hotelmanagementproject.utilities.ConstantUtils.APP_TAG;

public class GuestPendingResevationAdapter extends BaseAdapter {
    Context context;
    ArrayList<HotelRoom> roomList;
    public GuestPendingResevationAdapter(Context context, ArrayList<HotelRoom> roomList) {
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
        view = inflater.inflate(R.layout.list_view_guest_pending_reservations, null);

        TextView tvGstPendinglHotelName = view.findViewById(R.id.tvGstPendingHotelName);
        TextView tvGstPendinglNumOfRooms = view.findViewById(R.id.tvGstPendingNumOfRooms);
        TextView tvGstPendinglPricePerNightWkDay = view.findViewById(R.id.tvGstPendingRoomPrice);
        TextView tvGstPendinglRoomType = view.findViewById(R.id.tvGstPendingRoomType);
        TextView tvGstPendinglCheckInDate = view.findViewById(R.id.tvGstPendingCheckInDate);
        ImageView ivGstPendinglHotelIcon = view.findViewById(R.id.ivGstPendingHotel);

        HotelRoom hrGuestReqResHotelRoom = roomList.get(i);

        Log.i(APP_TAG, "hotelName: " + hrGuestReqResHotelRoom.getHotelName() + "    ||||||  roomId: " + hrGuestReqResHotelRoom.getHotelRoomId());
        tvGstPendinglHotelName.setText(hrGuestReqResHotelRoom.getHotelName());
        tvGstPendinglNumOfRooms.setText(hrGuestReqResHotelRoom.getNumOfRooms());
        tvGstPendinglRoomType.setText(hrGuestReqResHotelRoom.getRoomType());
        tvGstPendinglPricePerNightWkDay.setText(hrGuestReqResHotelRoom.getRoomPriceWeekDay());
        tvGstPendinglCheckInDate.setText(hrGuestReqResHotelRoom.getStartDate());

        if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_MAVERICK)) {
            ivGstPendinglHotelIcon.setImageResource(R.drawable.ic_hotel_maverick);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_LIBERTY)) {
            ivGstPendinglHotelIcon.setImageResource(R.drawable.ic_hotel_liberty);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_SHARD)) {
            ivGstPendinglHotelIcon.setImageResource(R.drawable.ic_hotel_shard);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_RANGER)) {
            ivGstPendinglHotelIcon.setImageResource(R.drawable.ic_hotel_ranger);
        } else if (hrGuestReqResHotelRoom.getHotelName().equalsIgnoreCase(ConstantUtils.HM_WILLIAMS)) {
            ivGstPendinglHotelIcon.setImageResource(R.drawable.ic_hotel_williams);
        }
        return view;
    }
}
