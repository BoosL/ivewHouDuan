package com.bolaa.manage.service;

import com.bolaa.manage.entity.Adchannel;
import com.bolaa.result.BaseListResult;

import java.util.Map;

public interface AdchannelService extends BaseService<Adchannel>{

	BaseListResult<Map<String, Object>> listAdchannel(int page, int rows);
	
	
}
