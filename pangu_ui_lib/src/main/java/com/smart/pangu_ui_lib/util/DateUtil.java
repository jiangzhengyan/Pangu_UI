package com.smart.pangu_ui_lib.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *  本类的主要功能是 :  日期工具类
 *
 * @author  jiangzhengyan  2024/4/19 14:57
 *
 */
public class DateUtil {
    private static final String TAG = "DateUtil";

    public DateUtil() {

    }

    /**
     * 以友好的方式显示时间 ,几秒前/几分钟前/几小时前/昨天/前天/天前/日期......
     *
     * @param strDate 1423224342字符串毫秒值
     * @return
     */
    public static String friendly_time(String strDate) {
        long oldTime = new Date(Long.parseLong(strDate)).getTime();
        String ftime = "";
        int minite = 0;
        Calendar cal = Calendar.getInstance();
        // 判断是否是同一天
        String curDate = formatDate(cal.getTime().getTime(), "yyyy-MM-dd");
        String paramDate = formatDate(oldTime, "yyyy-MM-dd");
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - oldTime) / 3600000);
            if (hour == 0) {
                // 判断是否为同一分钟内
                minite = (int) ((cal.getTimeInMillis() - oldTime) / 60000);
                if (minite == 0) {
                    ftime = Math.max(
                            (cal.getTimeInMillis() - oldTime) / 1000,
                            1)
                            + "秒前";
                } else {
                    ftime = Math
                            .max((cal.getTimeInMillis() - oldTime) / 60000,
                                    1)
                            + "分钟前";
                }

            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }

        long lt = oldTime / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - oldTime) / 3600000);
            if (hour == 0) {
                // 判断是否为同一分钟内
                minite = (int) ((cal.getTimeInMillis() - oldTime) / 60000);
                if (minite == 0) {
                    ftime = Math.max(
                            (cal.getTimeInMillis() - oldTime) / 1000,
                            1)
                            + "秒前";
                } else {
                    ftime = Math
                            .max((cal.getTimeInMillis() - oldTime) / 60000,
                                    1)
                            + "分钟前";
                }
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天 ";
        } else if (days > 2 && days < 31) {
            ftime = days + "天前";
        } else if (days >= 31 && days <= 2 * 31) {
            ftime = "一个月前";
        } else if (days > 2 * 31 && days <= 3 * 31) {
            ftime = "2个月前";
        } else if (days > 3 * 31 && days <= 4 * 31) {
            ftime = "3个月前";
        } else {
            ftime = formatDate(oldTime, "yyyy-MM-dd");
        }
        return ftime;
    }

    /**
     * 1413131232 -->一分钟之内,几分钟前,几小时前,日期2016-12-12 13:10
     * 用到的地方 : 1,投诉建议主页面,详情页.
     * 2, 有求必应主页面
     * 3, 随叫随到主页面
     * 4,园圈主页面,园圈详情页右上角
     *
     * @param strDate
     * @return
     */
    public static String friendly_time_2(String strDate) {
        long oldTime = new Date(Long.parseLong(strDate)).getTime();
        String ftime = "";
        int minite;
        Calendar cal = Calendar.getInstance();
        // 判断是否是同一天
        String curDate = formatDate(cal.getTime().getTime(), "yyyy-MM-dd");
        String paramDate = formatDate(oldTime, "yyyy-MM-dd");

        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - oldTime) / 3600000);
            if (hour == 0) {
                // 判断是否为同一分钟内
                minite = (int) ((cal.getTimeInMillis() - oldTime) / 60000);
                if (minite == 0) {
//					ftime = Math.max(
//							(cal.getTimeInMillis() - oldTime) / 1000,
//							1)
//							+ "秒前";
                    ftime = "1分钟内";
                } else {
                    ftime = Math
                            .max((cal.getTimeInMillis() - oldTime) / 60000,
                                    1)
                            + "分钟前";
                }

            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }

        long lt = oldTime / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - oldTime) / 3600000);
            if (hour == 0) {
                // 判断是否为同一分钟内
                minite = (int) ((cal.getTimeInMillis() - oldTime) / 60000);
                if (minite == 0) {
//					ftime = Math.max(
//							(cal.getTimeInMillis() - oldTime) / 1000,
//							1)
//							+ "秒前";
                    ftime = "1分钟内";
                } else {
                    ftime = Math
                            .max((cal.getTimeInMillis() - oldTime) / 60000,
                                    1)
                            + "分钟前";
                }
            } else {
                ftime = hour + "小时前";
            }
        } else {
            ftime = formatDate(oldTime, "yyyy-MM-dd HH:mm");
        }
        return ftime;
    }

    /**
     * 毫秒值转换成  分  ,1时23分,3日21时34分
     *
     * @param timeMillis
     * @return
     */
    public static String friendly_time_3(long timeMillis) {
        if (timeMillis <= 0) {
            return 0 + "min";
        }
        //秒
        int second = (int) (timeMillis / 1000);
        if (second <= 60) {
            return 0 + "min";
        }
        //min
        int mint = second / 60;
        if (mint <= 60) {
            return mint + "min";
        }
        int hour = second / 3600;
        if (hour <= 24) {
            return hour + "h" + (second % 3600) / 60 + "min";
        }
        int days = second / (3600 * 24);
        // if (days<30){
        return days + "d" + second % (3600 * 24) / 3600 + "h" + second % (3600 * 24) % 3600 / 60 + "min";
        //}


    }

    /**
     * 毫秒值转换成  分  ,1时23分,3日21时34分25秒
     *
     * @param timeMillis
     * @return
     */
    public static String friendly_time_4(long timeMillis) {
        if (timeMillis <= 0) {
            return 0 + "s";
        }
        long s = timeMillis / 1000L;
        long min = s / 60;
        long h = s / 3600;
        long d = s / (24 * 3600);

        if (s < 60) {
            return s + "秒";
        }

        if (min < 60) {
            return min + "分" + s % 60 + "秒";
        }

        if (h < 24) {
            return h + "时" + (s % 3600) / 60 + "分" + s % 60 + "秒";
        }
        return d + "天" + s % (24 * 3600) / 3600 + "时" + (s % 3600) / 60 + "分"
                + s % 60 + "秒";
    }

    /**
     * 毫秒值转换成  分  ,1时23分,3日21时34分
     *
     * @param timeMillis
     * @return
     */
    public static String friendly_time_2(long timeMillis) {
        if (timeMillis <= 0) {
            return 0 + "秒";
        }
        //秒
        int second = (int) (timeMillis / 1000);
        if (second <= 60) {
            return second + "秒";
        }
        //min
        int mint = second / 60;
        if (mint <= 60) {
            return mint + "分" + (second % 60) + "秒";
        }
        int hour = second / 3600;
        if (hour <= 24) {
            return hour + "小时" + (second % 3600) / 60 + "分" + (second % 60) + "秒";
        }
        int days = second / (3600 * 24);
        // if (days<30){
        return days + "天" + second % (3600 * 24) / 3600 + "小时" + second % (3600 * 24) % 3600 / 60 + "分" + (second % 60) + "秒";
        //}


    }

    /**
     * 毫秒时间转换成一定格式的日期,14002020304--->2016-12-12 14:12:43
     *
     * @param timeMillis  时间戳毫秒值
     * @param datePattern yyyy-MM-dd yyyy-MM-dd HH:mm:ss
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatDate(long timeMillis, String datePattern) {
        if (timeMillis == 0) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(datePattern);

        return simpleDateFormat.format(new Date(timeMillis));
    }

    /**
     * 获取一个日期的毫秒值
     *
     * @param dateTime    日期时间  2018-09-11 12:12:34
     * @param datePattern yyyy-MM-dd HH:mm:ss
     * @return 日期的毫秒值
     */
    public static long getDateTimeMillis(String dateTime, String datePattern) {
        if (TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(datePattern))
            return 0;
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(datePattern);
        try {
            return simpleDateFormat.parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 把毫秒值转换成 12:34 的样子
     *
     * @param timeInMillis
     * @return
     */
    public static String transMillisToHM(long timeInMillis) {
        if (timeInMillis <= 0) {
            return "00:00";
        }
        int time = (int) (timeInMillis / 1000);
        return String.format(Locale.getDefault(),"%02d:%02d", time / 60, time % 60);
    }

    /**
     * @param dateStr        日期时间  2018-09-11 12:12:34
     * @param datePattern    对应的yyyy-MM-dd HH:mm:ss
     * @param newDatePattern 需要转换成的  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String translateDateStr(String dateStr, String datePattern, String newDatePattern) {

        if (TextUtils.isEmpty(dateStr)) {
            return "";
        }
        if (TextUtils.isEmpty(datePattern)) {
            return "";
        }
        if (TextUtils.isEmpty(newDatePattern)) {
            return "";
        }
        long dateTimeMillis = getDateTimeMillis(dateStr, datePattern);

        return formatDate(dateTimeMillis, newDatePattern);
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param firstTimeMillis 第一个时间的毫秒值
     * @param lastTimeMillis  第二个时间的毫秒值
     * @return 是否是同一天.
     */
    public static boolean isSameDay(long firstTimeMillis, long lastTimeMillis) {
        if (firstTimeMillis <= 0 || lastTimeMillis <= 0)
            return false;
        String first = formatDate(firstTimeMillis, "yyyy-MM-dd");
        String second = formatDate(lastTimeMillis, "yyyy-MM-dd");
        return first.equalsIgnoreCase(second);
    }

    /**
     * 获取 SimpleDateFormat
     *
     * @param datePattern
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getSimpleDateFormat(String datePattern) {
        return new SimpleDateFormat(datePattern);
    }
    /**
     * 获取当前时间的毫秒值
     *
     * @return long毫秒时间
     */
    public static long getCurrentTimeMilliseconds() {

        return new Date().getTime();
    }

    /**
     * 获取当前时间  如2017-12-02
     *
     * @param datePattern yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDateStr(String datePattern) {

        return formatDate(getCurrentTimeMilliseconds(), datePattern);
    }


    /**
     * 对比时间是否超过某个时间长度 timelong
     *
     * @param historyTimeMilli 历史某个时间    .单位毫秒值
     * @param timeLongMilli    现在时间-历史时间是否超过这个时间.  单位毫秒值
     * @return true 超过规定时间,  false 未超过规定时间
     */
    public static boolean isTimeOverOneTime(String historyTimeMilli, String timeLongMilli) {
        long timeLong_ = Long.parseLong(timeLongMilli);//时长
        long historyTime_ = Long.parseLong(historyTimeMilli);//历史时间
        long currentTimeMilliseconds_ = getCurrentTimeMilliseconds();//现在时间
        long result_ = currentTimeMilliseconds_ - historyTime_;
        return result_ > timeLong_;
    }

    /**
     * 获取2月的天数
     *
     * @param year
     * @return
     */
    public static int getFebruaryDay(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, 2, 1);// year年的3月1日
        c.add(Calendar.DAY_OF_MONTH, -1);//将3月1日往左偏移一天结果是2月的天数
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月的天数集合
     *
     * @param year
     * @param month
     * @return
     */
    public static ArrayList<String> getDaysList(int year, int month) {
        ArrayList<String> days = new ArrayList<>();

        int daysLength = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysLength = 31;
                break;
            case 2:
                daysLength = getFebruaryDay(year);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysLength = 30;
                break;
        }

        for (int i = 1; i <= daysLength; i++) {
            days.add((i + "").length() == 1 ? "0" + i : i + "");
        }
        return days;
    }

    /**
     * 把毫秒值转换成 12'34" 的样子
     *
     * @param timeInMillis
     * @return
     */
    public static String translateMillisToFM(String timeInMillis) {
        if (TextUtils.isEmpty(timeInMillis)) {
            return "";
        }
        int times;
        String timesStr = "";
        try {
            times = (int) (Long.parseLong(timeInMillis) / 1000l);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        if (times < 60) {
            timesStr = times + "\"";
        } else if (times >= 60
            //&& times < 3600
        ) {

            timesStr = times / 60 + "'  " + times % 60 + "\"";
        }
        return timesStr;

    }

    /**
     * 把毫秒值转换成 12:34 的样子
     *
     * @param timeInMillis
     * @return
     */
    public static String translateMillisToFM(long timeInMillis) {
        if (timeInMillis <= 0) {
            return "00:00";
        }
        int time = (int) (timeInMillis / 1000);
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    /**
     * 获取之前的时间
     *
     * @param type 1,近一周、2,近一月(30天)、3,近一季度(近3个月)、4,近半年、5,近一年 6,本年
     *             近7天、近30天、近90天、近180天、近365天、本年
     * @return 格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getBeforeTime(int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        if (type == 1) {
            calendar.add(Calendar.DATE, -7);
            return formatDate(calendar.getTime().getTime(), "yyyy-MM-dd");
        } else if (type == 2) {
            calendar.add(Calendar.DATE, -30);
            return formatDate(calendar.getTime().getTime(), "yyyy-MM-dd");
        } else if (type == 3) {
            calendar.add(Calendar.MONTH, -3);
            return formatDate(calendar.getTime().getTime(), "yyyy-MM-dd");
        } else if (type == 4) {
            calendar.add(Calendar.MONTH, -6);
            return formatDate(calendar.getTime().getTime(), "yyyy-MM-dd");
        } else if (type == 5) {
            calendar.add(Calendar.YEAR, -1);
            return formatDate(calendar.getTime().getTime(), "yyyy-MM-dd");
        } else if (type == 6) {
            return getCurrentDateStr("yyyy") + "-01-01";
        }
        return getCurrentDateStr("yyyy-MM-dd");

    }

}
