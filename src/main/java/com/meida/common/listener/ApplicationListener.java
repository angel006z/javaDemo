package com.meida.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 应用启动时，初始化一些数据
 */
@Component
public class ApplicationListener  implements ServletContextListener{
	private static final Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info(".........tomcat服务停止.........");
		logger.info(".........开始停止后台线程.........");
		System.out.println(".........tomcat服务停止.........");
		try {

			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info(".........结束停止后台线程.........");
		
	}
	
}
