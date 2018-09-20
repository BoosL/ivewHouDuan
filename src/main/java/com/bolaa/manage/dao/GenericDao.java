package com.bolaa.manage.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T> {

	public T get(Serializable id) throws Exception;

	public void delete(Serializable id) throws Exception;

	public Serializable save(T o)  throws Exception;

	public void update(T o)  throws Exception;

	public void saveOrUpdate(T o) throws Exception;
	
	public void delete(T o) throws Exception;

	public T get(Class<T> c, Serializable id) throws Exception;

	public T get(String hql) throws Exception;

	public T get(String hql, Map<String, Object> params) throws Exception;

	public List<T> find(String hql) throws Exception;

	public List<T> find(String hql, Map<String, Object> params) throws Exception;

	public List<T> find(String hql, int page, int rows) throws Exception;

	public List<T> find(String hql, Map<String, Object> params, int page, int rows) throws Exception;

	public Integer count(String hql) throws Exception;

	public Integer count(String hql, Map<String, Object> params) throws Exception;

	public int executeHql(String hql) throws Exception;

	public List<Map<String, Object>> findMaps(String hql) throws Exception;

	public List<Map<String, Object>> findMaps(String hql, Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> findMaps(String hql, int page, int rows) throws Exception;

	public List<Map<String, Object>> findMaps(String hql, Map<String, Object> params, int page, int rows) throws Exception;

	public int exuceteSql(String sql) throws Exception;

	public Integer countBySQL(String sql, Map<String, ?> values) throws Exception;

	public List<Map<String, Object>> findBySQL(String sql, Map<String, ?> values) throws Exception;

	List<Map<String, Object>> findPageBySQL(String sql, Map<String, ?> values, int page, int rows) throws Exception;

	Integer countAllBySQL(String sql, Map<String, ?> values) throws Exception;

}
