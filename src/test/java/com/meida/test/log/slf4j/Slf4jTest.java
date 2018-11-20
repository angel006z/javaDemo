package com.meida.test.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
SLF4J，即简单日志门面（Simple Logging Facade for Java），不是具体的日志解决方案，它只服务于各种各样的日志系统。
按照官方的说法，SLF4J是一个用于日志系统的简单Facade，允许最终用户在部署其应用时使用其所希望的日志System

实际上，SLF4J所提供的核心API是一些接口以及一个LoggerFactory的工厂类。从某种程度上，SLF4J有点类似JDBC，
不过比JDBC更简单，在JDBC中，你需要指定驱动程序，而在使用SLF4J的时候，不需要在代码中或配置文件中指定你打算使用那个具体的日志系统。
如同使用JDBC基本不用考虑具体数据库一样，SLF4J提供了统一的记录日志的接口，只要按照其提供的方法记录即可，
最终日志的格式、记录级别、输出方式等通过具体日志系统的配置来实现，因此可以在应用中灵活切换日志系统。

slf4j只是一个日志标准，并不是日志系统的具体实现。
提供日志接口
提供获取具体日志对象的方法
*/
public class Slf4jTest {
	 private static final Logger logger = LoggerFactory.getLogger(Slf4jTest.class);// slf4j日志记录器
	 public static void main(String[] args) {
		// 普通的日志记录
	        logger.debug("普通的日志记录");

	        // {}占位符记录日志
	        for (int i = 0; i < 3; i++) {
	            logger.debug("这是第{}条记录", i);
	        }

	        // 用\转义{}
	        logger.debug("Set \\{} differs from {}", "3"); // output:Set {} differs
	                                                        // from 3

	        // 两个参数
	        logger.debug("两个占位符，可以传两个参数{}----{}", 1, 2);

	        // 多个参数(可变参数)
	        logger.debug("debug:多个占位符，{},{},{},{}", 1, 2, 3, 4);

	        // 多个参数(可变参数)
	        logger.info("info:多个占位符，{},{},{},{}", 1, 2, 3, 4);

	        // 多个参数(可变参数)
	        logger.error("error:多个占位符，{},{},{},{}", 1, 2, 3, 4);

	}
}
