package com.cmsg.util;

import java.io.File;

public class PathUtil {

	/** 
	 * getClasspath: 取类加载路径. <br/>
	 *
	 * @author Mr.Lee
	 * @return  
	 */ 
	public static String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}
}
