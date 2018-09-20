package com.bolaa.manage.dao;

import com.bolaa.manage.entity.Lydlink;

public interface LydlinkDao extends GenericDao<Lydlink> 
{

	/**
	 * 保存落地页链接
	 * @param link
	 * @param userId
	 * @throws Exception
	 */
	void addLink(Lydlink link, Integer userId) throws Exception;
	
	
}
