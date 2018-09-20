package com.bolaa.manage.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bolaa.manage.dao.LdysubmitDao;
import com.bolaa.manage.entity.Ldysubmit;

@Repository
public class LdysubmitDaoImpl extends GenericDaoImpl<Ldysubmit> implements LdysubmitDao {

	@Override
	public Integer countSubmitByPidAndDate(Integer pId,Date time) {
		Integer result = null;
		String sql = "SELECT project.pname as pname FROM ADProject  project"
				+ " LEFT JOIN Luodiye luodiye on project.pid = luodiye.pid "
				+ " LEFT JOIN lydlink link on link.luodiyeid = luodiye.luodiyeid"
				+ " LEFT JOIN LdySubmit submit on submit.ldylinkid = link.ldylinkid "
				+ " WHERE project.pid = :pId AND submit.ldysubid IS NOT NULL ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("pId", pId);
		if(null != time){
			sql += " AND submit.addtime >= :time ";
			values.put("time", time);
		}
		try {
			result = this.countBySQL(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
