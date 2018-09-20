package com.bolaa.manage.service;

import com.bolaa.manage.entity.Ldysubmit;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;

import java.util.Date;
import java.util.Map;

public interface LdysubmitService extends BaseService<Ldysubmit>{
	
	/**
	 * 根据项目id 统计表单数 
	 * @param pId
	 * @return
	 */
	Integer countSubmitByPid(Integer pId);
	
	/**
	 * 根据项目id 统计今日表单数
	 * @param pId
	 * @param time
	 * @return
	 */
	Integer countSubmitByPidAndDate(Integer pId, Date time);
	
	/**
	 * 查询已完成的表单集合分页
	 * @param luodiyeid
	 * @param page
	 * @param rows
	 * @return
	 */
	BaseObjectResult<Map<String, Object>> overSubList(int ldylinkid, int page, int rows);
	
}
