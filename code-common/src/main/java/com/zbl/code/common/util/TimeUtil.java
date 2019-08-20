package com.zbl.code.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: zbl
 * @Date: Created in 17:11 2019/8/20
 * @Description:  时间工具类
 * @Version: $
 */
public class TimeUtil {
    public static final String DEF_FORMAT = "";

    // 获取时间戳（秒）
    public static int getTimestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    // 时间转化为字符串
    public static String date2String(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date string2Date(String s, String format) throws ParseException {
        if (StringUtil.isBlank(s)) {
            return null;
        }
        return new SimpleDateFormat(format).parse(s);
    }

    // 获取当天整点时间
    public static Date getDay(Integer hour) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, hour);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    // 获取当天开始时间
    public static Date getDayStart() {
        return getDay(0);
    }

    // 获取当天结束时间
    public static Date getDayEnd() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static Long getSecondShort(Date fromDate, Date endDate){
        endDate = endDate == null? new Date() : endDate;
        return (endDate.getTime() - fromDate.getTime())/1000;
    }
}