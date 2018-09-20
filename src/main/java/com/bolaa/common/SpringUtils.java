package com.bolaa.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @decription
 * @author Administrator
 * @date 2017年4月28日 下午4:55:24
 */
public class SpringUtils implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext=arg0;
	}
	
	/**
	 * 获取指定service
	 * @param serverName
	 * @return
	 */
	public static Object getBean(String serverName){
		return applicationContext.getBean(serverName);
	}

}
