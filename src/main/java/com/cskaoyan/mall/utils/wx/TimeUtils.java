package com.cskaoyan.mall.utils.wx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 时间工具类
 * @author: Lime
 * @create: 2019-08-23 15:27
 **/

public class TimeUtils
{
    public static Date addTime(int days, Date newDate)
    {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, days);
        newDate = ca.getTime();
        return newDate;
    }
}
