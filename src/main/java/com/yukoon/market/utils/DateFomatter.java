package com.yukoon.market.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFomatter {

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date =  new Date();
        return sdf.format(date);
    }
}
