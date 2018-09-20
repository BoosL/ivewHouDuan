package com.bolaa.manage.dao;

import com.bolaa.manage.entity.Costcalculate;
import com.bolaa.manage.model.req.ReqCostcalculatMod;

public interface CostcalculateDao extends GenericDao<Costcalculate> 
{
	
	Costcalculate lookCoslate(ReqCostcalculatMod reqMod);
}
