package test;

import com.bolaa.common.PropertyUtil;

public class TestAssert {
	public static void main(String[] args) {
		
		
		// isnull  拦截对象不为null的
		// notnull   拦截对象为null的
		String ip = PropertyUtil.getProperty("redis_addr");
		
		System.out.println(ip);
	}
}
