package com.meida.common.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class RequestParameters {

	private static String getHttpRequestString(String szString, HttpServletRequest request) {
		return request.getParameter(szString);
	}

	/**
	 * UUID
	 * 
	 * @param szString
	 * @param request
	 * @return 默认返回Guid.Empty
	 */
	public static UUID getGuid(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		if (StringUtils.isEmpty(input))
			return UUIDUtils.Empty;

		String regex = "^[A-Fa-f0-9]{8}(-[A-Fa-f0-9]{4}){3}-[A-Fa-f0-9]{12}$";
		if (Pattern.matches(regex, input.trim())) {
			return UUID.fromString(input.trim());
		}

		return UUIDUtils.Empty;
	}

	/**
	 * String
	 * 
	 * @param szString
	 * @param request
	 * @return 默认返回“”
	 */
	public static String getString(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		return StringUtils.isEmpty(input) ? "" : input.trim();
	}

	/**
	 * String
	 * 
	 * @param szString
	 * @param request
	 * @return 默认返回null
	 */
	public static String getStringNull(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		return StringUtils.isEmpty(input) ? null : input.trim();
	}

	/**
	 * Date
	 * 
	 * @param szString
	 * @param request
	 * @return 默认返回null
	 */
	public static Date getDate(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		if (StringUtils.isEmpty(input))
			return null;
		return DateUtils.parseDate(input.trim(), DateUtils.DEFAULT_SECOND);
	}

	public static int getInt(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		int result = 0;
		if (StringUtils.isEmpty(input))
			return result;
		String regex = "^-?(([1-9]+\\d*)|(0))$";
		if (Pattern.matches(regex, input.trim())) {
			try {
				return Integer.parseInt(input.trim());
			} catch (Exception e) {
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}

	// float:浮点型，含字节数为4，32bit，数值范围为-3.4E38~3.4E38（7个有效位）
	// double:双精度实型，含字节数为8，64bit数值范围-1.7E308~1.7E308（15个有效位）
	// decimal:数字型，128bit，不存在精度损失，常用于银行帐目计算。（28个有效位）
	public static float getFloat(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		float result = 0.0f;
		if (StringUtils.isEmpty(input))
			return result;

		String regex = "^(-?\\d+)(\\.\\d+)?$";
		if (Pattern.matches(regex, input.trim())) {
			try {
				return Float.parseFloat(input.trim());
			} catch (Exception e) {
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}

	public static double getDouble(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		double result = 0.0d;
		if (StringUtils.isEmpty(input))
			return result;

		String regex = "^(-?\\d+)(\\.\\d+)?$";
		if (Pattern.matches(regex, input.trim())) {
			try {
				return Double.parseDouble(input.trim());
			} catch (Exception e) {
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}

	public static BigDecimal getDecimal(String szString, HttpServletRequest request) {
		String input = getHttpRequestString(szString, request);
		BigDecimal result = new BigDecimal("0.00");
		if (StringUtils.isEmpty(input))
			return result;

		String regex = "^(-?\\d+)(\\.\\d+)?$";
		if (Pattern.matches(regex, input.trim())) {
			try {
				return new BigDecimal(input.trim());
			} catch (Exception e) {
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}
}
