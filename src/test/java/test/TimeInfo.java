package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class TimeInfo {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
		try {
			Date date = sdf.parse(format);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(format);
	}
}
