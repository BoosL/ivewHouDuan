package com.bolaa.common;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class MapToBeanUtil {

	 /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
    
    /**
     * 实体类转化为map
     * @param obj
     * @return
     */
    public static Map<String,Object> ConvertObjToMap(Object obj){
    	  Map<String,Object> reMap = new HashMap<String,Object>();
    	  if (obj == null) 
    	   return null;
    	  Field[] fields = obj.getClass().getDeclaredFields();
    	  try {
    	   for(int i=0;i<fields.length;i++){
    	    try {
    	     Field f = obj.getClass().getDeclaredField(fields[i].getName());
    	     f.setAccessible(true);
    	           Object o = f.get(obj);
    	           reMap.put(fields[i].getName(), o);
    	    } catch (NoSuchFieldException e) {
    	   
    	     
    	    } catch (IllegalArgumentException e) {   	    
    	     
    	    } catch (IllegalAccessException e) {   	     
    	     
    	    }
    	   }
    	  } catch (SecurityException e) {   	  
    	   
    	  } 
    	  return reMap;
    	 } 
    
}
