package com.bolaa.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ASong
 *
 */

public class CalendarUtil {

    /**
     * 获取一个月的日历(当第一周和最后一周不为7天，则自动补齐)
     * 
     * @author ASong
     * @param newData
     *            要查询的时间
     * @return
     */
    public static List<List<Map<String, Object>>> getData(Date newData) {
	Calendar cad = Calendar.getInstance();
	cad.setTime(newData);
	int days = cad.getActualMaximum(Calendar.DAY_OF_MONTH);
	int k = cad.get(Calendar.DAY_OF_MONTH);


	// 结果集，格式为
	List<List<Map<String, Object>>> list = new ArrayList<>();
	List<Map<String, Object>> oneList = null;
	Map<String, Object> map = null;
	int[][] array = new int[6][7];

	for (int i = 0; i < days; i++) {
	    cad.set(Calendar.DAY_OF_MONTH, i + 1);
	    int weeks = cad.get(Calendar.WEEK_OF_MONTH);
	    int day = cad.get(Calendar.DAY_OF_WEEK);
	    array[weeks - 1][day - 1] = i + 1;
	}

	
	for (int i = 0; i < array.length; i++) {
	    oneList = new ArrayList<>();
	    for (int j = 0; j < array[i].length; j++) {
		// 每天
		if (array[i][j] != 0) {
		    map = new HashMap<>();
		    
		    // 1代表是今天
		    if (array[i][j] == k) {
			map.put("isToday", "1");
			map.put("fontColor", "#db4a6b");
		    } else {
			map.put("isToday", "0");
		    }
		    // 1代表本月 0代表上月 2代表下月
		    map.put("isTomonth", "1");
		    map.put("day", array[i][j]);
		    oneList.add(map);
		}
	    }
	    // 每月
	    if (oneList != null && oneList.size() > 0) {
		list.add(oneList);
	    }
	}

	// 如果当前月的第一个周的天数不为7天，则把上个月的最后一周拿过来
	if (list.get(0).size() != 7) {
	    array = new int[6][7];
	    // 获取上个月的日历
	    cad.set(Calendar.MONTH, cad.get(Calendar.MONTH) - 1); // 上个月
	    days = cad.getActualMaximum(Calendar.DAY_OF_MONTH);

	    for (int i = 0; i < days; i++) {
		cad.set(Calendar.DAY_OF_MONTH, i + 1);
		int weeks = cad.get(Calendar.WEEK_OF_MONTH);
		int day = cad.get(Calendar.DAY_OF_WEEK);
		array[weeks - 1][day - 1] = i + 1;
	    }
	    // 将上个月的最后一个周加在本月的第一个周前
	    List<Map<String, Object>> one = new ArrayList<>();
	    boolean boo = true;
	    int j = 1;
	    while (boo) {
		for (int i = 0; i < array[array.length - j].length; i++) {
		    if (array[array.length - j][i] != 0) {
			map = new HashMap<>();
			map.put("day", array[array.length - j][i]);
			    // 1代表本月 0代表上月 2代表下月
			map.put("isTomonth", "0");
			one.add(map);
		    }
		}
		if (one.size() > 0) {
		    boo = false;
		    one.addAll(list.get(0));
		    list.remove(0);
		    list.add(0, one);
		}
		j++;
	    }
	}

	// 如果当前月的最后一个周的天数不为7天，则把下个月的第一周拿过来
	if (list.get(list.size() - 1).size() != 7) {
	    array = new int[6][7];
	    // 获取上个月的日历
	    cad.set(Calendar.MONTH, cad.get(Calendar.MONTH) + 1); // 上个月
	    days = cad.getActualMaximum(Calendar.DAY_OF_MONTH);

	    for (int i = 0; i < days; i++) {
		cad.set(Calendar.DAY_OF_MONTH, i + 1);
		int weeks = cad.get(Calendar.WEEK_OF_MONTH);
		int day = cad.get(Calendar.DAY_OF_WEEK);
		array[weeks - 1][day - 1] = i + 1;
	    }
	    // 将上个月的最后一个周加在本月的最后一个周后
	    List<Map<String, Object>> one = new ArrayList<>();
	    boolean boo = true;
	    while (boo) {
		for (int i = 0; i < array[0].length; i++) {
		    if (array[0][i] != 0) {
			map = new HashMap<>();
			map.put("day", array[0][i]);
			    // 1代表本月 0代表上月 2代表下月
			map.put("isTomonth", "2");
			one.add(map);
		    }
		}
		if (one.size() > 0) {
		    boo = false;
		    list.get(list.size() - 1).addAll(one);
		}
	    }
	}
	return list;
    }

}
