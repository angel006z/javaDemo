package com.meida.common.util;

import java.util.Calendar;

/**
 * 身份证解析工具类
 */
public class CertificateUtils {
	
	public static void main(String[] args) {
		System.out.println(getAgeFromIdCard("61252519921006140X"));
		System.out.println(getSexFromIdCard("61252519921006140X"));
	}
	
	/**
	 * 从身份证中解析年龄
	 * @return
	 */
	public static Integer getAgeFromIdCard(String CertificateNo) {
		String birthYear = "";
		if(CertificateNo.length() == 15) {
			birthYear = 19 + CertificateNo.substring(6, 8);
		} else if(CertificateNo.length() == 18) {
			birthYear = CertificateNo.substring(6, 10);
		}
		if(!StringUtils.isEmpty(birthYear) && CheckUtils.checkNumber(birthYear)) {
			int currYear = Calendar.getInstance().get(Calendar.YEAR);
			int age = currYear - Integer.parseInt(birthYear);
			return age;
		}
        return -1;
	}
	
	/**
	 * 从身份证中解析性别
	 * @return 1为男 0为女
	 */
	public static String getSexFromIdCard(String CertificateNo) {
		String sexFlag = "";
		if(CertificateNo.length() == 15) {
			sexFlag = CertificateNo.substring(14, 15);
		} else if(CertificateNo.length() == 18) {
			sexFlag = CertificateNo.substring(16, 17);
		}
		if(!StringUtils.isEmpty(sexFlag) && CheckUtils.checkRightNumber(sexFlag)) {
			if(Integer.parseInt(sexFlag)%2 == 1) {
				return "1";
			} else {
				return  "0";
			}
		}
		return "-1";
	}
}
