package com.bolaa.manage.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.bolaa.manage.dao.AdprojectDao;
import com.bolaa.manage.entity.Adproject;


@Repository
public class AdprojectDaoImpl extends GenericDaoImpl<Adproject> 
		implements AdprojectDao {

	@Override
	public Map<String, Object> getInfoById(int pid) {
		String sql = " SELECT  project.*,class.classname FROM  ADProject project"
				+ " LEFT JOIN ADClass class on project.classid = class.classid "
				+ " WHERE project.pid = :projectId  ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("projectId", pid);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.findBySQL(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.get(0);
	}
	
	
}
