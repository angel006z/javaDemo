package com.meida.test.date;

import com.meida.common.util.DateUtils;

public class DateTest {
	public static void main(String[] args) {
		var nowTime = DateUtils.now();
		System.out.println(DateUtils.formatDate(nowTime, DateUtils.DEFAULT));
		System.out.println(DateUtils.formatDate(nowTime, DateUtils.DEFAULT_SECOND));
		
		System.out.println(DateUtils.getDayOfWeek(nowTime));
		System.out.println(DateUtils.getDayOfWeek(DateUtils.addDays(nowTime, 1)));
		System.out.println(DateUtils.getDayOfWeek(DateUtils.addDays(nowTime, 2)));
		System.out.println(DateUtils.getDayOfWeek(DateUtils.addDays(nowTime, 3)));
		System.out.println(DateUtils.getDayOfWeek(DateUtils.addDays(nowTime, 4)));
		System.out.println(DateUtils.getDayOfWeek(DateUtils.addDays(nowTime, 5)));
		System.out.println(DateUtils.getDayOfWeek(DateUtils.addDays(nowTime, 6)));
		System.out.println(DateUtils.getDayOfWeek(DateUtils.addDays(nowTime, 7)));
		
		System.out.println("当前："+DateUtils.getWeekDayName(nowTime));
		System.out.println(DateUtils.getWeekDayName(DateUtils.addDays(nowTime, 1)));
		System.out.println(DateUtils.getWeekDayName(DateUtils.addDays(nowTime, 2)));
		System.out.println(DateUtils.getWeekDayName(DateUtils.addDays(nowTime, 3)));
		System.out.println(DateUtils.getWeekDayName(DateUtils.addDays(nowTime, 4)));
		System.out.println(DateUtils.getWeekDayName(DateUtils.addDays(nowTime, 5)));
		System.out.println(DateUtils.getWeekDayName(DateUtils.addDays(nowTime, 6)));
		System.out.println(DateUtils.getWeekDayName(DateUtils.addDays(nowTime, 7)));
		
		System.out.println("年："+DateUtils.getYear(nowTime));
		System.out.println("月："+DateUtils.getMonth(nowTime));
		System.out.println("日："+DateUtils.getDay(nowTime));
		System.out.println("时："+DateUtils.getHour(nowTime));
		System.out.println("分："+DateUtils.getMinute(nowTime));
		System.out.println("秒："+DateUtils.getSecond(nowTime));
		System.out.println("毫秒："+DateUtils.getMilliSecond(nowTime));
	}
}
