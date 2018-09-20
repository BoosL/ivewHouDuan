package com.bolaa.manage.dao;

import java.util.Map;

import com.bolaa.manage.entity.Adproject;

public interface AdprojectDao extends GenericDao<Adproject> 
{
	
	Map<String, Object> getInfoById(int pid);
}
