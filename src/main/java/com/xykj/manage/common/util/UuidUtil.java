package com.cmsg.util;

import java.util.UUID;

/** 
 * ClassName: UuidUtil <br/> 
 * Function:  UUID工具类 <br/> 
 * date: 2016年9月21日 下午12:08:17 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class UuidUtil {
	/** 
	 * get32UUID: 取32位的UUID. <br/>
	 *
	 * @author Mr.Lee
	 * @return UUID字符串
	 */ 
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/** 
	 * main: 测试方法. <br/>
	 *
	 * @author Mr.Lee
	 * @param args 方法参数列表
	 */ 
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}
