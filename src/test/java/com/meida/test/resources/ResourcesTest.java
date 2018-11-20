package com.meida.test.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class ResourcesTest {
	/**
	 * 1、基于ClassLoder读取配置文件
	 * @throws IOException
	 */
	@Test
	public void testProperties01() throws IOException {
		Properties properties = new Properties();
	    // 使用ClassLoader加载properties配置文件生成对应的输入流
	    InputStream in = ResourcesTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
	    // 使用properties对象加载输入流
	    properties.load(in);
	    //获取key对应的value值
	   String jdbc_driver = properties.getProperty("jdbc.driver");
	   System.out.println(jdbc_driver);
	}
	/**
	 * 基于 InputStream 读取配置文件
	 * @throws IOException
	 */
	@Test
	public void testProperties02() throws IOException {
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\eclipse-workspace\\Demo\\src\\main\\resources\\jdbc.properties"));
		// 使用properties对象加载输入流
		properties.load(bufferedReader);
		//获取key对应的value值
		String jdbc_driver = properties.getProperty("jdbc.driver");
		System.out.println(jdbc_driver);
	}
}
