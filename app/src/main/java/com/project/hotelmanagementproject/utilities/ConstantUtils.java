package com.project.hotelmanagementproject.utilities;

public class ConstantUtils {

    public static final String APP_TAG = "hm_app_msg: ";

    public static final String DB_NAME = "HotelManagementProject.db";


    //USER_DATA_TABLE_KEY_COLUMN_NAMES
    public static final String TABLE_USER_DATA = "hm_user_data";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_PASSWORD = "password";
    public static final String COL_USER_ROLE = "user_role";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";
    public static final String COL_STREET_ADDRESS = "street_address";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_ZIP_CODE = "zip_code";
    public static final String COL_CREDIT_CARD_NUM = "credit_card_num";
    public static final String COL_CREDIT_CARD_EXP = "credit_card_exp";
    public static final String COL_CARD_TYPE = "card_type";

    //RESERV_DATA_TABLE_KEY_COLUMN_NAMES
    public static final String TABLE_RESERV_DATA = "hm_reservation_data";
    public static final String COL_RESERV_ID = "reservation_id";
    public static final String COL_GUEST_USER_NAME = "guest_user_name";
    public static final String COL_GUEST_FIRST_NAME = "guest_first_name";
    public static final String COL_GUEST_LAST_NAME = "guest_last_name";
    public static final String COL_RESERV_HOTEL_NAME = "reservation_hotel_name";
    public static final String COL_ROOM_TYPE = "room_type";
    public static final String COL_NUM_OF_ROOMS = "num_of_rooms";
    public static final String COL_TOTAL_PRICE = "total_price";
    public static final String COL_CHECKIN_DATE = "check_in_date";
    public static final String COL_CHECKOUT_DATE = "check_out_date";
    public static final String COL_START_TIME = "start_time";
    public static final String COL_NUM_OF_ADULTS_AND_CHILDREN = "num_of_adults_and_children";
    public static final String COL_NUM_OF_NIGHTS = "num_of_nights";

    //HOTEL_DATA_TABLE_KEY_COLUMN_NAMES
    public static final String TABLE_HOTEL_DATA = "hm_hotel_data";
    public static final String COL_HOTEL_NAME = "hotel_name";
    public static final String COL_ROOM_NUM = "room_num";
    public static final String COL_PRICE_WEEKDAY = "room_price_per_night_weekday";
    public static final String COL_HOTEL_ROOM_TYPE = "room_type";
    public static final String COL_PRICE_WEEKEND = "room_price_per_night_weekend";
    public static final String COL_AVAILABILITY_STATUS = "available_status";
    public static final String COL_OCCUPIED_STATUS = "occupied_status";
    public static final String COL_FLOOR_NUM = "floor_num";
    public static final String COL_TAX = "tax";
    public static final String COL_HOTEL_ROOM_ID = "hotel_room_id";

    //HOTEL_ROOM_TYPES
    public static final String STANDARD_ROOM = "Standard";
    public static final String DELUXE_ROOM = "Deluxe";
    public static final String SUITE_ROOM = "Suite";

    //HOTEL_ROOM_PRICE_WEEKEND
    public static final int STANDARD_ROOM_PRICE_WEEKEND = 150;
    public static final int DELUXE_ROOM_PRICE_WEEKEND = 185;
    public static final int SUITE_ROOM_PRICE_WEEKEND = 275;

    //HOTEL_ROOM_PRICE_WEEKDAY
    public static final int STANDARD_ROOM_PRICE_WEEKDAY = 100;
    public static final int DELUXE_ROOM_PRICE_WEEKDAY = 135;
    public static final int SUITE_ROOM_PRICE_WEEKDAY = 225;

    //HOTELROOMTAX
    public static final int HOTEL_TAX = 100;

    //HOTEL_NAMES
    public static final String HM_MAVERICK = "MAVERICK";
    public static final String HM_LIBERTY = "LIBERTY";
    public static final String HM_RANGER = "RANGER";
    public static final String HM_SHARD = "SHARD";
    public static final String HM_WILLIAMS = "WILLIAMS";

}
