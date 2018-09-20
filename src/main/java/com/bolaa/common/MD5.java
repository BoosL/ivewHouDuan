package com.bolaa.common;

import java.security.MessageDigest;

import org.slf4j.LoggerFactory;

public class MD5 {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MD5.class);

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			logger.error("系统异常,{}",e);

		}
		return str;
	}
	public static void main(String[] args) {
	}
}
