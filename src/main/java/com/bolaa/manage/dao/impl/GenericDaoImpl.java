package com.bolaa.manage.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.bolaa.common.ReflectionUtils;
import com.bolaa.manage.dao.GenericDao;


@Repository("genericDaoImpl")
public class GenericDaoImpl<T> implements GenericDao<T> {

	protected Logger logger = Logger.getLogger(getClass());

	private SessionFactory sessionFactory;

	public Class<T> entityClass;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 用于Dao层子类的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * HibernateDao<User, Long>{ }
	 */
	public GenericDaoImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于省略Dao层, Service层直接使用通用HibernateDao的构造函数. 在构造函数中定义对象类型Class. eg.
	 * HibernateDao<User, Long> userDao = new HibernateDao<User,
	 * Long>(sessionFactory, User.class);
	 */
	public GenericDaoImpl(final SessionFactory sessionFactory, final Class<T> entityClass) {

		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	@PostConstruct
	public void init() {
		// 1、通过反射获取注解“M”（即模型对象）的类类型
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
		// 2、得到模型对象的实体名
		// String entityName =
		// getSessionFactory().getClassMetadata(this.entityClass).getEntityName();
	}

	@Override
	public Serializable save(T o) throws Exception {
		return this.getCurrentSession().save(o);
	}

	@Override
    public T get(Class<T> c, Serializable id) throws Exception {
		return (T) this.getCurrentSession().get(c, id);
	}

	@Override
	public T get(String hql) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					q.setParameterList(key, (Object[]) obj);
				} else {
					q.setParameter(key, obj);
				}
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(T o) throws Exception {
		this.getCurrentSession().delete(o);
	}

	@Override
	public void update(T o) throws Exception {
		this.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o) throws Exception {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public List<T> find(String hql) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					q.setParameterList(key, (Object[]) obj);
				} else {
					q.setParameter(key, obj);
				}
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					q.setParameterList(key, (Object[]) obj);
				} else {
					q.setParameter(key, obj);
				}
			}
		}
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, int page, int rows) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	@Override
	public Integer count(String hql) throws Exception {
		Query q = this.getCurrentSession().createQuery(beforeCount(hql));
		List list = q.list();
		if (list != null && list.size() > 0) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	private String beforeCount(String hql){
	    String regex = "\\b(from)\\b";
        Pattern pat = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = pat.matcher(hql);
        m.find();
	    return "select count(*) "+ hql.substring(m.start());
	}
	
	@Override
	public Integer count(String hql, Map<String, Object> params) throws Exception {
		//"select count(*) "+ hql.substring(hql.indexOf("from"))
	    //Query q = this.getCurrentSession().createQuery(hql);
	    Query q = this.getCurrentSession().createQuery(beforeCount(hql));
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					q.setParameterList(key, (Object[]) obj);
				} else {
					q.setParameter(key, obj);
				}
			}
		}
		List list = q.list();
		if (list != null && list.size() > 0) {
			return Integer.valueOf(list.get(0).toString());
		}
		return 0;
	}

	@Override
	public int executeHql(String hql) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public T get(Serializable id) throws Exception {
		Assert.notNull(id, "id不能为空");
		T t = (T) this.getCurrentSession().get(entityClass, id);
		return t;
	}

	@Override
	public void delete(Serializable id) throws Exception {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		// logger.debug("delete entity {},id is {}___"+entityClass.getSimpleName()+"___"+
		// id);
	}

	@Override
	public List<Map<String, Object>> findMaps(String hql) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return q.list();
	}

	@Override
	public List<Map<String, Object>> findMaps(String hql, Map<String, Object> params) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					q.setParameterList(key, (Object[]) obj);
				} else {
					q.setParameter(key, obj);
				}
			}
		}
		q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return q.list();
	}

	@Override
	public List<Map<String, Object>> findMaps(String hql, int page, int rows) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	@Override
	public List<Map<String, Object>> findMaps(String hql, Map<String, Object> params, int page, int rows) throws Exception {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					q.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					q.setParameterList(key, (Object[]) obj);
				} else {
					q.setParameter(key, obj);
				}
			}
		}
		q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	@Override
	public int exuceteSql(String sql) throws Exception {
		int result = 0;
		try {
			result = this.getCurrentSession().createSQLQuery(sql).executeUpdate();
			logger.debug("excute sql:" + sql);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return result;
	}

	@Override
	public Integer countBySQL(String sql, Map<String, ?> values) throws Exception {
		String countSql = "select count(*) from ( " + sql + " ) t";
		List list = this.getCurrentSession().createSQLQuery(countSql).setProperties(values).list();
		if (list != null && list.size() > 0) {
			return Integer.valueOf(list.get(0).toString());
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> findBySQL(String sql, Map<String, ?> values) throws Exception {
		Assert.hasText(sql, "sql不能为空");
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (values != null) {
			for (Entry<String, ?> entry : values.entrySet()) {
				if (entry.getValue() instanceof Collection<?>) {
					query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
				} else if (entry.getValue() instanceof Object[]) {
					query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			// query.setProperties(values);
		}
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> findPageBySQL(String sql, Map<String, ?> values, int page, int rows) throws Exception {
		Assert.hasText(sql, "sql不能为空");
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (values != null) {
			for (Entry<String, ?> entry : values.entrySet()) {
				if (entry.getValue() instanceof Collection<?>) {
					query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
				} else if (entry.getValue() instanceof Object[]) {
					query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	
	@Override
	public Integer countAllBySQL(String sql, Map<String, ?> values) throws Exception {
		List list = this.getCurrentSession().createSQLQuery(this.beforeCount(sql)).setProperties(values).list();
		if (list != null && list.size() > 0) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
}
