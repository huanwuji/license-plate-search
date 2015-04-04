package com.huanwuji.lps.utils.lang;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

public class DateTools {
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return date == null ? null : DateFormatUtils.format(date, PATTERN);
    }
}
