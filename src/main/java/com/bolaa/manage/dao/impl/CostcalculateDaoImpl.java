package com.bolaa.manage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bolaa.manage.dao.CostcalculateDao;
import com.bolaa.manage.entity.Costcalculate;
import com.bolaa.manage.model.req.ReqCostcalculatMod;


@Repository
public class CostcalculateDaoImpl extends GenericDaoImpl<Costcalculate> 
		implements CostcalculateDao {

	@Override
	public Costcalculate lookCoslate(ReqCostcalculatMod reqMod) {
		
		String hql = " from Costcalculate cos where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		Costcalculate costcalculate = null;
		if(reqMod.getBeginTime() != null){
			hql += " and cos.begintime = :begintime ";
			params.put("begintime", reqMod.getBeginTime());
		}
		if(reqMod.getEndTime() != null ){
			hql += " and cos.endtime = :endtime ";
			params.put("endtime", reqMod.getEndTime());
		}
		if(reqMod.getpId() != null ){
			hql += " and cos.pid = :pid ";
			params.put("pid", reqMod.getpId());
		}
		if(reqMod.getLinkId() != null ){
			hql += " and cos.ldylinkid = :ldylinkid ";
			params.put("ldylinkid", reqMod.getLinkId());
		}
		if(reqMod.getChannelId() != null ){
			hql += " and cos.channelid = :channelid ";
			params.put("channelid", reqMod.getChannelId());
		}
		try {
			costcalculate = this.get(hql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return costcalculate;
	}
	
	
	
}
