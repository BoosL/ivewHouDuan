package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolaa.manage.dao.AdadmDao;
import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.service.AdadmService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseResult;


/**
 * 
 * @Name: AdadmServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class AdadmServiceImpl implements AdadmService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AdadmDao adadmDao;

	@Override
	public Serializable save(Adadm o)  throws Exception{
		return adadmDao.save(o);
	}

	@Override
	public void update(Adadm o)  throws Exception{
		adadmDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Adadm adadm = adadmDao.get(id);
		if(adadm != null){ 
			adadmDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Adadm get(Serializable id)  throws Exception{		
		return adadmDao.get(id);
	}

	@Override
	public List<Adadm> find(String hql)  throws Exception{
		return adadmDao.find(hql);
	}
	
	@Override
	public List<Adadm> find(String hql, Map<String, Object> params)  throws Exception{
		return adadmDao.find(hql, params);
	}

	@Override
	public List<Adadm> find(String hql, int page, int rows)  throws Exception{
		return adadmDao.find(hql, page, rows);
	}

	@Override
	public List<Adadm> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return adadmDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Adadm where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adadm> list = adadmDao.find(hql, values, page, rows);
		Integer count = adadmDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Adadm> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Adadm> result=new BaseListResult<>();
		String hql = "from Adadm where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adadm> list = adadmDao.find(hql, values, page, rows);
		Integer count = adadmDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return adadmDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return adadmDao.count(hql, params);
	}
	
	@Override
	public BaseResult userLogin(HttpServletRequest request, Adadm user) {
		BaseResult result = new BaseResult("1", "登录成功");
		String hql = "from Adadm adadm where adadm.username = :username and adadm.password = :password";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		try {
			Adadm adadm = adadmDao.get(hql, params);
			if(adadm == null){
				result.setE("0");
				result.setErrorMessage("登录失败，请检查您的用户名和密码");
			}else if("关闭".equals(adadm.getUserstatus())) {
				result.setE("0");
				result.setErrorMessage("登录失败，该用户已经被停用请联系超级管理员");
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("admin", adadm);
				System.out.println(session.getAttribute("admin"));
			}
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("登录失败，系统异常请稍后重试");
			e.printStackTrace();
		}
		return result;
	}

}
