package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.bolaa.manage.entity.Ldysubmit;
import com.bolaa.manage.service.LdysubmitService;
import com.bolaa.manage.dao.LdysubmitDao;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;

import net.sf.json.JSONObject;


/**
 * 
 * @Name: LdysubmitServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class LdysubmitServiceImpl implements LdysubmitService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private LdysubmitDao ldysubmitDao;

	@Override
	public Serializable save(Ldysubmit o)  throws Exception{
		return ldysubmitDao.save(o);
	}

	@Override
	public void update(Ldysubmit o)  throws Exception{
		ldysubmitDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Ldysubmit ldysubmit = ldysubmitDao.get(id);
		if(ldysubmit != null){ 
			ldysubmitDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Ldysubmit get(Serializable id)  throws Exception{		
		return ldysubmitDao.get(id);
	}

	@Override
	public List<Ldysubmit> find(String hql)  throws Exception{
		return ldysubmitDao.find(hql);
	}
	
	@Override
	public List<Ldysubmit> find(String hql, Map<String, Object> params)  throws Exception{
		return ldysubmitDao.find(hql, params);
	}

	@Override
	public List<Ldysubmit> find(String hql, int page, int rows)  throws Exception{
		return ldysubmitDao.find(hql, page, rows);
	}

	@Override
	public List<Ldysubmit> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return ldysubmitDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Ldysubmit where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Ldysubmit> list = ldysubmitDao.find(hql, values, page, rows);
		Integer count = ldysubmitDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Ldysubmit> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Ldysubmit> result=new BaseListResult<>();
		String hql = "from Ldysubmit where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Ldysubmit> list = ldysubmitDao.find(hql, values, page, rows);
		Integer count = ldysubmitDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return ldysubmitDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return ldysubmitDao.count(hql, params);
	}

	@Override
	public Integer countSubmitByPid(Integer pId) {
		Integer result = null;
		String sql = "SELECT project.pname as pname FROM ADProject  project"
				+ " LEFT JOIN Luodiye luodiye on project.pid = luodiye.pid "
				+ " LEFT JOIN lydlink link on link.luodiyeid = luodiye.luodiyeid"
				+ " LEFT JOIN LdySubmit submit on submit.ldylinkid = link.ldylinkid "
				+ " WHERE project.pid = :pId AND submit.ldysubid IS NOT NULL ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("pId", pId);
		try {
			result = ldysubmitDao.countBySQL(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer countSubmitByPidAndDate(Integer pId,Date time) {
		Integer result = null;
		String sql = "SELECT project.pname as pname FROM ADProject  project"
				+ " LEFT JOIN Luodiye luodiye on project.pid = luodiye.pid "
				+ " LEFT JOIN lydlink link on link.luodiyeid = luodiye.luodiyeid"
				+ " LEFT JOIN LdySubmit submit on submit.ldylinkid = link.ldylinkid "
				+ " WHERE project.pid = :pId AND submit.ldysubid IS NOT NULL AND submit.addtime >= :time";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("pId", pId);
		values.put("time", time);
		try {
			result = ldysubmitDao.countBySQL(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseObjectResult<Map<String, Object>> overSubList(int ldylinkid, int page, int rows) {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String,Object>>("1", "查询所有的已填表单 分页成功");
		String sql = " select * from LdySubmit sub where sub.ldylinkid = :ldylinkid";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ldylinkid", ldylinkid);
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = ldysubmitDao.findPageBySQL(sql, values, page, rows);
			
			LinkedList<String> keyList = new LinkedList<String>();
			for (Map<String, Object> map : list) {
				List<String> valList = new ArrayList<String>();
				String substance = (String) map.get("substance");
				String[] x = substance.split("，");
				for (int i = 0; i < x.length; i++) {
					String key = x[i].substring(0, x[i].indexOf("："));
					String val = x[i].substring(x[i].indexOf("："), x[i].length());
					keyList.add(key);
					valList.add(val);
				}
				map.put("stanceList", valList);
			}
			resMap.put("Keys", keyList);
			resMap.put("Vals", list);
			result.setResult(resMap);
		} catch (Exception e) {
			result.setE("1");
			result.setErrorMessage("查询所有的已填表单 分页失败");
			e.printStackTrace();
		}
		return result;
	}
	
	

}
