package com.meida.common.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * io流读取properties文件
 *
 */
public class PropertiesIOUtil {
	/*public static void main(String[] args) {
		Properties prop = new Properties();
		try {
			// 读取属性文件
			InputStream in = PropertiesIOUtil.class.getResourceAsStream("/log4j.properties");
			prop.load(in); // /加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				System.out.println(key + ":" + prop.getProperty(key));
			}
			in.close();

			//保存属性到b.properties文件
			FileOutputStream oFile = new FileOutputStream("b.properties", true);// true表示追加打开
			prop.setProperty("phone", "10086");
			prop.store(oFile, "The New properties file");
			oFile.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/
	
	/**
	 * 读取properties文件
	 */
	public static String getProperty(String path,String key) {
		Properties prop = new Properties();
		try {
			// 读取属性文件
			InputStream in = PropertiesIOUtil.class.getResourceAsStream(path);
			prop.load(in); // /加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				if(!StringUtils.isEmpty(key)) {
					return prop.getProperty(key);
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}