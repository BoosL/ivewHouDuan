package com.bolaa.manage.service;

import java.util.Map;

import com.bolaa.manage.entity.Userlog;
import com.bolaa.result.BaseListResult;

public interface UserlogService extends BaseService<Userlog>{

	BaseListResult<Map<String, Object>> logList(int page, int rows);
	
	
}
