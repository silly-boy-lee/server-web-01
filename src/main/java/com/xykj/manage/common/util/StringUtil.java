package com.cmsg.util;

/** 
 * ClassName: StringUtil <br/> 
 * Function: 字符串工具类 <br/> 
 * date: 2016年9月18日 上午8:59:14 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class StringUtil {
	
	/** 
	 * isEmpty: 检测字符串是否为空(null,"","null"). <br/>
	 *
	 * @author Mr.Lee
	 * @param s 字符串参数
	 * @return 为空则返回true，不否则返回false
	 */ 
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	
	/** 
	 * notEmpty: 检测字符串是否不为空(null,"","null"). <br/>
	 *
	 * @author Mr.Lee
	 * @param s 字符串参数
	 * @return true 表示不为空，false 表示为空
	 */ 
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/** 
	 * str2StrArray: 将字符串用指定的分隔符转换成字符串数组. <br/>
	 *
	 * @author Mr.Lee
	 * @param str 字符中
	 * @param splitRegex 分隔符
	 * @return 字符串数组
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/** 
	 * str2StrArray: 将字符串分隔成字符串数组. <br/>
	 *
	 * @author Mr.Lee
	 * @param str 
	 * @return String[]
	 */ 
	public static String[] str2StrArray(String str){
		return str2StrArray(str, ",\\s*");
	}

	/** 
	 * StrList: 将以逗号分隔的字符串转换成字符串数组. <br/>
	 *
	 * @author Mr.Lee
	 * @param valStr 分割字符串
	 * @return 字符串数组
	 */ 
	public static String[] StrList(String valStr) {
		int i = 0;
		String TempStr = valStr;
		String[] returnStr = new String[valStr.length() + 1
				- TempStr.replace(",", "").length()];
		valStr = valStr + ",";
		while (valStr.indexOf(',') > 0) {
			returnStr[i] = valStr.substring(0, valStr.indexOf(','));
			valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

			i++;
		}
		return returnStr;
	}

	/** 
	 * getEncoding: 获取字符串编码. <br/>
	 *
	 * @author Mr.Lee
	 * @param str 字符串参数
	 * @return 编码字符串
	 */ 
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
	
	/** 
	 * main: 测试方法. <br/>
	 *
	 * @author Mr.Lee
	 * @param args 方法参数列表
	 */ 
	public static void main(String[] args) {
		System.out.println(getEncoding("你好"));
		 
	}

}
