package com.xykj.manage.common.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ObjUtil <br/>
 * Function: 对象工具类 <br/>
 * date: 2016年9月21日 上午9:24:41 <br/>
 * 
 * @author Mr.Lee
 * @version
 */
public class ObjUtil {

	/**
	 * isEmpty: 判断对象是否为空("null",null ,长度为0). <br/>
	 * 
	 * @author Mr.Lee
	 * @param o
	 *            对象
	 * @return boolean
	 */
	public static boolean isEmpty(Object o) {
		return null == o || o.toString().trim().length() == 0
				|| "null".equalsIgnoreCase(o.toString());
	}

	/**
	 * noEmpty: 判断对象不为空("null",null ,长度为0). <br/>
	 * 
	 * @author Mr.Lee
	 * @param o
	 *            对象
	 * @return boolean
	 */
	public static boolean noEmpty(Object o) {
		return !(null == o || o.toString().trim().length() == 0 || "null"
				.equalsIgnoreCase(o.toString()));
	}

	/**
	 * ObjectToByte: 将对象转换成字节数组. <br/>
	 * 
	 * @author Mr.Lee
	 * @param obj
	 *            源对象
	 * @return byte[]
	 */
	public static byte[] ObjectToByte(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * getFieldValueByName: 根据属性名取对象中的属性值. <br/>
	 * 
	 * @author Mr.Lee
	 * @param fieldName 属性名
	 * @param o 
	 * @return Object
	 */
	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * getFiledName: 取对象的属性名组成的数组. <br/>
	 * 
	 * @author Mr.Lee
	 * @param o 
	 * @return String[]
	 */
	public static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/** 
	 * getFiledValues:获取对象的所有属性值，返回一个对象数组 . <br/>
	 *
	 * @author Mr.Lee
	 * @param o 
	 * @return Object[]
	 */ 
	public static Object[] getFiledValues(Object o) {
		String[] fieldNames = getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = getFieldValueByName(fieldNames[i], o);
		}
		return value;
	}

	/** 
	 * getFiledsInfo: 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list. <br/>
	 *
	 * @author Mr.Lee
	 * @param o 
	 * @return List
	 */ 
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static List getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List list = new ArrayList();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

}
