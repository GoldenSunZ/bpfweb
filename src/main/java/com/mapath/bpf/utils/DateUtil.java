package com.mapath.bpf.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xuyunlong on Tue Jan 17 2017 14:23:59 GMT+0800 (CST).
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATETIME_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_YYYY = "yyyy";

    /**
     * 默认的格式化工具
     */
    private static SimpleDateFormat formatTool = new SimpleDateFormat();

    private static final SimpleDateFormat defDateFormat =
            new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);

    private static final SimpleDateFormat defDateTimeFmt =
            new SimpleDateFormat(DATETIME_FORMAT_YYYY_MM_DD_HHMMSS);

    //获取系统日期，不含时间
    public static Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateString = defDateFormat.format(date);
        Date nowDate = defDateFormat.parse(dateString, new ParsePosition(0));

        return nowDate;
    }

    //获取系统日期，含时间
    public static Date getSystemDateTime() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateString = defDateTimeFmt.format(date);
        Date nowDateTime = defDateTimeFmt.parse(dateString, new ParsePosition(0));

        return nowDateTime;
    }
    /**
     * 获取2个日期之间周六，周日的天数
     * @param startDate
     * @param endDate
     * @param format
     * @return
     * @author zhouxiaobo
     * @date 2016-10-12
     */
    public static int getSundayNum(String startDate, String endDate, String format) {
        List yearMonthDayList = new ArrayList();
        Date start = null, stop = null;
        try {
            start = new SimpleDateFormat(format).parse(startDate);
            stop = new SimpleDateFormat(format).parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (start.after(stop)) {
            Date tmp = start;
            start = stop;
            stop = tmp;
        }
        //将起止时间中的所有时间加到List中
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(start);
        while (calendarTemp.getTime().getTime() <= stop.getTime()) {
            yearMonthDayList.add(new SimpleDateFormat(format)
                    .format(calendarTemp.getTime()));
            calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
        }
        Collections.sort(yearMonthDayList);
        int num=0;//周六，周日的总天数
        int size=yearMonthDayList.size();
        int week=0;
        for (int i = 0; i < size; i++) {
            String day=(String)yearMonthDayList.get(i);
            week=getWeek(day, format);
            if (week==6||week==0) {//周六，周日
                num++;
            }
        }
        return num;
    }
    /**
     * 获取某个日期是星期几
     * @param date
     * @param format
     * @return 0-星期日
     * @author zhouxiaobo
     * @date 2016-10-12
     */
    public static int getWeek(String date, String format) {
        Calendar calendarTemp = Calendar.getInstance();
        try {
            calendarTemp.setTime(new SimpleDateFormat(format).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = calendarTemp.get(Calendar.DAY_OF_WEEK);
        int value=i-1;//0-星期日
        //        System.out.println(value);
        return value;
    }

    /**
     * 获取2个日期相差几天
     * @param smdate
     * @param bdate
     * @return 0-相差几天
     * @author zhouxiaobo
     * @date 2016-10-12
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 将date 类型时间转换成formatStr格式的字符串时间
     * @param date
     * @param formatStr
     * @return
     */
    public static Date dateFormat(Date date, String formatStr) {
        formatTool.applyPattern(formatStr);
        try {
            date = formatTool.parse(formatTool.format(date));
        } catch (ParseException e) {
            logger.info("Date parse exception" + e, e);
        }
        return date;
    }

    /**
     * 将字符串时间转换成date 类型时间
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Date str2Date(String dateStr, String formatStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        formatTool.applyPattern(formatStr);
        try {
            return formatTool.parse(dateStr);
        } catch (ParseException e) {
            logger.info("Date parse exception" + e, e);
        }
        return null;
    }

    /**
     * 将传入的时间转换成format格式的字符串时间
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date == null) {
            return null;
        }
        formatTool.applyPattern(format);
        return formatTool.format(date);
    }

    public static Date getCurrentMonthFirstDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return dateFormat(calendar.getTime(), DATE_FORMAT_YYYY_MM_DD);
    }

    public static void main(String[] args) {
        int i=getSundayNum("2013-03-01", "2013-03-20", "yyyy-MM-dd");
        System.out.println(i);
    }

    //获取本月第一天
    public static Date getFirstDayofMonth(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime();
    }

    //获取本月第一天
    public static Date getLastDayofMonth(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR,0);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        return ca.getTime();
    }

    /**
     * 获取指定日期范围内的工作日列表
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> getWorkDayList(Date startDate, Date endDate){
    	List<Date> dates = new ArrayList<Date>();
    	while (endDate.compareTo(startDate)>=0) {
    		if (isWorkDay(startDate)) {
    			dates.add(startDate);
    		}
			startDate = addDay(startDate,1);
    	}
        return dates;
    }

    /**
     * 获取指定日期中的工作日
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> getWorkDayList(List<Date> dtList){
    	List<Date> dates = new ArrayList<Date>();
    	for (Date d : dtList) {
    		if (isWorkDay(d)) {
    			dates.add(d);
    		}
    	}
        return dates;
    }

    public static List<String> getWorkDayStrList(Date startDate, Date endDate){
    	List<String> dates = new ArrayList<String>();
    	while (endDate.compareTo(startDate)>=0) {
    		if (isWorkDay(startDate)) {
    			dates.add(date2Str(startDate, DATE_FORMAT_YYYY_MM_DD));
    			startDate = addDay(startDate,1);
    		}
    	}
        return dates;
    }

	/**
	 * 是否是工作日
	 * @param date
	 * @return int
	 */
	public static boolean isWorkDay(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int day = cal.get(Calendar.DAY_OF_WEEK);
    	if (Calendar.SATURDAY==day || Calendar.SUNDAY==day) {
    		return false;
    	}
    	return true;
	}

    /**
     * 获取几天后的日期
     * @param date
     * @param day
     * @return
     */
	public static Date addDay(Date date,int day) {
		Calendar cal = Calendar.getInstance();//返回Calendar对象的实例
		cal.setTime(date);
		cal.add(Calendar.DATE, day);

		return cal.getTime();
	}


}
