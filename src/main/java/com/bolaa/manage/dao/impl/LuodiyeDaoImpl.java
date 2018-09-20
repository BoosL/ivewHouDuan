package com.bolaa.manage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.bolaa.manage.dao.LuodiyeDao;
import com.bolaa.manage.entity.Luodiye;


@Repository
public class LuodiyeDaoImpl extends GenericDaoImpl<Luodiye> 
		implements LuodiyeDao {

	@Override
	public List<Map<String, Object>> luodiyeInfo(Integer luodiyeId) throws Exception {
		String sql = "SELECT items.* ,luoitems.lorder FROM LdyItems items"
				+ " LEFT JOIN Luoldyitems luoitems on items.ldyitemid = luoitems.ldyitemid "
				+ " WHERE luoitems.luodiyeid = :luodiyeId ORDER BY luoitems.lorder asc ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("luodiyeId", luodiyeId);
		List<Map<String,Object>> list = this.findBySQL(sql, values);
		return list;
	}
	
	
}
