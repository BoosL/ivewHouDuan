package com.bolaa.manage.service;

import java.util.Map;

import com.bolaa.manage.entity.Ldyitems;
import com.bolaa.result.BaseListResult;

public interface LdyitemsService extends BaseService<Ldyitems>{

	/**
	 * 根据落地页id 查询落地页模块集合
	 * @param luodiyeId
	 * @return
	 */
	BaseListResult<Map<String, Object>> listLdyitemsByLuodiyeId(Integer luodiyeId);
	
	
}
