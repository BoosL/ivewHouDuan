package com.bolaa.manage.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.bolaa.common.BeanUtil;
import com.bolaa.manage.dao.LydlinkDao;
import com.bolaa.manage.entity.Lydlink;


@Repository
public class LydlinkDaoImpl extends GenericDaoImpl<Lydlink> 
		implements LydlinkDao {

	@Override
	public void addLink(Lydlink link, Integer userId) throws Exception {
		if(null != link.getLdylinkid()){ // 修改
			Lydlink lydlink = this.get(link.getLdylinkid());
			BeanUtil.copyNotNullProperties(link, lydlink);
			this.saveOrUpdate(lydlink);
		}else{
			link.setAddtime(new Date());
			if(!StringUtils.hasText(link.getLstatus())){
				link.setLstatus("待发布");
			}
			link.setAdduserid(userId);
			this.save(link);
		}
	}
	
	
}
