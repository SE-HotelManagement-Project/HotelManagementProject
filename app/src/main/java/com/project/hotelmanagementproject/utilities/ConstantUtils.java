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
    public static final String COL_HOTEL_ACCESS = "hotel_access";
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
    public static final String COL_RID = "rt_id";
    public static final String COL_RESERV_ROOM_ID = "reservation_room_id";
    public static final String COL_PAYMENT_STATUS = "payment_status";
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

    //INTENT_TAGS
    public static final String MGR_RESV_ID = "mgr_resv_id";
    public static final String MGR_ROOM_ID = "mgr_room_id";
    public static final String MGR_SEARCH_ROOM_IP = "mgr_search_room_ip";
    public static final String MGR_HOTEL_NAME = "mgr_hotel_name";
    public static final String MGR_START_DATE = "mgr_start_date";
    public static final String MGR_START_TIME = "mgr_start_time";
    public static final String MGR_END_DATE = "mgr_end_date";
    public static final String MGR_OCCUPIED_STATUS = "mgr_occupied_status";

    public static final String ACTIVITY_RETURN_STATE = "activity_return_status";

    public static final String MGR_ROOM_STD = "mgr_room_std";
    public static final String MGR_ROOM_DELUXE = "mgr_room_deluxe";
    public static final String MGR_ROOM_SUITE = "mgr_room_suite";

    //Intent_guest_tags
    public static final String GUEST_RESV_ID = "guest_resv_id";
    public static final String GUEST_RESV_START_DATE = "guest_resv_start_date";
    public static final String GUEST_RESV_START_TIME = "guest_resv_start_time";
    public static final String GUEST_ACTIVITY_RETURN_STATE = "guest_activity_return_status";

    //Intent_admin_tags
    public static final String ADMIN_IP_NAME = "admin_ip_last_name";
    public static final String ADMIN_USER_NAME = "admin_user_name";

    //ACTIVITY RETURN STATE
    public static final String MGR_AVLBL_ROOM_ACTIVITY = "mgr_avlbl_room_state";
    public static final String MGR_SEARCH_ROOM_ACTIVITY = "mgr_search_room_state";
    public static final String MGR_RESV_LIST_ACTIVITY = "mgr_resv_list_state";
    public static final String HOME_ACTIVITY = "home_state";
    public static final String ADMIN_SEARCH_USER_ACTIVITY = "admin_search_user";

    //ROOM_OCCUPIED_STATUS
    public static final String ROOM_OCCUPIED = "Yes";
    public static final String ROOM_NOT_OCCUPIED = "No";
    public static final String OCCUPANCY_NOT_CHECKED = "Not Checked";


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

    public static final String ALL = "ALL";
    public static final String EMPTY = "";
    public static final String PAID = "PAID";
    public static final String GUEST_REQ_RESV_SELECTED_RESERV_DETAILS_TEXT = "Selected Reservation Details";
    public static final String PENDING = "PENDING";

    //    intent constanats for request reservation flow
    public static final String GUEST_REQ_RESV_SEARCH_HOTEL_NAME = "search_hotel_name";
    public static final String GUEST_REQ_RESV_SEARCH_CHECK_IN_DATE = "check_in_date";
    public static final String GUEST_REQ_RESV_SEARCH_START_TIME = "start_time";
    public static final String GUEST_REQ_RESV_SEARCH_END_TIME = "end_time";
    public static final String GUEST_REQ_RESV_SEARCH_END_TIME_VALUE = "23:59";
    public static final String GUEST_REQ_RESV_SEARCH_CHECK_OUT_DATE = "check_out_date";
    public static final String GUEST_REQ_RESV_SEARCH_NUM_ADULT_AND_CHLD = "num_of_adult_and_child";
    public static final String GUEST_REQ_RESV_SEARCH_TYPE_STANDARD = "search_room_type_standard";
    public static final String GUEST_REQ_RESV_SEARCH_ROOM_TYPE_DELUXE = "search_room_type_deluxe";
    public static final String GUEST_REQ_RESV_SEARCH_ROOM_TYPE_SUITE = "search_room_type_suite";
    public static final String GUEST_REQ_RESV_SEARCH_NUM_OF_ROOMS = "num_of_rooms";


    public static final String GUEST_REQ_RESV_SELECTED_HOTEL_NAME = "selected_hotel_name";
    public static final String GUEST_REQ_RESV_SELECTED_ROOM_TYPE ="selected_room_type";
    public static final String GUEST_REQ_RESV_PRICE_WK_DAY = "selected_room_PriceWkDay";
    public static final String GUEST_REQ_RESV_PRICE_WK_END = "selected_room_priceWkEnd";
    public static final String GUEST_REQ_RESV_SELECTED_NUM_OF_NIGHTS = "num_of_nights";
    public static final String GUEST_REQ_RESV_TOTAL_PRICE = "total_price";
    public static final String GUEST_REQ_RESV_CARD_TYPE = "card_type";
    public static final String GUEST_REQ_RESV_CARD_NUM = "card_num";
    public static final String GUEST_REQ_RESV_CARD_EXPIRY_DT = "card_expiry_dt";
    public static final String GUEST_REQ_RESV_CARD_CVV = "card_cvv";
    public static final String GUEST_REQ_RESV_RESERVID = "reserv_id";
    public static final String GUEST_REQ_RESV_SELECTED_ROOM_TAX = "selectedRoomTax";
    public static final String GUEST_REQ_RESV_GUEST_USER_NAME = "guestUserName";
    public static final String GUEST_REQ_RESV_GUEST_FIRST_NAME = "guestFirstName";
    public static final String GUEST_REQ_RESV_GUEST_LAST_NAME = "guestLastName";




    public static final String GUEST_REQ_RESV_SELECTED_TOTAL_PRICE = "guest_req_resv_selected_total_price";
}
