package com.wchy.common.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;
	
	
	public  void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextUtils.applicationContext = applicationContext;
	}
	
	
	public static <T> T getBean(Class<T> requiredType) {
		
		return applicationContext.getBean(requiredType);
	}
	
	
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}

