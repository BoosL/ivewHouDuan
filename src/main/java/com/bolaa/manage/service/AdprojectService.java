package com.bolaa.manage.service;

import com.bolaa.manage.entity.Adproject;
import com.bolaa.manage.model.req.ReqProjectListMod;
import com.bolaa.manage.model.req.ReqProjectReidsMod;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import java.util.Date;
import java.util.Map;

public interface AdprojectService extends BaseService<Adproject>{

	/**
	 * 根据条件查询项目集合
	 * @param beginTime
	 * @param endTime
	 * @param classId
	 * @param projectName
	 * @param pstatus
	 * @param page
	 * @param rows
	 * @return
	 */
	BaseListResult<Map<String, Object>> projectList(Date beginTime, Date endTime, Integer classId, String projectName, String pstatus, int page, int rows);

	/**
	 * 首页上边的计数
	 * @return
	 */
	BaseObjectResult<Map<String, Object>> topCount();
	
	/**
	 * 锁定项目
	 * @param pid
	 * @return
	 */
	BaseResult lockProject(Integer pid);

	/**
	 * 根据项目id 查询项目详情
	 * @param id
	 * @return
	 */
	BaseObjectResult<Map<String, Object>> getInfoById(int id);
	
	/**
	 * 首页查询项目
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 */
	BaseListResult<Map<String, Object>> indexProject(String pstatus, int page, int rows);

	/**
	 * 根据项目id 查询redis 
	 * @param pid
	 * @return
	 */
	BaseObjectResult<Map<String, Object>> getRedisByProjectId(Integer pid);
	
	/**
	 * 根据条件查询redis数据
	 * @param reqMod
	 * @return
	 */
	BaseObjectResult<Map<String, Object>> redisData(ReqProjectReidsMod reqMod);

	/**
	 * 检查项目名称是否存在
	 * @param adproject
	 * @return
	 */
	boolean checkOutPname(Adproject adproject);

	
	
}
