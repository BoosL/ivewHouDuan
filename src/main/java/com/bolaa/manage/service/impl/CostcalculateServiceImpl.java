package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolaa.manage.dao.CostcalculateDao;
import com.bolaa.manage.entity.Costcalculate;
import com.bolaa.manage.model.req.ReqCostcalculatMod;
import com.bolaa.manage.service.CostcalculateService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;


/**
 * 
 * @Name: CostcalculateServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class CostcalculateServiceImpl implements CostcalculateService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private CostcalculateDao costcalculateDao;

	@Override
	public Serializable save(Costcalculate o)  throws Exception{
		return costcalculateDao.save(o);
	}

	@Override
	public void update(Costcalculate o)  throws Exception{
		costcalculateDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Costcalculate costcalculate = costcalculateDao.get(id);
		if(costcalculate != null){ 
			costcalculateDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Costcalculate get(Serializable id)  throws Exception{		
		return costcalculateDao.get(id);
	}

	@Override
	public List<Costcalculate> find(String hql)  throws Exception{
		return costcalculateDao.find(hql);
	}
	
	@Override
	public List<Costcalculate> find(String hql, Map<String, Object> params)  throws Exception{
		return costcalculateDao.find(hql, params);
	}

	@Override
	public List<Costcalculate> find(String hql, int page, int rows)  throws Exception{
		return costcalculateDao.find(hql, page, rows);
	}

	@Override
	public List<Costcalculate> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return costcalculateDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Costcalculate where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Costcalculate> list = costcalculateDao.find(hql, values, page, rows);
		Integer count = costcalculateDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Costcalculate> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Costcalculate> result=new BaseListResult<>();
		String hql = "from Costcalculate where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Costcalculate> list = costcalculateDao.find(hql, values, page, rows);
		Integer count = costcalculateDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return costcalculateDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return costcalculateDao.count(hql, params);
	}


	@Override
	public BaseResult saveCompute(ReqCostcalculatMod reqMod) {
		BaseResult result = new BaseResult("1", "成本计算成功");
		Costcalculate coslate = costcalculateDao.lookCoslate(reqMod);
		if(coslate == null){// 未计算成本  插入信息
			Costcalculate cos = new Costcalculate();
			cos.setAddtime(new Date());
			cos.setAdduserid(1);
			cos.setBegintime(reqMod.getBeginTime());
			cos.setChannelid(reqMod.getChannelId());
			cos.setCost(reqMod.getCost());
			cos.setEndtime(reqMod.getEndTime());
			cos.setIpcount(reqMod.getIpCount());
			cos.setLdylinkid(reqMod.getLinkId());
			cos.setPid(reqMod.getpId());
			cos.setPvcount(reqMod.getPvCount());
			cos.setSubcount(reqMod.getSubCount());
			cos.setSum(reqMod.getSum());
			cos.setUvcount(reqMod.getUvCount());
			try {
				costcalculateDao.save(cos);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("成本计算失败");
				e.printStackTrace();
			}
		}else{
			result.setE("2");
			result.setErrorMessage("该条件的成本已经计算");
		}
		return result;
	}

	@Override
	public BaseObjectResult<Costcalculate> lookCompute(ReqCostcalculatMod reqMod) {
		BaseObjectResult<Costcalculate> result = new BaseObjectResult<Costcalculate>();
		Costcalculate coslate = costcalculateDao.lookCoslate(reqMod);
		if(coslate == null){
			result.setE("0");
			result.setErrorMessage("在该条件下的成本 未计算");
		}else{
			result.setE("1");
			result.setErrorMessage("该条件下的成本已经计算");
			result.setResult(coslate);
		}
		return result;
	}

}
