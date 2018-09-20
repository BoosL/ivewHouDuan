package com.bolaa.manage.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bolaa.common.redis.JedisManager;

@Repository
public class RedisDaoImp {

	@Autowired
	private JedisManager redisDao;

	/**
	 * 获取项目的PV数
	 * 
	 * @param pid
	 * @return
	 */
	public int getProjectPV(String pid) {
		boolean flg = redisDao.exists(pid);
		int i = 0;
		if (flg) {
			i = (int) redisDao.get(pid);
		}
		return i;
	}

	/**
	 * 获取项目跳出人数
	 * 
	 * @param jumpKey
	 * @return
	 */
	public int getProjectJumpNum(String jumpKey) {
		boolean flg = redisDao.exists(jumpKey);
		int i = 0;
		if (flg) {
			i = (int) redisDao.get(jumpKey);
		}
		return i;
	}

	/**
	 * 获取 项目跳出率
	 * 
	 * @param timeKey
	 * @return
	 */
	public Map<Object, Object> getProjectReadTime(String timeKey) {
		Map<Object, Object> result = redisDao.getMap(timeKey);
		return result;
	}

	/**
	 * 获取项目的ip总数
	 * 
	 * @param ipKey
	 * @return
	 */
	public long getProjectIP(String ipKey) {
		boolean flg = redisDao.exists(ipKey);
		long ret = 0;
		if (flg) {
			ret = redisDao.getSetSize(ipKey);
		}
		return ret;
	}
	
	
	/**
	 * 获取项目的uv总数
	 * @param uvKey
	 * @return
	 */
	public long getProjectUV(String uvKey){
		boolean flg = redisDao.exists(uvKey);
		long ret = 0;
		if(flg){
			ret = redisDao.getSetSize(uvKey);
		}
		return ret;
	}
	
	

	// ------------------------------------------------

	/**
	 * 获取落地页
	 * 
	 * @param mapKey
	 * @return
	 */
	public Map<String, Object> getClickTime(String mapKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		int time45 = 0;
		int time10 = 0;
		int time20 = 0;
		Boolean flg_45 = redisDao.hasMapKey(mapKey, "time_45");
		if (flg_45) {
			time45 = redisDao.getMapField(mapKey, "time_45");
		}
		Boolean flg_10 = redisDao.hasMapKey(mapKey, "time_10");
		if (flg_10) {
			time10 = redisDao.getMapField(mapKey, "time_10");
		}
		Boolean flg_20 = redisDao.hasMapKey(mapKey, "time_20");
		if (flg_20) {
			time20 = redisDao.getMapField(mapKey, "time_20");
		}
		map.put("time_45", time45);
		map.put("time_10", time10);
		map.put("time_20", time20);
		return map;
	}

	/**
	 * 获取落地页模块 统计信息
	 * 
	 * @param modKey
	 * @param modIds
	 * @return
	 */
	public List<Map<String, Object>> getLuodiyeMod(String modKey, String[] modIds) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (String id : modIds) {
			Map<String, Object> map = new HashMap<String, Object>();
			String leaveKey = "leave_" + id;
			String timeKey = "time_" + id;
			int leaveCount = 0;
			int timeCount = 0;
			Boolean flg_leave = redisDao.hasMapKey(modKey, leaveKey);
			if (flg_leave) {
				leaveCount = redisDao.getMapField(modKey, leaveKey);

			}
			Boolean flg_time = redisDao.hasMapKey(modKey, timeKey);
			if (flg_time) {
				timeCount = redisDao.getMapField(modKey, timeKey);
			}
			map.put("leaveCount", leaveCount);
			map.put("timeCount", timeCount);
			if (timeCount != 0 && timeCount != 0) {
				map.put("aveNum", timeCount / leaveCount);
			} else {
				map.put("aveNum", 0);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取落地页表单 统计信息
	 * 
	 * @param modKey
	 * @param modIds
	 * @return
	 */
	public Map<String, Object> getLuodiyeSub(String subKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		int fromCount = 0;
		int commitCount = 0;
		Boolean flg_leave = redisDao.hasMapKey(subKey, "fromKey");
		if (flg_leave) {
			fromCount = redisDao.getMapField(subKey, "fromKey");

		}
		Boolean flg_time = redisDao.hasMapKey(subKey, "commitKey");
		if (flg_time) {
			commitCount = redisDao.getMapField(subKey, "commitKey");
		}
		map.put("fromCount", fromCount);
		map.put("commitCount", commitCount);
		return map;
	}

	/**
	 * 模糊查询key
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> getLikeKeys(String key) {
		Set<String> set = redisDao.getLikeKeys(key);
		return set;
	}

	// 参数格式 pv_pid_YYYYMMDD_落地页ID_渠道ID

	/**
	 * 获取条件PV数
	 * 
	 * @param pid
	 * @return
	 */
	public int getMonyParamPV(String paramKey) {
		boolean flg = redisDao.exists(paramKey);
		int i = 0;
		if (flg) {
			i = (int) redisDao.get(paramKey);
		}
		return i;
	}
	/**
	 * 获取条件的uv总数
	 * 
	 * @param uvKey
	 * @return
	 */
	public long getMonyParamUV(String paramKey) {
		boolean flg = redisDao.exists(paramKey);
		long ret = 0;
		if (flg) {
			ret = redisDao.getSetSize(paramKey);
		}
		return ret;
	}

	/**
	 * 获取条件的ip总数
	 * 
	 * @param ipKey
	 * @return
	 */
	public long getMonyParamIP(String paramKey) {
		boolean flg = redisDao.exists(paramKey);
		long ret = 0;
		if (flg) {
			ret = redisDao.getSetSize(paramKey);
		}
		return ret;
	}

	// -----------------------------------------

}
