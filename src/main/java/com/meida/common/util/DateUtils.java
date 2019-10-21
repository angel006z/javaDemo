package com.meida.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 	日期处理工具类
 *  parse前缀：字符转日期格式
 *  format前缀：格式化日期
 *  add前缀：增加
 *  get前缀：获取
 */
public class DateUtils {

	/** yyyy-MM-dd */
	public static final String DEFAULT = "yyyy-MM-dd";
	/** yyyy-MM-dd HH:mm:ss */
	public static final String DEFAULT_SECOND = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd HH:mm */
	public static final String DEFAULT_MINUTE = "yyyy-MM-dd HH:mm";
	/** yyyyMMdd */
	public static final String DEFAULT_OTER = "yyyyMMdd";
	/** yyyy/MM/dd */
	public static final String DEFAULT_OTER_ONE = "yyyy/MM/dd";
	/** HH */
	public static final String HOURS = "HH";
	/** yyyy */
	public static final String YEAR = "yyyy";
	/** MM */
	public static final String MONTH = "MM";
	/** dd */
	public static final String DAY = "dd";
	/** yyyy-MM */
	public static final String YEAR_MONTH = "yyyy-MM";
	/** yyyy年MM月dd日 */
	public static final String DEFAULT_CHINA = "yyyy年MM月dd日";
	/** MM月dd日 */
	public static final String DEFAULT_CHINA_TWO = "MM月dd日";
	/** yyyy年MM月dd日 HH时:mm分 */
	public static final String DEFAULT_MINUTE_CHINA = "yyyy年MM月dd日 HH时:mm分";
	/** HH时:mm分 */
	public static final String MINUTE_CHINA = "HH时:mm分";
	/** HH:mm */
	public static final String MINUTE = "HH:mm";
	/** HH:mm:ss */
	public static final String MINUTES = "HH:mm:ss";

	/**
	 * 按照指定的格式，将字符串转换成日期类型对象，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String s, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		try {
			return formater.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 得到现在的时间
	 * 
	 * @return Date
	 */
	public static Date now() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 获取年
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 获取月
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 获取日
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}
	/**
	 * 获取时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR);
	}
	/**
	 * 获取分
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}
	/**
	 * 获取秒
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}
	/**
	 * 获取毫秒
	 */
	public static int getMilliSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MILLISECOND);
	}


	/**
	 * 获取星期
	 * 其中:0星期日、 1星期一、 2星期二、3星期三、4星期四、5星期五、6星期六
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK)-1;
	}

	/**
	 * 得到现在的时间戳
	 * 
	 * @return Date
	 */
	public static Long nowTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 按照指定的格式，将日期类型对象转换成字符串， 例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
	 * 
	 * @param date
	 * @param pattern 格式
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern, Locale.CHINA);
		return formater.format(date);
	}
	
	/**
	*  加年数
	*/
	public static Date addYears(Date date,int amount) {
		return calendarAdd(date,Calendar.YEAR,amount);
	}
	/**
	*  加月数
	*/
	public static Date addMonths(Date date,int amount) {
		return calendarAdd(date,Calendar.MONTH,amount);
	}
	/**
	*  加天数
	*/
	public static Date addDays(Date date,int amount) {
		return calendarAdd(date,Calendar.DATE,amount);
	}
	/**
	*  加小时数
	*/
	public static Date addHours(Date date,int amount) {
		return calendarAdd(date,Calendar.HOUR,amount);
	}
	/**
	*  加分钟数
	*/
	public static Date addMinutes(Date date,int amount) {
		return calendarAdd(date,Calendar.MINUTE,amount);
	}
	/**
	*  加秒数
	*/
	public static Date addSeconds(Date date,int amount) {
		return calendarAdd(date,Calendar.SECOND,amount);
	}
	/**
	*  加毫秒数
	*/
	public static Date addMilliseconds(Date date,int amount) {
		return calendarAdd(date,Calendar.MILLISECOND,amount);
	}
	private static Date calendarAdd(Date date,int field,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	
	/**
	 * 得到一个月有几周
	 * 
	 * @return
	 */
	public static Integer getMonthWeekCount(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获取星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekDayName(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int w = getDayOfWeek(date);
		if (w < 0)
			w = 0;
		return weekDays[w];		
	}

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	
	
	/**
	 * 时间比较, 比较到日
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 0表示相同, 正数是date1 > date2, 负数是date1 < date2
	 */
	public static int compareToDay(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return 0;
		}
		if (date1 == null) {
			return -1;
		}
		if (date2 == null) {
			return 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date1).compareTo(sdf.format(date2));
	}

	/**
	 * 时间比较, 比较到时
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 0表示相同, 正数是date1 > date2, 负数是date1 < date2
	 */
	public static int compareToHour(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return 0;
		}
		if (date1 == null) {
			return -1;
		}
		if (date2 == null) {
			return 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		return sdf.format(date1).compareTo(sdf.format(date2));
	}

	/**
	 * 时间比较, 比较到秒
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 0表示相同, 正数是date1 > date2, 负数是date1 < date2
	 */
	public static int compareToSecond(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return 0;
		}
		if (date1 == null) {
			return -1;
		}
		if (date2 == null) {
			return 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date1).compareTo(sdf.format(date2));
	}

	/**
	 * 时间比较, 比较时分秒
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 0表示相同, 正数是date1 > date2, 负数是date1 < date2
	 */
	public static int compareHour(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return 0;
		}
		if (date1 == null) {
			return -1;
		}
		if (date2 == null) {
			return 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(date1).compareTo(sdf.format(date2));
	}

	/**
	 * 比较两个日期相差的天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getDayDiffer(Date d1, Date d2) {
		long rel = d2.getTime() - d1.getTime();
		return Math.abs(rel / (1000 * 60 * 60 * 24));
	}

	/**
	 * 比较两个日期相关的天数 （比较到天）
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getDayDifferByDay(Date d1, Date d2) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(d1);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(d2);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;
	}

	/**
	 * 比较两个日期相差的小时
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getHourDiffer(Date d1, Date d2) {
		long rel = d2.getTime() - d1.getTime();
		return Math.abs(rel / (1000 * 60 * 60));
	}

	/**
	 * 比较两个日期相差的分钟数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getMinuteDiffer(Date d1, Date d2) {
		long rel = d2.getTime() - d1.getTime();
		return Math.abs(rel / (1000 * 60));
	}

	/**
	 * 比较两个日期相差的秒数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getSecondDiffer(Date d1, Date d2) {
		long rel = d2.getTime() - d1.getTime();
		return Math.abs(rel / (1000));
	}

	/**
	 * 比较两个日期相差的毫秒
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getDiffer(Date d1, Date d2) {
		long rel = d2.getTime() - d1.getTime();
		return Math.abs(rel);
	}

	/**
	 * 获取两个时间相差的时间字符串，供调试时间差使用
	 * 
	 * @param startTimeMillis 开始毫秒数 在代码开始加上：long startTimeMillis =
	 *                        System.currentTimeMillis();
	 * @param endTimeMillis   结束毫秒数 在代码结尾加上：long endTimeMillis =
	 *                        System.currentTimeMillis();
	 * @return
	 */
	public static String getTimeDifferenceString(long startTimeMillis, long endTimeMillis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(endTimeMillis - startTimeMillis);

		return "耗时: " + c.get(Calendar.MINUTE) + "分 " + c.get(Calendar.SECOND) + "秒 " + c.get(Calendar.MILLISECOND)
				+ " 毫秒";
	}
}
