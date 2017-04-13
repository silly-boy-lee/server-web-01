package com.cmsg.util;


import com.cmsg.constant.SystemConstant;
import com.cmsg.core.ResultInfo;

/** 
 * ClassName: ExceptionUtil <br/> 
 * Function: 异常处理工具类 <br/> 
 * date: 2016年9月18日 上午11:50:27 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class ExceptionUtil {
	
	/** 
	 * processException: 异常处理. <br/>
	 *
	 * @author Mr.Lee
	 * @param e 异常
	 * @param info 结果信息
	 * @return ResultInfo
	 * @throws Exception  
	 */
	public static ResultInfo processException(Exception e,ResultInfo info) throws Exception{
		if (SystemConstant.IS_DUBUG) {
			info.setStatus(SystemConstant.REQ_ERROR);
			info.setMsg(e.getMessage());
			e.printStackTrace();
			return info;
		}
		e.printStackTrace();
		throw e;
	}

}
