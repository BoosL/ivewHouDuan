package com.bolaa.manage.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.bolaa.manage.entity.Adchannel;
import com.bolaa.manage.service.AdchannelService;
import com.bolaa.manage.dao.AdchannelDao;
import com.bolaa.result.BaseListResult;


/**
 * 
 * @Name: AdchannelServiceImpl
 * @author: 
 * @Date: 
 * @Description:请添加该类的主要描述注释
 */
@Service
public class AdchannelServiceImpl implements AdchannelService {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AdchannelDao adchannelDao;

	@Override
	public Serializable save(Adchannel o)  throws Exception{
		return adchannelDao.save(o);
	}

	@Override
	public void update(Adchannel o)  throws Exception{
		adchannelDao.update(o);
	}
	
	@Override
	public void delete(Serializable id)  throws Exception{
		Adchannel adchannel = adchannelDao.get(id);
		if(adchannel != null){ 
			adchannelDao.delete(id);
		}
	}
	

	/**
	 * 通过id查询
	 */
	@Override
	public Adchannel get(Serializable id)  throws Exception{		
		return adchannelDao.get(id);
	}

	@Override
	public List<Adchannel> find(String hql)  throws Exception{
		return adchannelDao.find(hql);
	}
	
	@Override
	public List<Adchannel> find(String hql, Map<String, Object> params)  throws Exception{
		return adchannelDao.find(hql, params);
	}

	@Override
	public List<Adchannel> find(String hql, int page, int rows)  throws Exception{
		return adchannelDao.find(hql, page, rows);
	}

	@Override
	public List<Adchannel> find(String hql, Map<String, Object> params,int page, int rows)  throws Exception{
		return adchannelDao.find(hql, params, page, rows);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		Map<String, Object> mp = new HashMap<String, Object>();
		String hql = "from Adchannel where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adchannel> list = adchannelDao.find(hql, values, page, rows);
		Integer count = adchannelDao.count(hql, values);
		mp.put("total", count);
		mp.put("rows", list);
		return mp;
	}
	
	@Override
	public BaseListResult<Adchannel> findListByPage(Map<String, Object> params,int page, int rows)  throws Exception{
		BaseListResult<Adchannel> result=new BaseListResult<>();
		String hql = "from Adchannel where 1=1 ";
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("userName", params.get("name"));
		List<Adchannel> list = adchannelDao.find(hql, values, page, rows);
		Integer count = adchannelDao.count(hql, values);
		result.setTotal(count);
		result.setResult(list);
		return result;
	}
	
	
	@Override
	public Integer count(String hql)  throws Exception{
		return adchannelDao.count(hql);
	}

	@Override
	public Integer count(String hql, Map<String, Object> params)  throws Exception{
		return adchannelDao.count(hql, params);
	}

	@Override
	public BaseListResult<Map<String, Object>> listAdchannel(int page, int rows) {
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String,Object>>("1", "查询所有渠道列表分页成功");
		String sql = " SELECT channel.* ,adadm.username AS userName  FROM ADChannel channel LEFT JOIN ADAdm adadm  on channel.adduserid = adadm.userid ";
		try {
			List<Map<String,Object>> list = adchannelDao.findPageBySQL(sql, null, page, rows);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询所有渠道列表分页失败");
			e.printStackTrace();
		}
		return result;
	}

}
