package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bolaa.manage.dao.AdprojectDao;
import com.bolaa.manage.dao.LdysubmitDao;
import com.bolaa.manage.dao.LuodiyeDao;
import com.bolaa.manage.entity.Adproject;
import com.bolaa.manage.model.req.ReqProjectReidsMod;
import com.bolaa.manage.service.AdprojectService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;


/**
 * 
 * @Name: AdprojectServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class AdprojectServiceImpl implements AdprojectService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AdprojectDao adprojectDao;
	
	@Autowired
	private LuodiyeDao luodiyeDao;
	
	@Autowired
	private LdysubmitDao ldysubmitDao;
	
	@Autowired RedisServiceImpl redisService;
	
	@Override
	public Serializable save(Adproject o)  throws Exception{
		return adprojectDao.save(o);
	}

	@Override
	public void update(Adproject o)  throws Exception{
		adprojectDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Adproject adproject = adprojectDao.get(id);
		if(adproject != null){ 
			adprojectDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Adproject get(Serializable id)  throws Exception{		
		return adprojectDao.get(id);
	}

	@Override
	public List<Adproject> find(String hql)  throws Exception{
		return adprojectDao.find(hql);
	}
	
	@Override
	public List<Adproject> find(String hql, Map<String, Object> params)  throws Exception{
		return adprojectDao.find(hql, params);
	}

	@Override
	public List<Adproject> find(String hql, int page, int rows)  throws Exception{
		return adprojectDao.find(hql, page, rows);
	}

	@Override
	public List<Adproject> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return adprojectDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Adproject where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adproject> list = adprojectDao.find(hql, values, page, rows);
		Integer count = adprojectDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Adproject> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Adproject> result=new BaseListResult<>();
		String hql = "from Adproject where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adproject> list = adprojectDao.find(hql, values, page, rows);
		Integer count = adprojectDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return adprojectDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return adprojectDao.count(hql, params);
	}
	
	
	@Override
	public BaseListResult<Map<String, Object>> projectList(Date beginTime, Date endTime, Integer classId,
			String projectName, String pstatus, int page, int rows) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>();
		Map<String, Object> values = new HashMap<String, Object>();
		String sql = "SELECT project.pid as pid,"
				+ "project.pname as pname,"
				+ "adclass.classname as classname,"
				+ "project.begintime as begintime,"
				+ "project.endtime as endtime,"
				+ "project.pstatus as pstatus,"
				+ "(SELECT COUNT(luodiye.lname) FROM Luodiye AS luodiye WHERE luodiye.pid = project.pid ) as countLuodiye "
			  + "FROM ADProject AS project LEFT JOIN ADClass AS adclass ON project.classid = adclass.classid "
			  + "where 1=1 ";
			  if(beginTime != null) {
				  sql += " and project.begintime >= :beginTime ";
				  values.put("beginTime", beginTime);
			  }
			  if(endTime != null) {
				  sql += " and project.endtime <= :endTime ";
				  values.put("endTime", endTime);
			  }
			  if(classId != null) {
				  sql += " and adclass.classid = :classId ";
				  values.put("classId", classId);
			  }
			  if(projectName != null){
				  sql += " and project.pname like :projectName ";
				  values.put("projectName", "%"+projectName+"%");
			  }
			  if(pstatus != null){
				  sql += " and project.pstatus = :pstatus ";
				  values.put("pstatus", pstatus);
			  }
			  sql += " ORDER BY project.addtime desc ";
		try {
			List<Map<String,Object>> list = adprojectDao.findPageBySQL(sql, values, page, rows);
			Integer count = adprojectDao.countBySQL(sql, values);
			result.setResult(list);
			result.setTotal(count);
			result.setE("1");
			result.setErrorMessage("根据条件查询项目集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据条件查询项目集合失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseObjectResult<Map<String, Object>> topCount() {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String,Object>>("1", "查询首页上边的计数成功");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//统计项目已（未）完成的个数
			String hql = "from Adproject project where project.pstatus = :pstatus";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pstatus", "完成");
			Integer doneCount = adprojectDao.count(hql, params);
			map.put("doneCount", doneCount);
			
			params.put("pstatus", "未完成");
			Integer undoneCount = adprojectDao.count(hql, params);
			map.put("undoneCount", undoneCount);
			//统计含有表单数的落地页
			//count(DISTINCT luodiye.luodiyeid)
			String sql = "SELECT DISTINCT luodiye.luodiyeid AS luodiyeCount FROM Luodiye luodiye"
					+ " LEFT JOIN Luoldyitems ldyitems ON luodiye.luodiyeid = ldyitems.luodiyeid"
					+ " LEFT JOIN LdyItems items ON items.ldyitemid = ldyitems.ldyitemid"
					+ " WHERE items.itemclass IN ('表单单选','表单多选','表单框','表单地区')";
			Integer ldyCount = luodiyeDao.countBySQL(sql , null);
			map.put("ldyCount", ldyCount);
			// 统计所有的表单数
			Integer allCount = ldysubmitDao.count("from Ldysubmit");
			map.put("allCount", allCount);
			// 统计今天新增的表单数
			Map<String, Object> dateParams = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date today = sdf.parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00"));
			dateParams.put("today",today);
			Integer todayCount = ldysubmitDao.count("from Ldysubmit ldysubmit where ldysubmit.addtime >= :today ", dateParams);
			map.put("todayCount", todayCount);
			
			result.setResult(map);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询首页上边的计数失败");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public BaseResult lockProject(Integer pid) {
		BaseResult result = new BaseResult("1", "项目锁定成功");
		try {
			Adproject project = adprojectDao.get(pid);
			Assert.notNull(project, "该项目不存在");
			if("完成".equals(project.getPstatus())){
				project.setPstatus("未完成");
				result.setErrorMessage("项目解锁成功");
			}else{
				project.setPstatus("完成");
			}
			adprojectDao.update(project);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("项目操作失败");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public BaseObjectResult<Map<String, Object>> getInfoById(int id) {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String,Object>>("1", "根据项目id查询项目详情成功");
		String sql = " SELECT  project.*,class.classname FROM  ADProject project"
				+ " LEFT JOIN ADClass class on project.classid = class.classid "
				+ " WHERE project.pid = :projectId  ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("projectId", id);
		try {
			List<Map<String,Object>> list = adprojectDao.findBySQL(sql, values);
			result.setResult(list.get(0));
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据项目id查询项目详情失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseListResult<Map<String, Object>> indexProject(String pstatus, int page, int rows) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>("1", "首页项目查询成功");
		Map<String, Object> values = new HashMap<String, Object>();
		String sql = "select * from ADProject project where 1=1 ";
		if(pstatus != null){
			sql += " and project.pstatus = :pstatus";
			values.put("pstatus", pstatus);
		}
		try {
			// 初始化今天时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
			List<Map<String,Object>> listMap = adprojectDao.findPageBySQL(sql, values, page, rows);
			if(listMap.isEmpty()){
				return result;
			}
			Integer countBySQL = adprojectDao.countBySQL(sql, values);
			List<Integer> pids = new ArrayList<Integer>();
			for (Map<String, Object> map : listMap) {
				pids.add((int)map.get("pid"));
				Integer subCount = ldysubmitDao.countSubmitByPidAndDate((Integer)map.get("pid"), sdf.parse(format));
				Integer subtoday = ldysubmitDao.countSubmitByPidAndDate((Integer)map.get("pid"), null);
				map.put("subCount", subCount);
				map.put("subtoday", subtoday);
				map.put("pv", 0);
				map.put("jump", 0);
				map.put("timeOut", 0);
			}
//			Map<String, Object> val = new HashMap<String, Object>();
//			val.put("jsonStr", StringUtils.join(pids.toArray(),","));
//			String redisAddr = PropertyUtil.getProperty("redis_addr");
//			String URl = redisAddr+"/indexRedis/project";
//			String doPost = HttpClientUtil.doPost(URl,val);
//			if(org.springframework.util.StringUtils.hasText(doPost)){
//				JSONArray jsonArray = JSONArray.fromObject(doPost);
//				for (int i = 0; i < jsonArray.size(); i++) {
//					JSONObject jsonObject = jsonArray.getJSONObject(i);
//					Map<String, Object> map = listMap.get(i);
//					map.put("pv", jsonObject.get("pv"));
//					map.put("jump", jsonObject.get("jump"));
//					map.put("timeOut", jsonObject.get("timeOut"));
//				}
//			}
			List<Map<String, Object>> redisList = redisService.indexProjectInfo(StringUtils.join(pids.toArray(),","));
			for (int i = 0; i < redisList.size(); i++) {
				Map<String, Object> map = listMap.get(i);
				map.put("pv", redisList.get(i).get("pv"));
				map.put("jump", redisList.get(i).get("jump"));
				map.put("timeOut", redisList.get(i).get("timeOut"));
			}
			
			result.setResult(listMap);
			result.setTotal(countBySQL);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("首页项目查询失败");
			e.printStackTrace();
		}
		
		return result;
	}

	
	@Override
	public BaseObjectResult<Map<String, Object>> getRedisByProjectId(Integer pid) {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String,Object>>("1", "根据项目id查询项目redis信息成功");
		Map<String, Object> map = adprojectDao.getInfoById(pid);
		// 获取表单数
		Integer subCout = ldysubmitDao.countSubmitByPidAndDate(pid, null);
		map.put("subCout", subCout);
//		Map<String, Object> val = new HashMap<String, Object>();
//		val.put("pid", pid);
//		String redisAddr = PropertyUtil.getProperty("redis_addr");
//		String URl = redisAddr+"/projectRedis/project";
//		String doPost = HttpClientUtil.doPost(URl,val);
//		JSONObject jsonObject = JSONObject.fromObject(doPost);
//		if("".equals(doPost) || doPost == null){
//			result.setE("0");
//			result.setErrorMessage("根据项目id查询项目redis信息失败");
//		}else{
//			map.put("uv", jsonObject.get("uv"));
//			map.put("ip", jsonObject.get("ip"));
//			map.put("pv", jsonObject.get("pv"));
//			result.setResult(map);
//		}
		Map<String, Object> redisMap = redisService.getRedisBypid(pid.toString());
		if(redisMap.size()>0){
			map.put("uv", redisMap.get("uv"));
			map.put("ip", redisMap.get("ip"));
			map.put("pv", redisMap.get("pv"));
		}
		result.setResult(map);
		return result;
	}

	@Override
	public BaseObjectResult<Map<String, Object>> redisData(ReqProjectReidsMod reqMod) {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String,Object>>("1", "条件查询redis数据成功");
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> values = new HashMap<String, Object>();
		String sql = "SELECT sub.ldysubid FROM LdySubmit sub"
				+ " LEFT JOIN lydlink link ON sub.ldylinkid = link.ldylinkid "
				+ " LEFT JOIN Luodiye luo on luo.luodiyeid = link.luodiyeid "
				+ " LEFT JOIN ADProject project ON project.pid = luo.pid "
				+ " LEFT JOIN ADChannel channel ON channel.channelid = link.channelid "
				+ " where 1 = 1 ";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Map<String, Object> params = new HashMap<String, Object>();
		if(org.springframework.util.StringUtils.hasText(reqMod.getpId())){
			params.put("pId", reqMod.getpId());
			sql += " AND project.pid = :pid ";
			values.put("pid", reqMod.getpId());
		}else{
			params.put("pId", "");
		}
		if(org.springframework.util.StringUtils.hasText(reqMod.getLinkId())){
			params.put("linkId", reqMod.getLinkId());
			sql += " AND link.ldylinkid = :linkId  ";
			values.put("linkId", reqMod.getLinkId());
		}else{
			params.put("linkId", "");
		}
		if(org.springframework.util.StringUtils.hasText(reqMod.getChannelId())){
			params.put("channelId", reqMod.getChannelId());
			sql += " AND channel.channelid = :channelId ";
			values.put("channelId", reqMod.getChannelId());
		}else{
			params.put("channelId", "");
		}
		if(reqMod.getBeginTime() == null){
			params.put("beginTime", "");
		}else{
			params.put("beginTime", sdf.format(reqMod.getBeginTime()));
			sql += " AND sub.addtime > :beginTime ";
			values.put("beginTime", reqMod.getBeginTime());
		}
		if(reqMod.getEndTime() == null){
			params.put("endTime", "");
		}else{
			params.put("endTime", sdf.format(reqMod.getEndTime()));
			sql += " AND sub.addtime < :endTime ";
			values.put("endTime", reqMod.getEndTime());
		}
		
		
		try {
			Integer subCount = ldysubmitDao.countBySQL(sql , values);
			resMap.put("subCount", subCount);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("条件查询redis数据失败");
			e.printStackTrace();
		}
//		String redisAddr = PropertyUtil.getProperty("redis_addr");
//		String doPost = HttpClientUtil.doPost(redisAddr+"/monyParamRedis/getParam", params);
//		if(org.springframework.util.StringUtils.hasText(doPost)){
//			JSONObject jsonObject = JSONObject.fromObject(doPost);
//			resMap.put("ipCount", jsonObject.get("ipCount"));
//			resMap.put("pvCount", jsonObject.get("pvCount"));
//			resMap.put("uvCount", jsonObject.get("uvCount"));
//			result.setResult(resMap);
//		}else{
//			result.setE("0");
//			result.setErrorMessage("条件查询redis数据失败");
//		}
		Map<String, Object> redisMap = redisService.getParam(reqMod.getpId(), sdf.format(reqMod.getBeginTime()), sdf.format(reqMod.getEndTime()), reqMod.getLinkId(), reqMod.getChannelId());
		resMap.put("ipCount", redisMap.get("ipCount"));
		resMap.put("pvCount", redisMap.get("pvCount"));
		resMap.put("uvCount", redisMap.get("uvCount"));
		result.setResult(resMap);
		return result;
	}

	@Override
	public boolean checkOutPname(Adproject adproject) {
		boolean flg = true;
		String hql = "from Adproject where pname = :pname";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pname", adproject.getPname());
		try {
			Adproject project = adprojectDao.get(hql, params);
			if(project == null){
				flg = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flg;
	}


}
