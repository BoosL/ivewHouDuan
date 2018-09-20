package com.bolaa.common;

import org.apache.log4j.Logger;


public class Log4jLogger {
    
    private static Logger logger=Logger.getLogger(Log4jLogger.class);
	/**
	 * @param 异常标题
	 * @param 异常信息
	 */
	public static void error(String title,Throwable e){
		logger.error(title,e);
	}
	
	/**
	 * 异常日志
	 * @param e 异常信息
	 */
	public static void error(Throwable e){
		logger.error("========================================",e);
	}

	/**
	 * 调试信息
	 * @param msg
	 */
	public static void debug(String msg){
		logger.debug("========================================"+msg);
	}
	
	/**
	 * 信息
	 * @param msg
	 */
	public static void info(String msg){
		logger.info("========================================"+msg);
	}
}
