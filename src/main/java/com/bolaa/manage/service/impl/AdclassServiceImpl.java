package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.bolaa.manage.entity.Adclass;
import com.bolaa.manage.service.AdclassService;
import com.bolaa.manage.dao.AdclassDao;
import com.bolaa.result.BaseListResult;


/**
 * 
 * @Name: AdclassServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class AdclassServiceImpl implements AdclassService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AdclassDao adclassDao;

	@Override
	public Serializable save(Adclass o)  throws Exception{
		return adclassDao.save(o);
	}

	@Override
	public void update(Adclass o)  throws Exception{
		adclassDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Adclass adclass = adclassDao.get(id);
		if(adclass != null){ 
			adclassDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Adclass get(Serializable id)  throws Exception{		
		return adclassDao.get(id);
	}

	@Override
	public List<Adclass> find(String hql)  throws Exception{
		return adclassDao.find(hql);
	}
	
	@Override
	public List<Adclass> find(String hql, Map<String, Object> params)  throws Exception{
		return adclassDao.find(hql, params);
	}

	@Override
	public List<Adclass> find(String hql, int page, int rows)  throws Exception{
		return adclassDao.find(hql, page, rows);
	}

	@Override
	public List<Adclass> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return adclassDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Adclass where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adclass> list = adclassDao.find(hql, values, page, rows);
		Integer count = adclassDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Adclass> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Adclass> result=new BaseListResult<>();
		String hql = "from Adclass where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adclass> list = adclassDao.find(hql, values, page, rows);
		Integer count = adclassDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return adclassDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return adclassDao.count(hql, params);
	}

}
