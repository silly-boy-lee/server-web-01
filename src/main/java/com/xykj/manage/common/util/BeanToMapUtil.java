package com.xykj.manage.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: BeanToMapUtil
 * @description: java bean与map对象相互转换工具类
 * @modifyDate: 2017/4/14 + 下午1:45
 * @author: mr.lee
 */
@SuppressWarnings("unused")
public class BeanToMapUtil {

	/**
	 * @MethodName: convertMap
	 * @description: 将map对象转找成对应类型的java bean对象
	 * @modifyDate: 2017/4/14 + 下午1:46
	 * @author: mr.lee
	 * @param type java bean 类型
	 * @param map 待转换的map对象
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @return Object 返回bean实例
	 */
	public static Object convertMap(Class<?> type, Map<String, Object> map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		// 获取类属性
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		// 创建 JavaBean 对象
		Object obj = type.newInstance();

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();

		for (int i = 0; i < propertyDescriptors.length; i++) {
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
	 * @MethodName: transBean2Map
	 * @description: 将bean转换成map
	 * @modifyDate: 2017/4/14 + 下午1:55
	 * @author: mr.lee
	 * @param obj  待转换的java bean
	 * @param notNull notNull true:表示只对bean中属性值不为空的属性进行转换，false:对bean中的所有属性进行转换
	 * @return 返回生成的map
	 */
	public static Map<String, Object> transBean2Map(Object obj,boolean notNull) {  
        if(obj == null){
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!"class".equals(key)) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);
                    if (notNull) {
						if (ObjUtil.noEmpty(value)) {
							map.put(key, value);
						}else{
							continue ;
						}
					}else{
						map.put(key, value);
					}
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
    }
}
