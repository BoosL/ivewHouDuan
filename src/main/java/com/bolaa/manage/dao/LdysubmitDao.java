package com.bolaa.manage.dao;

import java.util.Date;

import com.bolaa.manage.entity.Ldysubmit;

public interface LdysubmitDao extends GenericDao<Ldysubmit> 
{

	Integer countSubmitByPidAndDate(Integer pId, Date time);
	
	
}
