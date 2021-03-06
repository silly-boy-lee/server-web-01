package com.xykj.manage.common.util;

import java.util.Map;

import com.xykj.manage.common.pageVo.PageData;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.util.JSONPObject;

/** 
 * ClassName: AppUtil <br/> 
 * Function: 接口参数校验 <br/>
 * date: 2016年10月9日 下午4:25:11 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */
@SuppressWarnings("unused")
public class AppUtil{
	
	/** 
	 * logger:
	 */ 
	protected static Logger logger = Logger.getLogger(AppUtil.class);

	/** 
	 * @MethodName: returnObject
	 * @description: 返回接口请求结果
	 * @modifyDate: 2017/4/14 + 下午1:29
	 * @author: mr.lee
	 * @param pd
	 * @param map
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
