package com.meida.common.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.meida.common.util.security.DesUtils;

/**
 * cookie工具类
 * @author dqwu
 *
 */
public class CookieUtils {

	public static Log log = LogFactory.getLog(CookieUtils.class);

	/**
	 * 添加cookie
	 * @param cookName cookie名称
	 * @param cookValue 要添加的cookie值
	 * @param cookieSeconds cookie保存的秒数 
	 */
	public static void addCooke(HttpServletResponse response, String cookName, String cookValue, int cookieSeconds) {
		String encodeValue = null;
		try {
			encodeValue = DesUtils.encrypt(cookValue);
		} catch (Exception e) {
			log.error("添加cookie，对值加密失败！", e);
			return;
		}
		Cookie cookie = new Cookie(cookName, encodeValue);
		cookie.setMaxAge(cookieSeconds);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 根据cookie名称查找cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		String encodeValue = getCookieValue(request, name);
		if (encodeValue == null) {
			return null;
		}
		String decodeValue = null;
		try {
			decodeValue = DesUtils.decrypt(encodeValue);
		} catch (Exception e) {
			log.error("查找cookie，对值解密失败！", e);
		}
		return decodeValue;
	}

	/**
	 * 根据cookie名称删除cookie
	 * @param request
	 * @param response
	 * @param name
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		try {
			String encodeValue = getCookieValue(request, name);
			if (encodeValue != null) {
				Cookie cookie = new Cookie(name, encodeValue);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		} catch (Exception e) {
			log.error("删除cookie失败！", e);
		}

	}

	/**
	 * 获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	private static String getCookieValue(HttpServletRequest request, String name) {
		String cookieStr = request.getHeader("cookie");
		if (cookieStr == null) {
			return null;
		}
		String[] cookies = cookieStr.split(";");
		for (int i = 0; i < cookies.length; i++) {
			String cookie = cookies[i].trim();
			if (cookie.startsWith(name)) {// 判断的是cookie的名称
				String cookieValue = cookie.substring(name.length() + 1);
				if (cookieValue != null && (cookieValue.startsWith("\"") || cookieValue.endsWith("\""))) {
					cookieValue = cookieValue.replaceAll("\"", "");
				}
				return cookieValue;
			}
		}
		return null;
	}
}
