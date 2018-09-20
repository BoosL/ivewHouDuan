package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.bolaa.manage.entity.Luoldyitems;
import com.bolaa.manage.service.LuoldyitemsService;
import com.bolaa.manage.dao.LuoldyitemsDao;
import com.bolaa.result.BaseListResult;


/**
 * 
 * @Name: LuoldyitemsServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class LuoldyitemsServiceImpl implements LuoldyitemsService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private LuoldyitemsDao luoldyitemsDao;

	@Override
	public Serializable save(Luoldyitems o)  throws Exception{
		return luoldyitemsDao.save(o);
	}

	@Override
	public void update(Luoldyitems o)  throws Exception{
		luoldyitemsDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Luoldyitems luoldyitems = luoldyitemsDao.get(id);
		if(luoldyitems != null){ 
			luoldyitemsDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Luoldyitems get(Serializable id)  throws Exception{		
		return luoldyitemsDao.get(id);
	}

	@Override
	public List<Luoldyitems> find(String hql)  throws Exception{
		return luoldyitemsDao.find(hql);
	}
	
	@Override
	public List<Luoldyitems> find(String hql, Map<String, Object> params)  throws Exception{
		return luoldyitemsDao.find(hql, params);
	}

	@Override
	public List<Luoldyitems> find(String hql, int page, int rows)  throws Exception{
		return luoldyitemsDao.find(hql, page, rows);
	}

	@Override
	public List<Luoldyitems> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return luoldyitemsDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Luoldyitems where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Luoldyitems> list = luoldyitemsDao.find(hql, values, page, rows);
		Integer count = luoldyitemsDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Luoldyitems> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Luoldyitems> result=new BaseListResult<>();
		String hql = "from Luoldyitems where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Luoldyitems> list = luoldyitemsDao.find(hql, values, page, rows);
		Integer count = luoldyitemsDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return luoldyitemsDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return luoldyitemsDao.count(hql, params);
	}

}
