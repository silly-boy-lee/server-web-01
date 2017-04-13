package com.cmsg.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.cmsg.pagebean.PageData;
import com.fasterxml.jackson.databind.util.JSONPObject;

/** 
 * ClassName: AppUtil <br/> 
 * Function: 接口参数校验 <br/> 
 * date: 2016年10月9日 下午4:25:11 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class AppUtil  {
	
	/** 
	 * logger:
	 */ 
	protected static Logger logger = Logger.getLogger(AppUtil.class);
	 
	/** 
	 * returnObject: 返回接口请求结果 . <br/>
	 *
	 * @author Mr.Lee
	 * @param pd 
	 * @param map 
	 * @return Object
	 */ 
	@SuppressWarnings("rawtypes")
	public static Object returnObject(PageData pd, Map map){
		if(pd.containsKey("callback")){
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}else{
			return map;
		}
	}
}
