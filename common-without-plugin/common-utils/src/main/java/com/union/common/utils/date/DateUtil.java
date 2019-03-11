package com.union.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author GaoWei
 * @describe 时间格式工具类
 * @time 2017/12/26,17:59
 */
public class DateUtil {
    /**
     * 获取格式化时间yyyyMMddHHmmss
     *
     * @return
     */
    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddHHmmss");
        Date date = new Date ();
        return sdf.format (date);
    }

    /**
     * 获取格式化时间yyyyMMdd
     *
     * @return
     */
    public static String getNowTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        Date date = new Date ();
        return sdf.format (date);
    }

    /**
     * long转Date
     *
     * @return
     */
    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat (formatType).format (data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate (currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString (date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat (formatType);
        Date date = null;
        date = formatter.parse (strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date (currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString (dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate (sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate (strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong (date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime ();
    }


    public static String dateTimeFormatStr(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat (format);
        return dateFormat.format (date);
    }


    /**
     * 获取前N天的日期时间
     *
     * @param day 前N天天数
     * @return 日期时间格式的字符串
     * @throws ParseException
     */
    public static String getFrontDay(Date dNow, int day, String format) throws ParseException {
        Date dBefore = new Date ();
        Calendar calendar = Calendar.getInstance ();  //得到日历
        calendar.setTime (dNow);//把当前时间赋给日历
        calendar.add (Calendar.DAY_OF_MONTH, -day);  //设置为前N天
        dBefore = calendar.getTime ();   //得到前一天的时间
        String result = DateUtil.dateTimeFormatStr (dBefore, format);
        return result;
    }


    public static String getTomorrowTime() {
        Date date = new Date ();//取时间
        Calendar calendar = new GregorianCalendar ();
        calendar.setTime (date);
        //把日期往后增加一天.整数往后推,负数往前移动
        calendar.add (GregorianCalendar.DATE, 1);
        //这个时间就是日期往后推一天的结果
        date = calendar.getTime ();
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
        String dateString = formatter.format (date);
        return dateString;
    }


    /**
     * 根据当前时间计算往后延的指定时间
     *
     * @param cur  当前要操作的时间
     * @param day  天数
     * @param hour 小时
     * @param min  分钟
     * @return
     */
    public static Date getNewDateByDelay(Date cur, int day, int hour, int min) {
        Calendar c = Calendar.getInstance ();
        c.setTime (cur);   //设置时间
        c.add (Calendar.DATE, day); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
        c.add (Calendar.HOUR, hour);
        c.add (Calendar.MINUTE, min);
        Date date = c.getTime (); //结果
        return date;
    }

    /**
     * 年月日字符串转换date
     *
     * @param str
     * @return
     */
    public static Date getDateByStr(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse (str);
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * date转年月日字符粗
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getStrByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return sdf.format (date);
    }


    /**
     * 查询第二天的零点
     *
     * @return
     */
    public static Date todayFirstDate() {
        Calendar calendar = Calendar.getInstance ();
        calendar.add (Calendar.DAY_OF_MONTH, +1);
        calendar.set (Calendar.HOUR_OF_DAY, 0);
        calendar.set (Calendar.MINUTE, 0);
        calendar.set (Calendar.SECOND, 0);
        calendar.set (Calendar.MILLISECOND, 0);
        return calendar.getTime ();
    }

    /**
     * 查询第二天的23点59分59秒
     *
     * @return
     */
    public static Date todayLastDate() {
        Calendar calendar = Calendar.getInstance ();
        calendar.add (Calendar.DAY_OF_MONTH, +1);
        calendar.set (Calendar.HOUR_OF_DAY, 23);
        calendar.set (Calendar.MINUTE, 59);
        calendar.set (Calendar.SECOND, 59);
        calendar.set (Calendar.MILLISECOND, 999);
        return calendar.getTime ();
    }


    /**
     * 获取上一个月
     *
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance ();
        cal.add (Calendar.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat ("yyyy-MM");
        String lastMonth = dft.format (cal.getTime ());
        return lastMonth;
    }

    /**
     * 描述:获取下一个月.
     *
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance ();
        cal.add (Calendar.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat ("yyyy-MM");
        String preMonth = dft.format (cal.getTime ());
        return preMonth;
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static String getCurrentStartMonth() {
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        Calendar c = Calendar.getInstance ();
        c.add (Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        c.set (Calendar.DAY_OF_MONTH, 1);
        return format.format (c.getTime ());
    }

    /**
     * 获取当前月最后一天
     *
     * @return
     */
    public static String getCurrentEndMonth() {
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance ();
        ca.set (Calendar.DAY_OF_MONTH, ca.getActualMaximum (Calendar.DAY_OF_MONTH));
        return format.format (ca.getTime ());
    }

    /**
     * 查询当天零点
     *
     * @return
     */
    public static String todayFirstCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance ();
        calendar.set (Calendar.HOUR_OF_DAY, 0);
        calendar.set (Calendar.MINUTE, 0);
        calendar.set (Calendar.SECOND, 0);
        calendar.set (Calendar.MILLISECOND, 0);
        return format.format (calendar.getTime ());
    }

    /**
     * 查询当天的23点59分59秒
     *
     * @return
     */
    public static String todayLastCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance ();
        calendar.set (Calendar.HOUR_OF_DAY, 23);
        calendar.set (Calendar.MINUTE, 59);
        calendar.set (Calendar.SECOND, 59);
        calendar.set (Calendar.MILLISECOND, 999);
        return format.format (calendar.getTime ());
    }



    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCurrentYearMoth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCurrentYearMothDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCurrentYearMothDayHms() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取单前月份
     *
     * @return
     */
    public static int getCurrentMonth() {
        return 1 + Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * 获得该月第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 描述:获取下一个月的时间.
     *
     * @return
     */
    public static String getPerFirstMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }


    public static void main(String[] args) {
        System.out.println (DateUtil.todayFirstCurrentDate ());
        System.out.println (DateUtil.todayLastCurrentDate ());
    }
}
