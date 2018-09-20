package com.bolaa.manage.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bolaa.result.BaseListResult;

/**
 * @decription
 * @author Administrator
 * @date 2017年4月25日 上午11:33:51
 * @param <T>
 */
public interface BaseService<T> {

	/**保存*/
	public Serializable save(T o) throws Exception;
	
	/**更新*/
	public void update(T o) throws Exception;
	
	/**删除*/
	public void delete(Serializable id) throws Exception;
	
	/**根据id获取对象*/
	public T get(Serializable id) throws Exception;
	
	/**根据hql查询*/
	public List<T> find(String hql) throws Exception;
	
	/**根据hql和参数查询*/
	public List<T> find(String hql, Map<String, Object> params) throws Exception;
	
	/**根据hql、页数和每页条数分页查询*/
	public List<T> find(String hql, int page, int rows) throws Exception;
	
	/**根据hql、参数、页数和每页条数分页查询*/
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) throws Exception;
	
	/**根据参数、页数和每页条数分页查询*/
	public Map<String, Object> findByPage(Map<String, Object> params, int page, int rows) throws Exception;
	
	/**根据参数、页数和每页条数分页查询*/
	public BaseListResult<T> findListByPage(Map<String, Object> params, int page, int rows) throws Exception;
	
	/**根据hql统计*/
	public Integer count(String hql) throws Exception;
	
	/**根据hql和参数统计*/
	public Integer count(String hql, Map<String, Object> params) throws Exception;
	
	
	
}
