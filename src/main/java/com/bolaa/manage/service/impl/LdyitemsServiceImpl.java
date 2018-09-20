package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolaa.common.HttpClientUtil;
import com.bolaa.common.PropertyUtil;
import com.bolaa.manage.dao.LdyitemsDao;
import com.bolaa.manage.entity.Ldyitems;
import com.bolaa.manage.service.LdyitemsService;
import com.bolaa.result.BaseListResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 * @Name: LdyitemsServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class LdyitemsServiceImpl implements LdyitemsService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private LdyitemsDao ldyitemsDao;
	
	@Autowired
	private RedisServiceImpl redisService;

	@Override
	public Serializable save(Ldyitems o)  throws Exception{
		return ldyitemsDao.save(o);
	}

	@Override
	public void update(Ldyitems o)  throws Exception{
		ldyitemsDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Ldyitems ldyitems = ldyitemsDao.get(id);
		if(ldyitems != null){ 
			ldyitemsDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Ldyitems get(Serializable id)  throws Exception{		
		return ldyitemsDao.get(id);
	}

	@Override
	public List<Ldyitems> find(String hql)  throws Exception{
		return ldyitemsDao.find(hql);
	}
	
	@Override
	public List<Ldyitems> find(String hql, Map<String, Object> params)  throws Exception{
		return ldyitemsDao.find(hql, params);
	}

	@Override
	public List<Ldyitems> find(String hql, int page, int rows)  throws Exception{
		return ldyitemsDao.find(hql, page, rows);
	}

	@Override
	public List<Ldyitems> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return ldyitemsDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Ldyitems where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Ldyitems> list = ldyitemsDao.find(hql, values, page, rows);
		Integer count = ldyitemsDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Ldyitems> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Ldyitems> result=new BaseListResult<>();
		String hql = "from Ldyitems where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Ldyitems> list = ldyitemsDao.find(hql, values, page, rows);
		Integer count = ldyitemsDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return ldyitemsDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return ldyitemsDao.count(hql, params);
	}

	/* (non-Javadoc)
	 * @see com.bolaa.manage.service.LdyitemsService#listLdyitemsByLuodiyeId(java.lang.Integer)
	 */
	@Override
	public BaseListResult<Map<String, Object>> listLdyitemsByLuodiyeId(Integer luodiyeId) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>("1", "根据落地页id查询模块成功");
		String sql = "SELECT items.* ,luoitems.lorder FROM LdyItems items"
				+ " LEFT JOIN Luoldyitems luoitems on items.ldyitemid = luoitems.ldyitemid "
				+ " WHERE luoitems.luodiyeid = :luodiyeId ORDER BY luoitems.lorder asc ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("luodiyeId", luodiyeId);
		try {
			List<Integer> modIds = new ArrayList<Integer>();
			List<Map<String,Object>> list = ldyitemsDao.findBySQL(sql, values);
			for (Map<String, Object> map : list) {
				if("视频".equals(map.get("itemclass")) || "图片".equals(map.get("itemclass")) 
						|| "轮播图".equals(map.get("itemclass")) || "文本框".equals(map.get("itemclass"))
						){
					modIds.add((int)map.get("ldyitemid"));
				};
			}
//			String string = StringUtils.join(modIds, ",");
//			Map<String, Object> redisMap = new HashMap<String, Object>();
//			redisMap.put("modIds", string);
//			redisMap.put("luodiyeid", luodiyeId);
//			String redisAddr = PropertyUtil.getProperty("redis_addr");
//			String URl = redisAddr+"/luodiyeRedis/getluoMod";
//			String doPost = HttpClientUtil.doPost(URl, redisMap);
//			JSONObject jsonObject = JSONObject.fromObject(doPost);
//			JSONArray jsonArray = jsonObject.getJSONArray("modInfo");
//			for (int i = 0; i < jsonArray.size(); i++) {
//				Map<String, Object> map = list.get(i);
//				JSONObject jObj = (JSONObject) jsonArray.get(i);
//				map.put("leaveCount", jObj.get("leaveCount"));
//				map.put("timeCount", jObj.get("timeCount"));
//				map.put("aveNum", jObj.get("aveNum"));
//			}
			//在集合最后加上点击按钮数的统计
//			Map<String, Object> map = list.get(list.size()-1);
//			Map<String, Object> subMap = (Map<String, Object>) jsonObject.get("subInfo");
//			map.put("fromCount", subMap.get("fromCount"));
//			map.put("commitCount", subMap.get("commitCount"));
//			System.out.println(list);
			Map<String, Object> redisMap = redisService.getluoMod(StringUtils.join(modIds, ","), luodiyeId.toString());
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> modList = (List<Map<String, Object>>) redisMap.get("modInfo");
			for (int i = 0; i < modList.size(); i++) {
				Map<String, Object> map = list.get(i);
				map.put("leaveCount", modList.get(i).get("leaveCount"));
				map.put("timeCount", modList.get(i).get("timeCount"));
				map.put("aveNum", modList.get(i).get("aveNum"));
			}
			//在集合最后加上点击按钮数的统计
			Map<String, Object> map = list.get(list.size()-1);
			@SuppressWarnings("unchecked")
			Map<String, Object> subMap = (Map<String, Object>) redisMap.get("subInfo");
			map.put("fromCount", subMap.get("fromCount"));
			map.put("commitCount", subMap.get("commitCount"));
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据落地页id查询模块失败");
			e.printStackTrace();
		}
		return result;
	}

}
