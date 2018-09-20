package com.bolaa.manage.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bolaa.common.HtmlUtil;
import com.bolaa.common.PropertyUtil;
import com.bolaa.common.StaticOperate;
import com.bolaa.manage.dao.LydlinkDao;
import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Lydlink;
import com.bolaa.manage.service.LydlinkService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;


/**
 * 
 * @Name: LydlinkServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class LydlinkServiceImpl implements LydlinkService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private LydlinkDao lydlinkDao;

	@Override
	public Serializable save(Lydlink o)  throws Exception{
		return lydlinkDao.save(o);
	}

	@Override
	public void update(Lydlink o)  throws Exception{
		lydlinkDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Lydlink lydlink = lydlinkDao.get(id);
		if(lydlink != null){ 
			lydlinkDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Lydlink get(Serializable id)  throws Exception{		
		return lydlinkDao.get(id);
	}

	@Override
	public List<Lydlink> find(String hql)  throws Exception{
		return lydlinkDao.find(hql);
	}
	
	@Override
	public List<Lydlink> find(String hql, Map<String, Object> params)  throws Exception{
		return lydlinkDao.find(hql, params);
	}

	@Override
	public List<Lydlink> find(String hql, int page, int rows)  throws Exception{
		return lydlinkDao.find(hql, page, rows);
	}

	@Override
	public List<Lydlink> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return lydlinkDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Lydlink where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Lydlink> list = lydlinkDao.find(hql, values, page, rows);
		Integer count = lydlinkDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Lydlink> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Lydlink> result=new BaseListResult<>();
		String hql = "from Lydlink where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Lydlink> list = lydlinkDao.find(hql, values, page, rows);
		Integer count = lydlinkDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return lydlinkDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return lydlinkDao.count(hql, params);
	}
	
	@Override
	public BaseResult lockLink(int lydlinkId) {
		BaseResult result = new BaseResult();
		try {
			Lydlink lydlink = lydlinkDao.get(lydlinkId);
			Assert.notNull(lydlink, "落地页连接对象不存在");
			if("锁定".equals(lydlink.getLstatus())){
				result.setE("0");
				result.setErrorMessage("该落地页连接已经锁定");
			}else{
				lydlink.setLstatus("锁定");
				lydlinkDao.update(lydlink);
				result.setE("1");
				result.setErrorMessage("落地页连接锁定成功");
			}
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("落地页连接锁定失败,系统异常稍后再试");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseResult unlockLink(int lydlinkId) {
		BaseResult result = new BaseResult();
		try {
			Lydlink lydlink = lydlinkDao.get(lydlinkId);
			Assert.notNull(lydlink, "落地页连接对象不存在");
			if(!"锁定".equals(lydlink.getLstatus())){
				result.setE("0");
				result.setErrorMessage("该落地页连接未锁定");
			}else{
				lydlink.setLstatus("待发布");
				lydlinkDao.update(lydlink);
				result.setE("1");
				result.setErrorMessage("落地页连接解锁成功");
			}
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("落地页连接解锁失败,系统异常稍后再试");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseResult isExistCode(String code) {
		BaseResult result = new BaseResult("1", "该自定义代码已经存在");
		String hql = " from Lydlink where linkcode = :code";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		try {
			Lydlink lydlink = lydlinkDao.get(hql, params);
			if(null == lydlink){
				result.setE("0");
				result.setErrorMessage("该自定义代码不存在可以添加");
			}
		} catch (Exception e) {
			result.setE("-1");
			result.setErrorMessage("系统异常");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BaseObjectResult<Lydlink> getInfoByLuodiyeId(int luodiyeid) {
		BaseObjectResult<Lydlink> result = new BaseObjectResult<Lydlink>("1", "根据落地页id查询落地页连接成功");
		String hql = " from Lydlink where luodiyeid = :luodiyeid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("luodiyeid", luodiyeid);
		try {
			Lydlink lydlink = lydlinkDao.get(hql, params);
			result.setResult(lydlink);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据落地页id查询落地页失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseObjectResult<Map<String, Object>> issueLink(Lydlink link, HttpServletRequest req) {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String, Object>>("1", "落地页链接发布成功");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Adadm user = (Adadm) req.getSession().getAttribute("admin");
			if(link.getLuodiyeid() == null){
				result.setE("0");
				result.setErrorMessage("发布失败");
				return result;
			}
			link.setLstatus("发布");
			link.setAdduserid(user.getUserid());
			lydlinkDao.addLink(link, user.getUserid());
			// 生成静态页面
			String path = req.getSession().getServletContext().getRealPath("");
			String webAppStr = (new File(path)).getParent();
			String CDNname = PropertyUtil.getProperty("CDNName");
			String fileUrl = webAppStr + "/" + CDNname + "/luodiye/page/"+link.getLinkcode(); // 将资源保存到CDN上面
//			String httpHtml = "http://localhost:8080/Poster/luoOperate/lookLuodiye?luodiyeId="+link.getLuodiyeid();
			String httpHtml = "http://139.159.142.120:80/Poster/luoOperate/lookLuodiye?luodiyeId="+link.getLuodiyeid();
			StaticOperate.convert2Html(httpHtml, fileUrl, "default.html");
			map.put("link", link.getLink());
			result.setResult(map);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("落地页链接发布失败");
			e.printStackTrace();
		}
		return result;
	}

}
