package com.bolaa.manage.dao;

import java.util.List;
import java.util.Map;

import com.bolaa.manage.entity.Luodiye;

public interface LuodiyeDao extends GenericDao<Luodiye> 
{

	/**
	 * 根据落地页id 查询整个落地页的模块信息
	 * @param luodiyeId
	 * @return
	 * @throws Exception 
	 */
	List<Map<String, Object>> luodiyeInfo(Integer luodiyeId) throws Exception;
	
	
}
