package com.cmsg.util;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.cmsg.constant.SystemConstant;
import com.cmsg.core.ResultInfo;
import com.cmsg.pagebean.RequestJsonData;
import com.cmsg.security.DES;
import com.cmsg.security.MD5;
import com.cmsg.security.RSA;

/** 
 * ClassName: RequestUtil <br/> 
 * Function: app请求参数工具类 <br/> 
 * date: 2016年9月28日 下午5:15:16 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class RequestUtil {

	/** 
	 * getRequestJsonData: 取app前端发来的请求参数. <br/>
	 *
	 * @author Mr.Lee
	 * @param req 
	 * @return RequestJsonData
	 * @throws Exception 
	 */ 
	public static RequestJsonData getRequestJsonData(HttpServletRequest req) throws Exception{
		RSA rsa = new RSA(RSA.PUBLIC_KEY_FILE_PATH, RSA.PRIVATE_KEY_FILE_PATH);
		String reqDt = rsa.decryptWithBase64(req.getParameter(SystemConstant.REQ_DATA));
		String reqMd5 = req.getParameter(SystemConstant.REQ_MD5);
		String reqDes = req.getParameter(SystemConstant.REQ_DES);
		RequestJsonData reqJsonDt = new RequestJsonData(reqDt, reqMd5, reqDes);
		return reqJsonDt;
	}
	
	/** 
	 * getObjectFromJson:将请求参数映射到对象. <br/>
	 *
	 * @author Mr.Lee
	 * @param req 
	 * @param beanType objectType
	 * @param <T> 
	 * @return T
	 * @throws Exception 
	 */ 
	public static <T> T getObjectFromJson(HttpServletRequest req ,Class<T> beanType) throws Exception{
		RequestJsonData reqJsonDt = getRequestJsonData(req);
		if (ObjUtil.noEmpty(reqJsonDt)) {
			String jsonStr = reqJsonDt.getRequestData();
			if (StringUtil.notEmpty(jsonStr)) {
				return JsonUtils.jsonToPojo(jsonStr, beanType);
			}
		}
		return null;
	}
	
	/** 
	 * validateRequestSign: app请求验证签名. <br/>
	 *
	 * @author Mr.Lee
	 * @param req 
	 * @return true 验签成功 ，false 验签失败
	 * @throws Exception 
	 */ 
	public static boolean validateRequestSign(HttpServletRequest req) throws Exception{
		RequestJsonData reqJsonDt;
		boolean isOk = false;
		try {
			reqJsonDt = getRequestJsonData(req);
			String jsonDt = reqJsonDt.getRequestData();
			//如果请求中没有请求参数，则不进行验签
			if (StringUtil.isEmpty(jsonDt)) {
				isOk = true;
				return isOk;
			}else{
				String md5SignStr = MD5.getMD5Code(jsonDt);
				if (md5SignStr.equals(reqJsonDt.getRequestMd5())) {
					isOk = true;
					return isOk;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
	/** 
	 * encryptResponseData: 对返回给app(IOS android)的数据进行MD5签名  DES加密. <br/>
	 *
	 * @author Mr.Lee
	 * @author Mr.Lee
	 * @param msg 对应的提示信息
	 * @param info 响应对象
	 * @return ResultInfo
	 * @throws Exception 
	 */ 
	public static ResultInfo encryptResponseData(String msg,Object obj,ResultInfo info) throws Exception{
		try {
			if (ObjUtil.noEmpty(obj)) {
				//将返回数据转换成JSON字符串
				String jsonDt = JsonUtils.objectToJson(obj);
				System.out.println(jsonDt);
				//对返回数据进行DES加密
				String ciphertext = DES.encryptDES(jsonDt, DES.PASSWORD_CRYPT_KEY);
				System.out.println(ciphertext);
				String md5Sign = MD5.getMD5Code(JsonUtils.objectToJson(obj));
				System.out.println(DES.decryptDES(ciphertext, DES.PASSWORD_CRYPT_KEY));
				System.out.println(md5Sign);
				
				//返回数据封装
				info.setData(ciphertext);
				info.setSign(md5Sign);
				info.setMsg(PropUtil.getPropValueFromClasspath("msg", msg));
				info.setStatus(SystemConstant.REQ_SUCCESS);
				
				return info;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return info;
	}
	
	public static void assemblyRequestParams(HttpServletRequest req,String jsonStr){
    	JSONObject jsonObj  = JSONObject.fromObject(jsonStr);
    	Iterator<?> it = jsonObj.keys();
    	while (it.hasNext()) {
    		//获取map的key  
            String key = (String) it.next();  
            //得到value的值  
            Object value = jsonObj.get(key);
            //System.out.println(value);  
            //递归遍历  
            req.setAttribute(key, value);
		}
    }
}
