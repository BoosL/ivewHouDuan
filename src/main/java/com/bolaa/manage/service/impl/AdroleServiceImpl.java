package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.bolaa.manage.entity.Adrole;
import com.bolaa.manage.service.AdroleService;
import com.bolaa.manage.dao.AdroleDao;
import com.bolaa.result.BaseListResult;


/**
 * 
 * @Name: AdroleServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class AdroleServiceImpl implements AdroleService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AdroleDao adroleDao;

	@Override
	public Serializable save(Adrole o)  throws Exception{
		return adroleDao.save(o);
	}

	@Override
	public void update(Adrole o)  throws Exception{
		adroleDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Adrole adrole = adroleDao.get(id);
		if(adrole != null){ 
			adroleDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Adrole get(Serializable id)  throws Exception{		
		return adroleDao.get(id);
	}

	@Override
	public List<Adrole> find(String hql)  throws Exception{
		return adroleDao.find(hql);
	}
	
	@Override
	public List<Adrole> find(String hql, Map<String, Object> params)  throws Exception{
		return adroleDao.find(hql, params);
	}

	@Override
	public List<Adrole> find(String hql, int page, int rows)  throws Exception{
		return adroleDao.find(hql, page, rows);
	}

	@Override
	public List<Adrole> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return adroleDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Adrole where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adrole> list = adroleDao.find(hql, values, page, rows);
		Integer count = adroleDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Adrole> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Adrole> result=new BaseListResult<>();
		String hql = "from Adrole where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adrole> list = adroleDao.find(hql, values, page, rows);
		Integer count = adroleDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return adroleDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return adroleDao.count(hql, params);
	}

}
