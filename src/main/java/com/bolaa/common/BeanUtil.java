package com.bolaa.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

/**
 * Bean对象工具集，包括以下功能： 1、对象属性拷贝 2、动态对象创建
 * 
 */
public class BeanUtil {

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DEFAULT_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT2 = "yyyy-MM-dd";
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(BeanUtil.class);

	/**
	 * 将一个对象的非空属性值拷贝另一个对象
	 * 
	 * @param srcObject
	 *            提供数据的源对象
	 * @param destObject
	 *            接受数据的目标对象
	 */
	public static void copyNotNullProperties(Object srcObject, Object destObject) {
		copyProperties(srcObject, destObject, null, true);
	}

	/**
	 * 将一个对象的非空属性值拷贝另一个对象（不拷贝忽略的属性）
	 * 
	 * @param srcObject
	 *            提供数据的源对象
	 * @param destObject
	 *            接受数据的目标对象
	 * @param ignoreProperties
	 *            需要忽略的属性数组
	 */
	public static void copyNotNullProperties(Object srcObject, Object destObject, String[] ignoreProperties) {
		copyProperties(srcObject, destObject, ignoreProperties, true);
	}

	/**
	 * 将一个对象的属性值拷贝另一个对象（包括空属性值）
	 * 
	 * @param srcObject
	 *            提供数据的源对象
	 * @param destObject
	 *            接受数据的目标对象
	 */
	public static void copyProperties(Object srcObject, Object destObject) {
		copyProperties(srcObject, destObject, null, false);
	}

	/**
	 * 将一个对象的属性值拷贝另一个对象（包括空属性值，但不拷贝忽略的属性）
	 * 
	 * @param srcObject
	 *            提供数据的源对象
	 * @param destObject
	 *            接受数据的目标对象
	 * @param ignoreProperties
	 *            需要忽略的属性数组
	 */
	public static void copyProperties(Object srcObject, Object destObject, String[] ignoreProperties) {
		copyProperties(srcObject, destObject, ignoreProperties, false);
	}

	/**
	 * 指定源对象是否具有指定的可读属性
	 * 
	 * @param srcObject
	 *            源对象
	 * @param propertyName
	 *            指定属性名
	 */
	public static boolean hasReadableProperty(Object srcObject, String propertyName) {
		try {
			PropertyDescriptor pd = getPropertyDescriptor(srcObject.getClass(), propertyName);
			Method method = pd.getReadMethod();
			if (method != null)
				return Modifier.isPublic(method.getDeclaringClass().getModifiers());

		} catch (Exception e) {
			logger.error("系统异常,{}",e);	
			// TODO: handle exception
		}

		return false;
	}

	/**
	 * 指定源对象是否具有指定的可写属性
	 * 
	 * @param srcObject
	 *            源对象
	 * @param propertyName
	 *            指定属性名
	 */
	public static boolean hasWritableProperty(Object srcObject, String propertyName) {
		try {
			PropertyDescriptor pd = getPropertyDescriptor(srcObject.getClass(), propertyName);
			if (pd != null) {
				Method method = pd.getWriteMethod();
				if (method != null)
					return Modifier.isPublic(method.getDeclaringClass().getModifiers());
			}

		} catch (Exception e) {
			logger.error("系统异常,{}",e);	
			// TODO: handle exception
		}

		return false;
	}

	/**
	 * 设置某对象属性值
	 * 
	 * @param srcObject
	 *            源对象
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            属性值
	 * @return //TODO待确定
	 */
	public static Object setProperty(Object srcObject, String propertyName, Object value) {
		PropertyDescriptor pd = getPropertyDescriptor(srcObject.getClass(), propertyName);
		Method method = pd.getWriteMethod();
		if (method != null && Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			method.setAccessible(true);

			try {
				String paramType = method.getParameterTypes()[0].getName();

				if (null != paramType && paramType.length() > 0) {
					if ("java.lang.String".equals(paramType)) {
						if ("null".equals(value)) {
							value = "";
						}
						String revStr = String.valueOf(value);
						if (StringUtils.isNotBlank(revStr)) {
							revStr = revStr.trim();
						}
						return method.invoke(srcObject, revStr);
					} else if ("java.lang.Integer".equals(paramType)) {
						return method.invoke(srcObject, Integer.parseInt(String.valueOf(value)));
					} else if ("java.lang.Long".equals(paramType)) {
						if (value == null || "null".equals(value)) {
							return null;
						}
						return method.invoke(srcObject, Long.parseLong(String.valueOf(value)));
					} else if ("java.lang.Double".equals(paramType)) {
						return method.invoke(srcObject, Double.parseDouble(String.valueOf(value)));
					} else if ("java.lang.Boolean".equals(paramType)) {
						return method.invoke(srcObject, Boolean.parseBoolean(String.valueOf(value)));
					} else if ("java.lang.Short".equals(paramType)) {
						return method.invoke(srcObject, Short.parseShort(String.valueOf(value)));
					} else if ("java.util.Date".equals(paramType)) {
						Date date = (Date) getTime(value);
						if (null != date) {
							return method.invoke(srcObject, date);
						}

					} else if ("java.sql.Timestamp".equals(paramType)) {
						Date date = (Date) getTime(value);
						if (null != date) {
							Timestamp time = new Timestamp(date.getTime());
							return method.invoke(srcObject, time);
						}

					} else if ("java.math.BigDecimal".equals(paramType)) {
						return method.invoke(srcObject, new BigDecimal(String.valueOf(value)));
					} else {
						return method.invoke(srcObject, value);
					}

				} else {
					return method.invoke(srcObject, new Object[] { value });
				}
			} catch (IllegalArgumentException e) {
				// TODO 待确定是否要处理该异常
				logger.error("系统异常,{}",e);	
			} catch (IllegalAccessException e) {
				// TODO 待确定是否要处理该异常
				logger.error("系统异常,{}",e);	
			} catch (InvocationTargetException e) {
				// TODO 待确定是否要处理该异常
				logger.error("系统异常,{}",e);	
			}
		}

		return null;
	}

	public static Object  getTime(Object value )
	{
		String strValue=String.valueOf(value);
		strValue = strValue.trim();
		SimpleDateFormat sdf = null;
		try{
		if(strValue.indexOf("-")>-1)
		{
			int length=strValue.length();
			if(length==10)
			{
				sdf = new SimpleDateFormat(DEFAULT_FORMAT2);
				return sdf.parse(strValue);
			}else if(length==19){
				sdf = new SimpleDateFormat(DEFAULT_FORMAT1);
				return sdf.parse(strValue);
			}else if(length==23)
			{
				sdf = new SimpleDateFormat(DEFAULT_FORMAT2);
				return sdf.parse(strValue);
			}
			
		}else if(strValue.indexOf("/")>-1){
			int length=strValue.length();
			if(length==10)
			{
				sdf = new SimpleDateFormat("yyyy/MM/dd");
				return sdf.parse(strValue);
			} 
			else if(length==16)
			{
				sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				return sdf.parse(strValue);
			} 
			else if(length==19){
				sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				return sdf.parse(strValue);
			}else if(length==23)
			{
				sdf = new SimpleDateFormat("yyyy/MM/dd");
				return sdf.parse(strValue);
			}
		}
		else{
			int length=strValue.length();
			if(length==8)
			{
				sdf = new SimpleDateFormat("yyyyMMdd");
				return sdf.parse(strValue);
			}else if(length==14)
			{
				sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				return sdf.parse(strValue);
			}
			else if(length==6)
			{
				sdf = new SimpleDateFormat("yyyyMM");
				return sdf.parse(strValue);
			}
			
			if("null".equals(strValue.trim())){
				return null;
			}
			if (strValue.indexOf("-") > -1) {
				sdf = new SimpleDateFormat(DEFAULT_FORMAT1);
				return sdf.parse(strValue);
			} else if (strValue.indexOf("/") > -1) {
				sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				return sdf.parse(strValue);
			} else {
				sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				return sdf.parse(strValue);
			}
		}
		}catch(Exception e){
			logger.error("系统异常,{}",e);	
		}
		
		
		return null;
	}
	 

	/**
	 * 返回源对象所有属性
	 * 
	 * @param srcObject
	 *            源对象
	 * @return 通过字符串数组来表示所有属性
	 */
	public static String[] getProperties(Object srcObject) {
		Class clazz = srcObject.getClass();
		PropertyDescriptor[] pds = getPropertyDescriptors(clazz);

		String[] props = new String[pds.length];

		for (int i = 0; i < pds.length; i++) {
			props[i] = pds[i].getName();
		}

		return props;
	}

	/**
	 * 返回源对象所有可读属性
	 * 
	 * @param srcObject
	 *            源对象
	 * @return 通过字符串数组来表示所有可读属性
	 */
	public static String[] getReadableProperties(Object srcObject) {
		Class clazz = srcObject.getClass();
		PropertyDescriptor[] pds = getPropertyDescriptors(clazz);

		List<String> propList = new ArrayList<String>(pds.length);

		for (int i = 0; i < pds.length; i++) {
			Method method = pds[i].getReadMethod();
			if (method != null && Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
				propList.add(pds[i].getName());
			}
		}

		String[] props = new String[propList.size()];
		propList.toArray(props);

		return props;
	}

	/**
	 * 返回源对象所有可写属性
	 * 
	 * @param srcObject
	 *            源对象
	 * @return 通过字符串数组来表示所有可写属性
	 */
	public static String[] getWritableProperties(Object srcObject) {
		Class clazz = srcObject.getClass();
		PropertyDescriptor[] pds = getPropertyDescriptors(clazz);

		List<String> propList = new ArrayList<String>(pds.length);

		for (int i = 0; i < pds.length; i++) {
			Method method = pds[i].getWriteMethod();
			if (method != null && Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
				propList.add(pds[i].getName());
			}
		}

		String[] props = new String[propList.size()];
		propList.toArray(props);

		return props;
	}

	public static Object getProperty(Object srcObject, String propertyName) {
		PropertyDescriptor pd = getPropertyDescriptor(srcObject.getClass(), propertyName);
		Method method = pd.getReadMethod();
		if (method != null && Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			method.setAccessible(true);

			try {
				return method.invoke(srcObject, new Object[0]);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				logger.error("系统异常,{}",e);	
			} catch (IllegalAccessException e) {
				// TODO 待确定是否要处理该异常
				logger.error("系统异常,{}",e);	
			} catch (InvocationTargetException e) {
				// TODO 待确定是否要处理该异常
				logger.error("系统异常,{}",e);	
			}
		}

		return null;
	}

	/**
	 * 根据对象名创建对象实例
	 * 
	 * @param className
	 *            对象的全名
	 */
	public static Object createObject(String className) {
		Class clazz = getClass(className);
		Object object = null;
		if (clazz != null)
			object = createObject(clazz);

		return object;
	}

	/**
	 * 根据对象类型创建实例
	 * 
	 * @param clazz
	 *            对象类型
	 */
	public static Object createObject(Class clazz) {
		Object object = null;

		if (clazz != null) {
			try {
				object = clazz.newInstance();
			} catch (InstantiationException e) {
				logger.error("系统异常,{}",e);	
				// ignore
			} catch (IllegalAccessException e) {
				logger.error("系统异常,{}",e);	
				// ignore
			}
		}

		return object;
	}

	/**
	 * 根据对象全名及参数创建对象
	 * 
	 * @param className
	 *            对象全名
	 * @param parameters
	 *            初始化参数
	 */
	public static Object createObject(String className, Object[] parameters) {
		Class clazz = getClass(className);
		Object object = null;
		if (clazz != null)
			object = createObject(clazz, parameters);

		return object;
	}

	/**
	 * 根据对象类型及参数创建对象
	 * 
	 * @param clazz
	 *            对象类型
	 * @param parameters
	 *            初始化参数
	 */
	public static Object createObject(Class clazz, Object[] parameters) {
		Object object = null;

		if (clazz != null) {
			Constructor contructor = getConstructor(clazz, parameters);

			if (contructor != null) {
				try {
					object = contructor.newInstance(parameters);
				} catch (IllegalArgumentException e) {
					logger.error("系统异常,{}",e);	
					// ignore
				} catch (InstantiationException e) {
					logger.error("系统异常,{}",e);	
					// ignore
				} catch (IllegalAccessException e) {
					logger.error("系统异常,{}",e);	
					// ignore
				} catch (InvocationTargetException e) {
					logger.error("系统异常,{}",e);	
					// ignore
				}
			}
		}

		return object;
	}

	/**
	 * 根据对象全名取得对象类型对象
	 * 
	 * @param className
	 *            对象全名
	 */
	public static Class getClass(String className) {
		Class clazz = null;

		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			logger.error("系统异常,{}",e);	
			// ignore
		}

		return clazz;
	}

	protected static Constructor getConstructor(Class clazz, Object[] parameters) {
		Constructor constructor = null;

		if (clazz != null) {
			Constructor[] contructors = clazz.getConstructors();

			for (Constructor ctor : contructors) {
				if (isParametersInstanceof(parameters, ctor.getParameterTypes()))
					constructor = ctor;
			}
		} else {
			throw new RuntimeException("clazz must not be null.");
		}

		return constructor;
	}

	protected static Class[] getParameterTypes(Object[] parameters) {
		Class[] parameterTypes = null;

		if (parameters != null) {
			parameterTypes = new Class[parameters.length];

			for (int i = 0; i < parameters.length; i++) {
				Object o = parameters[i];
				if (o != null)
					parameterTypes[i] = o.getClass();
			}
		}

		return parameterTypes;
	}

	protected static boolean isParametersInstanceof(Object[] parameters, Class[] parameterTypes) {
		if (parameterTypes == null) {
			return parameters == null || parameters.length == 0;
		}

		if (parameters == null) {
			return parameterTypes.length == 0;
		}

		if (parameterTypes.length != parameters.length) {
			return false;
		}

		for (int i = 1; i < parameterTypes.length; i++) {
			if (!parameterTypes[i].isInstance(parameters[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 对象拷贝
	 * 
	 * @param srcObject
	 *            提供数据的源对象
	 * @param destObject
	 *            接受数据的目标对象
	 * @param ignoreProperties
	 *            需要忽略的属性
	 */
	protected static void copyProperties(Object srcObject, Object destObject, String[] ignoreProperties,
			boolean ignoreNull) {
		if (srcObject == null || destObject == null) {
			throw new RuntimeException("属性拷贝时源对象和目标对象都不能为null。");
		}

		PropertyDescriptor[] targetPds = null;

		targetPds = getPropertyDescriptors(destObject.getClass());

		List ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];

			if (targetPd.getWriteMethod() != null
					&& (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = null;

				try {
					sourcePd = getPropertyDescriptor(srcObject.getClass(), targetPd.getName());
				} catch (Exception e) {
					logger.error("系统异常,{}",e);	 // 忽略异常
				}
				Method writeMethod = null;
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();

						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}

						Object value = readMethod.invoke(srcObject, new Object[0]);
						writeMethod = targetPd.getWriteMethod();

						if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
							writeMethod.setAccessible(true);
						}

						if (ignoreNull) // 忽略空属性值
						{
							if (value != null) {
								writeMethod.invoke(destObject, new Object[] { value });
							}
						} else {
							writeMethod.invoke(destObject, new Object[] { value });
						}
					} catch (Throwable e) {
						logger.error("系统异常,{}",e);	
						// throw new RuntimeException("无法从源对象拷贝数据到目标对象：", e);
					}
				}
			}
		}
	}

	/**
	 * 取得指定类指定属性的描述信息
	 * 
	 * @param clazz
	 *            指定类
	 * @param propertyName
	 *            属性名
	 * @return 返回PropertyDescriptor对象
	 */
	protected static PropertyDescriptor getPropertyDescriptor(Class clazz, String propertyName) {

		CachedPropertyDescriptor cr = null;

		try {
			cr = CachedPropertyDescriptor.forClass(clazz);
		} catch (Exception e) {
			logger.error("系统异常,{}",e);	
			// throw new RuntimeException("取得[" + clazz.getName() + "]类" +
			// propertyName + "属性描述信息时出现错误：", e);
		}

		return cr.getPropertyDescriptor(propertyName);
	}

	/**
	 * 取得指定类指定属性的描述信息（以数组方式返回）
	 * 
	 * @param clazz
	 *            指定类
	 * @param propertyName
	 *            属性名
	 * @return 返回PropertyDescriptor数组
	 */
	protected static PropertyDescriptor[] getPropertyDescriptors(Class clazz) {
		CachedPropertyDescriptor cr = null;

		try {
			cr = CachedPropertyDescriptor.forClass(clazz);
		} catch (Exception e) {
			logger.error("系统异常,{}",e);	
			// throw new RuntimeException("取得[" + clazz.getName() +
			// "]类属性描述信息时出现错误：", e);
		}

		return cr.getBeanInfo().getPropertyDescriptors();
	}

	protected static class CachedPropertyDescriptor {

		/**
		 * Set of ClassLoaders that this CachedIntrospectionResults class will
		 * always accept classes from, even if the classes do not qualify as
		 * cache-safe.
		 */
		static final Set acceptedClassLoaders = Collections.synchronizedSet(new HashSet());

		/**
		 * Map keyed by class containing CachedIntrospectionResults. Needs to be
		 * a WeakHashMap with WeakReferences as values to allow for proper
		 * garbage collection in case of multiple class loaders.
		 */
		static final Map classCache = Collections.synchronizedMap(new WeakHashMap());

		/**
		 * Accept the given ClassLoader as cache-safe, even if its classes would
		 * not qualify as cache-safe in this CachedIntrospectionResults class.
		 * <p>
		 * This configuration method is only relevant in scenarios where the
		 * Spring classes reside in a 'common' ClassLoader (e.g. the system
		 * ClassLoader) whose lifecycle is not coupled to the application. In
		 * such a scenario, CachedIntrospectionResults would by default not
		 * cache any of the application's classes, since they would create a
		 * leak in the common ClassLoader.
		 * <p>
		 * Any <code>acceptClassLoader</code> call at application startup should
		 * be paired with a {@link #clearClassLoader} call at application
		 * shutdown.
		 * 
		 * @param classLoader
		 *            the ClassLoader to accept
		 */
		static void acceptClassLoader(ClassLoader classLoader) {
			if (classLoader != null) {
				acceptedClassLoaders.add(classLoader);
			}
		}

		/**
		 * Clear the introspection cache for the given ClassLoader, removing the
		 * introspection results for all classes underneath that ClassLoader,
		 * and deregistering the ClassLoader (and any of its children) from the
		 * acceptance list.
		 * 
		 * @param classLoader
		 *            the ClassLoader to clear the cache for
		 */
		static void clearClassLoader(ClassLoader classLoader) {
			if (classLoader == null) {
				return;
			}
			synchronized (classCache) {
				for (Iterator it = classCache.keySet().iterator(); it.hasNext();) {
					Class beanClass = (Class) it.next();
					if (isUnderneathClassLoader(beanClass.getClassLoader(), classLoader)) {
						it.remove();
					}
				}
			}
			synchronized (acceptedClassLoaders) {
				for (Iterator it = acceptedClassLoaders.iterator(); it.hasNext();) {
					ClassLoader registeredLoader = (ClassLoader) it.next();
					if (isUnderneathClassLoader(registeredLoader, classLoader)) {
						it.remove();
					}
				}
			}
		}

		/**
		 * Create CachedIntrospectionResults for the given bean class.
		 * <P>
		 * We don't want to use synchronization here. Object references are
		 * atomic, so we can live with doing the occasional unnecessary lookup
		 * at startup only.
		 * 
		 * @param beanClass
		 *            the bean class to analyze
		 * @return the corresponding CachedIntrospectionResults
		 * @throws BeansException
		 *             in case of introspection failure
		 */
		static CachedPropertyDescriptor forClass(Class beanClass) throws Exception {
			CachedPropertyDescriptor results = null;

			Object value = classCache.get(beanClass);
			if (value instanceof Reference) {
				Reference ref = (Reference) value;
				results = (CachedPropertyDescriptor) ref.get();
			} else {
				results = (CachedPropertyDescriptor) value;
			}
			if (results == null) {
				// can throw BeansException
				results = new CachedPropertyDescriptor(beanClass);
				if (isCacheSafe(beanClass) || isClassLoaderAccepted(beanClass.getClassLoader())) {
					classCache.put(beanClass, results);
				} else {
					classCache.put(beanClass, new WeakReference(results));
				}
			}
			return results;
		}

		/**
		 * Check whether this CachedIntrospectionResults class is configured to
		 * accept the given ClassLoader.
		 * 
		 * @param classLoader
		 *            the ClassLoader to check
		 * @return whether the given ClassLoader is accepted
		 * @see #acceptClassLoader
		 */
		private static boolean isClassLoaderAccepted(ClassLoader classLoader) {
			// Iterate over array copy in order to avoid synchronization for the
			// entire
			// ClassLoader check (avoiding a synchronized acceptedClassLoaders
			// Iterator).
			Object[] acceptedLoaderArray = acceptedClassLoaders.toArray();
			for (int i = 0; i < acceptedLoaderArray.length; i++) {
				ClassLoader registeredLoader = (ClassLoader) acceptedLoaderArray[i];
				if (isUnderneathClassLoader(classLoader, registeredLoader)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Check whether the given class is cache-safe, i.e. whether it is
		 * loaded by the same class loader as the CachedIntrospectionResults
		 * class or a parent of it.
		 * <p>
		 * Many thanks to Guillaume Poirier for pointing out the garbage
		 * collection issues and for suggesting this solution.
		 * 
		 * @param clazz
		 *            the class to analyze
		 */
		private static boolean isCacheSafe(Class clazz) {
			ClassLoader target = clazz.getClassLoader();
			if (target == null) {
				return false;
			}
			ClassLoader cur = CachedPropertyDescriptor.class.getClassLoader();
			if (cur == target) {
				return true;
			}
			while (cur != null) {
				cur = cur.getParent();
				if (cur == target) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Check whether the given ClassLoader is underneath the given parent,
		 * that is, whether the parent is within the candidate's hierarchy.
		 * 
		 * @param candidate
		 *            the candidate ClassLoader to check
		 * @param parent
		 *            the parent ClassLoader to check for
		 */
		private static boolean isUnderneathClassLoader(ClassLoader candidate, ClassLoader parent) {
			if (candidate == null) {
				return false;
			}
			if (candidate == parent) {
				return true;
			}
			ClassLoader classLoaderToCheck = candidate;
			while (classLoaderToCheck != null) {
				classLoaderToCheck = classLoaderToCheck.getParent();
				if (classLoaderToCheck == parent) {
					return true;
				}
			}
			return false;
		}

		/** The BeanInfo object for the introspected bean class */
		private final BeanInfo beanInfo;

		/** PropertyDescriptor objects keyed by property name String */
		private final Map propertyDescriptorCache;

		/**
		 * Create a new CachedIntrospectionResults instance for the given class.
		 * 
		 * @param beanClass
		 *            the bean class to analyze
		 * @throws BeansException
		 *             in case of introspection failure
		 */
		private CachedPropertyDescriptor(Class beanClass) throws Exception {
			try {
				this.beanInfo = Introspector.getBeanInfo(beanClass);

				// Immediately remove class from Introspector cache, to allow
				// for proper
				// garbage collection on class loader shutdown - we cache it
				// here anyway,
				// in a GC-friendly manner. In contrast to
				// CachedIntrospectionResults,
				// Introspector does not use WeakReferences as values of its
				// WeakHashMap!
				Class classToFlush = beanClass;
				do {
					Introspector.flushFromCaches(classToFlush);
					classToFlush = classToFlush.getSuperclass();
				} while (classToFlush != null);

				this.propertyDescriptorCache = new HashMap();

				// This call is slow so we do it once.
				PropertyDescriptor[] pds = this.beanInfo.getPropertyDescriptors();
				for (int i = 0; i < pds.length; i++) {
					PropertyDescriptor pd = pds[i];

					this.propertyDescriptorCache.put(pd.getName(), pd);
				}
			} catch (IntrospectionException ex) {
				throw new Exception("Cannot get BeanInfo for object of class [" + beanClass.getName() + "]", ex);
			}
		}

		BeanInfo getBeanInfo() {
			return this.beanInfo;
		}

		Class getBeanClass() {
			return this.beanInfo.getBeanDescriptor().getBeanClass();
		}

		PropertyDescriptor getPropertyDescriptor(String propertyName) {
			return (PropertyDescriptor) this.propertyDescriptorCache.get(propertyName);
		}

	}

	/**
	 * 获取字段－属性 对应 map
	 * 
	 * @param classtype
	 */
	@SuppressWarnings(value = { "rawtypes" })
	public static Map<String, String> getColumns(Class classtype) {
		Field[] fields = classtype.getDeclaredFields();
		Method[] methods = classtype.getDeclaredMethods();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < fields.length; i++) {
			PropertyDescriptor pd = getPropertyDescriptor(classtype, fields[i].getName());
			if (null != pd) {
				Method method = pd.getReadMethod();
				Annotation[] annotations = method.getAnnotations();
				for (int j = 0; j < annotations.length; j++) {
					if (annotations[j] instanceof Column) {
						Column column = (Column) annotations[j];
						map.put(column.name(), fields[i].getName());
						break;
					}
				}
			}

		}
		return map;
	}

	/**
	 * 根据字段名获取实体对就的必性名称
	 * 
	 * @param classObj
	 * @param columnName
	 * @return
	 */
	public static String getPropertyByColumnName(Class<?> classObj, String columnName) {

		Map<String, String> map = getColumns(classObj);
		return map.get(columnName);
	}

	/*
	 * public static void main(String[] args) { BpGroupCustomerAdslOrder o = new
	 * BpGroupCustomerAdslOrder();
	 * 
	 * Map<String, String> map = getColumns(o.getClass());
	 * 
	 */

}
