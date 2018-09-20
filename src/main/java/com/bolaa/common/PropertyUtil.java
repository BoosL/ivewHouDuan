package com.bolaa.common;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class PropertyUtil {
	
	protected static Logger log = Logger.getLogger(PropertyUtil.class);
	private static Properties properties = new Properties();
	private static Long lastModefiedTime = null;//配置文件的最后一次修改时间

	/**
	 * 检查配置文件是否有变化，有变化时自动重启加载配置文件
	 */
	private static void checkFile() {
		try {
			String savePath = Thread.currentThread().getContextClassLoader().getResource("redis.properties").toURI().getPath();
			File file = new File(savePath);
			if (lastModefiedTime == null || lastModefiedTime != file.lastModified()) {
				readFile();
				lastModefiedTime = file.lastModified();
			}
		} catch (URISyntaxException e) {
			log.error("读取文件redis.properties发生异常 >> ", e);
		}
	}

	/**
	 * 读取配置文件
	 */
	private static void readFile() {
		if (log.isInfoEnabled()) {
			log.info("读取redis.properties文件");
		}

		InputStream is = null;
		try {
            //add by wengjf at20170826 jar包资源文件读取[begin]
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("redis.properties");
            //add by wengjf at20170826 jar包资源文件读取[end]
			properties.load(is);
		} catch (Exception e) {
			log.error("读取文件redis.properties发生异常 >> ", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("关闭文件redis.properties发生异常 >> ", e);
				}
			}
		}
	}

	/**
	 * 返回配置文件中指定键所对应的值
	 * @param key 键名
	 * @return
	 */
	private static String get(String key) {
		//检查配置文件是否有变化
		checkFile();

		return properties.getProperty(key);
	}

	/**
	 * 返回配置文件中指定键所对应的值，并强转成想要的泛型类型
	 * @param key 键名
	 * @return 如果配置文件中没有对应的key，则返回null
	 */
	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	/**
	 * 返回配置文件中指定键所对应的值
	 * @param key 键名
	 * @param defaultValue 默认值（如果配置文件中没有对应的key，返回该值）
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {
		String value = get(key);
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			PropertyUtil.getProperty("codeExpires");
		}
		System.err.println("time>>" + (System.currentTimeMillis() - t));
	}
	
	/** 
	* @Title: getCommonProByKey 
	* @Description: 获取配置文件参数，在spring.xml中定义 
	* @param   输入参数
	* @return String    返回类型 
	* @throws
	*/
	public static String getCommonProByKey(String key){
	    Properties properties = (Properties) WebContextUtil.getBean("commonProperties");
	    return (String) properties.get(key);
	}
}
