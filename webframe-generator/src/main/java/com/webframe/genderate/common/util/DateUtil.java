package com.webframe.genderate.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {

	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat yyyyMMdd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final String[] parsePatterns = { "yyyy/MM/dd", "yyyy-MM-dd",
		"yyyy-M-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
		"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy年MM月",
		"yyyy年MM月dd日" };
	
	/**
	 * 年月日形式 
	 * @param dateStr
	 * @return
	 */
	public static String getDateStrCN(String dateStr){
		if(!StringUtil.isEmpty(dateStr) && dateStr.length() == 10){
			return dateStr.substring(0, 4) + "年" + dateStr.substring(5, 7) + "月" + dateStr.substring(8, 10) + "日";
		}
		return "";
	}
	
	
    /**
     * 获取当前日期时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDateStr() {
    	return yyyyMMdd_HHmmss.format(new Date());
    }
    
    
    /**
     * 获取当天日期 yyyy-MM-dd
     * @return
     */
    public static String getTodayStr(){
    	return yyyyMMdd.format(new Date());
    }
    
    /**
     * 获取当天日期
     * @return
     */
    public static Date getToday(){
    	return new Date();
    }
    
     /*********  日期与字符串的转换   ************/
    
    
    
    /**
     * 日期转字符串  yyyy-MM-dd
     * @param date
     */
    public static String date2Str(Object date) {
        if (date == null){ 
        	return "";
        }
        return yyyyMMdd.format(date);
    }
    
    /**
     * 日期转字符串  yyyy-MM-dd HH:mm:ss
     * @param date
     */
    public static String dateTime2Str(Object date) {
        if (date == null){ 
        	return "";
        }
        return yyyyMMdd_HHmmss.format(date);
    }
    
    /**
     * 字符串转日期
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) {
    	Date date = null;
    	if(StringUtil.isEmpty(dateStr)){
			return date;
		}
    	try {
			date = DateUtils.parseDate(dateStr,parsePatterns);
		} catch (ParseException e) {
			
		}
		return date;
	}
    
    
    /*********  日期与字符串的转换结束   ************/
    
    /**
     * 获取当天年份
     * @return
     */
    public static String getYear(){
    	return getTodayStr().substring(0,4);
    }
    
    /**
     * 获取当天月份
     * @return
     */
    public static String getMonth(){
    	return getTodayStr().substring(5,7);
    }

    
    /**
     * 得到当前日期是本月第几周
     * @param curday
     */
    public static int getMonthWeek(Date curDate) {
        try {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(curDate);
            return rightNow.get(Calendar.WEEK_OF_MONTH);
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * 得到当前日期是本年第几周
     * @param curday
     */    
    public static int getYearWeek(Date curDate) {
        try {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(curDate);
            return rightNow.get(Calendar.WEEK_OF_YEAR);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    
    /****************  获取日期前后的日期   *******************/
    
    
    /**
     * 天数前后的日期  正数表示天数后  负数表示天数前   
     * 2013-10-10,8  结果:2013-10-18
     * @param date
     * @param days
     * @return
     */
    public static String getPerAfterDay(String dateStr,int days){
    	Date date = parseDate(dateStr);
    	return getPerAfterDay(date,days);
    }
    
    public static String getPerAfterDay(Date date,int days){
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.DAY_OF_YEAR, days);
    	return date2Str(rightNow.getTime());
    }
    
    /**
     * 月数前后的日期  正数表示月数后  负数表示月数前
     * 2013-10-10,8  结果:2014-6-18
     * @param date
     * @param months
     * @return
     */
    public static String getPerAfterMonth(String dateStr,int months){
    	Date date = parseDate(dateStr);
    	return getPerAfterMonth(date,months);
    }
    
    public static String getPerAfterMonth(Date date,int months){
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.MONTH, months);
    	return date2Str(rightNow.getTime());
    }
    
    /**
     * 年数前后的日期  正数表示年数后  负数表示年数前
     * 2013-10-10,8  结果:2021-6-18
     * @param date
     * @param years
     * @return
     */
    public static String getPerAfterYear(String dateStr,int years){
    	Date date = parseDate(dateStr);
    	return getPerAfterMonth(date,years);
    }
    
    public static String getPerAfterYear(Date date,int years){
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.YEAR, years);
    	return date2Str(rightNow.getTime());
    }
    
    
    /**
     * 周数前后的日期  正数表示周数后  负数表示周数前
     * 2013-10-10,2  结果:2013-10-24
     * @param date
     * @param weeks
     * @return
     */
    public static String getPerAfterWeek(String dateStr,int weeks){
    	Date date = parseDate(dateStr);
    	return getPerAfterMonth(date,weeks);
    }
    
    public static String getPerAfterWeek(Date date,int weeks){
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.WEEK_OF_YEAR, weeks);
    	return date2Str(rightNow.getTime());
    }
    
    
    
    /**
     * 返回周几 1-7  1表示周日 7表示周六
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        try {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            return rightNow.get(Calendar.DAY_OF_WEEK);
        }
        catch (Exception e) {
            return 1;
        }
    }
 
    /**
    * 返回星期几 
    * @param date
    * @return
    */
    public static String getWeekCN(Date date) {
        int i = getWeekDay(date);
        String s = "";
        switch (i) {
            case 1:
                s = "星期天";
                break;
            case 2:
                s = "星期一";
                break;
            case 3:
                s = "星期二";
                break;
            case 4:
                s = "星期三";
                break;
            case 5:
                s = "星期四";
                break;
            case 6:
                s = "星期五";
                break;
            case 7:
                s = "星期六";
                break;
        }
        return s;
    }

    
   /**
    * 返回日期当月最后一天
    * @param date
    * @return
    */
    public static String getMonthLastDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.roll(Calendar.DAY_OF_MONTH, -1);
        return  yyyyMMdd.format(rightNow.getTime());
    }
    
    public static String getMonthLastDay(String dateStr) {
    	Date date = parseDate(dateStr);
    	return getMonthLastDay(date);
    }
    
    /************ 两个日期之间的月数、天数  *******************/
    
   /**
    * 两个日期的月数  endDate - startDate  
    * startDate 2013-01-01 endDate 2013-07-02  结果为6
    * @param startDate
    * @param endDate
    * @return
    */
    public static int getDiffMonths(Date startDate, Date endDate) {
        String startDateStr = date2Str(startDate);
        String endDateStr = date2Str(endDate);
    	return getDiffMonths(startDateStr, endDateStr);
    }
    
    public static int getDiffMonths(String startDateStr, String endDateStr) {
    	int len;
        int year = Integer.parseInt(endDateStr.substring(0, 4)) -Integer.parseInt(startDateStr.substring(0, 4));
        len = 12 * year + Integer.parseInt(endDateStr.substring(5, 7)) - Integer.parseInt(startDateStr.substring(5, 7));
        return len;
    }
    

    /**
     * 两个日期的天数  endDate - startDate  
     * startDate 2013-01-01 endDate 2013-01-02  结果为1
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDiffDays(Date startDate, Date endDate) {
        Long str = (endDate.getTime() - startDate.getTime()) / (3600 * 24 * 1000);
        return str.intValue();
    }
    
    public static int getDiffDays(String startDateStr, String endDateStr) {
    	Date startDate = parseDate(startDateStr);
    	Date endDate = parseDate(endDateStr);
    	return getDiffDays(startDate, endDate);
    }
	
    
	/**
	 * 判断某年是平年还是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(String year){
		boolean isLeapYear = false;
		int yearInt = Integer.parseInt(year);
		if(yearInt / 400 == 0 || (yearInt % 4 == 0 && yearInt / 100 != 0)){
			isLeapYear = true;
		}
		return isLeapYear;
	}

	 /**
	  * 将其他格式转为yyyy-MM-dd
	  * @param str
	  * @return
	  */
	 public static String toDateStr(String str){
		 String dateStr = "";
		 if(str != null && str.length() == 10){
			 dateStr = str.substring(0, 4) + "-" + str.substring(5, 7) + "-" + str.substring(8, 10);
		 }
		 return dateStr;
	 }
	 
	 
	 
}





