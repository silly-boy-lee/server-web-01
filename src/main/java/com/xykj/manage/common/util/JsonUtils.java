package com.cmsg.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONObject;

import com.cmsg.constant.SystemConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
 * ClassName: JsonUtils <br/> 
 * Function: json工具类 <br/> 
 * date: 2016年9月13日 下午7:59:34 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class JsonUtils {

    /** 
     * MAPPER:定义jackson对象
     */ 
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** 
     * objectToJson:将对象转换成json字符串。. <br/>
     *
     * @author Mr.Lee 
     * @param data 待转换对象
     * @return 返回转换后的JSON字符串
     */ 
    public static String objectToJson(Object data) {
    	String string = "";
		try {
			string = new String(MAPPER.writeValueAsBytes(data), SystemConstant.DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return string;
    }
    
    /** 
     * jsonToPojo:将json结果集转化为对象. <br/>
     *
     * @author Administrator 
     * @param jsonStr json数据
     * @param beanType 对象类型
     * @param <T> 泛型
     * @return 返回对象
     */ 
    public static <T> T jsonToPojo(String jsonStr, Class<T> beanType) {
    	//设置转换实体时忽略没有属性  lh
    	MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            T t = MAPPER.readValue(jsonStr, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * jsonToList:将json数据转换成pojo对象list. <br/>
     *
     * @author Administrator 
     * @param jsonData json数据
     * @param beanType 对象类型
     * @param <T> 泛型
     * @return 返回list
     */ 
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static <T> T jsonToObj(String jsonStr,Object obj,Class<T> beanType){
    	
    	try {
    		String[] props = ObjUtil.getFiledName(obj);
        	JSONObject jsonObj  = JSONObject.fromObject(jsonStr);
        	JSONObject rsStr = new JSONObject();
        	for (String str : props) {
    			if (jsonObj.containsKey(str)) {
    				rsStr.put(str, jsonObj.get(str));
    			}
    		}
			return MAPPER.readValue(rsStr.toString(),beanType);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}
