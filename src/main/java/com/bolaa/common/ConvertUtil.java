package com.bolaa.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public  class ConvertUtil {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

	/**
	 * 时间格式化参数
	 */
	public static final String DATA_FORMAT_YYYY = "yyyy";

	public static final String DATA_FORMAT_YYYYMM = "yyyyMM";

	public static final String DATA_FORMAT_YYYYMMDD = "yyyyMMdd";

	public static final String DATA_FORMAT_YYYY_MM = "yyyy-MM";

	public static final String DATA_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String DATE_FORMATyyyyMMdd_CHN = "yyyy年MM月dd日";

	public final static String DATE_FORMAT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

	public final static String DATE_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";

	public static final long DAY_MOLIS = 1000 * 60 * 60 * 24;
	public static final long HOUR_MOLIS = 1000 * 60 * 60;
	public static final long MINUTE_MOLIS = 1000 * 60;
	public static final long SECOND_MOLIS = 1000;
	
	
	/**
	 * 解析时间
	 * 
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String time, String format) throws ParseException {
		SimpleDateFormat formatTime = new SimpleDateFormat(format);
		return formatTime.parse(time);
	}

	/**
	 * 获取现在的年月yyyyMM
	 * 
	 * @return
	 */
	public static String getYearMonth() {
		return DateToString(getCurrentDate(), DATA_FORMAT_YYYYMM);
	}

	private static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 当前时间的数据以你传入的格式返回
	 * 
	 * @return
	 */
	public static String getCurrentTime(String formatString) {
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		return format.format(time);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param pattern
	 *            传常量格式
	 * @return
	 */
	public static String DateToString(Date date, String pattern) {
		String returnValue = null;
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		try {
			if (date == null) {
				return "";
			}
			returnValue = sf.format(date);
		} catch (Exception e) {
			logger.error("系统异常,{}",e);	
		}
		return returnValue;
	}

	/**
	 * 是否为空判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotOrEmpty(String str) {
		if (!"".equals(str) && str != null) {
			return true;
		}
		return false;
	}

	public static String emptyToNbsp(Object obj) {
		String returnValue = "&nbsp;";
		if (obj != null) {
			returnValue = obj.toString();
			if (StringUtils.isBlank(returnValue)) {
				returnValue = "&nbsp;";
			}
		}
		return returnValue;
	}

	public static String ObjectToString(Object obj) {
		String returnValue = "";
		if (obj != null) {
			returnValue = obj.toString();
		}
		return returnValue;
	}

	public static Integer ObjectToInteger(Object obj) {
		Integer returnValue = 0;
		if (obj != null) {
			String str = obj.toString();
			if (isNotOrEmpty(str)) {
				try {
					returnValue = Integer.parseInt(str);
				} catch (NumberFormatException e) {
					logger.error("系统异常,{}",e);	
					return 0;
				}
			}
		}
		return returnValue;
	}

	public static Long ObjectToLong(Object obj) {
		Long returnValue = new Long(0);
		if (obj != null) {
			String str = obj.toString();
			if (isNotOrEmpty(str)) {
				try {
					returnValue = Long.parseLong(obj.toString());
				} catch (NumberFormatException e) {
					logger.error("系统异常,{}",e);	
					return new Long(0);
				}
			}
		}
		return returnValue;
	}

	public static Double ObjectToDouble(Object obj) {
		Double returnValue = new Double(0);
		if (obj != null) {
			String str = obj.toString();
			if (isNotOrEmpty(str)) {
				try {
					returnValue = Double.parseDouble(obj.toString());
				} catch (NumberFormatException e) {
					logger.error("系统异常,{}",e);	
					return new Double(0);
				}
			}
		}
		return returnValue;
	}

	public static Date ObjectToDate(Object obj) {
		Date reDate = null;
		if (obj == null) {
			return null;
		} else {
			try {
				reDate = (Date) obj;
			} catch (Exception e) {
				logger.error("系统异常,{}",e);	
				return null;
			}
		}
		return reDate;
	}

	public static Date StringToDate(String str) {
		Date reDate = null;
		if (StringUtils.isBlank(str)) {
			return null;
		} else {
			try {
				SimpleDateFormat sf = new SimpleDateFormat(DATA_FORMAT_YYYY_MM_DD);
				reDate = sf.parse(str);
			} catch (Exception e) {
				logger.error("系统异常,{}",e);	
				return null;
			}
		}
		return reDate;
	}

	public static Date StringToDate(String str, String pra) {
		Date reDate = null;
		if (StringUtils.isBlank(str)) {
			return null;
		} else {
			try {
				SimpleDateFormat sf = new SimpleDateFormat(pra);
				reDate = sf.parse(str);
			} catch (Exception e) {
				logger.error("系统异常,{}",e);	
				return null;
			}
		}
		return reDate;
	}

	/**
	 * 获取当年
	 * 
	 * @return
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当年
	 * 
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当日
	 * 
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 年份加减
	 * 
	 * @return
	 */
	public static int addYear(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, num);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当月
	 * 
	 * @return
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当月
	 * 
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 月份加减
	 * 
	 * @return
	 */
	public static int addMonth(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得当月的最后一天
	 * 
	 * @return
	 */
	public static int lastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取时间最后一个月的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取当天
	 * 
	 * @return
	 */
	public static int getDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 天数加减
	 * 
	 * @return
	 */
	public static int addDay(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 时间
	 * 
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

	/**
	 * 当前小时
	 * 
	 * @return
	 */
	public static int getHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 当前分
	 * 
	 * @return
	 */
	public static int getMinute() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 当前秒
	 * 
	 * @return
	 */
	public static int getSecond() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 比较2个时间的大小 第一个大于第二个 返回 true
	 * 
	 * @param frist
	 * @param two
	 * @return
	 */
	public static Boolean compareTime(Date frist, Date two) {
		Calendar oneTime = Calendar.getInstance();
		Calendar twoTime = Calendar.getInstance();
		oneTime.setTime(frist);
		twoTime.setTime(two);
		return oneTime.after(twoTime);
	}

	/**
	 * 显示2个时间差 到天数
	 * 
	 * @param frist
	 * @param two
	 * @return
	 */
	public static String timeDifference(Date frist, Date two) {
		long timeDifference = frist.getTime() - two.getTime();
		boolean isTrue = false;
		if (timeDifference >= 0) {
			isTrue = true;
		}
		// 绝对值处理
		timeDifference = Math.abs(timeDifference);
		long day = timeDifference / DAY_MOLIS;
		long hour = timeDifference % DAY_MOLIS / HOUR_MOLIS;
		long minute = timeDifference % HOUR_MOLIS / MINUTE_MOLIS;
		long second = timeDifference % MINUTE_MOLIS / SECOND_MOLIS;
		if (isTrue) {
			return "两个时间相差：" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
		} else {
			return "两个时间相差为负的：" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
		}

	}

	/**
	 * 获取星期
	 * 
	 * @return 
	 */
	public static String getWeek(Date date) {
		Calendar oneTime = Calendar.getInstance();
		oneTime.setTime(date);
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int w = oneTime.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	// 返回第几个月份，不是几月
	// 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月-1月
	public static int getQuarterInMonth(int month, boolean isQuarterStart) {
		int months[] = { 1, 4, 7, 10 };
		if (!isQuarterStart) {
			months = new int[] { 3, 6, 9, 12 };
		}
		if (month >= 2 && month <= 4)
			return months[0];
		else if (month >= 5 && month <= 7)
			return months[1];
		else if (month >= 8 && month <= 10)
			return months[2];
		else
			return months[3];
	}

	/**
	 * 将Date类转换为XMLGregorianCalendar
	 * 
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar dateToXmlDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DatatypeFactory dtf = null;
		try {
			dtf = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
		}
		XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
		dateType.setYear(cal.get(Calendar.YEAR));
		// 由于Calendar.MONTH取值范围为0~11,需要加1
		dateType.setMonth(cal.get(Calendar.MONTH) + 1);
		dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
		dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
		dateType.setMinute(cal.get(Calendar.MINUTE));
		dateType.setSecond(cal.get(Calendar.SECOND));
		return dateType;
	}

	/**
	 * 将XMLGregorianCalendar转换为Date
	 * 
	 * @param cal
	 * @return
	 */
	public static Date xmlDate2Date(XMLGregorianCalendar cal) {
		return cal.toGregorianCalendar().getTime();
	}

	public static void main(String[] args) {
		// 季度初
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int month = getQuarterInMonth(calendar.get(Calendar.MONTH), true);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		// 季度末
		calendar.setTime(new Date());
		month = getQuarterInMonth(calendar.get(Calendar.MONTH), false);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
	}
	
	/**
	 * 将字CLOB转成STRING类型
	 * @param clob
	 * @return str
	 * @throws SQLException
	 * @throws IOException
	    */
	public static String ClobToString(Clob clob){
		
		if(clob == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		try (
		        Reader is = clob.getCharacterStream();
		        BufferedReader br = new BufferedReader(is);
		) {
		    String str = br.readLine();
		    while (str != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
		        sb.append(str);
		        str = br.readLine();
		    }
		} catch (Exception e) {
			logger.error("系统异常,{}",e);	
		}
		return sb.toString();
	}
}
