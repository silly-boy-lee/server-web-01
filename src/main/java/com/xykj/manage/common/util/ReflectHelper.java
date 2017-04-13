package com.cmsg.util;

import java.lang.reflect.Field;

/** 
 * ClassName: ReflectHelper <br/> 
 * Function: 反射工具 <br/> 
 * date: 2016年10月21日 上午11:14:00 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class ReflectHelper {
	 
	/** 
	 * getFieldByFieldName:获取obj对象fieldName的Field . <br/>
	 *
	 * @author Mr.Lee
	 * @param obj 
	 * @param fieldName 字段名
	 * @return Field
	 */ 
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/** 
	 * getValueByFieldName: 获取obj对象fieldName的属性值. <br/>
	 *
	 * @author Mr.Lee
	 * @param obj 
	 * @param fieldName 
	 * @return Object
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException  
	 */ 
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/** 
	 * setValueByFieldName: 设置obj对象fieldName的属性值. <br/>
	 *
	 * @author Mr.Lee
	 * @param obj 
	 * @param fieldName 
	 * @param value 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException  
	 */ 
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}
