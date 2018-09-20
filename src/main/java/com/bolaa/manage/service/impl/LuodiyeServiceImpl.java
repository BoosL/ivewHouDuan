package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bolaa.manage.dao.LdyitemsDao;
import com.bolaa.manage.dao.LuodiyeDao;
import com.bolaa.manage.dao.LuoldyitemsDao;
import com.bolaa.manage.dao.UserlogDao;
import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Ldyitems;
import com.bolaa.manage.entity.Luodiye;
import com.bolaa.manage.entity.Luoldyitems;
import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.model.req.ReqAddLuodiyeMod;
import com.bolaa.manage.model.req.ReqLdyitemsMod;
import com.bolaa.manage.model.req.ReqLuodiyeListMod;
import com.bolaa.manage.service.LuodiyeService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;


/**
 * 
 * @Name: LuodiyeServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class LuodiyeServiceImpl implements LuodiyeService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private LuodiyeDao luodiyeDao;
	@Autowired
	private LdyitemsDao ldyitemsDao;
	@Autowired
	private LuoldyitemsDao luoldyitemsDao;
	@Autowired
	private UserlogDao userlogDao;
	

	@Override
	public Serializable save(Luodiye o)  throws Exception{
		return luodiyeDao.save(o);
	}

	@Override
	public void update(Luodiye o)  throws Exception{
		luodiyeDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Luodiye luodiye = luodiyeDao.get(id);
		if(luodiye != null){ 
			luodiyeDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Luodiye get(Serializable id)  throws Exception{		
		return luodiyeDao.get(id);
	}

	@Override
	public List<Luodiye> find(String hql)  throws Exception{
		return luodiyeDao.find(hql);
	}
	
	@Override
	public List<Luodiye> find(String hql, Map<String, Object> params)  throws Exception{
		return luodiyeDao.find(hql, params);
	}

	@Override
	public List<Luodiye> find(String hql, int page, int rows)  throws Exception{
		return luodiyeDao.find(hql, page, rows);
	}

	@Override
	public List<Luodiye> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return luodiyeDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Luodiye where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Luodiye> list = luodiyeDao.find(hql, values, page, rows);
		Integer count = luodiyeDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Luodiye> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Luodiye> result=new BaseListResult<>();
		String hql = "from Luodiye where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Luodiye> list = luodiyeDao.find(hql, values, page, rows);
		Integer count = luodiyeDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return luodiyeDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return luodiyeDao.count(hql, params);
	}
	
	@Override
	public BaseListResult<Map<String, Object>> luodiyeListByProjectId(Integer projectId, int page, int rows) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>();
		String sql = "SELECT luodiye.luodiyeid as luodiyeid ,"
				+ " link.ldylinkid as ldylinkid ,"
				+ " luodiye.lname as lname , "
				+ " link.link as link , "
				+ " link.lnote as lnote, link.lstatus as lstatus, channel.channelname as channelname FROM Luodiye luodiye"
				+ " LEFT JOIN lydlink link ON luodiye.luodiyeid = link.luodiyeid "
				+ " LEFT JOIN ADChannel channel ON link.channelid = channel.channelid "
				+ " WHERE luodiye.pid = :projectId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("projectId", projectId);
		try {
			List<Map<String,Object>> list = luodiyeDao.findPageBySQL(sql, values, page, rows);
			Integer count = luodiyeDao.countBySQL(sql, values);
			result.setResult(list);
			result.setTotal(count);
			result.setE("1");
			result.setErrorMessage("根据项目id查询落地页集合成功");
		} catch (Exception e) {
			result.setE("1");
			result.setErrorMessage("根据项目id查询落地页集合失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseListResult<Map<String, Object>> luodiyelist(ReqLuodiyeListMod reqMod) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>("1", "根据条件查询所有的落地页成功");
		String sql = "SELECT luo.luodiyeid AS luodiyeid, "
				+ " luo.lname AS lname, "
				+ " luo.addtime AS addtime, "
				+ " class.classname AS classname, "
				+ " link.lstatus AS lstatus, "
				+ " link.link AS link, "
				+ " channel.channelname AS channelname, "
				+ " adadm.username AS username "
				+ " FROM Luodiye luo "
				+ " LEFT JOIN lydlink link ON luo.luodiyeid = link.luodiyeid "
				+ " LEFT JOIN ADAdm adadm ON adadm.userid = luo.adduserid "
				+ " LEFT JOIN ADProject project ON project.pid = luo.pid "
				+ " LEFT JOIN ADChannel channel ON channel.channelid = link.channelid "
				+ " LEFT JOIN ADClass class ON project.classid = class.classid WHERE 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		if(reqMod.getAddTime()!=null){
			sql += " AND luo.addtime = :addtime ";
			values.put("addtime", reqMod.getAddTime());
		}
		if(reqMod.getAdduserid() != null){
			sql += " AND adadm.userid = :userid ";
			values.put("userid", reqMod.getAdduserid());
		}
		if(reqMod.getClassId() != null){
			sql += " AND class.classid = :classid ";
			values.put("classid", reqMod.getClassId());
		}
		if(StringUtils.hasText(reqMod.getLstatus())){
			sql += " AND link.lstatus = :lstatus ";
			values.put("lstatus", reqMod.getLstatus());
		}
		try {
			List<Map<String,Object>> list = luodiyeDao.findPageBySQL(sql, values, reqMod.getPage(), reqMod.getRows());
			result.setResult(list);
			Integer count = luodiyeDao.countBySQL(sql, values);
			result.setTotal(count);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据条件查询所有的落地页失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseResult addLuodiye(ReqAddLuodiyeMod reqMod,HttpServletRequest req) {
		BaseResult result = new BaseResult("1", "添加落地页成功");
		Luodiye luodiye = reqMod.getLuodiye();
		try {
			Serializable luodiyeId = luodiyeDao.save(luodiye);
			List<ReqLdyitemsMod> items = reqMod.getItems();
			int index = 0;
			for (ReqLdyitemsMod item : items) {
				index++;
				Ldyitems ldyitem = new Ldyitems();
				ldyitem.setLtitle(item.getLtitle());
				ldyitem.setItemclass(item.getItemclass());
				if("图片".equals(item.getItemclass())){
					ldyitem.setPicurl(item.getFilesUrl());
				}else if("视频".equals(item.getItemclass())) {
					ldyitem.setVideourl(item.getFilesUrl());
				}else if("轮播图".equals(item.getItemclass())) {
					ldyitem.setPicurls(item.getFilesUrl());
				}else if("文本".equals(item.getItemclass())) {
					ldyitem.setLtext(item.getContent());
				}else{
					ldyitem.setChoiceitmes(item.getContent());
				}
				Serializable itemId = ldyitemsDao.save(ldyitem);
				Luoldyitems luoldyitem = new Luoldyitems();
				luoldyitem.setLuodiyeid(Integer.parseInt(luodiyeId.toString()));
				luoldyitem.setLuoldyitemid(Integer.parseInt(itemId.toString()));
				luoldyitem.setLorder(index);
				luoldyitemsDao.save(luoldyitem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setE("0");
			result.setErrorMessage("添加落地页失败");
		}
		return result;
	}

	@Override
	public BaseObjectResult<Map<String, Object>> addluodiyeItems(HttpServletRequest request, Luodiye luodiye, String items) {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String, Object>>("1", "添加落地页成功");
		Map<String, Object> map = new HashMap<String, Object>();
		Adadm user = (Adadm) request.getSession().getAttribute("admin");
		if(user == null){
			result.setE("0");
			result.setErrorMessage("请先登录");
			return result;
		}
		luodiye.setAdduserid(user.getUserid());
		try {
			Serializable luoid = luodiyeDao.save(luodiye);
			map.put("luodiyeId", luoid);
			result.setResult(map);
			String[] split = items.split(",");
			for (int i = 0; i < split.length; i++) {
				Luoldyitems luoitems = new Luoldyitems();
				luoitems.setLdyitemid(Integer.parseInt(split[i]));
				luoitems.setLuodiyeid(Integer.parseInt(luoid.toString()));
				luoitems.setLorder(i);
				luoldyitemsDao.save(luoitems);
			}
			
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(luodiye.toString());
			log.setOptname("添加落地页");
			log.setUserid(user.getUserid());
			userlogDao.save(log);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加落地页失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseListResult<Map<String, Object>> luodiyeInfo(Integer luodiyeId) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>("1", "根据id查询落地页模块信息成功");
		try {
			List<Map<String,Object>> list = luodiyeDao.luodiyeInfo(luodiyeId);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询落地页模块信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<String, Object> getDefaultInfo(int luodiyeId) {
		String sql = " SELECT luo.luodiyeid, luo.pid, link.ldylinkid, link.link, channel.channelid, channel.channelname FROM Luodiye luo "
				+ " LEFT JOIN ADProject project ON project.pid = luo.pid "
				+ " LEFT JOIN lydlink link ON luo.luodiyeid = link.luodiyeid "
				+ " LEFT JOIN ADChannel channel ON channel.channelid = link.channelid "
				+ " WHERE luo.luodiyeid = :luodiyeId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("luodiyeId", luodiyeId);
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		try {
			listMap = luodiyeDao.findBySQL(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap.get(0);
	}

}
