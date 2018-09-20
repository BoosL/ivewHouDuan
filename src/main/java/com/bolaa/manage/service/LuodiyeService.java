package com.bolaa.manage.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bolaa.manage.entity.Luodiye;
import com.bolaa.manage.model.req.ReqAddLuodiyeMod;
import com.bolaa.manage.model.req.ReqLuodiyeListMod;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

public interface LuodiyeService extends BaseService<Luodiye>{
	
	/**
	 * 根据项目id查询落地页
	 * @param projectId
	 * @param page
	 * @param rows
	 * @return
	 */
	BaseListResult<Map<String, Object>> luodiyeListByProjectId(Integer projectId, int page, int rows);

	/**
	 * 根据条件查询落地页集合
	 * @param reqMod
	 * @return
	 */
	BaseListResult<Map<String, Object>> luodiyelist(ReqLuodiyeListMod reqMod);
	
	/**
	 * 添加落地页
	 * @param reqMod
	 * @return
	 */
	BaseResult addLuodiye(ReqAddLuodiyeMod reqMod, HttpServletRequest req);
	
	/**
	 * 添加落地页和模块的 关联
	 * @param request
	 * @param luodiye
	 * @param items
	 * @return
	 */
	BaseObjectResult<Map<String, Object>> addluodiyeItems(HttpServletRequest request, Luodiye luodiye, String items);

	
	/**
	 * 根据落地页查询模块信息
	 * @param integer
	 * @return
	 */
	BaseListResult<Map<String, Object>> luodiyeInfo(Integer integer);

	/**
	 * 获取静态页面需要的默认值
	 * @param luodiyeId
	 * @return
	 */
	Map<String, Object> getDefaultInfo(int luodiyeId);
	
	
}
