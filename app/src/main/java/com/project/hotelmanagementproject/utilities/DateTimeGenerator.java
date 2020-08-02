package com.project.hotelmanagementproject.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeGenerator {

    public static String getDateString() {
        return new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    }
}
