package com.bolaa.manage.service;

import com.bolaa.manage.entity.Costcalculate;
import com.bolaa.manage.model.req.ReqCostcalculatMod;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

public interface CostcalculateService extends BaseService<Costcalculate>{

	/**
	 * 成本计算
	 * @param reqMod
	 * @return
	 */
	 BaseResult saveCompute(ReqCostcalculatMod reqMod);
	 
	 /**
	  * 查看计算的成本
	  * @param reqMod
	  * @return
	  */
	 BaseObjectResult<Costcalculate> lookCompute(ReqCostcalculatMod reqMod);
	
	
}
