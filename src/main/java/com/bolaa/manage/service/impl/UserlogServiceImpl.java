package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.manage.dao.UserlogDao;
import com.bolaa.result.BaseListResult;


/**
 * 
 * @Name: UserlogServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class UserlogServiceImpl implements UserlogService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserlogDao userlogDao;

	@Override
	public Serializable save(Userlog o)  throws Exception{
		return userlogDao.save(o);
	}

	@Override
	public void update(Userlog o)  throws Exception{
		userlogDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Userlog userlog = userlogDao.get(id);
		if(userlog != null){ 
			userlogDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Userlog get(Serializable id)  throws Exception{		
		return userlogDao.get(id);
	}

	@Override
	public List<Userlog> find(String hql)  throws Exception{
		return userlogDao.find(hql);
	}
	
	@Override
	public List<Userlog> find(String hql, Map<String, Object> params)  throws Exception{
		return userlogDao.find(hql, params);
	}

	@Override
	public List<Userlog> find(String hql, int page, int rows)  throws Exception{
		return userlogDao.find(hql, page, rows);
	}

	@Override
	public List<Userlog> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return userlogDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Userlog where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Userlog> list = userlogDao.find(hql, values, page, rows);
		Integer count = userlogDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Userlog> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Userlog> result=new BaseListResult<>();
		String hql = "from Userlog where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Userlog> list = userlogDao.find(hql, values, page, rows);
		Integer count = userlogDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return userlogDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return userlogDao.count(hql, params);
	}

	@Override
	public BaseListResult<Map<String, Object>> logList(int page, int rows) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>("1", "查询所有日志成功");
		String sql = " SELECT userlog.*,adadm.username FROM UserLog userlog LEFT JOIN ADAdm adadm ON userlog.userid = adadm.userid ";
		try {
			List<Map<String,Object>> list = userlogDao.findPageBySQL(sql, null, page, rows);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询所有的日志失败");
			e.printStackTrace();
		}
		return result;
	}

}
