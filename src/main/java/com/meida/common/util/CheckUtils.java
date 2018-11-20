package com.meida.common.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据检测方法工具类
 * 待测试或重写该内方法
 */
public class CheckUtils {

	/**
	 * 判断字符串是否为空, null,或trim size==0
	 *
	 * @param s 要判断的字符串
	 * @return true: 空, false:非空
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * 判断一个字符串是否为长整形
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isLong(String s) {
		try {
			new Long(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断一个字符串是否为浮点形
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isDouble(String s) {
		try {
			new Double(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断一个字符串是否为数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isInteger(String s) {
		try {
			new Integer(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 检测用户名是否正确
	 * 
	 * @param userName
	 * @return
	 */
	public static boolean checkUserName(String userName) {
		if (isEmpty(userName)) {
			return false;
		}
		return Pattern.matches("^[a-zA-Z0-9_]{3,30}$", userName.trim());
	}

	/**
	 * 检测固话是否正确
	 * 
	 * @param telPhone
	 * @return
	 */
	public static boolean checkTelPhone(String telPhone) {
		if (isEmpty(telPhone)) {
			return false;
		}
		return Pattern.matches("^((\\(\\d{3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}+$",
				telPhone.trim());
	}

	/**
	 * 检测手机号码是否正确
	 * 
	 * @param telPhone
	 * @return
	 */
	public static boolean checkMobilPhone(String telPhone) {
		if (isEmpty(telPhone)) {
			return false;
		}
		/* return Pattern.matches("^1[3,4,5,7,8]\\d{9}+$", telPhone.trim()); */
		return Pattern.matches("^1\\d{10}+$", telPhone.trim());
	}

	/**
	 * 验证身份证号
	 * 
	 * @author sls
	 * @date 2018年8月29日
	 * @param @param str
	 * @param @return
	 * @return boolean
	 */
	public static boolean IsIDcard(String s) {
		if (isEmpty(s)) {
			return false;
		}
		return Pattern.matches("(^\\d{18}$)|(^\\d{15}$)", s.trim());
	}

	/**
	 * 检测QQ号码是否正确
	 * 
	 * @param qq QQ号码
	 * @return
	 */
	public static boolean checkQq(String qq) {
		if (isEmpty(qq)) {
			return false;
		}
		return Pattern.matches("^[1-9][0-9]{4,}$", qq.trim());
	}

	/**
	 * 检测email是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}
		return Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email.trim());
	}

	/**
	 * 检测IP地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkIP(String ip) {
		if (isEmpty(ip)) {
			return false;
		}
		return Pattern.matches(
				"^\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b+$",
				ip.trim());
	}

	/**
	 * 检测传真号码 faxNumber 是否正确
	 * 
	 * @author sw
	 * @date 2015-12-8 上午9:00:35
	 * @param faxNumber 传真号码
	 * @return
	 */
	public static boolean checkFaxNumber(String faxNumber) {
		if (isEmpty(faxNumber)) {
			return false;
		}
		return Pattern.matches("(\\d{3,4})?(\\-)?\\d{7,8}", faxNumber.trim());
	}

	/**
	 * 检测价格是否正确
	 * 
	 * @param price
	 * @return
	 */
	public static boolean checkPrice(String price) {
		if (isEmpty(price)) {
			return false;
		}
		return Pattern.matches("^\\d+(\\.[0-9]{0,2})?+$", price.trim());
	}

	/**
	 * 检测是否正整数
	 * 
	 * @param price
	 * @return
	 */
	public static boolean checkNumber(String numStr) {
		if (isEmpty(numStr)) {
			return false;
		}

		return Pattern.matches("^[0-9]*[1-9][0-9]*$", numStr.trim());
	}

	/**
	 * 检测是否正整数和0
	 * 
	 * @author sw
	 * @date 2016-4-21 上午9:09:40
	 * @param numStr
	 * @return
	 */
	public static boolean checkRightNumber(String numStr) {
		if (isEmpty(numStr)) {
			return false;
		}
		return Pattern.matches("^(0|\\+?[1-9][0-9]*)$", numStr.trim());
	}

	/**
	 * 检测日期格式是否 正确
	 * 
	 * @param dateStr 日期（如：2010-05-01）
	 * @return
	 */
	public static boolean checkDate(String dateStr) {
		if (isEmpty(dateStr)) {
			return false;
		}
		return Pattern.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", dateStr.trim());
	}

	/**
	 * 验证数字含负数是否 正确
	 * 
	 * @param dateStr 日期（如：2010-05-01）
	 * @return
	 */
	public static boolean isNumeric(String numStr) {
		if (isEmpty(numStr)) {
			return false;
		}
		return Pattern.matches("-?[0-9]*.?[0-9]*", numStr.trim());
	}

	/**
	 * 判断输入的字符串是否满足时间格式 ： yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr 需要验证的字符串
	 * @return 合法返回 true ; 不合法返回false
	 */
	public static boolean checkDateTime(String dateStr) {

		Pattern a = Pattern.compile(
				"^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");

		Matcher b = a.matcher(dateStr);
		if (b.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证大于等于0小于等于1的小数
	 * 
	 * @return
	 */
	public static boolean checkLessOne(String numStr) {
		if (isEmpty(numStr)) {
			return false;
		}
		return Pattern.matches("0.[0-9]{1,2}|0|1", numStr.trim());
	}

	/**
	 * 身份证校验工具（支持15位或者18位身份证）<br/>
	 * 身份证号码结构：
	 * <ol>
	 * <li>17位数字和1位校验码：6位地址码数字，8位生日数字，3位出生时间顺序码，1位校验码。</li>
	 * <li>地址码（前6位）：表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。</li>
	 * <li>出生日期码（第七位至十四位）：表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。</li>
	 * <li>顺序码（第十五位至十七位） ：表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
	 * 顺序码的奇数分配给男性，偶数分配给女性。</li>
	 * <li>校验码（第十八位数）：<br/>
	 * <ul>
	 * <li>十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, , 16 ，先对前17位数字的权求和；
	 * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
	 * 2；</li>
	 * <li>计算模 Y = mod(S, 11)</li>
	 * <li>通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2</li>
	 * </ul>
	 * </li>
	 * </ol>
	 *
	 * 身份证号是否基本有效
	 * 
	 * @param s 号码内容
	 * @return 是否有效，null和""都是false
	 */
	public static boolean isIdcard(String s) {

		try {
			final Map<Integer, String> zoneNum = new HashMap<Integer, String>();
			zoneNum.put(11, "北京");
			zoneNum.put(12, "天津");
			zoneNum.put(13, "河北");
			zoneNum.put(14, "山西");
			zoneNum.put(15, "内蒙古");
			zoneNum.put(21, "辽宁");
			zoneNum.put(22, "吉林");
			zoneNum.put(23, "黑龙江");
			zoneNum.put(31, "上海");
			zoneNum.put(32, "江苏");
			zoneNum.put(33, "浙江");
			zoneNum.put(34, "安徽");
			zoneNum.put(35, "福建");
			zoneNum.put(36, "江西");
			zoneNum.put(37, "山东");
			zoneNum.put(41, "河南");
			zoneNum.put(42, "湖北");
			zoneNum.put(43, "湖南");
			zoneNum.put(44, "广东");
			zoneNum.put(45, "广西");
			zoneNum.put(46, "海南");
			zoneNum.put(50, "重庆");
			zoneNum.put(51, "四川");
			zoneNum.put(52, "贵州");
			zoneNum.put(53, "云南");
			zoneNum.put(54, "西藏");
			zoneNum.put(61, "陕西");
			zoneNum.put(62, "甘肃");
			zoneNum.put(63, "青海");
			zoneNum.put(64, "宁夏");
			zoneNum.put(65, "新疆");
			zoneNum.put(71, "台湾");
			zoneNum.put(81, "香港");
			zoneNum.put(82, "澳门");
			zoneNum.put(91, "国外");

			int[] PARITYBIT = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
			int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
			if (s == null || (s.length() != 15 && s.length() != 18))
				return false;
			final char[] cs = s.toUpperCase().toCharArray();
			// （1）校验位数
			int power = 0;
			for (int i = 0; i < cs.length; i++) {// 循环比正则表达式更快
				if (i == cs.length - 1 && cs[i] == 'X')
					break;// 最后一位可以是X或者x
				if (cs[i] < '0' || cs[i] > '9')
					return false;
				if (i < cs.length - 1)
					power += (cs[i] - '0') * POWER_LIST[i];
			}
			// （2）校验区位码
			if (!zoneNum.containsKey(Integer.valueOf(s.substring(0, 2)))) {
				return false;
			}
			// （3）校验年份
			String year = s.length() == 15 ? "19" + s.substring(6, 8) : s.substring(6, 10);
			final int iyear = Integer.parseInt(year);
			if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
				return false;// 1900年的PASS，超过今年的PASS
			}
			// （4）校验月份
			String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10, 12);
			final int imonth = Integer.parseInt(month);
			if (imonth < 1 || imonth > 12)
				return false;
			// （5）校验天数
			String day = s.length() == 15 ? s.substring(10, 12) : s.substring(12, 14);
			final int iday = Integer.parseInt(day);
			if (iday < 1 || iday > 31)
				return false;
			// （6）校验一个合法的年月日
			if (!validate(iyear, imonth, iday))
				return false;
			// （7）校验“校验码”
			if (s.length() == 15)
				return true;
			return cs[cs.length - 1] == PARITYBIT[power % 11];
		} catch (Exception e) {
			return false;
		}
	}

	static boolean validate(int year, int month, int day) {
		// 比如考虑闰月，大小月等
		return true;
	}

	/**
	 * 判断输入的字符序列是否与正则表达式相匹配，若匹配， 则返回 true,否则返回 false。
	 * 
	 * @param regex 正则表达式
	 * @param value 字符序列
	 */
	public static boolean isMatch(String regex, String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.matches();
	}
}
